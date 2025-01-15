package controller;

import controller.listeners.GameButtonClickListener;
import model.cardStack.CardStack;
import model.cards.AriadneCard;
import model.cards.Card;
import model.cards.MinotaurCard;
import model.findings.Finding;
import model.findings.FrescoFinding;
import model.findings.RareFinding;
import model.findings.SnakeGoddessFinding;
import model.paths.*;
import model.pawns.Pawn;
import model.players.Player;
import model.players.PlayerGreen;
import model.players.PlayerRed;
import model.positions.FindingPosition;
import model.positions.Position;
import sound.AudioPlayer;
import util.*;
import view.window.MainWindow;
import view.components.centralContent.CentralContent;
import view.components.playerMenu.CardView;
import view.components.playerMenu.PlayerMenu;

import java.util.*;
import java.util.stream.Collectors;

import static util.FindingsInfo.getFindingDetails;

/**
 * Main Controller of the application:
 * <ul>
 *     <li>Manages game state and logic.</li>
 *     <li>Handles user interactions (UI events).</li>
 *     <li>Coordinates between the model and the view.</li>
 * </ul>
 * <p>
 * Implements {@link GameButtonClickListener} to handle user interactions.
 */
public class Controller implements GameButtonClickListener {

    // -------------------------------------------------------------------------
    // Fields
    // -------------------------------------------------------------------------

    /**
     * Indicates whose turn it is (Green starts).
     */
    private boolean isGreensTurn;

    /**
     * Indicates if the next clicked card should be rejected (discarded).
     */
    private boolean rejectionStackClicked;

    private AudioPlayer audioPlayer;
    private MainWindow mainWindow;

    private PlayerGreen playerGreen;
    private PlayerRed playerRed;

    private CardStack cardStack;
    private Path[] paths;

    private long startTurnTime;
    private Timer turnTimer;


    /**
     * Last cards played per path (index=pathValue) for each player.
     */
    private final Map<Player, Card[]> lastCardsPlayed = new HashMap<>();

    /**
     *  rare findings discovered by each player, it's indexed by pathName.getValue()
     */
    private final Map<Player, FindingName[]> rareFindingPlayerFound = new HashMap<>();

    /**
     * Tracks if a player has passed a checkpoint (index=pathValue).
     */
    private final Map<Player, boolean[]> hasCheckpointPassed = new HashMap<>();

    /**
     * Usage counters for each player’s pawns.
     * Index 0 = how many Theseus pawns used
     * Index 1 = how many Archeologist pawns used
     */
    private final Map<Player, int[]> pawnsUsed = new HashMap<>();

    /**
     * Score array: index 0=Green, index 1=Red.
     */
    private int[] playerScore;

    /**
     * Number of total checkpoint passes across all players.
     */
    private int checkPointsPassed;

    // -------------------------------------------------------------------------
    // Public Methods
    // -------------------------------------------------------------------------

    /**
     * Initializes the entire game: model, view, audio and starts the 30-second counter
     */
    public void initializeGame() {
        initializeModel();
        initializeView();
        initializeAudio();

        startTurnTimer();
    }

    /**
     * Advances the game to the next player's turn, unless the game is over.
     */
    public void nextTurn() {
        if (isGameOver()) {
            endGame();
            return;
        }
        updateUIForNextTurn();
        isGreensTurn = !isGreensTurn;

        startTurnTimer();
    }

    /**
     * Ends the game by announcing the winner.
     */
    public void endGame() {
        int greenScore = playerScore[PlayerName.PLAYER_GREEN.getValue()];
        int redScore = playerScore[PlayerName.PLAYER_RED.getValue()];

        PlayerName winner = (greenScore > redScore)
                ? PlayerName.PLAYER_GREEN
                : PlayerName.PLAYER_RED;
        mainWindow.announceWinner(winner);
    }


    /**
     * Determines if the game is over (all cards used or all checkpoints passed).
     */
    public boolean isGameOver() {
        return (cardStack.getStackSize() == 0 || checkPointsPassed >= 4);
    }

    // -------------------------------------------------------------------------
    // Event Handlers (Implementation of GameButtonClickListener)
    // -------------------------------------------------------------------------

    /**
     * Invoked when the user wants to save the game
     * <p>
     * Saves the game
     */
    @Override
    public void onSaveGameClicked() {
        // TODO
//        GameStateService.saveGameState(this);
        mainWindow.showGameSavedMessage();
    }

    @Override
    public void onMusicSoundClicked(){
        if (!audioPlayer.isMusicPlaying())audioPlayer.playMusicForTurn(!isGreensTurn);
        else audioPlayer.stopMusic();
    }

    public void openSavedGame() {
        // TODO
    }

    /**
     * Starts a timer to count seconds until the player plays a card.
     * He has
     */
    private void startTurnTimer() {
        startTurnTime = System.currentTimeMillis();

        turnTimer = new Timer();

        turnTimer.scheduleAtFixedRate(new TimerTask() {
            private final long startTime = System.currentTimeMillis();

            @Override
            public void run() {
                long elapsedSeconds = (System.currentTimeMillis() - startTime) / 1000;
                if (elapsedSeconds >= GameConstants.Turn.MAX_TIME_FOR_TURN) {
                    stopTimer();
                    mainWindow.showNoTimePopUp();
                    nextTurn();
                }
            }
        }, 0, 1000);
    }

    /**
     * Stops the timer and calculates elapsed time.
     */
    private void stopTimer() {
        if (turnTimer != null) {
            turnTimer.cancel();
            turnTimer = null;
        }
    }

    /**
     * Invoked when the rejection stack is clicked.
     * <p>
     * We do NOT end the turn here. Instead, we set a flag so that
     * the next clicked card is simply "rejected."
     */
    @Override
    public void onCardRejectionClicked() {
        rejectionStackClicked = true;
    }

    /**
     * Handles clicks on a player's card in the deck.
     */
    @Override
    public void onCardInDeckClicked(CardView[] cardDeckView, int cardClickedIdx, PlayerName currentPlayerName) {
        // Stop the timer as the player has played
        stopTimer();

        // 1) Identify current player & retrieve the clicked card/path
        Player currentPlayer = (currentPlayerName == PlayerName.PLAYER_GREEN) ? playerGreen : playerRed;
        Card clickedCard = currentPlayer.getCardDeck()[cardClickedIdx];
        Path clickedPath = clickedCard.getPath();
        PathName pathName = clickedPath.getPathName();

        // 2) Check if card is illegal (Ariadne or Minotaur constraints)
        if (handleIllegalCardUsage(currentPlayer, clickedCard)) {
            // If it's illegal, we've already shown a popup, so just return
            return;
        }

        // 3) Draw a new card from the stack to replace the clicked one
        Card newCard = cardStack.getCard();
        currentPlayer.setCardInDeck(cardClickedIdx, newCard);

        // 4) If the rejection stack was clicked, discard & replace, then end turn
        if (rejectionStackClicked) {
            discardAndReplaceCard(currentPlayer, cardClickedIdx, pathName, newCard);
            nextTurn();
            return;
        }

        // 5) Process the actual card play
        handleCardPlay(currentPlayer, clickedCard, cardClickedIdx, newCard, clickedPath);
    }

    /**
     * Checks whether the given card is illegal for the current situation.
     * If so, displays the relevant popup and returns true; otherwise false.
     */
    private boolean handleIllegalCardUsage(Player currentPlayer, Card clickedCard) {
        boolean cannotPlay = cannotPlayCard(currentPlayer, clickedCard);
        if (cannotPlay) {
            if (clickedCard.getName() == CardName.ARIADNE_CARD) {
                mainWindow.noAriadneCardPopUp();
            } else if (clickedCard.getName() == CardName.MINOTAUR_CARD) {
                mainWindow.noMinotaurCardPopUp();
            }
            return true;
        }
        return false;
    }

    /**
     * Performs the steps needed to actually play the card:
     *  - Retrieve or create the pawn.
     *  - Check if pawn is immobilized.
     *  - Store old position if not finished.
     *  - Handle Ariadne skip logic if needed.
     *  - Play the card (model logic).
     *  - Do post-move steps (finding collection, minotaur logic, UI updates, etc.).
     */
    private void handleCardPlay(
            Player currentPlayer,
            Card clickedCard,
            int cardClickedIdx,
            Card newCard,
            Path clickedPath
    ) {
        // Retrieve the player's pawn
        Pawn currentPlayerPawn = getOrCreatePawnForPath(currentPlayer, clickedPath);

        if (currentPlayerPawn.isImmobilized()) {
            mainWindow.PawnImmobilized();
            return;
        }

        boolean wasPawnFinished = currentPlayerPawn.hasFinished();
        int oldPos = (!wasPawnFinished && currentPlayerPawn.getPosition() != null)
                ? currentPlayerPawn.getPosition().getCellIdx()
                : -1;

        // If this is an Ariadne card, handle skip logic
        if (!wasPawnFinished && (clickedCard instanceof AriadneCard)) {
            handleAriadneSkipFinding(currentPlayer, currentPlayerPawn, clickedPath);
        }

        // Pawn is no longer immobilized; play the card
        currentPlayerPawn.setImmobilized(false);
        clickedCard.play(currentPlayer);

        boolean pawnFinished = currentPlayerPawn.hasFinished();
        handlePostPlaySteps(
                currentPlayer,
                clickedCard,
                clickedPath,
                wasPawnFinished,
                oldPos,
                pawnFinished,
                cardClickedIdx,
                newCard
        );
    }

    /**
     * After the card has been played, handle finishing logic, removing from old cell,
     * updating checkpoint, awarding points, UI updates, etc., then end the turn.
     */
    private void handlePostPlaySteps(
            Player currentPlayer,
            Card clickedCard,
            Path clickedPath,
            boolean wasPawnFinished,
            int oldPos,
            boolean pawnFinished,
            int cardClickedIdx,
            Card newCard
    ) {
        // If not finished, see if there's a finding to collect
        if (!wasPawnFinished && !pawnFinished) {
            Position newPos = currentPlayer.getPawnInPath(clickedPath.getPathName()).getPosition();
            if (newPos.getFinding() != null) {
                FindingPosition fp = (FindingPosition) newPos;
                if (fp.hasFindingAvailable()) {
                    handleFinding(currentPlayer.getPawnInPath(clickedPath.getPathName()), currentPlayer, fp);
                }
            }
        }

        // Remove from old cell if the pawn was on the path
        if (!wasPawnFinished && oldPos >= 0) {
            removePawnFromUI(clickedPath, currentPlayer, oldPos);
        }

        // If minotaur, handle opponent's pawn UI. Otherwise, handle player's own UI.
        if (clickedCard instanceof MinotaurCard) {
            handleMinotaurUIUpdate(currentPlayer, clickedPath);
        } else {
            handlePlayerUIUpdate(currentPlayer, clickedPath, wasPawnFinished, pawnFinished);
        }

        // If not finished, update checkpoints
        if (!wasPawnFinished && !pawnFinished) {
            Pawn currentPlayerPawn = clickedPath.getPlayerPawn(currentPlayer);
            updateCheckpoints(currentPlayer, currentPlayerPawn, clickedPath.getPathName());
        }

        // Calculate new score, update the player's menu, next turn
        playerScore[currentPlayer.getName().getValue()] = countPoints(currentPlayer);
        updatePlayerMenuUI(currentPlayer, cardClickedIdx, clickedPath.getPathName(), newCard);
        nextTurn();
    }

    /**
     * If this is a Minotaur card, we might need to update the opponent's pawn position on the board.
     */
    private void handleMinotaurUIUpdate(Player currentPlayer, Path clickedPath) {
        Player opponent = getOpponent(currentPlayer);
        Pawn opponentPawn = clickedPath.getPlayerPawn(opponent);
        if (opponentPawn != null && !opponentPawn.hasFinished()) {
            // The Minotaur logic might have moved opponent's pawn backward
            // So we forcibly re-draw it in the new position
            int newOpponentPos = opponentPawn.getPosition().getCellIdx();
            int oldOpponentPos = newOpponentPos + 2; // or find a better way if needed
            updatePathUI(clickedPath,
                    opponent,
                    oldOpponentPos,
                    newOpponentPos,
                    opponentPawn,
                    opponentPawn.isRevealed());
        }
    }

    /**
     * If not a Minotaur card, we simply place the current player's pawn in the new position if it's still on the path.
     */
    private void handlePlayerUIUpdate(Player currentPlayer, Path clickedPath, boolean wasPawnFinished, boolean pawnFinished) {
        // If the pawn is still on the path, we place it in the new cell
        if (!wasPawnFinished && !pawnFinished) {
            Pawn currentPlayerPawn = clickedPath.getPlayerPawn(currentPlayer);
            int newPos = currentPlayerPawn.getPosition().getCellIdx();
            updatePathUI(clickedPath,
                    currentPlayer,
                    -1,  // Already removed from old cell
                    newPos,
                    currentPlayerPawn,
                    currentPlayerPawn.isRevealed());
        }
    }




    private boolean isIllegalCard(Player currentPlayer, Card clickedCard) {
        boolean cannotPlayCard = cannotPlayCard(currentPlayer, clickedCard);

        if (cannotPlayCard && clickedCard.getName() == CardName.ARIADNE_CARD) {
            mainWindow.noAriadneCardPopUp();
            return true;
        } else if (cannotPlayCard && clickedCard.getName() == CardName.MINOTAUR_CARD) {
            mainWindow.noMinotaurCardPopUp();
            return true;
        }
        return false;
    }

    /**
     * Invoked when a player opts to give up (unimplemented).
     */
    @Override
    public void onGiveUpClicked() {
        // TODO
    }

    // -------------------------------------------------------------------------
    // Private Initialization Methods
    // -------------------------------------------------------------------------

    private void initializeModel() {
        // Scores
        playerScore = new int[2];

        // Shuffle & create findings
        Finding[] nonRareFindings = createNonRareFindings();
        Collections.shuffle(Arrays.asList(nonRareFindings), new Random(43));

        // Rare findings
        RareFinding phaistosDisc = new RareFinding(FindingName.PHAISTOS_DISC, 35);
        RareFinding minosRing = new RareFinding(FindingName.MINOS_RING, 25);
        RareFinding maliaJewelry = new RareFinding(FindingName.MALIA_JEWELRY, 25);
        RareFinding rhytonOfZakros = new RareFinding(FindingName.RHYTHON_OF_ZAKROS, 25);

        // Create paths
        Path maliaPath = new MaliaPath(maliaJewelry, Arrays.copyOfRange(nonRareFindings, 0, 5));
        Path knossosPath = new KnossosPath(minosRing, Arrays.copyOfRange(nonRareFindings, 5, 10));
        Path phaistosPath = new PhaistosPath(phaistosDisc, Arrays.copyOfRange(nonRareFindings, 10, 15));
        Path zakrosPath = new ZakrosPath(rhytonOfZakros, Arrays.copyOfRange(nonRareFindings, 15, 20));

        paths = new Path[]{knossosPath, maliaPath, phaistosPath, zakrosPath};

        // Card stack
        rejectionStackClicked = false;
        cardStack = new CardStack(paths);

        // Create players with a full deck each
        playerGreen = new PlayerGreen(cardStack.getNCards(GameConstants.Cards.NUMBER_OF_DECK_CARDS));
        playerRed = new PlayerRed(cardStack.getNCards(GameConstants.Cards.NUMBER_OF_DECK_CARDS));

        // Initialize supporting maps
        initializeControllerMaps();
        checkPointsPassed = 0;
    }

    private void initializeControllerMaps() {


        // Pawn usage counters
        pawnsUsed.put(playerGreen, new int[]{0, 0});
        pawnsUsed.put(playerRed, new int[]{0, 0});

        // Rare findings last discovered
        rareFindingPlayerFound.put(playerGreen, new FindingName[]{null, null , null, null});
        rareFindingPlayerFound.put(playerRed, new FindingName[]{null, null , null, null});

        // Last cards played (4 paths)
        lastCardsPlayed.put(playerGreen, new Card[4]);
        lastCardsPlayed.put(playerRed, new Card[4]);

        // Checkpoint arrays
        hasCheckpointPassed.put(playerGreen, new boolean[4]);
        hasCheckpointPassed.put(playerRed, new boolean[4]);
    }

    private Finding[] createNonRareFindings() {
        Finding[] nonRare = new Finding[GameConstants.Findings.NUMBER_OF_RELICS];
        for (int i = 0; i < GameConstants.Findings.NUMBER_OF_SNAKE_GODDESS_STATUES; i++) {
            nonRare[i] = new SnakeGoddessFinding();
        }
        final int base = GameConstants.Findings.NUMBER_OF_SNAKE_GODDESS_STATUES;
        nonRare[base] = new FrescoFinding(20, FindingName.FRESCO_1);
        nonRare[base + 1] = new FrescoFinding(20, FindingName.FRESCO_2);
        nonRare[base + 2] = new FrescoFinding(15, FindingName.FRESCO_3);
        nonRare[base + 3] = new FrescoFinding(15, FindingName.FRESCO_4);
        nonRare[base + 4] = new FrescoFinding(20, FindingName.FRESCO_5);
        nonRare[base + 5] = new FrescoFinding(20, FindingName.FRESCO_6);
        return nonRare;
    }

    private void initializeView() {
        CardView[] cardsOfGreen = ControllerUtil.convertCardsToViewCards(playerGreen.getCardDeck());
        CardView[] cardsOfRed = ControllerUtil.convertCardsToViewCards(playerRed.getCardDeck());

        mainWindow = new MainWindow(cardsOfRed, cardsOfGreen,
                this,
                cardStack.getStackSize(),
                0,
                !isGreensTurn);

        // Red doesn’t go first
        mainWindow.getRedPlayerMenu().setDeckButtonsClickable(false);
    }

    private void initializeAudio() {
        audioPlayer = new AudioPlayer();
        audioPlayer.playMusicForTurn(!isGreensTurn);
    }


    // -------------------------------------------------------------------------
    // Private Helper Methods
    // -------------------------------------------------------------------------

    /**
     * Discards the clicked card and replaces it with a new one from the deck,
     * then updates the UI. This does not occur unless the user specifically
     * clicked the "rejection stack" first.
     */
    private void discardAndReplaceCard(Player currentPlayer, int cardIdx, PathName pathName, Card newCard) {
        updatePlayerMenuUI(currentPlayer, cardIdx, pathName, newCard);

        // The next user action won't be a rejection anymore
        rejectionStackClicked = false;
    }

    /**
     * If we are playing an Ariadne card, we might skip the cell at oldPos+1.
     * If that cell has a Finding, prompt user to ignore or collect it.
     */
    private void handleAriadneSkipFinding(Player currentPlayer, Pawn playerPawn, Path clickedPath) {
        int oldPos = playerPawn.getPosition().getCellIdx();
        int skipIndex = oldPos + 1;
        int pathLength = clickedPath.getPositions().length;

        // If skipping is out of bounds or we are literally on the second/last cell, do nothing.
        if (skipIndex >= pathLength) {
            return;
        }

        Position skipPos = clickedPath.getPosition(skipIndex);
        if (skipPos.getFinding() != null) {
            FindingPosition findingPos = (FindingPosition) skipPos;
            if (findingPos.hasFindingAvailable()) {
                handleFinding(playerPawn, currentPlayer, findingPos);
            }
        }
    }

    /**
     * @return true if the card cannot be played due to special rules
     * (Ariadne with no active pawn, Minotaur on checkpoint, etc.)
     */
    private boolean cannotPlayCard(Player currentPlayer, Card card) {
        Path path = card.getPath();
        PathName pathName = path.getPathName();
        CardName cardName = card.getName();

        // ARIADNE: can't play if the player has no active pawn in that path
        // (which includes first turn on that path).
        if (cardName == CardName.ARIADNE_CARD && !playerHasActivePawnInPath(currentPlayer, pathName)) {
            return true;
        }

        // MINOTAUR: can't place if no active pawn in path for current player,
        // or if that pawn has already passed the checkpoint
        if (cardName == CardName.MINOTAUR_CARD) {
            if (!playerHasActivePawnInPath(currentPlayer, pathName)) {
                return true;
            }
            if (hasCheckpointPassed.get(currentPlayer)[pathName.getValue()]) {
                return true;
            }
        }

        return false;
    }

    private boolean playerHasActivePawnInPath(Player currentPlayer, PathName pathName) {
        Pawn existing = currentPlayer.getPawnInPath(pathName);
        return (existing != null && !existing.hasFinished());
    }

    /**
     * Returns an existing pawn if the player has one on this path;
     * otherwise, prompts the user to choose which pawn to create.
     */
    private Pawn getOrCreatePawnForPath(Player player, Path path) {
        Pawn existingPawn = path.getPlayerPawn(player);
        if (existingPawn != null) {
            // If the pawn has finished, we do nothing special
            return existingPawn;
        }

        int[] usage = pawnsUsed.get(player);
        boolean canUseTheseus = usage[PawnName.THESEUS.getValue()] < GameConstants.Tokens.NUMBER_OF_THESEUS;
        boolean canUseArcheologist = usage[PawnName.ARCHEOLOGIST.getValue()] < GameConstants.Tokens.NUMBER_OF_ARCHEOLOGIST;

        PawnName chosenPawn = mainWindow.askUserForPawn(canUseTheseus, canUseArcheologist);
        player.setPawn(chosenPawn, path.getPathName());
        usage[chosenPawn.getValue()]++;

        Pawn newPawn = player.getPawnInPath(chosenPawn, path.getPathName());
        newPawn.setRevealed(false);
        path.setPlayerPawn(player, newPawn);
        return newPawn;
    }

    private void updateCheckpoints(Player player, Pawn pawn, PathName pathName) {
        int pathIdx = pathName.getValue();
        if (!hasCheckpointPassed.get(player)[pathIdx]) {
            Position pos = pawn.getPosition();
            if (pos != null && pos.getCellIdx() >= GameConstants.Paths.CHECKPOINT_INDEX) {
                hasCheckpointPassed.get(player)[pathIdx] = true;
                checkPointsPassed++;
            }
        }
    }

    private void removePawnFromUI(Path path, Player player, int oldPosIdx) {
        if (oldPosIdx < 0) return;
        CentralContent centralContent = mainWindow.getCentralContent();
        centralContent.getPathPanel(path.getPathName())
                .removePawnFromCell(oldPosIdx, player.getName());
    }

    private void updatePathUI(Path path, Player player, int oldPos, int newPos, Pawn pawn, boolean isRevealed) {
        CentralContent centralContent = mainWindow.getCentralContent();


        if (oldPos >= 0) {
            centralContent.getPathPanel(path.getPathName())
                    .removePawnFromCell(oldPos, player.getName());
        }
        centralContent.getPathPanel(path.getPathName())
                .addPawnToCell(newPos, pawn.getPawnName(), player.getName(), isRevealed);
    }

    private Player getOpponent(Player currentPlayer) {
        return (currentPlayer == playerGreen) ? playerRed : playerGreen;
    }

    /**
     * Interacts with a finding on the given position by asking the user
     * if they want to ignore it. If not ignored, the pawn collects the finding.
     */
    private void handleFinding(Pawn pawn, Player player, FindingPosition position) {
        Finding finding = position.getFinding();
        assert (finding == null); // No actual finding, case obv should not happen

        PathName currentPath = pawn.getPosition().getPathName();
        // If it is a rare finding, record it
        FindingName findingName = finding.getFindingName();
        if (GameConstants.Findings.RARE_FINDING_NAMES.contains(findingName)) {
            FindingName[] findings = rareFindingPlayerFound.get(player);
            findings[currentPath.getValue()] = findingName;
        }

        // Prompt user about ignoring it
        String[] details = getFindingDetails(findingName);
        boolean ignore = mainWindow.askUserForFindingInteraction(
                details[1],
                details[2],
                findingName,
                pawn.getPawnName()
        );
        if (!ignore) {
            pawn.interactWithFinding(position, player);
            pawn.setRevealed(true);
        }
    }

    private int countPoints(Player player) {
        int totalPathPoints = 0;
        for (PathName pathName : PathName.values()) {
            Path path = paths[pathName.getValue()];
            Pawn pawnOnPath = path.getPlayerPawn(player);
            if (pawnOnPath == null || pawnOnPath.hasFinished()) {
                continue;
            }
            int posIdx = pawnOnPath.getPosition().getCellIdx();
            totalPathPoints += path.getPositions()[posIdx].getRewardScore();

            // Double if Theseus
            if (pawnOnPath.getPawnName() == PawnName.THESEUS) {
                totalPathPoints *= 2;
            }
        }

        // Count the points from snakes statues
        int totalStatuesPoints = 0, totalFindingPoints = 0;
        int snakeStatuesCount = 0;
        for (Finding finding : player.getFindings()) {
            totalFindingPoints += finding.getPoints(); // statues will get 0 points
            if (finding instanceof SnakeGoddessFinding) snakeStatuesCount++;
        }
        totalStatuesPoints += GameConstants.Rewards.REWARD_PATH_FOR_NUM_OF_STATUES[snakeStatuesCount];
        return totalPathPoints + totalStatuesPoints + totalFindingPoints;
    }

    /**
     * Updates the player's UI: draws a new card, updates last cards, etc.
     * Called when a card is legitimately played or replaced (via rejection).
     */
    private void updatePlayerMenuUI(Player currentPlayer, int cardClickedIdx, PathName pathName, Card newCard) {
        // We already replaced the card in `discardAndReplaceCard()` or
        // are about to do so here if the card was played.
        // For a normal play, we draw a new card from the stack:
        // If you want to only replace on a successful move, do it here.
        CardView[] lastPlayedViews = ControllerUtil.convertCardsToViewCards(
                lastCardsPlayed.get(currentPlayer)
        );

        CardView newCardView = ControllerUtil.convertCardsToViewCards(new Card[]{newCard})[0];

        // Gather relic names for display
        List<FindingName> relicNames = currentPlayer.getFindings()
                .stream()
                .map(Finding::getFindingName)
                .collect(Collectors.toList());

        PlayerMenu menu = (currentPlayer == playerGreen)
                ? mainWindow.getGreenPlayerMenu()
                : mainWindow.getRedPlayerMenu();


        FindingName rareFindingInPath = rareFindingPlayerFound.get(currentPlayer)[pathName.getValue()];

        menu.updatePlayerMenu(
                cardClickedIdx,
                newCardView,
                lastPlayedViews,
                rareFindingInPath,
                pathName,
                pawnsUsed.get(currentPlayer),
                countPoints(currentPlayer),
                ControllerUtil.countSnakeGoddess(relicNames),
                ControllerUtil.playerFrescoes(relicNames)
        );
    }

    private void updateUIForNextTurn() {
        mainWindow.getGreenPlayerMenu().setDeckButtonsClickable(isGreensTurn);
        mainWindow.getRedPlayerMenu().setDeckButtonsClickable(!isGreensTurn);

        CentralContent centralContent = mainWindow.getCentralContent();
        centralContent.updateInformation(cardStack.getStackSize(), checkPointsPassed, !isGreensTurn);

        // TODO: audio shouldn't be in this function
        audioPlayer.playMusicForTurn(isGreensTurn);
    }

    // -------------------------------------------------------------------------
    // Main method for local testing (Application would be used in two devices irl)
    // -------------------------------------------------------------------------
    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.initializeGame();
    }
}

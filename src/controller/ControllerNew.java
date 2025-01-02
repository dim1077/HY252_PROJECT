package controller;

import model.cardStack.CardStack;
import model.cards.AriadneCard;
import model.cards.Card;
import model.cards.MinotaurCard;
import model.findings.*;
import model.paths.*;
import model.pawns.Pawn;
import model.players.Player;
import model.players.PlayerGreen;
import model.players.PlayerRed;
import model.positions.FindingPosition;
import model.positions.Position;
import sound.AudioPlayer;
import util.*;
import view.componentT.MainWindowT;
import view.componentT.centralContentT.CentralContentT;
import view.componentT.playerMenuT.PlayerMenuT;
import view.components.menus.CardView;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import static util.FindingsInfo.getFindingDetails;

/**
 * This is the main Controller of the application,
 * where the model and view are integrated.
 *
 * Responsibilities:
 * <ul>
 *     <li>Manages game state and logic.</li>
 *     <li>Handles user interactions via UI components.</li>
 *     <li>Coordinates between the model (game state) and the view (UI).</li>
 * </ul>
 *
 * Implements {@link GameButtonClickListener} to handle user actions such as
 *  interacting with cards.
 */
public class ControllerNew implements GameButtonClickListener {

    private boolean isGreensTurn; // green always plays first
    private AudioPlayer audioPlayer;
    private PlayerGreen playerGreen;
    private PlayerRed playerRed;
    private CardView[] cardsOfRed;
    private CardView[] cardsOfGreen;
    private MainWindowT mainWindow;
    private Map<Player, Card[]> lastCardsPlayed = new HashMap<>();
    private Map<Player, FindingName> lastRareFindingPlayerFound = new HashMap<>();
    private Map<Player, boolean[]> hasCheckpointPassed = new HashMap<>();
    private Map<Player, int[]> pawnsUsed = new HashMap<>();
    private Map<Player, List<Finding>> playerFindings = new HashMap<>();
    private int[] playerScore;
    private CardStack cardStack;
    private Path[] paths; // VERY IMPORTANT: The paths are ordered with respect to PathNames.

    private int checkPointsPassed;
    boolean rejectionStackCLicked;


    public void initializeGame(){
        // ******-MODEL******

        //--- PLAYER SCORE INIT----

        this.playerScore = new int[2];

        //--- FINDINGS INIT----

        Finding[] nonRarefindings = new Finding[GameConstants.NUMBER_OF_RELICS];
        for (int i = 0; i < GameConstants.NUMBER_OF_SNAKE_GODDESS_STATUES; i++) nonRarefindings[i] = new SnakeGoddessFinding();
        final int baseIndex = GameConstants.NUMBER_OF_SNAKE_GODDESS_STATUES;
        nonRarefindings[baseIndex]  = new FrescoFinding(20, FindingName.FRESCO_1);
        nonRarefindings[baseIndex + 1]  = new FrescoFinding(20, FindingName.FRESCO_2);
        nonRarefindings[baseIndex + 2]  = new FrescoFinding(15, FindingName.FRESCO_3);
        nonRarefindings[baseIndex + 3]  = new FrescoFinding(15, FindingName.FRESCO_4);
        nonRarefindings[baseIndex + 4]  = new FrescoFinding(20, FindingName.FRESCO_5);
        nonRarefindings[baseIndex + 5]  = new FrescoFinding(20, FindingName.FRESCO_6);

        Collections.shuffle(Arrays.asList(nonRarefindings), new java.util.Random(42));


        RareFinding phaistosDisc = new RareFinding(FindingName.PHAISTOS_DISC, 35);
        RareFinding minosRing = new RareFinding(FindingName.MINOS_RING, 25);
        RareFinding maliaJewelry= new RareFinding(FindingName.MALIA_JEWELRY, 25);
        RareFinding rhytonOfZakros = new RareFinding(FindingName.RHYTHON_OF_ZAKROS, 25);

        // ----------PATHS INIT----------

        Path maliaPath = new MaliaPath(maliaJewelry, Arrays.copyOfRange(nonRarefindings, 0, 5));
        Path knossosPath = new KnossosPath(minosRing, Arrays.copyOfRange(nonRarefindings, 5, 10));
        Path phaistosPath = new PhaistosPath(phaistosDisc, Arrays.copyOfRange(nonRarefindings, 10, 15));
        Path zakrosPath = new ZakrosPath(rhytonOfZakros, Arrays.copyOfRange(nonRarefindings, 15, 20));

        this.paths = new Path[]{knossosPath, maliaPath, phaistosPath, zakrosPath};

        // ----------CARD STACK INIT----------


        rejectionStackCLicked = false;
        cardStack = new CardStack(paths);

        // ----------PLAYER/PLAYER INFO INIT----------

        playerRed = new PlayerRed(cardStack.getNCards(GameConstants.NUMBER_OF_DECK_CARDS));
        playerGreen = new PlayerGreen(cardStack.getNCards(GameConstants.NUMBER_OF_DECK_CARDS));

        playerFindings.put(playerGreen, new ArrayList<>());
        playerFindings.put(playerRed, new ArrayList<>());

        pawnsUsed.put(playerGreen, new int[]{0,0});
        pawnsUsed.put(playerRed, new int[]{0,0});

        lastRareFindingPlayerFound.put(playerGreen, null);
        lastRareFindingPlayerFound.put(playerRed, null);

        lastCardsPlayed.put(playerGreen, new Card[4]);
        lastCardsPlayed.put(playerRed, new Card[4]);

        // ----------CHECKPOINTS PASSED----------

        checkPointsPassed = 0;
        hasCheckpointPassed.put(playerRed, new boolean[4]);
        hasCheckpointPassed.put(playerGreen, new boolean[4]);



        // ******-VIEW/UI******

        this.cardsOfRed = new CardView[GameConstants.NUMBER_OF_DECK_CARDS];
        this.cardsOfGreen = new CardView[GameConstants.NUMBER_OF_DECK_CARDS];

        cardsOfRed = ControllerUtil.convertCardsToViewCards(playerRed.getCardDeck());
        cardsOfGreen = ControllerUtil.convertCardsToViewCards(playerGreen.getCardDeck());
        mainWindow = new MainWindowT(cardsOfRed, cardsOfGreen, this, cardStack.getStackSize(), 0, !isGreensTurn);

        PlayerMenuT redMenu = mainWindow.getRedPlayerMenu();
        redMenu.setDeckButtonsClickable(false);

        // ******-MUSIC/AUDIO******
        this.audioPlayer = new AudioPlayer();
        audioPlayer.playMusicForTurn(!isGreensTurn);
    }




    /**
     * Advances the game to the next player's turn.
     * Handles card interactions, pawn movement, findings, and game state updates.
     *
     */
    public void nextTurn() {
    if (isGameOver()){ endGame();}
    CentralContentT centralContent = mainWindow.getCentralContent();

    mainWindow.getGreenPlayerMenu().setDeckButtonsClickable(isGreensTurn);
    mainWindow.getRedPlayerMenu().setDeckButtonsClickable(!isGreensTurn);
    centralContent.updateInformation(cardStack.getStackSize(), checkPointsPassed, !isGreensTurn);
    audioPlayer.playMusicForTurn(isGreensTurn);
    isGreensTurn = !isGreensTurn;

    }


    private int lookForCheckPoints(Player player) {
        int checkPoints = 0;
        boolean[] checkpoints = hasCheckpointPassed.get(player);
        for (int i = 0; i < checkpoints.length; i++) {
            if (checkpoints[i]) checkPoints++;
        }
        return checkPoints;
    }

    /**
     * Closes the window and ends game.
     */
    public void endGame(){
        PlayerName winner = playerScore[PlayerName.PLAYER_GREEN.getValue()] > playerScore[PlayerName.PLAYER_RED.getValue()] ? PlayerName.PLAYER_GREEN : PlayerName.PLAYER_RED;
        mainWindow.announceWinner(winner);
    }

    /**
     * @return returns a boolean value that checks if any of user has won or gave up
     * */
    public boolean isGameOver(){
        return (cardStack.getStackSize() == 0 || checkPointsPassed >= 4);
    }

    public int countPoints(Player player){
        int totalPoints = 0;
        for (PathName currPath : PathName.values()) {
            int pointsInPath = 0;
            int pathIdx = currPath.getValue();
            Pawn pawnInPath = paths[pathIdx].getPlayerPawn(player);
            if (pawnInPath == null) continue;
            for (int j = 0; j < GameConstants.NUMBER_OF_PATH_CELLS; j++){
                pointsInPath = paths[pathIdx].getPositions()[j].getRewardScore();
                if (paths[pathIdx].getPositions()[j].hasPlayer(player)) break;
            }
            totalPoints += pointsInPath;
            if (pawnInPath.getPawnName() == PawnName.THESEUS) totalPoints *= 2;
        }

        List<Finding> findings = playerFindings.get(player);
        int numOfStatues = 0;
        for (Finding finding : findings){
            if (finding instanceof SnakeGoddessFinding) numOfStatues++;
            totalPoints += finding.getPoints(); // statues offer 0 points
        }
        totalPoints += GameConstants.REWARD_PATH_FOR_NUM_OF_STATUES[numOfStatues];

        return totalPoints;
    }

    /**
     * Handles the event where the rejection stack is clicked.
     */
    @Override
    public void onCardRejectionClicked() {
        System.out.println("rejection stack clicked");
        rejectionStackCLicked = true;
    }

    public void onCardInDeckClicked(CardView[] cardDeckView, int cardClickedIdx, PlayerName currentPlayerName) {
        Player currentPlayer = (currentPlayerName == PlayerName.PLAYER_GREEN) ? playerGreen : playerRed;
        Card[] cards = currentPlayer.getCardDeck();
        Card cardClicked = cards[cardClickedIdx];
        Path currentPath = cardClicked.getPath();
        PathName currentPathName = currentPath.getPathName();


        CardName cardName = cardClicked.getName();

        boolean endTurn = checkForSpecialCases(currentPlayer, currentPathName, cardName) || rejectionStackCLicked;
        if (endTurn){
            // I assume that this isn't considered as a last card played
            rejectionStackCLicked = false;
            updatePlayerMenuUI(currentPlayer, cardClickedIdx, currentPathName);
            return;
        }

        lastCardsPlayed.get(currentPlayer)[currentPathName.getValue()] = cardClicked;

        boolean playerHasPawnInPath = currentPlayer.hasPawnInPath(currentPathName);
        int[] pawnsUsedByCurrPlayer = pawnsUsed.get(currentPlayer);
        Pawn currentPlayerPawn = getPawn(currentPlayer, currentPath, pawnsUsedByCurrPlayer);
        if (!playerHasPawnInPath) pawnsUsedByCurrPlayer[currentPlayerPawn.getPawnName().getValue()]++;


        int prevPawnPosition = -1, prevOpponentPawnPosition = -1;
        if (playerHasPawnInPath){
            prevPawnPosition = currentPlayerPawn.getPosition().getCellIdx();
        }

        cardClicked.play(currentPlayer);


        boolean isMinotaurCard = cardClicked instanceof MinotaurCard; // TODO: make that a function
        Player opponentPlayer = null;
        Pawn opponentPawn = null;
        if (isMinotaurCard){
            opponentPlayer = (currentPlayerName == PlayerName.PLAYER_GREEN) ? playerRed : playerGreen;
            opponentPawn = currentPath.getPlayerPawn(opponentPlayer);
            prevOpponentPawnPosition = opponentPawn.getPosition().getCellIdx();
        }

        // Since Ariadne card currently moves two cards, it may skip a findingPosition which we want the user to handle
        boolean isAriadneCard = cardClicked instanceof AriadneCard;
        boolean passedFinding = false;
        // prevPawnPosition != GameConstants.NUMBER_OF_PATH_CELLS - 2: means we were in the second last position in the path, so we didn't miss a finding
        Position positionPassed = currentPath.getPosition(prevPawnPosition + 1);
        if (prevPawnPosition != GameConstants.NUMBER_OF_PATH_CELLS - 2) passedFinding =  positionPassed.getFinding() != null;
        if (isAriadneCard && passedFinding){
            handleFinding(currentPlayerPawn, currentPlayer,  (FindingPosition) positionPassed);
        }

        updateCheckpoints(currentPlayer, currentPlayerPawn, currentPathName); // TODO: put those in nextTurn()
        boolean posHasFinding = currentPlayerPawn.getPosition().getFinding() != null;
        if (posHasFinding) handleFinding(currentPlayerPawn, currentPlayer, (FindingPosition) currentPlayerPawn.getPosition());
        if (isMinotaurCard) updatePathUI(currentPath, opponentPlayer, prevOpponentPawnPosition, opponentPawn);
        else updatePathUI(currentPath, currentPlayer, prevPawnPosition, currentPlayerPawn);
        playerScore[currentPlayerName.getValue()] = countPoints(currentPlayer);
        updatePlayerMenuUI(currentPlayer, cardClickedIdx, currentPathName);
        nextTurn();
    }

    private Pawn getPawn(Player currentPlayer, Path currentPath, int[] pawnsUsed) {
        int numOfTheseusUsed = pawnsUsed[PawnName.THESEUS.getValue()];
        int numOfArcheologistUsed = pawnsUsed[PawnName.ARCHEOLOGIST.getValue()];
        assert(numOfTheseusUsed + numOfArcheologistUsed > GameConstants.NUMBER_OF_PAWNS);

        PathName currentPathName = currentPath.getPathName();
        boolean playerHasPawnInPath = currentPlayer.hasPawnInPath(currentPathName);
        Pawn currentPlayerPawn = null;
        if (!playerHasPawnInPath) {
            PawnName pawnName = mainWindow.askUserForPawn(numOfTheseusUsed != GameConstants.NUMBER_OF_THESEUS, numOfArcheologistUsed != GameConstants.NUMBER_OF_ARCHEOLOGIST);
            currentPlayer.setPawn(pawnName, currentPathName);
            Pawn choosenPawn = currentPlayer.getPawnInPath(pawnName, currentPathName);
            currentPath.setPlayerPawn(currentPlayer, choosenPawn);
            currentPlayerPawn = currentPath.getPlayerPawn(currentPlayer);
        } else {
            currentPlayerPawn = currentPath.getPlayerPawn(currentPlayer);
        }
        return currentPlayerPawn;
    }

    private void updateCheckpoints(Player currentPlayer, Pawn currentPlayerPawn, PathName currentPathName){
        int newPawnPosition = currentPlayerPawn.getPosition().getCellIdx();

        boolean[] checkpoints = hasCheckpointPassed.get(currentPlayer);
        checkpoints[currentPathName.getValue()] = (newPawnPosition >= GameConstants.checkPointIdx);
        checkPointsPassed += (newPawnPosition >= GameConstants.checkPointIdx) ? 1 : 0;
    }

//    private void handleFinding(Pawn currentPlayerPawn, Player currentPlayer){
//        Finding currentFinding = currentPlayerPawn.getPosition().getFinding();
//        FindingName findingName = currentFinding.getFindingName();
//
//        if (GameConstants.rareFindingNames.contains(findingName)) lastRareFindingPlayerFound.put(currentPlayer, findingName);
//
//        String[] findingDetails = getFindingDetails(findingName);
//        boolean ignore = mainWindow.askUserForFindingInteraction(findingDetails[1], findingDetails[2], findingName, currentPlayerPawn.getPawnName());
//        if (!ignore) {
//            currentPlayerPawn.interactWithFinding((FindingPosition) currentPlayerPawn.getPosition(), currentPlayer);
//            playerFindings.get(currentPlayer).add(currentFinding);
//            currentPlayerPawn.setRevealed(true);
//        }
//    }
//
    private void handleFinding(Pawn currentPlayerPawn, Player currentPlayer, FindingPosition currentFindingPosition){
        Finding currentFinding = currentFindingPosition.getFinding();
        FindingName findingName = currentFinding.getFindingName();

        if (GameConstants.rareFindingNames.contains(findingName)) lastRareFindingPlayerFound.put(currentPlayer, findingName);

        String[] findingDetails = getFindingDetails(findingName);
        boolean ignore = mainWindow.askUserForFindingInteraction(findingDetails[1], findingDetails[2], findingName, currentPlayerPawn.getPawnName());
        if (!ignore) {
            currentPlayerPawn.interactWithFinding(currentFindingPosition, currentPlayer);
            playerFindings.get(currentPlayer).add(currentFinding);
            currentPlayerPawn.setRevealed(true);
        }
    }

    private void updatePathUI(Path currentPath, Player currentPlayer, int prevPawnPosition, Pawn currentPlayerPawn){
        CentralContentT centralContent = mainWindow.getCentralContent();

        PathName currentPathName = currentPath.getPathName();
        PlayerName currentPlayerName = currentPlayer.getName();

        PawnName currentPawnName = currentPath.getPlayerPawn(currentPlayer).getPawnName();
        if (prevPawnPosition == -1) {
            centralContent.getPathPanel(currentPathName).addPawnToCell(0, currentPawnName, currentPlayerName, true);
        } else {
            int newPawnPosition = currentPlayerPawn.getPosition().getCellIdx();
            centralContent.getPathPanel(currentPathName).removePawnFromCell(currentPathName, prevPawnPosition, currentPlayerName);
            centralContent.getPathPanel(currentPathName).addPawnToCell(newPawnPosition, currentPawnName, currentPlayerName, true);
        }
    }

    private void updatePlayerMenuUI(Player currentPlayer, int cardClickedIdx, PathName currentPathName){
        PlayerName currentPlayerName = currentPlayer.getName();
        Card newCard = cardStack.getCard();
        currentPlayer.setCardInDeck(cardClickedIdx, newCard);
        List<FindingName> playerRelics = playerFindings.get(currentPlayer)
                .stream().map(Finding::getFindingName)
                .collect(Collectors.toList());

        PlayerMenuT playerMenu = (currentPlayerName == PlayerName.PLAYER_GREEN) ? mainWindow.getGreenPlayerMenu() : mainWindow.getRedPlayerMenu();
        playerMenu.updatePlayerMenu(cardClickedIdx, ControllerUtil.convertCardsToViewCards(new Card[]{newCard})[0], ControllerUtil.convertCardsToViewCards(lastCardsPlayed.get(currentPlayer)), lastRareFindingPlayerFound.get(currentPlayer),
                currentPathName, pawnsUsed.get(currentPlayer), countPoints(currentPlayer), ControllerUtil.countSnakeGoddess(playerRelics), ControllerUtil.playerFrescoes(playerRelics));
    }

    private boolean checkForSpecialCases(Player currentPlayer, PathName currentPathName, CardName cardName){
        boolean playerHasPawnInPath = currentPlayer.hasPawnInPath(currentPathName);

        if (!playerHasPawnInPath && cardName == CardName.ARIADNE_CARD) {
            mainWindow.noAriadneCardPopUp();
            return true;
        }else if ((cardName == CardName.MINOTAUR_CARD && hasCheckpointPassed.get(currentPlayer)[currentPathName.getValue()])
                || (CardName.MINOTAUR_CARD == cardName && !playerHasPawnInPath)){
            mainWindow.noMinotaurCardPopUp();
            return true;
        }
        return false;
    }


    @Override
    public void onGiveUpClicked() {
        // TODO
    }

    public static void main(String[] args){
        ControllerNew controller = new ControllerNew();
        controller.initializeGame();
    }
}
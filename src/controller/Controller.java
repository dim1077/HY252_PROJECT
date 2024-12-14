package controller;

import com.sun.xml.internal.ws.addressing.WsaActionUtil;
import model.cardStack.CardStack;
import model.cards.AriadneCard;
import model.cards.Card;
import model.cards.MinotaurCard;
import model.cards.NumberCard;
import model.findings.*;
import model.paths.*;
import model.pawns.Pawn;
import model.players.Player;
import model.players.PlayerGreen;
import model.players.PlayerRed;
import util.*;
import view.components.centralContent.CentralContent;
import view.components.menus.CardView;
import view.components.menus.PlayerMenu;
import view.window.MainWindow;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
public class Controller implements GameButtonClickListener {

    private boolean isGreensTurn; // green always plays first
    private PlayerGreen playerGreen;
    private PlayerRed playerRed;
    private CardView[] cardsOfRed;
    private CardView[] cardsOfGreen;
    private MainWindow mainWindow;

    // TODO: perhaps this should've been an array with PlayerName type instead, map is an overkill
    private Map<Player, Card[]> lastCardsPlayed = new HashMap<>();
    private CardStack cardStack;
    private Path maliaPath;
    private Path knossosPath;
    private Path phaistosPath;
    private Path zakrosPath;
    private Path[] paths; // VERY IMPORTANT: The paths are ordered with respect to PathNames.

    // max 4 TODO: put them in the constructor
    private int checkPointsPassed;
    static boolean rejectionStackCLicked;


    public void initializeGame(){
        // TODO: this or constructor?

        checkPointsPassed = 0;
        rejectionStackCLicked = false;

        this.cardsOfRed = new CardView[GameConstants.NUMBER_OF_DECK_CARDS];
        this.cardsOfGreen = new CardView[GameConstants.NUMBER_OF_DECK_CARDS];


        Finding[] nonRarefindings = new Finding[GameConstants.NUMBER_OF_RELICS];
        for (int i = 0; i < GameConstants.NUMBER_OF_SNAKE_GODDESS_STATUES; i++) nonRarefindings[i] = new SnakeGoddessFinding();
        final int baseIndex = GameConstants.NUMBER_OF_SNAKE_GODDESS_STATUES;
        nonRarefindings[baseIndex]  = new FrescoFinding(20);
        nonRarefindings[baseIndex + 1]  = new FrescoFinding(20);
        nonRarefindings[baseIndex + 2]  = new FrescoFinding(15);
        nonRarefindings[baseIndex + 3]  = new FrescoFinding(15);
        nonRarefindings[baseIndex + 4]  = new FrescoFinding(15);
        nonRarefindings[baseIndex + 5]  = new FrescoFinding(20);
        // Note that points are tied to images; not images are tied to points

        Collections.shuffle(Arrays.asList(nonRarefindings));

        // model

        RareFinding phaistosDisc = new RareFinding(RareFindingNames.PHAISTOS_DISC, 35);
        RareFinding minosRing = new RareFinding(RareFindingNames.MINOS_RING, 25);
        RareFinding maliaJewelry= new RareFinding(RareFindingNames.MALIA_JEWELRY, 25);
        RareFinding rhytonOfZakros = new RareFinding(RareFindingNames.RHYTHON_OF_ZAKROS, 25);


        maliaPath = new MaliaPath(maliaJewelry, Arrays.copyOfRange(nonRarefindings, 0, 4));
        knossosPath = new KnossosPath(minosRing, Arrays.copyOfRange(nonRarefindings, 4, 8));
        phaistosPath = new PhaistosPath(phaistosDisc, Arrays.copyOfRange(nonRarefindings, 8, 12));
        zakrosPath = new ZakrosPath(rhytonOfZakros, Arrays.copyOfRange(nonRarefindings, 12, 16));

        this.paths = new Path[]{knossosPath, maliaPath, phaistosPath, zakrosPath};

        cardStack = new CardStack(paths);



        playerRed = new PlayerRed(cardStack.getNCards(GameConstants.NUMBER_OF_DECK_CARDS));
        playerGreen = new PlayerGreen(cardStack.getNCards(GameConstants.NUMBER_OF_DECK_CARDS));


        lastCardsPlayed.put(playerRed, new Card[GameConstants.NUMBER_OF_LAST_CARD_PLAYED_DECK]);
        lastCardsPlayed.put(playerGreen, new Card[GameConstants.NUMBER_OF_LAST_CARD_PLAYED_DECK]);






        // View/UI

        cardsOfRed = convertCardsToViewCards(playerRed.getCardDeck());
        cardsOfGreen = convertCardsToViewCards(playerGreen.getCardDeck());

        mainWindow = new MainWindow(cardsOfRed, cardsOfGreen, this);

        CentralContent centralContent = mainWindow.getCentralContent();

        PlayerMenu greenMenu = mainWindow.getGreenPlayerMenu();
        PlayerMenu redMenu = mainWindow.getRedPlayerMenu();


//        centralContent.onCardRejectionClicked(e -> onCardInDeckClicked(5));
//        greenMenu.onCardClicked(0, e -> System.out.println("rejection stack clicked"));


        /* Creates the UI stuff */

        /* Initializes MaliaPath, KnossosPath, PhaistosPath and ZakrosPath */

        /* Initializes PlayerGreen and PlayerRed */

        /*  Initializes card stack */



        // Green always plays first
//        nextTurn(playerGreen);
    }



    private void initializeListeners(CentralContent centralContent, PlayerMenu playerMenu) {
//        for (int i = 0; i < cards.size(); i++) {
//            final int cardIndex = i;
//            playerMenu.setCardButtonActionListener(cardIndex + 1, );
//        }
    }


    /**
     * Advances the game to the next player's turn.
     * Handles card interactions, pawn movement, findings, and game state updates.
     *
     * @param player The player whose turn it is.
     */
    public void nextTurn(Player player) {
    if (isGameOver()){ endGame();}

//    try{
        mainWindow.getGreenPlayerMenu().setButtonsClickable(isGreensTurn);
        mainWindow.getRedPlayerMenu().setButtonsClickable(!isGreensTurn);
        isGreensTurn = !isGreensTurn;



//            Card userCard = handleCardClick();
//            int cardIdx = CentralContent.getCardIdx();
//
//            Pawn playerPawn = null;
//
//            // first time the player played in this path: he needs to choose a pawn
//            Path cardPath = userCard.getPath();
//            int pathIdxOfCard = cardPath.getPathIdx();
//            if (lastCardsPlayed.get(player)[pathIdxOfCard] == null){
//                Card[] currentLastCardsPlayed = lastCardsPlayed.get(player);
//                currentLastCardsPlayed[pathIdxOfCard] = userCard;
//                lastCardsPlayed.put(player, currentLastCardsPlayed);
//                playerPawn = playerChoosePawn();
//                cardPath.setPlayerPawn(player, playerPawn);
//            }
//
//            playerPawn = cardPath.getPlayerPawn(player);
//
//            Position currentPosition = playerPawn.getPosition();
//
//            Finding currentFinding = null;
//            if (currentPosition.getFinding() != null){
//                 currentFinding = currentPosition.getFinding();
//            }
//
//
//            if (currentFinding != null){
//                boolean takeFinding = askPlayerIfToKeepFinding(currentFinding);
//                if (takeFinding) {
//                    currentFinding.collectFinding(player);
//                }
//            }
//
//            userCard.play(player);
//
//            Card newCard = cardStack.getCard();
//            player.setCardInDeck(cardIdx, newCard);
//
//            updateCardDeck(currentPlayer, player.getCardDeck());
//            updateInformationLabel(cardStack.getStackSize(), checkPointsPassed, currentPlayer);
//            updatePath(/* TODO */);
//
//            lookForCheckPoints(player);




//        } catch (Exception e){
        // TODO: also change the exception as well, or maybe don't make it that long
//    }
//    nextTurn(player instanceof PlayerRed ? playerGreen : playerRed);










        // Step 1: Determine which player's turn it is
        // - Use the `isGreenTurn` flag to decide.
        // - Notify players (or the UI) which player's turn it is.

        // Step 2: Let the current player choose a card from their hand.
        // - If it’s the first round, throw an InvalidCardUsageException
        //   if the player attempts to use an AriadneCard (not allowed).
        // - Validate the selected card (e.g., ensure it follows game rules).

        // Step 3: Allow the player to choose whether to move a pawn.
        // - Let the player select a pawn to move on the path.
        // - Validate the move:
        //   - Ensure the pawn is on a valid path.
        //   - Ensure the selected card allows the move.
        //   - Throw exceptions for invalid moves if necessary.

        // Step 4: Handle interactions on the new position (if the pawn is moved).
        // - Check if the pawn lands on a finding or mural position.
        // - If the pawn is an Archaeologist:
        //   - Allow the player to excavate findings or photograph murals.
        // - If the pawn is Theseus:
        //   - Allow the player to destroy findings (if applicable).

        // Step 5: Check if the game is over.
        // - Use the `isGameOver()` method to determine if any end-game conditions are met.
        // - If the game is over, call `endGame()` and return immediately.

        // Step 6: Update the game state (model).
        // - Update the player's score based on findings, murals, or other interactions.
        // - Remove the played card from the player’s hand.
        // - Draw a new card for the player from the card stack.
        // - Reshuffle the discard pile into the card stack if necessary.

        // Step 7: Update the UI.
        // - Update the last played card on the UI (e.g., a JLabel).
        // - Update the player's score on the scoreboard.
        // - Update the player's hand (e.g., JButtons representing cards).
        // - Update fresco or finding displays if their state has changed.
        // - Update the turn indicator to show whose turn is next.
        // Step 8: Pass the turn to the next player.
        // - Toggle the `isGreenTurn` flag to switch players.
        // - Notify the UI or log that the turn has ended.
    }


    /**
     * Handles the event where a card is clicked.
     *
     * @param cardId The ID of the clicked card.
     * @return An integer indicating the result of the card click.
     */
    public int handleCardClick(int cardId) {
        return 1;
    }



    private void lookForCheckPoints(Player player) {
//        player.getPawns();
        // TODO
    }

    /**
     * Closes the window and ends game.
     */
    public void endGame(){

    }

    /**
     * @return returns a boolean value that checks if any of user has won or gave up
     * */
    public boolean isGameOver(){
        return true;
    }


    public static void main(String[] args){
        Controller controller = new Controller();
        controller.initializeGame();
    }


    private CardView[] convertCardsToViewCards(Card[] cards) {
        CardView[] cardViews = new CardView[cards.length];
        for (int i = 0; i < cards.length; i++) {
            if (cards[i].getName() != CardName.NUMBER_CARD) cardViews[i] = new CardView(cards[i].getName(), cards[i].getPath().getPathName());
            else cardViews[i] = new CardView(cards[i].getName(), cards[i].getPath().getPathName(), String.valueOf(((NumberCard)cards[i]).getNumber()));
        }
        return cardViews;
    }

    @Deprecated
    private Card[] convertCardViewsToCards(CardView[] cardViews) {
        Card[] cards = new Card[cardViews.length];
        for (int i = 0; i < cardViews.length; i++) {
            int pathIdx = cardViews[i].getPathName().getValue();
            Path currentPath = paths[pathIdx];
            if (cards[i].getName() == CardName.MINOTAUR_CARD) new MinotaurCard(currentPath);
            else if (cards[i].getName() == CardName.ARIADNE_CARD) new AriadneCard(currentPath);
            else if (cards[i].getName() == CardName.NUMBER_CARD) new NumberCard(currentPath, Integer.parseInt(cardViews[i].getNumber()));
            else throw new IllegalArgumentException();
        }
        return cards;
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
        // TODO: you have to make the buttons of the other player unclickable




        Player currentPlayer = (currentPlayerName == PlayerName.PLAYER_GREEN) ? playerGreen : playerRed;

        Card[] cards = currentPlayer.getCardDeck();
        Card cardClicked = cards[cardClickedIdx];

        Path currentPath = cardClicked.getPath();
        PathName currentPathName = currentPath.getPathName();
        boolean playerHasPawnInPath = currentPlayer.hasPawnInPath(currentPathName);


        if (!playerHasPawnInPath && cardClicked.getName() == CardName.ARIADNE_CARD){
            mainWindow.noAriadneCardPopUp(); // TODO: maybe an exception?
            return;
        }

        if (!playerHasPawnInPath) {
            PawnName pawnName = mainWindow.askUserForPawn();
            currentPlayer.setPawn(pawnName, currentPathName);
            Pawn choosenPawn = currentPlayer.getPawnInPath(pawnName, currentPathName);
            currentPath.setPlayerPawn(currentPlayer, choosenPawn);
        }
        cardClicked.play(currentPlayer);


        Card newCard = cardStack.getCard();
        currentPlayer.setCardInDeck(cardClickedIdx, newCard);


        PlayerMenu playerMenu = (currentPlayerName == PlayerName.PLAYER_GREEN) ? mainWindow.getGreenPlayerMenu() : mainWindow.getRedPlayerMenu();
        playerMenu.updatePlayerMenu(cardClickedIdx, convertCardsToViewCards(new Card[]{newCard})[0]);

        nextTurn(currentPlayer);
    }

    @Override
    public void onFrescoClicked() {

    }

    @Override
    public void onGiveUpClicked() {

    }
}

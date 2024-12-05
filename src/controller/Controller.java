package controller;

import model.cardStack.CardStack;
import model.cards.Card;
import model.cards.NumberCard;
import model.cards.SpecialCard;
import model.findings.RareFinding;
import model.findings.RareFindingNames;
import model.paths.*;
import model.pawns.Pawn;
import model.players.Player;
import model.players.PlayerGreen;
import model.players.PlayerRed;
import view.components.centralContent.CentralContent;
import view.components.menus.PlayerMenu;
import view.window.MainWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * This is the Controller of the application,
 * where the model and view are put together.
*/
public class Controller {

    private boolean isGreenTurn;
    private PlayerGreen playerGreen;
    private PlayerRed playerRed;

    // TODO: perhaps public?
    private Map<Player, Card[]> lastCardsPlayed = new HashMap<>();
    private CardStack cardStack;
    private Path maliaPath;
    private Path knossosPath;
    private Path phaistosPath;
    private Path zakrosPath;

    // max 4
    private int checkPointsPassed = 0;

    public void initializeGame(){
        // TODO: this or constructor?
        // Model

        playerRed = new PlayerRed();
        playerGreen = new PlayerGreen();

        lastCardsPlayed.put(playerRed, new Card[4]);
        lastCardsPlayed.put(playerGreen, new Card[4]);

        RareFinding phaistosDisc = new RareFinding(RareFindingNames.PHAISTOS_DISC);
        RareFinding minosRing = new RareFinding(RareFindingNames.MINOS_RING);
        RareFinding maliaJewelry= new RareFinding(RareFindingNames.MALIA_JEWELRY);
        RareFinding RhytonOfZakros = new RareFinding(RareFindingNames.RHYTHON_OF_ZAKROS);


         maliaPath = new MaliaPath(maliaJewelry);
         knossosPath = new KnossosPath(minosRing);
         phaistosPath = new PhaistosPath(phaistosDisc);
         zakrosPath = new ZakrosPath(RhytonOfZakros);

        CardStack cardStack = new CardStack(new Path[]{maliaPath, knossosPath, phaistosPath, zakrosPath});



        // View/UI
        MainWindow mainWindow = new MainWindow();


        /* Creates the UI stuff */

        /* Initializes MaliaPath, KnossosPath, PhaistosPath and ZakrosPath */

        /* Initializes PlayerGreen and PlayerRed */

        /*  Initializes card stack */



        // Green always plays first
        nextTurn(playerGreen);
    }

    public void nextTurn(Player player) {
        if (isGameOver()) endGame();

        try{

            Card userCard = CentralContent.getCard();
            int cardIdx = CentralContent.getCardIdx();

            Pawn playerPawn = null;

            // first time the player played in this path: he needs to choose a pawn
            Path cardPath = userCard.getPath();
            int pathIdxOfCard = cardPath.getPathIdx();
            if (lastCardsPlayed.get(player)[pathIdxOfCard] == null){
                Card[] currentLastCardsPlayed = lastCardsPlayed.get(player);
                currentLastCardsPlayed[pathIdxOfCard] = userCard;
                lastCardsPlayed.put(player, currentLastCardsPlayed);
                playerPawn = playerChoosePawn();
                cardPath.setPlayerPawn(player, playerPawn);
            }

            playerPawn = cardPath.getPlayerPawn(player);

            userCard.play(cardPath, playerPawn);

            Card newCard = cardStack.getCard();
            player.setCardInDeck(cardIdx, newCard);

            updateCardDeck(currentPlayer, player.getCardDeck());
            updateInformationLabel(cardStack.getStackSize(), checkPointsPassed, currentPlayer);
            updatePath(/* TODO */);

            lookForCheckPoints(player);




            } catch (Exception e){
            // TODO: also change the exception as well, or maybe don't make it that long
        }
        nextTurn(player instanceof PlayerRed ? playerGreen : playerRed);










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
}

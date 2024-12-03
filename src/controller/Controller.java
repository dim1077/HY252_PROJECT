package controller;

import model.cardStack.CardStack;
import model.findings.RareFinding;
import model.findings.RareFindingNames;
import model.paths.*;
import model.players.PlayerGreen;
import model.players.PlayerRed;
import view.components.centralContent.CentralContent;
import view.components.menus.PlayerMenu;
import view.window.MainWindow;

/**
 * This is the Controller of the application,
 * where the model and view are put together.
*/
public class Controller {

    private boolean isGreenTurn;
    private PlayerRed playerGreen;
    private PlayerGreen playerRed;

    private Path maliaPath;
    private Path knossosPath;
    private Path phaistosPath;
    private Path zakrosPath;

    // max 4
    private int checkPointsPassed = 0;

    public void initializeGame(){

        // Model
//        CardStack cardStack = new CardStack();
//
//        PlayerRed playerRed = new PlayerRed();
//        PlayerGreen playerGreen = new PlayerGreen();
//
//        RareFinding phaistosDisc = new RareFinding(RareFindingNames.PHAISTOS_DISC);
//        RareFinding minosRing = new RareFinding(RareFindingNames.MINOS_RING);
//        RareFinding maliaJewelry= new RareFinding(RareFindingNames.MALIA_JEWELRY);
//        RareFinding RhytonOfZakros = new RareFinding(RareFindingNames.RHYTHON_OF_ZAKROS);
//
//
//        Path maliaPath = new MaliaPath(maliaJewelry);
//        Path knossosPath = new KnossosPath(minosRing);
//        Path phaistosPath = new PhaistosPath(phaistosDisc);
//        Path zakrosPath = new ZakrosPath(RhytonOfZakros);

        // View/UI
        MainWindow mainWindow = new MainWindow();


        /* Creates the UI stuff */

        /* Initializes MaliaPath, KnossosPath, PhaistosPath and ZakrosPath */

        /* Initializes PlayerGreen and PlayerRed */

        /*  Initializes card stack */
    }

    public void nextTurn() {







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

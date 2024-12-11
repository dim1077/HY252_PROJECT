//package controller;
//
//import controller.listeners.PlayerActionListener;
//import controller.listeners.ViewUpdateListener;
//import model.*;
//import model.cardStack.CardStack;
//import model.cards.Card;
//import model.findings.RareFinding;
//import model.findings.RareFindingNames;
//import model.paths.*;
//import model.players.Player;
//import model.players.PlayerGreen;
//import model.players.PlayerRed;
//import view.MainWindow;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class GameController {
//    private final PlayerActionHandler playerActionHandler;
//    private final ViewUpdateHandler viewUpdateHandler;
//
//    private final PlayerGreen playerGreen;
//    private final PlayerRed playerRed;
//    private final CardStack cardStack;
//    private final Map<Player, Card[]> lastCardsPlayed = new HashMap<>();
//    private final Path maliaPath;
//    private final Path knossosPath;
//    private final Path phaistosPath;
//    private final Path zakrosPath;
//
//    private boolean isGreenTurn = true;
//    private int checkPointsPassed = 0;
//
//    public GameController(MainWindow view) {
//
//        // Model
//        playerRed = new PlayerRed();
//        playerGreen = new PlayerGreen();
//
//        lastCardsPlayed.put(playerRed, new Card[4]);
//        lastCardsPlayed.put(playerGreen, new Card[4]);
//
//        RareFinding phaistosDisc = new RareFinding(RareFindingNames.PHAISTOS_DISC);
//        RareFinding minosRing = new RareFinding(RareFindingNames.MINOS_RING);
//        RareFinding maliaJewelry= new RareFinding(RareFindingNames.MALIA_JEWELRY);
//        RareFinding RhytonOfZakros = new RareFinding(RareFindingNames.RHYTHON_OF_ZAKROS);
//
//
//        maliaPath = new MaliaPath(maliaJewelry);
//        knossosPath = new KnossosPath(minosRing);
//        phaistosPath = new PhaistosPath(phaistosDisc);
//        zakrosPath = new ZakrosPath(RhytonOfZakros);
//
//        CardStack cardStack = new CardStack(new Path[]{maliaPath, knossosPath, phaistosPath, zakrosPath});
//
//        playerActionHandler = new PlayerActionHandler(this, lastCardsPlayed, cardStack);
//        viewUpdateHandler = new ViewUpdateHandler(view);
//
//        // Link handlers to the view
//        view.setPlayerActionListener(playerActionHandler);
//        view.setViewUpdateListener(viewUpdateHandler);
//
//        // Start the game
//        nextTurn(playerGreen);
//    }
//
//    public void nextTurn(Player player) {
//        if (isGameOver()) endGame();
//
//        try {
//            // Delegate turn logic to the PlayerActionHandler
//            playerActionHandler.handlePlayerTurn(player);
//
//            // Update the view with the current game state
//            viewUpdateHandler.updateInformationLabel(
//                    cardStack.getStackSize(),
//                    checkPointsPassed,
//                    player
//            );
//        } catch (Exception e) {
//
//        }
//
//        // Toggle the turn
//        nextTurn(player instanceof PlayerRed ? playerGreen : playerRed);
//    }
//
//    public boolean isGameOver() {
//        // Implement logic to check game-over conditions
//        return false;
//    }
//
//    public void endGame() {
//        System.out.println("Game Over!");
//        // Game-ending logic
//    }
//}

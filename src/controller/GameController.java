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
//import view.window.MainWindow;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class GameController {
//    private PlayerActionHandler playerActionHandler;
//    private ViewUpdateHandler viewUpdateHandler;
//
//    private PlayerGreen playerGreen;
//    private PlayerRed playerRed;
//    private CardStack cardStack;
//    private Map<Player, Card[]> lastCardsPlayed = new HashMap<>();
//    private Path maliaPath;
//    private Path knossosPath;
//    private Path phaistosPath;
//    private Path zakrosPath;
//
//    private boolean isGreenTurn = true;
//    private int checkPointsPassed = 0;
//
//    public GameController() {
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
//        Path paths[] = new Path[]{maliaPath, knossosPath, phaistosPath, zakrosPath};
//
//        CardStack cardStack = new CardStack(paths);
//
//        MainWindow mainWindow = new MainWindow(this);
//
//        playerActionHandler = new PlayerActionHandler(playerRed, playerGreen, paths);
//        viewUpdateHandler = new ViewUpdateHandler(mainWindow);
////
////        // Link handlers to the view
////        view.setPlayerActionListener(playerActionHandler);
////        view.setViewUpdateListener(viewUpdateHandler);
//
//    }
//
//    public void startGame(){ nextTurn(playerGreen);}
//
//    public void nextTurn(Player player) {
//        if (isGameOver()) endGame();
//
////        try {
////            // Delegate turn logic to the PlayerActionHandler
////            playerActionHandler.handlePlayerTurn(player);
////
////            // Update the view with the current game state
////            viewUpdateHandler.updateInformationLabel(
////                    cardStack.getStackSize(),
////                    checkPointsPassed,
////                    player
////            );
////        } catch (Exception e) {
////
////        }
//
//        // And no I'm not afraid of stack overflow: The game is not going to last that long
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
//
//    public static void main(String[] args){
//        GameController gameController = new GameController();
//        gameController.startGame();
//    }
//}

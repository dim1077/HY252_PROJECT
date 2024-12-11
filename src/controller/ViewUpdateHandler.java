//package controller;
//
//import controller.listeners.ViewUpdateListener;
//import model.*;
//import model.cards.Card;
//import model.paths.Path;
//import model.players.Player;
//import view.window.MainWindow;
//
//public class ViewUpdateHandler implements ViewUpdateListener {
//    private final MainWindow view;
//
//    public ViewUpdateHandler(MainWindow view) {
//        this.view = view;
//    }
//
//    @Override
//    public void updateCardDeck(Player player, Card[] updatedDeck) {
//        view.updateCardDeck(player, updatedDeck);
//    }
//
//    @Override
//    public void updateInformationLabel(int stackSize, int checkPointsPassed, Player currentPlayer) {
//        view.updateInformationLabel(stackSize, checkPointsPassed, currentPlayer);
//    }
//
//
//    @Override
//    public void updatePath(Path path) {
//        view.updatePath(path);
//    }
//}
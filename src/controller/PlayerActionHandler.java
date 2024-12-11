//package controller;
//
//import controller.listeners.PlayerActionListener;
//import model.*;
//import model.cardStack.CardStack;
//import model.cards.Card;
//import model.findings.Finding;
//import model.paths.Path;
//import model.pawns.Pawn;
//import model.players.Player;
//
//import java.util.Map;
//
//public class PlayerActionHandler implements PlayerActionListener {
//    private final GameController gameController;
//    private final Map<Player, Card[]> lastCardsPlayed;
//    private final CardStack cardStack;
//
//    public PlayerActionHandler(GameController gameController, Map<Player, Card[]> lastCardsPlayed, CardStack cardStack) {
//        this.gameController = gameController;
//        this.lastCardsPlayed = lastCardsPlayed;
//        this.cardStack = cardStack;
//    }
//
//    public void handlePlayerTurn(Player player) {
//        // Simulate a card click (this should come from the UI in real implementation)
//        int cardId = 1; // Example card ID
//        onCardClicked(cardId, player);
//    }
//
//    @Override
//    public void onCardClicked(int cardId, Player player) {
//        Card userCard = cardStack.getCardById(cardId);
//        Path cardPath = userCard.getPath();
//        int pathIdx = cardPath.getPathIdx();
//
//        // First-time logic for playing on a path
//        if (lastCardsPlayed.get(player)[pathIdx] == null) {
//            lastCardsPlayed.get(player)[pathIdx] = userCard;
//            Pawn playerPawn = new Pawn();
//            cardPath.setPlayerPawn(player, playerPawn);
//        }
//
//        // Process card play
//        userCard.play(player);
//        Card newCard = cardStack.getCard();
//        player.setCardInDeck(cardId, newCard);
//    }
//
//    @Override
//    public void onCardClicked(int cardId) {
//
//    }
//
//    @Override
//    public void onPawnChosen(Player player, Pawn pawn) {
//        // Logic for pawn selection
//    }
//
//    @Override
//    public void onFindingCollected(Player player, Finding finding) {
//        // Logic for collecting findings
//    }
//}

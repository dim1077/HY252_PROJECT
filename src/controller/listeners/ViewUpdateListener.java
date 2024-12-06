package controller.listeners;

import model.cards.Card;
import model.paths.Path;
import model.players.Player;

public interface ViewUpdateListener {
    void updateCardDeck(Player player, Card[] updatedDeck);
    void updateInformationLabel(int stackSize, int checkPointsPassed, Player currentPlayer);
    void updatePath(Path path);
}
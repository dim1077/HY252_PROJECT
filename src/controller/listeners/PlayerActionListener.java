package controller.listeners;

import model.findings.Finding;
import model.pawns.Pawn;
import model.players.Player;

public interface PlayerActionListener {
    void onCardClicked(int cardId);
    void onPawnChosen(Player player, Pawn pawn);
    void onFindingCollected(Player player, Finding finding);
}
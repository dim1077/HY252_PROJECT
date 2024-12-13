package model.cards;

import model.paths.Path;
import model.pawns.Pawn;
import model.players.Player;
import util.CardName;

public class AriadneCard extends Card {
    public AriadneCard(Path name) {
        super(name);
    }

    @Override
    public void play(Player player) {
        Pawn playerPawn = path.getPlayerPawn(player);


        // Invalid, can't use Ariadne card in the first round. TODO: 0 is not the correct thing here
        if (playerPawn.getPosition().getCellIdx() == 0) throw new IllegalArgumentException();
        // bluh bluh bluh TODO
    }

    @Override
    void setCardName() {
        cardName = CardName.ARIADNE_CARD;
    }
}

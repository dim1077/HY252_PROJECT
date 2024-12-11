package model.cards;

import model.paths.Path;
import model.players.Player;
import util.CardName;

public class MinotaurCard extends Card {
    public MinotaurCard(Path pathName) {
        super(pathName);
    }

    @Override
    public void play(Player player) {

    }

    @Override
    void setCardName() {
        cardName = CardName.MINOTAUR_CARD;
    }


}

package model.players;

import model.cards.Card;
import util.PlayerName;

/***
 * As the name suggests, PlayerGreen represent the green player.
 */
public class PlayerGreen extends Player {
    public PlayerGreen(Card[] nCards) {
        super(nCards);
    }

    @Override
    void setPlayerName() {
        this.name = PlayerName.PLAYER_GREEN;
    }
}

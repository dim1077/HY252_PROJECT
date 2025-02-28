package model.players;


import model.cards.Card;
import util.PlayerName;

/***
 * As the name suggests, PlayerGreen represent the red player.
 */
public class PlayerRed extends Player{
    public PlayerRed(Card[] nCards) {
        super(nCards);
        setPlayerName();
    }

    @Override
    void setPlayerName() {
        this.name = PlayerName.PLAYER_RED;
    }
}

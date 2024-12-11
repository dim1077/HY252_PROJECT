package model.cards;

import model.paths.Path;
import model.players.Player;
import model.positions.Position;
import util.CardName;

public class NumberCard extends Card {
    private final int number; // TODO: maybe byte

    public NumberCard(Path path, int number) {
        super(path);
        if (number < 0 || number > 10) throw new IllegalArgumentException();
        this.number = number;
    }

    @Override
    public void play(Player player) {
//        if () throw new IllegalArgumentException();
        if (number < path.getMaxCardPlayed(player)) return;
        Position[] position = path.getPositions();
        for (int posIdx = 0; posIdx < position.length; posIdx++) {

            // The Player is in the last position: he is now removed from the map, and the pawn doesn't exist anymore
            if (posIdx == position.length - 1){
                position[posIdx].setHasPlayer(player,false);
                break;
            }

            // move the pawn into the next position
            if (position[posIdx].hasPlayer(player)) {
                position[posIdx].setHasPlayer(player,false);
                position[posIdx + 1].setHasPlayer(player,true);
                break;
            }
        }
        path.setMaxCardPlayed(number, player);
    }

    @Override
    void setCardName() {
        cardName = CardName.NUMBER_CARD;
    }

    /**
     * @return returns the number associated with this card. This number
     * has to be higher than the previously drawn number from that path
     * */
    public int getNumber(){
        return number;
    }
}

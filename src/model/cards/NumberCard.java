package model.cards;

import model.paths.Path;
import model.pawns.Pawn;
import model.positions.Position;

public class NumberCard extends Card {
    private final int number;

    public NumberCard(Path path, int number) {
        super(path);
        if (number < 0 || number > 10) throw new IllegalArgumentException();
        this.number = number;
    }

    @Override
    public void play(Pawn pawn) {
        if () throw new IllegalArgumentException();
        if (number < path.getMaxCardPlayed()) return;
        Position[] position = path.getPositions();
        for (int posIdx = 0; posIdx < position.length; posIdx++) {

            // player is in the last position: he is now removed from the map and the pawn doesn't exist anymore
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
    }

    public int getNumber(){
        return number;
    }
}

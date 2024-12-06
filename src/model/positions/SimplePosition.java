package model.positions;

import model.util.PathName;

/**
 * This class represents a cell in the path.
 * A cell has a certain score gained if the player passes by it.
 * This class has no findings.
 * */
public class SimplePosition extends Position {
    /**
     * Constructs a simple position.
     *
     * @param pathName The path name this position belongs to.
     * @param cellIdx The index of this cell on the path.
     * @param rewardScore the score associated with this position.
     */
    public SimplePosition(PathName pathName, int cellIdx, int rewardScore) {
        super(pathName, cellIdx, rewardScore);
    }

}

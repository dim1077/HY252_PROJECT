package model.positions;

import model.paths.Path;
/**
 * This class represents a cell in the path.
 * A cell has a certain score gained if the player passes by it.
 * This class has no findings.
 * */
public class SimplePosition extends Position {
    /**
     * Constructs a simple position.
     *
     * @param pathIdx The path index this position belongs to.
     * @param cellIdx The index of this cell on the path.
     * @param rewardScore the score associated with this position.
     */
    public SimplePosition(int pathIdx, int cellIdx, int rewardScore) {
        super(pathIdx, cellIdx, rewardScore);
    }
}

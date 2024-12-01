package model.positions;

import model.paths.Path;

public class SimplePosition extends Position {
    /**
     * Constructs a Position.
     *
     * @param pathIdx The path index this position belongs to
     * @param index the index of this position on the path
     * @param score the score associated with this position
     */
    public SimplePosition(int pathIdx, int index, int score) {
        super(pathIdx, index, score);
    }
}

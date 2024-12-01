package model.positions;

public abstract class Position {

    /** The path index (0-3) this position belongs to. */
    protected int pathIdx;

    /** The index of this position on the path (0–indexed). */
    protected final int index;

    /** The score this position rewards. */
    protected final int score;


    /**
     * Constructs a Position with a relic.
     * @param pathIdx The path index this position belongs to
     * @param index the index of this position on the path
     * @param score the score associated with this position
     */
    public Position(int pathIdx, int index, int score) {
        this.pathIdx = pathIdx;
        this.index = index;
        this.score = score;
    }
}

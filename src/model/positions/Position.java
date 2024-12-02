package model.positions;





/**
 * This class represents a cell in the path.
 * A cell has a certain score gained if the player passes by it.
 * */
public abstract class Position {

    protected int pathIdx;
    protected final int cellIdx;
    protected final int rewardScore;


    /**
     * Constructs a Position with a relic.
     * @param pathIdx The path index this position belongs to.
     * @param cellIdx The index of this cell on the path.
     * @param rewardScore the score associated with this position.
     */
    public Position(int pathIdx, int cellIdx, int rewardScore) {
        this.pathIdx = pathIdx;
        this.cellIdx = cellIdx;
        this.rewardScore = rewardScore;
    }


    /** @return returns the path index (0-3) this position belongs to. */
    public int getPathIdx() {
        return pathIdx;
    }

    /** @return The index of this cell on the path. (0–8). */
    public int getCellIdx() {
        return cellIdx;
    }

    /** @return the score this position rewards. */
    public int getRewardScore() {
        return rewardScore;
    }
}

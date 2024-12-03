package model.positions;


import model.paths.PathNames;

/**
 * This class represents a cell in the path.
 * A cell has a certain score gained if the player passes by it.
 * */
public abstract class Position {

    protected PathNames pathName;
    protected final int cellIdx;
    protected final int rewardScore;


    /**
     * Constructs a Position with a relic.
     * @param pathName The path name this position belongs to.
     * @param cellIdx The index of this cell on the path.
     * @param rewardScore the score associated with this position.
     */
    public Position(PathNames pathName, int cellIdx, int rewardScore) {
        this.pathName = pathName;
        this.cellIdx = cellIdx;
        this.rewardScore = rewardScore;
    }


    /** @return returns the path name (0-3) this position belongs to. */
    public PathNames getPathName() {
        return pathName;
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

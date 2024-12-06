package model.positions;


import model.findings.Finding;
import model.paths.PathNames;
import model.players.Player;

import java.util.Map;

/**
 * This class represents a cell in the path.
 * A cell has a certain score gained if the player passes by it.
 * */
public abstract class Position {

    protected int pathName;
    protected final int cellIdx;
    protected final int rewardScore;
    protected Map<Player, Boolean> hasPlayer;

    /**
     * Constructs a Position with a relic.
     * @param pathName The path name this position belongs to.
     * @param cellIdx The index of this cell on the path.
     * @param rewardScore the score associated with this position.
     */
    public Position(int pathName, int cellIdx, int rewardScore) {
        this.pathName = pathName;
        this.cellIdx = cellIdx;
        this.rewardScore = rewardScore;
    }

    // Perhaps the code stinks because this method is pointless for SimplePosition,
    // but it's the only way I thought that doesn't break polymorphism later on.
    // Furthermore, this is we could've made the getter for finding abstract instead
    public abstract Finding getFinding();


    public void setHasPlayer(Player player, boolean hasPlayerValue) {
        hasPlayer.put(player, hasPlayerValue);
    }

    /** @return returns the path name (0-3) this position belongs to. */
    public int getPathName() {
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

    public boolean hasPlayer(Player player) {
        return hasPlayer.get(player);
    }


}

package model.positions;


import model.findings.Finding;
import model.players.Player;
import util.PathName;
import util.PlayerName;

/**
 * This class represents a cell in the path.
 * A cell has a certain score gained if the player passes by it.
 * */
public abstract class Position {

    protected final PathName pathName;
    protected final int cellIdx;
    protected final int rewardScore;
    boolean[] hasPlayer;

    /**
     * Constructs a Position with a relic.
     * @param pathName The path name this position belongs to.
     * @param cellIdx The index of this cell on the path.
     * @param rewardScore the score associated with this position.
     */
    public Position(PathName pathName, int cellIdx, int rewardScore) {
        this.pathName = pathName;
        this.cellIdx = cellIdx;
        this.rewardScore = rewardScore;
        this.hasPlayer = new boolean[2];
    }

    // Perhaps the code stinks because this method is pointless for SimplePosition,
    // but it's the only way I thought that doesn't break polymorphism later on.
    // YAGNI and/or Violation of Liskov Substitution Principle?
    // This way I don't force SimpleFinding to implement this method, not sure if that
    // really fixes things though
    public Finding getFinding() {
        return null; // Default behavior for non-finding positions
    }


    /**
     * Sets whether a specific player is on this position.
     * @param player The player whose presence is being set.
     * @param hasPlayerValue True if the player is on this position, false otherwise.
     */
    public void setHasPlayer(Player player, boolean hasPlayerValue) {
        PlayerName playerName = PlayerName.playerObjectEncode(player);
        setHasPlayer(playerName, hasPlayerValue);
    }

    /**
     * Sets whether a player is on this position by their encoded name.
     * @param playerName The name of the player.
     * @param hasPlayerValue True if the player is on this position, false otherwise.
     */
    public void setHasPlayer(PlayerName playerName, boolean hasPlayerValue) {
        int playerNameIdx = playerName.getValue();
        hasPlayer[playerNameIdx] = hasPlayerValue;
    }


    /** @return returns the path name (0-3) this position belongs to. */
    public PathName getPathName() {
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

    /**
     * Checks if a specific player (by encoded name) is on this position.
     * @param playerName The encoded name of the player.
     * @return True if the player is on this position, false otherwise.
     */
    public boolean hasPlayer(PlayerName playerName) {
        int playerNameIdx = playerName.getValue();
        return hasPlayer[playerNameIdx];
    }

    /**
     * Checks if a specific player is on this position.
     * @param player The player object.
     * @return True if the player is on this position, false otherwise.
     */
    public boolean hasPlayer(Player player) {
        PlayerName playerName = PlayerName.playerObjectEncode(player);
        return hasPlayer(playerName);
    }
}

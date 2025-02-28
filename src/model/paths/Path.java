package model.paths;

import model.findings.Finding;
import model.findings.RareFinding;
import model.pawns.Pawn;
import model.players.Player;
import model.positions.FindingPosition;
import model.positions.Position;
import model.positions.SimplePosition;
import util.PathName;
import util.PlayerName;
import util.GameConstants;
import java.util.Random;

import java.util.*;

/**
 * This class represents a path, which is an array of 9 positions
 * that when completed marks the finding of the lost city
 * */
public abstract class Path {



    /** The path’s unique name (Knossos, Malia, etc.). */
    protected PathName pathName;

    /**
     * Which pawn each player has on this path, if any.
     * <br/>Key = PlayerName (GREEN=0, RED=1), Value = Pawn object.
     */
    protected final Map<PlayerName, Pawn> playerPawn = new HashMap<>();

    /**
     * Tracks the maximum numeric card a player has played in this path so far.
     * <br/>Index: [0 -> Green, 1 -> Red].
     */
    protected final int[] maxCardPlayed;

    /** The 9 positions that constitute this path. */
    protected final Position[] positions;

    /** Array of non-rare findings to be placed in the path’s available finding positions. */
    protected final Finding[] nonRareFindings;

    /** The unique rare finding for this path. */
    protected final RareFinding rareFinding;

    // TODO: perhaps add something to hold player pawn positions? (Probably not)

    public Path(RareFinding rareFinding, Finding[] nonRareFindings) {
        this.rareFinding = rareFinding;
        this.nonRareFindings = nonRareFindings;

        // Subclasses are forced to set pathName via setPathName() call
        setPathName();

        // Initialize maxCardPlayed to -1 (meaning no card played yet).
        this.maxCardPlayed = new int[GameConstants.Players.NUMBER_OF_PLAYERS];
        Arrays.fill(this.maxCardPlayed, -1);

        this.positions = new Position[GameConstants.Paths.NUMBER_OF_PATH_CELLS];
        initializePositions();
    }

    /**
     * Every subclass must assign a valid {@link PathName} to {@code pathName}.
     */
    abstract void setPathName();

    private void initializePositions() {
        List<Integer> findingCells = new ArrayList<>(GameConstants.Paths.POSITIONS_WITH_FINDINGS);
        findingCells.addAll(GameConstants.Paths.POSITIONS_WITH_FINDINGS);

        // Randomly pick one of these cells to host the rare finding
        Random random = new Random();
        int rareFinding1Based = findingCells.get(random.nextInt(findingCells.size()));
        int rareFindingIndex = rareFinding1Based - 1; // convert 1-based to 0-based

        // Place the rare finding
        positions[rareFindingIndex] = new FindingPosition(
                pathName,
                rareFindingIndex,
                GameConstants.Rewards.REWARD_PATH_FOR_ITH_CELL[rareFindingIndex],
                rareFinding,
                true
        );

        // Fill the other positions
        int nonRareIndex = 0;
        for (int pos = 0; pos < GameConstants.Paths.NUMBER_OF_PATH_CELLS; pos++) {
            if (pos == rareFindingIndex) continue; // skip the rare-finding cell

            // If 1-based index is in findingCells, place a non-rare finding
            if (findingCells.contains(pos + 1)) {
                positions[pos] = new FindingPosition(
                        pathName,
                        pos,
                        GameConstants.Rewards.REWARD_PATH_FOR_ITH_CELL[pos],
                        nonRareFindings[nonRareIndex++],
                        true
                );
            } else {
                // Otherwise, just a simple position
                positions[pos] = new SimplePosition(
                        pathName,
                        pos,
                        GameConstants.Rewards.REWARD_PATH_FOR_ITH_CELL[pos]
                );
            }
        }
    }

    /**
     * @return The path’s rare finding object.
     */
    public RareFinding getRareFinding() {
        return rareFinding;
    }

    /**
     * @return All 9 positions (0-indexed) of this path.
     */
    public Position[] getPositions() {
        return positions;
    }

    /**
     * Retrieves a specific position by index.
     * @param cellIndex The 0-based cell index in [0..8].
     * @return The corresponding {@link Position}.
     */
    public Position getPosition(int cellIndex) {
        return positions[cellIndex];
    }

    /**
     * Gets the maximum numeric card value the specified player has played so far on this path.
     */
    public int getMaxCardPlayed(Player player) {
        PlayerName pName = PlayerName.playerObjectEncode(player);
        return getMaxCardPlayed(pName);
    }

    public int getMaxCardPlayed(PlayerName playerName) {
        return maxCardPlayed[playerName.getValue()];
    }

    /**
     * Assigns a pawn to the path for a given player.
     */
    public void setPlayerPawn(Player player, Pawn pawn) {
        setPlayerPawn(PlayerName.playerObjectEncode(player), pawn);
    }

    public void setPlayerPawn(PlayerName playerName, Pawn pawn) {
        playerPawn.put(playerName, pawn);
    }

    /**
     * Retrieves the pawn belonging to the specified player (or null if none).
     */
    public Pawn getPlayerPawn(Player player) {
        return getPlayerPawn(PlayerName.playerObjectEncode(player));
    }

    public Pawn getPlayerPawn(PlayerName playerName) {
        return playerPawn.get(playerName);
    }

    /**
     * @return The {@link PathName} for this path (KNOSSOS, MALIA, etc.).
     */
    public PathName getPathName() {
        return pathName;
    }

    /**
     * Sets the maximum numeric card value for the given player.
     */
    public void setMaxCardPlayed(int maxCardPlayedValue, Player player) {
        setMaxCardPlayed(maxCardPlayedValue, PlayerName.playerObjectEncode(player));
    }

    public void setMaxCardPlayed(int maxCardPlayedValue, PlayerName playerName) {
        maxCardPlayed[playerName.getValue()] = maxCardPlayedValue;
    }
}

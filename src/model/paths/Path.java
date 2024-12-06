package model.paths;

import model.findings.RareFinding;
import model.pawns.Pawn;
import model.players.Player;
import model.positions.Position;
import model.util.PathName;
import model.util.PlayerName;
import util.GameConstants;

import java.util.*;
import java.util.Set;

/**
 * This class represents a path, which is an array of 9 positions
 * that when completed marks the finding of the lost city
 * */
public abstract class Path {

    /** Represents the numbers of the positions in the path that have a finding (1-indexed) */
    final static Set<Integer> numOfPositionsWithFindings = new HashSet<>(Arrays.asList(2, 4, 6, 8, 9));




    protected PathName pathName;
    protected Map<Player, Pawn> playerPawn = new HashMap<>();
    protected int[] maxCardPlayed;
    protected Position[] positions;
    protected final RareFinding rareFinding;
    // TODO: perhaps add something to hold player pawn positions? (Probably not)


    public Path(RareFinding rareFinding) {
        maxCardPlayed = new int[GameConstants.NUMBER_OF_PLAYERS];
        this.rareFinding = rareFinding;
        this.positions = new Position[GameConstants.NUMBER_OF_PATH_CELLS];
        initializeFindings();
    }

    /**
     * As the name suggests, we force each class to define
     * which relics will be on the current path.
     */
    protected abstract void initializeFindings();

//    public void updateMaxNumber(){
//      // throw something
//    }


    /**
     * @return returns the (unique) rare finding of the path
     */
    public RareFinding getRareFinding() {
        return rareFinding;
    }


    /**
     * @return returns an array of 9 positions
     * */
    public Position[] getPositions() {
        return positions;
    }

    public int getMaxCardPlayed(Player player) {
        PlayerName playerName = PlayerName.playerObjectEncode(player);
        return getMaxCardPlayed(playerName);
    }

    public int getMaxCardPlayed(PlayerName playerName) {
        int PlayerNameIdx = playerName.getValue();
        return maxCardPlayed[PlayerNameIdx];
    }

    public void setPlayerPawn(Player player, Pawn pawn) {
         playerPawn.put(player, pawn);
    }

    public Pawn getPlayerPawn(Player player) {
        return playerPawn.get(player);
    }

    public PathName getPathName(){
        return pathName;
    }

    public void setMaxCardPlayed(int maxCardPlayedValue, PlayerName playerName) {
        int PlayerNameIdx = playerName.getValue();
        maxCardPlayed[PlayerNameIdx] = maxCardPlayedValue;
    }

    public void setMaxCardPlayed(int maxCardPlayedValue, Player player) {
        PlayerName playerName = PlayerName.playerObjectEncode(player);
        setMaxCardPlayed(maxCardPlayedValue, playerName);
    }
}

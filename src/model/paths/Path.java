package model.paths;

import model.cards.Card;
import model.findings.RareFinding;
import model.pawns.Pawn;
import model.players.Player;
import model.players.PlayerRed;
import model.positions.Position;

import java.util.*;
import java.util.Set;

/**
 * This class represents a path, which is an array of 9 positions
 * that when completed marks the finding of the lost city
 * */
public abstract class Path {

    /** Represents the numbers of the positions in the path that have a finding (1-indexed) */
    final static Set<Integer> numOfPositionsWithFindings = new HashSet<>(Arrays.asList(2, 4, 6, 8, 9));


    /** Represents the amount of cells in a path*/
    final static int NUM_OF_POSITIONS = 9;


    final static int NUM_OF_PATHS = 4;

    protected int pathIdx;
    protected Map<Player, Pawn> playerPawn = new HashMap<>();
    protected int maxCardPlayed;
    protected Position[] positions;
    protected final RareFinding rareFinding;


    public Path(RareFinding rareFinding) {
        maxCardPlayed = 0;
        this.rareFinding = rareFinding;
        this.positions = new Position[NUM_OF_POSITIONS];
        // TODO: perhaps something for player pawns?
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

    public int getMaxCardPlayed() {
        return maxCardPlayed;
    }

    public void setPlayerPawn(Player player, Pawn pawn) {
         playerPawn.put(player, pawn);
    }

    public Pawn getPlayerPawn(Player player) {
        return playerPawn.get(player);
    }

    public int getPathIdx(){
        return pathIdx;
    }

    public void setMaxCardPlayed(int maxCardPlayed) {
        this.maxCardPlayed = maxCardPlayed;
    }

}

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



    protected PathName pathName;
    protected Map<PlayerName, Pawn> playerPawn = new HashMap<>();
    protected int[] maxCardPlayed;
    protected Position[] positions;
    protected Finding[] nonRareFindings;
    protected final RareFinding rareFinding;
    // TODO: perhaps add something to hold player pawn positions? (Probably not)


    public Path(RareFinding rareFinding, Finding[] nonRareFindings) {
        setPathName();
        maxCardPlayed = new int[GameConstants.NUMBER_OF_PLAYERS];
        Arrays.fill(maxCardPlayed, -1); // -1 means player hasn't played in that path.
        this.nonRareFindings = nonRareFindings;
        this.rareFinding = rareFinding;
        this.positions = new Position[GameConstants.NUMBER_OF_PATH_CELLS];
        initializePositions();
    }

    private void initializePositions(){
        List<Integer> positionsList = new ArrayList<>(GameConstants.numOfPositionsWithFindings); // TODO:Perhaps numOfPositionsWithFindings should be an array after all
        Random random = new Random();
        int rareFindingPosition = positionsList.get(random.nextInt(positionsList.size())) - 1;
        positions[rareFindingPosition] = new FindingPosition(pathName, rareFindingPosition, GameConstants.REWARD_PATH_FOR_ITH_CELL[rareFindingPosition], rareFinding,true);

        // Reminder: positions 2, 4, 6, 8, 9 are FindingPositions (1-indexed)
        int currFindingIdx = 0;
        for (int pos = 0; pos < GameConstants.NUMBER_OF_PATH_CELLS; pos++) {
            if (pos == rareFindingPosition) continue;

            if (GameConstants.numOfPositionsWithFindings.contains(pos + 1)) {
                positions[pos] = new FindingPosition(pathName, pos, GameConstants.REWARD_PATH_FOR_ITH_CELL[pos], nonRareFindings[currFindingIdx++], true); // the Finding will be initialized in the initializeFindings() function
            } else {
                positions[pos] = new SimplePosition(pathName, pos, GameConstants.REWARD_PATH_FOR_ITH_CELL[pos]);
            }
        }
    }

    /**
     * Forces all subclasses of Path to have a name
     * */
    abstract void setPathName();

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

    public Position getPosition(int cellIndex){
        return positions[cellIndex];
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
        PlayerName playerName = PlayerName.playerObjectEncode(player);
        setPlayerPawn(playerName, pawn);
    }

    public void setPlayerPawn(PlayerName player, Pawn pawn) {
        playerPawn.put(player, pawn);
    }

    public Pawn getPlayerPawn(Player player) {
        PlayerName playerName = PlayerName.playerObjectEncode(player);
        return getPlayerPawn(playerName);
    }

    public Pawn getPlayerPawn(PlayerName player) {
        return playerPawn.get(player);
    }


//    public Pawn getPlayerPawn(PlayerName playerName) {
//        for (int i = 0; i < GameConstants.NUMBER_OF_PATH_CELLS; i++){
//            if (positions[i].hasPlayer(playerName)) return playerPawn.get(playerName);
//        }
//        return null;
//    }

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

package model.paths;

import model.findings.RareFinding;
import model.positions.FindingPosition;
import model.positions.Position;
import model.positions.SimplePosition;

import java.util.Set;
import java.util.Arrays;
import java.util.HashSet;
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
    final static int NUM_OF_PATHS = 3;
    final static int NUM_OF_PATH_CELLS = 9;

    protected Position[] positions;
    protected final RareFinding rareFinding;


    public Path(RareFinding rareFinding) {
        this.rareFinding = rareFinding;
        this.positions = new Position[NUM_OF_POSITIONS];
        initializeFindings();
    }

    /**
     * As the name suggests, we force each class to define
     * which relics will be on the current path.
     */
    protected abstract void initializeFindings();


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
}

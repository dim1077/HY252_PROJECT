package model.positions;

import model.findings.Finding;
import model.paths.Path;

public class FindingPosition extends Position {

    /** The finding this position contains */
    private Finding finding;

    /** Indicates whether the relic is taken or destroyed (For Fresco this is always true)*/
    boolean findingAvailable;

    /**
     * Constructs a Position.
     *
     * @param pathIdx the path index this position belongs to
     * @param index the index of this position on the path
     * @param score the score associated with this position
     * @param findingAvailable Indicates whether the relic is taken or destroyed
     */
    public FindingPosition(int pathIdx, int index, int score, Finding finding, boolean findingAvailable) {
        super(pathIdx, index, score);
        this.finding = finding;
        this.findingAvailable = findingAvailable;
    }
}

package model.positions;

import model.findings.Finding;
import model.util.PathName;


/**
 * This class represents a cell in the path.
 * A cell has a certain score gained if the player passes by it.
 * This class also has a finding in it.
 * */
public class FindingPosition extends Position {

    private final Finding finding;
    boolean findingAvailable;

    /**
     * Constructs a Position.
     *
     * @param pathName the path name this position belongs to
     * @param index The index of this cell on the path
     * @param rewardScore the score associated with this position
     * @param findingAvailable Indicates whether the relic is taken or destroyed
     */
    public FindingPosition(PathName pathName, int index, int rewardScore, Finding finding, boolean findingAvailable) {
        super(pathName, index, rewardScore);
        this.finding = finding;
        this.findingAvailable = findingAvailable;
    }

    /**
     * Returns the finding this position contains iff the finding is available.
     * Then makes the finding unavailable (if it isn't a fresco)
     */
    public Finding getFinding() {
        if (!findingAvailable) return null;
        if (finding.isCollectable()) findingAvailable = false;
        return finding;
    }


    /**
     * Destroys the finding by making it unavailable
     * */
    public void destroyFinding(){
        findingAvailable = false;
    }


    /**
     * Returns an indication of whether the relic is available, either taken or destroyed.
     * (For Fresco, this always returns true.)
     */
    public boolean hasFindingAvailable() {
        return findingAvailable;
    }
}

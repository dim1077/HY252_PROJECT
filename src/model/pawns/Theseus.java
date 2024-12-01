package model.pawns;

import model.players.Player;
import model.positions.Position;



/**
 * Represents the Theseus pawn,
 * which can destroy findings but not collect them.
 */
public class Theseus extends Pawn {

    /** The number of destroyed findings. */
    private int findingsDestroyed = 0;

    /** The maximum number of findings Theseus can destroy. */
    private static final int MAX_DESTRUCTION_COUNT = 3;


    /**
     * Destroys a finding at the current position.
     *
     * @param position the position to interact with
     */
    @Override
    public void interact(Position position) {

    }

    public Theseus(Player owner) {
        super(owner);
        this.findingsDestroyed = 0;
    }

    public int getFindingsDestroyed() {
        return findingsDestroyed;
    }

    public void setFindingsDestroyed(int findingsDestroyed) {
        this.findingsDestroyed = findingsDestroyed;
    }
}

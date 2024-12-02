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
     * @param owner the owner of the pawn (PlayerGreen or PlayerRed)
     * */
    public Theseus(Player owner) {
        super(owner);
        this.findingsDestroyed = 0;
    }

    /**
     * @precondition The position must contain a finding
     *
     * This method is responsible for interacting with
     * a position that has a finding. Theseus can either destroy
     * a finding, which reveals his position, or ignore it.
     * @param position the position to interact with
     */
    @Override
    public void interact(Position position) {

    }

    /**
     * @return returns the number of findings destroyed, which are smaller or equal to thre
     * */
    public int getFindingsDestroyed() {
        return findingsDestroyed;
    }

    /**
     *  set the number of findings destroyed
     * */
    public void setFindingsDestroyed(int findingsDestroyed) {
        this.findingsDestroyed = findingsDestroyed;
    }
}

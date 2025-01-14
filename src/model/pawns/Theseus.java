package model.pawns;

import model.players.Player;
import model.positions.FindingPosition;
import util.GameConstants;
import util.PathName;
import util.PawnName;
import util.PlayerName;


/**
 * Represents the Theseus pawn,
 * which can destroy findings but not collect them.
 */
public class Theseus extends Pawn {
    private int findingsDestroyed;

    /**
     * @param owner the owner of the pawn (PlayerGreen or PlayerRed)
     */
    public Theseus(PlayerName owner, PathName pathName) {
        super(owner, pathName);
        this.findingsDestroyed = 0;
        this.immobilized = false;
    }

    /**
     * @precondition The position must contain a finding
     * <p>
     * This method is responsible for interacting with
     * a position that has a finding. Theseus can either destroy
     * a finding, which reveals his position, or ignore it.
     */
    @Override
    public void interactWithFinding(FindingPosition currentPosition, Player currentPlayer) {
        if (findingsDestroyed != GameConstants.MAX_DESTRUCTION_COUNT) return;
        currentPosition.removeFinding();
        findingsDestroyed++;
    }


    /**
     * @return returns the number of findings destroyed, which are smaller or equal to thre
     */
    @Deprecated
    public int getFindingsDestroyed() {
        return findingsDestroyed;
    }

    /**
     * set the number of findings destroyed
     */
    @Deprecated
    public void setFindingsDestroyed(int findingsDestroyed) {
        this.findingsDestroyed = findingsDestroyed;
    }


    @Override
    void setPawnName() {
        name = PawnName.THESEUS;
    }
}

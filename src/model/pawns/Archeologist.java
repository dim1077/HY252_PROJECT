package model.pawns;

import model.findings.Finding;
import model.players.Player;
import model.positions.FindingPosition;
import model.positions.Position;
import model.positions.SimplePosition;
import util.PathName;
import util.PawnName;
import util.PlayerName;


/**
 * Creates a pawn of type Archeologist, which
 * has the ability of excavate or ignore a finding
 * (excavating includes photographing frescoes)
 *
 */
public class Archeologist extends Pawn {

    /**
     * @param owner the owner of the pawn (PlayerGreen or PlayerRed)
     * */
    public Archeologist(PlayerName owner, PathName pathName) {
        super(owner, pathName);
    }

    /**
     * @precondition The position must contain a finding
     *
     * This method is responsible for interacting with
     * a position that has a finding. The player can either choose
     * to ignore the finding (thus also leaving it available for other players, expect if the
     * finding is a fresco) or take it and add it to their collections.
     *
     * In case the player chooses to exploit the finding, his archeologist will be
     * revealed to his opponent.
     *
     */
    @Override
    public void interactWithFinding(FindingPosition currentPosition, Player currentPlayer) {
        Finding finding = currentPosition.collectFinding();
        finding.addFindingInCollection(currentPosition, currentPlayer);
    }

    @Override
    void setPawnName() {
        name = PawnName.ARCHEOLOGIST;
    }
}

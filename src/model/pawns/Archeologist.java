package model.pawns;

import model.positions.Position;
import model.positions.SimplePosition;
import model.util.PlayerName;


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
    public Archeologist(PlayerName owner) {
        super(owner);
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
     * @param position the position to the archeologist is in
     */
    @Override
    public void interact(Position position) {
        if (position instanceof SimplePosition) throw new IllegalArgumentException("precondition broken," +
                " position must have a finding ");
    }
}

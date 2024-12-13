package model.pawns;


import model.positions.Position;
import util.PlayerName;


/**
 * Represents a general pawn in the game.
 * All pawns have a position, an owner and a revealable identity.
 */
public abstract class Pawn {

    protected Position currentPosition;
    protected boolean isRevealed;
    protected final PlayerName owner; // could've given Player type, but I don't like bidirectional classes

    /**
     * @param owner the owner of the pawn (PlayerGreen or PlayerRed)
     * */
    public Pawn(PlayerName owner) {
        this.owner = owner;
        this.currentPosition = null;
        this.isRevealed = false;
    }

    /**
     * Abstract method to define the pawn's special interaction.
     * Must be implemented by subclasses.
     */
    abstract void interact(Position position);

    /**
     * Moves a pawn by 'steps' steps
     * @param steps the number of steps to move the pawn on the path
     */
    void move(int steps) {

    }

    /** @return returns the position of the pawn */
    public Position getPosition(){
        return currentPosition;
    }

    /**
     * @return Returns the owner the of the pawn which could be either
     * PlayerGreen or PlayerRed
     * */
    public PlayerName getOwner() {
        return owner;
    }

    /**
     * @return returns true if the pawn is revealed, false otherwise
     */
    public boolean isRevealed() {
        return isRevealed;
    }


    /**
     * Sets the revealed state of the pawn.
     *
     * @param revealed True to reveal the pawn; false to conceal it.
     */
    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
    }

    /**
     * Sets the current position of the pawn.
     *
     * @param currentPosition The new position of the pawn.
     */
    public void setCurrentPosition(Position currentPosition) {
        this.currentPosition = currentPosition;
    }
}

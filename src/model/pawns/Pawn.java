package model.pawns;


import model.players.Player;
import model.positions.FindingPosition;
import model.positions.Position;
import util.PathName;
import util.PawnName;
import util.PlayerName;


/**
 * Represents a general pawn in the game.
 * A pawn is a game piece with a specific position, an owner, and an identity that can be revealed or hidden.
 * This class serves as a base for specialized pawn types, providing essential properties and methods.
 */
public abstract class Pawn {

    /*** The name of the pawn, which defines its identity.*/
    protected PawnName name;

    /*** The path name associated with the pawn, indicating the path it follows in the game.*/
    protected final PathName pathName;

    /*** The current position of the pawn on the game board.*/
    protected Position currentPosition;

    /*** Indicates whether the pawn's identity has been revealed.*/
    protected boolean isRevealed;

    /*** Indicates whether the pawn has finished its journey on the path.* When true, {@code currentPosition} is set to null.*/
    protected boolean hasFinished;

    /*** The owner of the pawn, represented by a {@code PlayerName}.*/
    protected final PlayerName owner;

    /*** Indicates whether the pawn can move or not*/
    protected boolean immobilized;

    /**
     * Constructs a Pawn with the specified owner and path.
     * The pawn's name is set via the {@code setPawnName()} method, and its initial state is hidden and unfinished.
     *
     * @param owner    The owner of the pawn, represented by a {@code PlayerName}.
     * @param pathName The path name associated with the pawn.
     */
    public Pawn(PlayerName owner, PathName pathName) {
        setPawnName();
        this.owner = owner;
        this.hasFinished = false;
        this.pathName = pathName;
        this.currentPosition = null;
        this.isRevealed = false;
    }

    /**
     * Defines the pawn's special interaction at a specific position.
     * This method must be implemented by subclasses to define custom behaviors.
     *
     * @param currentPosition The current position of the pawn, which may involve special interactions.
     * @param currentPlayer   The player controlling the pawn during the interaction.
     */
    abstract public void interactWithFinding(FindingPosition currentPosition, Player currentPlayer);

    /**
     * Moves the pawn along its path by a specified number of steps.
     *
     * @param steps The number of steps to move the pawn.
     */
    void move(int steps) {
        // Implementation here
    }

    /**
     * Retrieves the current position of the pawn on the game board.
     *
     * @return The current position of the pawn, or null if the pawn has finished.
     */
    public Position getPosition() {
        return currentPosition;
    }

    /**
     * Retrieves the name of the pawn.
     *
     * @return The pawn's name as a {@code PawnName}.
     */
    public PawnName getPawnName() {
        return name;
    }

    /**
     * Retrieves the path name associated with the pawn.
     *
     * @return The path name as a {@code PathName}.
     */
    public PathName getPathName() {
        return pathName;
    }

    /**
     * Retrieves the owner of the pawn.
     *
     * @return The owner's name as a {@code PlayerName}.
     */
    public PlayerName getOwner() {
        return owner;
    }

    /**
     * Checks if the pawn has finished its journey.
     *
     * @return {@code true} if the pawn has finished, {@code false} otherwise.
     */
    public boolean hasFinished() {
        return hasFinished;
    }

    /**
     * Checks if the pawn's identity has been revealed.
     *
     * @return {@code true} if the pawn is revealed, {@code false} otherwise.
     */
    public boolean isRevealed() {
        return isRevealed;
    }

    /**
     * Sets the revealed state of the pawn.
     *
     * @param revealed {@code true} to reveal the pawn; {@code false} to conceal it.
     */
    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
    }

    /**
     * Updates the current position of the pawn.
     *
     * @param currentPosition The new position of the pawn.
     */
    public void setCurrentPosition(Position currentPosition) {
        this.currentPosition = currentPosition;
    }

    /**
     * Sets the finished state of the pawn and clears its current position.
     *
     * @param hasFinished {@code true} if the pawn has finished, {@code false} otherwise.
     */
    public void setHasFinished(boolean hasFinished) {
        this.hasFinished = hasFinished;
        currentPosition = null;
    }


    /**
     *  Sets whether the pawn can move or not,
     */
    public void setImmobilized(boolean immobilized) {
        this.immobilized = immobilized;
    }

    /**
     *  Returns whether the pawn can move or not,
     */
    public boolean isImmobilized() {
        return immobilized;
    }

    /**
     * Assigns a name to the pawn. This method must be implemented by subclasses.
     */
    abstract void setPawnName();
}

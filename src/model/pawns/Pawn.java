package model.pawns;

import model.cards.Card;
import model.paths.Path;
import model.players.Player;
import model.positions.Position;


/**
 * Represents a general pawn in the game.
 * All pawns have a position, an owner and a revealable identity.
 */
public abstract class Pawn {

//    /** An indicator of whether the pawn has been placed on a path */
//    protected boolean usedInGame;

    /** The current position of the pawn on the path. */
    protected Position currentPosition;

    /** Whether the pawn's identity is revealed to the opponent. */
    protected boolean isRevealed;

    /** The owner of the pawn */
    protected Player owner;

    public Pawn(Player owner) {
        this.owner = owner;
        this.currentPosition = null;
        this.isRevealed = false;
//        this.usedInGame = false;
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


    public Position getPosition(){
        return currentPosition;
    }

    /**
     * Checks if the pawn is revealed.
     *
     * @return true if the pawn is revealed, false otherwise
     */
    public boolean isRevealed() {
        return isRevealed;
    }

}

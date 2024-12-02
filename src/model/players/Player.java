package model.players;

import model.cards.Card;
import model.findings.Finding;
import model.pawns.Pawn;

import java.util.ArrayList;
import java.util.List;

/*
The PlayerRed and PlayerGreen classes were created separately to allow for
future customization of their behavior (e.g., unique abilities or traits)
without too much of a headache.

This design also enforces the constraint that only two players can exist,
making it straightforward to manage them independently.
 */

/**
 * Abstract class representing a player in the game.
 * A Player has their pawns, current cards, findings, and score.
 */
public abstract class Player {

    private int score;
    private Card[] currentCards;
    private Pawn[] pawns;
    private List<Finding> findings;

    /**
     * Constructs a new Player with an initial score of 0,
     * an hand of 8 cards, and 4 initialized (not used) pawns.
     */
    Player() {
        this.score = 0; // TODO: make 8 and 4 constants
        this.findings = new ArrayList<>();
        initializeCards();
        initializePawns();
    }

    private void initializeCards(){

    }

    private void initializePawns(){

    }

    /**
     * @return current score (integer) of player, calculated based on findings and pawn positions.
     */
    int getScore(){
        return score;
    }

    /**
    Ends the game and exits the application. Player who called this lost.
     */
    void giveUp(){

    }

    /**
     *  Player plays move, which could involve:
     *  1) Throw number card for some path
     *  2) Throw a MinotaurCard on opponent
     *  3) Reject a card (throw in the rejection stack)
     *
     */
    void playMove(){

    }
    /**
     * @precondition stack is not empty
    * Get a new card from the available cards stack.
     */
    public Card getNewCard(){
        return null;
    }

    /**
     * @return currents the deck of cards the player current possess.
     */
    public Card[] getCards(){
        return currentCards;
    }


    // TODO: make the numbers below constants
    /**
     * @return returns 4 pawns, 3 archeologist 1 theseus.
     */
    public Pawn[] getPawns(){
        return pawns;
    }

    /**
     * @return returns findings, which could be type of FrescoFinding, RareFinding or SnakeGodnessFinding
     */
    public List<Finding> getFindings(){
        return findings;
    }

}

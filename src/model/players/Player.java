package model.players;

import model.cards.Card;
import model.findings.Finding;
import model.pawns.Pawn;

import model.pawns.Theseus;
import model.pawns.Archeologist;

import java.util.ArrayList;
import java.util.List;


/**
 * Abstract class representing a player in the game.
 * A Player has their pawns, current cards, findings, and score.
 */
public abstract class Player {

    /** The player's score, calculated based {on findings and pawn positions}. */
    private int score;

    /** The player's 8 current cards. */
    private Card[] currentCards;

    /** The player's collection of 4 pawns: three archeologists and one theseus */
    private Pawn[] pawns;

    /** The findings (Fresco, rare or snake goddess) currently collected by the player. */
    private List<Finding> findings;

    /**
     * Constructs a new Player with an initial score of 0,
     * an empty hand of 8 cards, and 4 initialized pawns.
     */
    Player() {
        this.score = 0; // TODO: make 8 and 4 constants
        this.currentCards = initializeCards();
        this.pawns = initializePawns();
        this.findings = new ArrayList<>();
    }

    private Card[] initializeCards(){
        return null;
    }

    private Pawn[] initializePawns(){
        return null;
    }

    /**
     * Retrieves the current score
     * @return: current score of player
     */
    int getScore(){
        return -1;
    }

    /**
    Ends the game and exits the application.
     */
    void giveUp(){

    };


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
     * @precondition: stack is not empty
     *
    * Get a new card from the available cards stack
     *
     */
    Card getNewCard(){
        return null;
    }
}

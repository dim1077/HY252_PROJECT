package model.players;

import  model.cards.Card;
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
     * a hand of 8 cards, and 4 initialized (not used) pawns.
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
    Ends the game and exits the application. Player who called this considered to have lost.
     */
    void giveUp(){

    }

    /**
     * Represents the player's move in the game. This could involve:
     * <ul>
     *     <li>Throwing a number card for some path.</li>
     *     <li>Throwing a MinotaurCard on the opponent.</li>
     *     <li>Rejecting a card (throwing it into the rejection stack).</li>
     * </ul>
     *
     * @deprecated This method is unlikely to be used.
     */
    @Deprecated
    void playMove(){

    }

    /**
     * Sets a specific card in the player's deck.
     *
     * @param cardIdx The index in the deck where the card will be set.
     * @param card The card to be placed at the specified index.
     */
    public void setCardInDeck(int cardIdx, Card card){
        this.currentCards[cardIdx] = card;
    }

    /**
     * Replaces the player's entire deck of cards.
     *
     * @param cards An array of cards to set as the player's current deck.
     */
    public void setCardsInDeck(Card[] cards){
        this.currentCards = cards;
    }

    /**
     * Adds a finding to the player's collection of findings.
     *
     * @param finding The finding to be added.
     */
    public void addFinding(Finding finding){
        findings.add(finding);
    }


    /**
     * @return current score (integer) of player, calculated based on findings and pawn positions.
     */
    int getScore(){
        return score;
    }

    /**
     * @precondition stack is not empty.
    * Get a new card from the available cards stack.
     */
    public Card getNewCard(){
        return null;
    }

    /**
     * @return currents the deck of cards the player current possess.
     */
    public Card[] getCardDeck(){
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

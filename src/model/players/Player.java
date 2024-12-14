package model.players;

import model.cardStack.CardStack;
import  model.cards.Card;
import model.findings.Finding;
import model.paths.Path;
import model.pawns.Archeologist;
import model.pawns.Pawn;
import model.pawns.Theseus;
import util.GameConstants;
import util.PathName;
import util.PawnName;
import util.PlayerName;

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

    PlayerName name;
    private int score;
    private Card[] currentCards;
    private Pawn[] pawns;
    private List<Finding> findings;
    private int numberOfPawnsUsed = 0;


    /**
     * Constructs a new Player with an initial score of 0,
     * a hand of 8 cards, and 4 initialized (not used) pawns.
     */
    Player(Card[] initialCards) {
        this.score = 0; // TODO: make 8 and 4 constants
        this.currentCards = initialCards;
        this.findings = new ArrayList<>();
        this.pawns = new Pawn[GameConstants.NUMBER_OF_PAWNS];
        initializePawns();
    }


    private void initializePawns(){
//        PlayerName currentPlayer = PlayerName.playerObjectEncode(this);
//        pawns[0] = new Theseus(currentPlayer);
//        pawns[1] = new Archeologist(currentPlayer);
//        pawns[2] = new Archeologist(currentPlayer);
//        pawns[3] = new Archeologist(currentPlayer);
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

    public void setNumberOfPawnsUsed(int numberOfPawnsUsed){
        this.numberOfPawnsUsed = numberOfPawnsUsed;
    }

    /**
     * Adds a finding to the player's collection of findings.
     *
     * @param finding The finding to be added.
     */
    public void addFinding(Finding finding){
        findings.add(finding);
    }

    public void setPawn(PawnName pawnName, PathName pathName){
        if (pawnName == PawnName.ARCHEOLOGIST){
            pawns[numberOfPawnsUsed++] = new Archeologist(this.getName(), pathName);
        }else if (pawnName == PawnName.THESEUS){
            pawns[numberOfPawnsUsed++] = new Theseus(this.getName(), pathName);
        }else{
            throw new IllegalArgumentException("Invalid pawn name: " + pawnName);
        }
    }

    abstract void setPlayerName();


    /**
     * @return current score (integer) of player, calculated based on findings and pawn positions.
     */
    int getScore(){
        return score;
    }

    public int getNumberOfPawnsUsed() {
        return numberOfPawnsUsed;
    }

    /**
     * @precondition stack is not empty.
    * Get a new card from the available cards stack.
     */
    public Card getNewCard(){
        return null;
    }

    public PlayerName getName(){
        return name;
    }

    public Pawn getPawnInPath(PawnName pawnName, PathName currentPath){
        Pawn pawn = getPawnInPath(currentPath);
        assert(pawnName == pawn.getPawnName());
        return pawn;
    }

    @Deprecated
    public Pawn getPawnInPath(PathName currentPathName){
        for (int i = 0; i < GameConstants.NUMBER_OF_PAWNS; i++) {
            if (pawns[i] == null) continue; // pawn has finished or hasn't been played yet
            if (pawns[i].getPathName().equals(currentPathName)) return pawns[i];
        }
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

    public boolean hasPawnInPath(PathName pathName){
        for (int i = 0; i < GameConstants.NUMBER_OF_PAWNS; i++){
            if (pawns[i] == null) continue;
            assert (pawns[i].getPosition() != null);
            if (pawns[i].getPosition().getPathName() == pathName) return true;
        }
        return false;
    }



}

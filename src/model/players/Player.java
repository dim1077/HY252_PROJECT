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

    /** The player's unique name (GREEN or RED). Set in subclass constructors. */
    protected PlayerName name;

    /** The player's current score (not always auto-updated if scoring is done externally). */
    private int score;

    /** The current hand of cards (e.g., 8 cards). */
    private Card[] currentCards;

    /** The player's 4 total pawns (index 0..3). */
    private final Pawn[] pawns;

    /** The findings (Frescos, RareFinding, SnakeGoddess) that this player has discovered. */
    private final List<Finding> findings;

    /**
     * How many pawns have been placed so far (out of 4 total).
     * Typically 0..4 (1 Theseus + 3 Archeologists).
     */
    private int numberOfPawnsUsed = 0;

    /**
     * Constructs a new Player with:
     * <ul>
     *   <li>Initial score = 0</li>
     *   <li>An initial deck of {@code initialCards}</li>
     *   <li>A list to store any discovered findings</li>
     *   <li>An array to hold up to 4 pawns (instantiated later)</li>
     * </ul>
     * Subclasses must call {@link #setPlayerName()} to define {@link #name}.
     *
     * @param initialCards The starting hand (e.g., 8 cards).
     */
    protected Player(Card[] initialCards) {
        this.score = 0;
        this.currentCards = initialCards;
        this.findings = new ArrayList<>();
        this.pawns = new Pawn[GameConstants.Players.NUMBER_OF_PAWNS]; // typically 4
        // If you want to initialize pawns up-front, do so in a separate method or a subclass
        initializePawns();
    }

    /**
     * Optional method to pre-initialize the 4 pawns.
     * Currently commented out to show how you might auto-create 1 Theseus + 3 Archeologists, etc.
     */
    protected void initializePawns() {
        // Example if you want them created immediately (not placed on paths yet):
        // PlayerName currentPlayer = getName();
        // pawns[0] = new Theseus(currentPlayer, someDefaultPathIfNeeded);
        // pawns[1] = new Archeologist(currentPlayer, someDefaultPathIfNeeded);
        // pawns[2] = new Archeologist(currentPlayer, ...);
        // pawns[3] = new Archeologist(currentPlayer, ...);
    }

    /**
     * Allows this player to forfeit or end the game. By default, does nothing.
     * If you want to handle this event in your controller (and possibly declare the other player winner),
     * you could do so from within the controller logic.
     */
    void giveUp() {
        // Possibly notify the controller or set a flag that this player has given up.
    }

    /**
     * A placeholder for future expansions:
     * <ul>
     *   <li>Throwing a number card for some path.</li>
     *   <li>Using a MinotaurCard on the opponent.</li>
     *   <li>Rejecting a card to the rejection stack.</li>
     * </ul>
     * Currently unused.
     */
    @Deprecated
    void playMove() {
        // Possibly do something or remove if truly unneeded
    }

    /**
     * Sets a specific card in the player's deck at the given index.
     *
     * @param cardIdx The deck index to replace.
     * @param card    The new card.
     */
    public void setCardInDeck(int cardIdx, Card card) {
        this.currentCards[cardIdx] = card;
    }

    /**
     * Replaces the player's entire deck of cards.
     *
     * @param cards The new set of cards to become the player's deck.
     */
    public void setCardsInDeck(Card[] cards) {
        this.currentCards = cards;
    }

    /**
     * Manual override of how many pawns have been used.
     * Typically you'd increment this internally in {@link #setPawn(PawnName, PathName)}.
     */
    public void setNumberOfPawnsUsed(int numberOfPawnsUsed) {
        this.numberOfPawnsUsed = numberOfPawnsUsed;
    }

    /**
     * Adds a finding to this player's collection.
     *
     * @param finding The discovered {@link Finding}.
     */
    public void addFinding(Finding finding) {
        findings.add(finding);
    }

    /**
     * Creates a new pawn (Theseus or Archeologist) and stores it in the player's array.
     * <br/>In gameplay, you'd typically also place it on a path's first cell (if appropriate).
     *
     * @param pawnName The type of pawn to create (THESEUS or ARCHEOLOGIST).
     * @param pathName The path the pawn is assigned to (though it might not start physically on cell 0).
     * @throws IllegalArgumentException If the {@code pawnName} is invalid or the array is full.
     */
    public void setPawn(PawnName pawnName, PathName pathName) {
        if (pawnName == PawnName.ARCHEOLOGIST) {
            pawns[numberOfPawnsUsed++] = new Archeologist(this.getName(), pathName);
        } else if (pawnName == PawnName.THESEUS) {
            pawns[numberOfPawnsUsed++] = new Theseus(this.getName(), pathName);
        } else {
            throw new IllegalArgumentException("Invalid pawn name: " + pawnName);
        }
    }

    /**
     * Subclasses must assign {@link #name} (GREEN or RED).
     */
    abstract void setPlayerName();

    /**
     * Returns the player's current score.
     *
     * Note: This may not be dynamically updated if your controller or game logic updates score externally.
     * Some designs might recalculate the "score" from findings & positions rather than storing it here.
     */
    public int getScore() {
        return score;
    }

    /**
     * Provides the total number of pawns used so far.
     */
    public int getNumberOfPawnsUsed() {
        return numberOfPawnsUsed;
    }

    /**
     * An optional method to draw a new card from a stack. Returns null by default;
     * your controller typically handles card distribution.
     */
    public Card getNewCard() {
        return null;
    }

    /**
     * @return The {@link PlayerName} that identifies this player.
     */
    public PlayerName getName() {
        return name;
    }

    /**
     * Retrieves the pawn of a certain {@code pawnName} if it is on a specific path.
     *
     * @param pawnName    The name of the pawn type (THESEUS, ARCHEOLOGIST).
     * @param currentPath The path to look on.
     * @return The matching pawn, or null if not found.
     * @throws AssertionError If the found pawn does not match the requested {@code pawnName}.
     */
    public Pawn getPawnInPath(PawnName pawnName, PathName currentPath) {
        Pawn pawn = getPawnInPath(currentPath);
        if (pawn != null) {
            assert (pawnName == pawn.getPawnName())
                    : "Found a different PawnName than requested!";
        }
        return pawn;
    }

    /**
     * Retrieves any pawn this player has on the given path (first found).
     *
     * @param currentPathName The path in question.
     * @return The player's pawn on that path, or null if none.
     */
    @Deprecated
    public Pawn getPawnInPath(PathName currentPathName) {
        for (Pawn pawn : pawns) {
            if (pawn == null) {
                continue; // Not used yet
            }
            // It's possible this is an issue if a pawn has no position set, but I have a pathName in the constructor
            if (pawn.getPathName().equals(currentPathName)) {
                return pawn;
            }
        }
        return null;
    }

    /**
     * @return The player's current hand of cards.
     */
    public Card[] getCardDeck() {
        return currentCards;
    }

    /**
     * @return All pawns belonging to this player (some may be null if not used yet).
     */
    public Pawn[] getPawns() {
        return pawns;
    }

    /**
     * @return All findings collected by this player (including Fresco, RareFinding, SnakeGoddess, etc.).
     */
    public List<Finding> getFindings() {
        return findings;
    }

    /**
     * Determines if this player has any (active) pawn on the given path.
     *
     * @param pathName The path to check.
     * @return True if a pawn is found on this path; false otherwise.
     */
    public boolean hasPawnInPath(PathName pathName) {
        for (Pawn pawn : pawns) {
            if (pawn == null) continue;

            if (pawn.getPosition() != null
                    && pawn.getPosition().getPathName() == pathName) {
                return true;
            }
        }
        return false;
    }
}

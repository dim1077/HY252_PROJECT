package tests;

import model.cards.Card;
import model.findings.Finding;
import model.findings.RareFinding;
import model.pawns.Pawn;
import model.players.PlayerGreen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.FindingName;
import util.GameConstants;
import util.PathName;
import util.PawnName;

import static org.junit.jupiter.api.Assertions.*;

/**
 *  Tests for Player (Green or Red).
 */
class PlayerTests {

    private PlayerGreen greenPlayer;
    private Card[] initialDeck;

    @BeforeEach
    void setUp() {
        initialDeck = new Card[GameConstants.NUMBER_OF_DECK_CARDS];
        greenPlayer = new PlayerGreen(initialDeck);
    }

    @Test
    void testInitialDeck() {
        assertSame(initialDeck, greenPlayer.getCardDeck());
    }

    @Test
    void testSetPawn() {
        // No pawns initially used
        assertEquals(0, greenPlayer.getNumberOfPawnsUsed());
        greenPlayer.setPawn(PawnName.THESEUS, PathName.KNOSSOS_PATH);
        assertEquals(1, greenPlayer.getNumberOfPawnsUsed());
        Pawn p = greenPlayer.getPawnInPath(PawnName.THESEUS, PathName.KNOSSOS_PATH);
        assertNotNull(p, "Should have a Theseus pawn in path KNOSSOS_PATH");
    }

    @Test
    void testAddFinding() {
        Finding rare = new RareFinding(FindingName.PHAISTOS_DISC, 35);
        greenPlayer.addFinding(rare);
        assertTrue(greenPlayer.getFindings().contains(rare));
        assertEquals(1, greenPlayer.getFindings().size());
    }

    @Test
    void testSetCardInDeck() {
        Card dummy = null;
        greenPlayer.setCardInDeck(0, dummy);
        assertNull(greenPlayer.getCardDeck()[0]);
    }
}

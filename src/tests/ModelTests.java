package tests;

import model.cards.AriadneCard;
import model.cards.Card;
import model.findings.RareFinding;
import model.paths.KnossosPath;
import model.paths.Path;
import model.pawns.Archeologist;
import model.pawns.Pawn;
import model.pawns.Theseus;
import model.players.PlayerGreen;
import model.positions.FindingPosition;
import model.positions.Position;
import model.positions.SimplePosition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.FindingName;
import util.GameConstants;
import util.PathName;
import util.PawnName;
import util.PlayerName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Demonstrates tests for core model functionality.
 */
class ModelTests {

    private SimplePosition simplePosition;
    private FindingPosition findingPosition;
    private Path knossosPath;
    private PlayerGreen greenPlayer;

    @BeforeEach
    void setUp() {
        // Minimal positions
        simplePosition = new SimplePosition(PathName.KNOSSOS_PATH, 1, 5);
        findingPosition = new FindingPosition(
                PathName.KNOSSOS_PATH,
                2,
                10,
                new RareFinding(FindingName.MINOS_RING, 25),
                true
        );
        // Minimal path
        RareFinding rare = new RareFinding(FindingName.MINOS_RING, 25);
        knossosPath = new KnossosPath(rare, new model.findings.Finding[5]);  // 5 non-rare placeholders
        // Minimal player
        greenPlayer = new PlayerGreen(new Card[GameConstants.NUMBER_OF_DECK_CARDS]);
    }

    @Test
    void testPositionBasics() {
        assertEquals(5, simplePosition.getRewardScore());
        assertNull(simplePosition.getFinding());
        // Mark a player on the position
        assertFalse(simplePosition.hasPlayer(PlayerName.PLAYER_GREEN));
        simplePosition.setHasPlayer(PlayerName.PLAYER_GREEN, true);
        assertTrue(simplePosition.hasPlayer(PlayerName.PLAYER_GREEN));
    }

    @Test
    void testFindingPositionCollection() {
        assertNotNull(findingPosition.getFinding());
        assertTrue(findingPosition.hasFindingAvailable());
        findingPosition.collectFinding();
        assertFalse(findingPosition.hasFindingAvailable(), "Finding should no longer be available after collection");
    }

    @Test
    void testPathAndRareFinding() {
        assertEquals(PathName.KNOSSOS_PATH, knossosPath.getPathName());
        assertEquals(9, knossosPath.getPositions().length);
        assertNotNull(knossosPath.getRareFinding());
    }

    @Test
    void testPawnUsage() {
        // Create a Theseus for KNOSSOS
        greenPlayer.setPawn(PawnName.THESEUS, PathName.KNOSSOS_PATH);
        Pawn theseus = greenPlayer.getPawnInPath(PawnName.THESEUS, PathName.KNOSSOS_PATH);
        assertNotNull(theseus);

        // Mark it as finished
        theseus.setHasFinished(true);
        assertTrue(theseus.hasFinished());
        assertNull(theseus.getPosition());
    }
}

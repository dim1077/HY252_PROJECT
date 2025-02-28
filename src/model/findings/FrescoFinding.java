package model.findings;

import model.players.Player;
import model.positions.FindingPosition;
import util.FindingName;

/**
 * Represents a specific type of finding called "Fresco" in the game.
 * A Fresco finding awards points to the player upon discovery and can be added to their collection.
 */
public class FrescoFinding extends Finding {

    /**
     * The number of points awarded to the player upon discovering this finding.
     */
    private final int points;

    /**
     * Constructs a Fresco finding with the specified points and name.
     *
     * @param points      The points awarded to the player for discovering this finding.
     * @param findingName The name associated with this Fresco finding.
     */
    public FrescoFinding(int points, FindingName findingName) {
        this.points = points;
        setFindingName(findingName);
    }

    /**
     * Retrieves the number of points awarded for this finding.
     *
     * @return The points assigned to the Fresco finding.
     */
    @Override
    public int getPoints() {
        return points;
    }

    /**
     * Adds this Fresco finding to the player's collection.
     *
     * @param currentPosition The position where the finding was discovered.
     * @param currentPlayer   The player who discovered the finding.
     */
    @Override
    public void addFindingInCollection(FindingPosition currentPosition, Player currentPlayer) {
        currentPlayer.addFinding(this);
    }


    /**
     * Determines whether this finding is collectable.
     *
     * @return {@code true} since a Fresco finding is always collectable.
     */
    @Override
    public boolean isCollectable() {
        return true;
    }

    /**
     * Sets the name of this finding.
     *
     * @param findingName The name to assign to this Fresco finding.
     */
    @Override
    public void setFindingName(FindingName findingName) {
        this.findingName = findingName;
    }
}

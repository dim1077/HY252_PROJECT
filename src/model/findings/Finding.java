package model.findings;

import model.players.Player;
import model.positions.FindingPosition;
import util.FindingName;

/**
 * Represents a generic finding in the game.
 * A finding can be an artifact such as a Fresco, a rare discovery, or a snake goddess statue.
 * Findings may provide points (directly or indirectly) upon discovery and can be added to a player's collection.
 *
 * This abstract class defines the essential properties and methods that all findings must implement.
 */
public abstract class Finding {

    /**
     * The name of the finding, identifying its type or category.
     */
    protected FindingName findingName;

    /**
     * Adds the finding to the player's collection.
     * This method should handle updating the player's inventory or score based on the finding type.
     *
     * @param currentPosition The position where the finding was discovered.
     * @param currentPlayer   The player who discovered the finding.
     */
    abstract public void addFindingInCollection(FindingPosition currentPosition, Player currentPlayer);

    /**
     * Determines whether the finding is collectable by a player.
     * Some findings may serve special purposes (like frescoes) and might not be directly collectible.
     *
     * @return {@code true} if the finding can be collected, {@code false} otherwise.
     */
    abstract public boolean isCollectable();

    /**
     * Sets the name of the finding.
     * Forces subclasses to define a specific name for the finding during initialization.
     *
     * @param findingName The name to assign to the finding.
     */
    abstract public void setFindingName(FindingName findingName);

    /**
     * Retrieves the name of the finding.
     *
     * @return The name of the finding as a {@code FindingName}.
     */
    public FindingName getFindingName() {
        return findingName;
    }

    /**
     * Retrieves the number of points associated with the finding.
     * Points may be awarded immediately upon discovery or through specific game mechanics.
     *
     * @return The number of points awarded for collecting the finding.
     */
    abstract public int getPoints();
}

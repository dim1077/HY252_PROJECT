package model.findings;

import model.players.Player;
import model.positions.FindingPosition;
import util.FindingName;

import java.util.Arrays;

/**
 * Represents a rare finding in the game.
 * A rare finding is a special artifact that may award points upon discovery but is not collectable.
 * It has a predefined name and enforces validation to ensure the name is valid.
 */
public class RareFinding extends Finding {

    /**
     * The number of points awarded for discovering this rare finding.
     */
    private final int points;

    /**
     * Constructs a RareFinding object with the specified name and points.
     *
     * @param name   The name of the rare finding, which must be a valid {@code FindingName}.
     * @param points The points awarded for discovering this rare finding.
     * @throws IllegalArgumentException if the provided name is not a valid rare finding name.
     */
    public RareFinding(FindingName name, int points) {
        this.points = points;
        setFindingName(name);

        // Validate that the name is a valid FindingName.
        boolean isValid = Arrays.stream(FindingName.values())
                .anyMatch(finding -> finding == name);
        if (!isValid) {
            throw new IllegalArgumentException("Invalid rare finding name: " + name);
        }
    }

    /**
     * Adds this rare finding to the player's collection and removes it from the current position.
     *
     * @param currentPosition The position where the finding was discovered.
     * @param currentPlayer   The player who discovered the finding.
     */
    @Override
    public void addFindingInCollection(FindingPosition currentPosition, Player currentPlayer) {
        currentPlayer.addFinding(this);
        currentPosition.removeFinding();
    }

    /**
     * Determines whether this rare finding is collectable.
     *
     * @return {@code true} since rare findings are collectable.
     */
    @Override
    public boolean isCollectable() {
        return true;
    }

    /**
     * Sets the name of the rare finding.
     *
     * @param findingName The name to assign to this rare finding.
     */
    @Override
    public void setFindingName(FindingName findingName) {
        this.findingName = findingName;
    }

    /**
     * Retrieves the number of points awarded for this rare finding.
     *
     * @return The points associated with this rare finding.
     */
    @Override
    public int getPoints() {
        return points;
    }
}

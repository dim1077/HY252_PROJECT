package model.findings;

import model.players.Player;
import model.positions.FindingPosition;
import util.FindingName;

/**
 * Represents a **Snake Goddess Statue** finding in the game.
 * <p>
 * The discovery of the Snake Goddess Statue does not directly award the player points.
 * Instead, the player's score is calculated at the end of the game based on the number of statues collected,
 * using the following table:
 * </p>
 * <table style="border: 1px solid black; border-collapse: collapse; text-align: center;">
 *   <thead>
 *     <tr>
 *       <th style="border: 1px solid black; padding: 5px;">Number of Statues</th>
 *       <th style="border: 1px solid black; padding: 5px;">Scores</th>
 *     </tr>
 *   </thead>
 *   <tbody>
 *     <tr>
 *       <td style="border: 1px solid black; padding: 5px;">0</td>
 *       <td style="border: 1px solid black; padding: 5px;">0</td>
 *     </tr>
 *     <tr>
 *       <td style="border: 1px solid black; padding: 5px;">1</td>
 *       <td style="border: 1px solid black; padding: 5px;">-20</td>
 *     </tr>
 *     <tr>
 *       <td style="border: 1px solid black; padding: 5px;">2</td>
 *       <td style="border: 1px solid black; padding: 5px;">-15</td>
 *     </tr>
 *     <tr>
 *       <td style="border: 1px solid black; padding: 5px;">3</td>
 *       <td style="border: 1px solid black; padding: 5px;">10</td>
 *     </tr>
 *     <tr>
 *       <td style="border: 1px solid black; padding: 5px;">4</td>
 *       <td style="border: 1px solid black; padding: 5px;">15</td>
 *     </tr>
 *     <tr>
 *       <td style="border: 1px solid black; padding: 5px;">5</td>
 *       <td style="border: 1px solid black; padding: 5px;">30</td>
 *     </tr>
 *     <tr>
 *       <td style="border: 1px solid black; padding: 5px;">6</td>
 *       <td style="border: 1px solid black; padding: 5px;">50</td>
 *     </tr>
 *   </tbody>
 * </table>
 */
public class SnakeGoddessFinding extends Finding {

    /**
     * Constructs a Snake Goddess Finding and assigns it a predefined name.
     */
    public SnakeGoddessFinding() {
        setFindingName(FindingName.SNAKE_GODDESS);
    }

    /**
     * Adds this finding to the player's collection and removes it from its current position.
     * <p>
     * Two actions are performed when this finding is collected:
     * 1. The finding is added to the player's collection.
     * 2. The finding is removed from its current position.
     * </p>
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
     * Determines whether this finding is directly collectable.
     *
     * @return {@code false}, as the Snake Goddess Statue is not directly collectable for points but contributes
     *         to end-game scoring.
     */
    @Override
    public boolean isCollectable() {
        return false;
    }

    /**
     * Sets the name of this finding.
     *
     * @param findingName The name to assign to this finding.
     */
    @Override
    public void setFindingName(FindingName findingName) {
        this.findingName = findingName;
    }

    /**
     * Retrieves the points awarded for this finding.
     * <p>
     * Since the Snake Goddess Statue does not provide points directly, this method always returns 0.
     * The actual points are calculated at the end of the game based on the number of statues collected.
     * </p>
     *
     * @return 0, as points are calculated later based on collection count.
     */
    @Override
    public int getPoints() {
        return 0;
    }
}

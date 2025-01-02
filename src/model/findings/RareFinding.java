package model.findings;


import model.players.Player;
import model.positions.FindingPosition;
import util.FindingName;

import java.util.Arrays;

public class RareFinding extends Finding {

    private int points;

    /***
     *  @param name The name of the relic
     * @param points The points the player gains by its discovery
     */
    public RareFinding(FindingName name, int points) {
        this.points = points;
        setFindingName(name);
        // The if statements unfortunately goes after the super() statement in this version of JDK

        boolean isValid = Arrays.stream(FindingName.values())
                .anyMatch(finding -> finding == name);
        if (!isValid) throw new IllegalArgumentException("Invalid rare finding name: " + name);
    }

    @Override
    public void addFindingInCollection(FindingPosition currentPosition, Player currentPlayer) {
        currentPlayer.addFinding(this);
        currentPosition.removeFinding();
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public boolean isCollectable() {
        return false;
    }

    @Override
    public void setFindingName(FindingName findingName) {
        this.findingName = findingName;
    }

    /**
     * @return returns the points of the finding
     */
    @Override
    public int getPoints() {
        return points;
    }
}

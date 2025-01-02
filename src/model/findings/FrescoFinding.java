package model.findings;

import model.players.Player;
import model.positions.FindingPosition;
import util.FindingName;

public class FrescoFinding extends Finding {

    private final int points;

    /***
     * @param points The points the player gains by its discovery
     */
    public FrescoFinding(int points, FindingName findingName) {
        this.points = points;
        setFindingName(findingName);
    }

    /**
     * @return returns the points of the finding
     */
    @Override
    public int getPoints() {
        return points;
    }


    @Override
    public void addFindingInCollection(FindingPosition currentPosition, Player currentPlayer) {
        currentPlayer.addFinding(this);
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public boolean isCollectable() {
        return true;
    }

    @Override
    public void setFindingName(FindingName findingName) {
        this.findingName = findingName;
    }


}

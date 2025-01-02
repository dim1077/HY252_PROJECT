package model.findings;


import model.players.Player;
import model.positions.FindingPosition;
import util.FindingName;

/**
 * This class represents a finding.
 * A finding could represent a Fresco, a rare finding or a snake goddess statue
 * Each finding has a name and rewards points (directly or indirectly) by its discovery
 * */
public abstract class Finding {
    protected FindingName findingName;

    /**
     * Collects the finding, adding to the player's inventory
     */
    abstract public void addFindingInCollection(FindingPosition currentPosition, Player currentPlayer);


    /**
     * Gets a description of the finding.
     * This includes the name, type, or other details about the finding.
     *
     * @return A string description of the finding.
     */
    abstract public String getDescription();

    /**
     * Determines whether the finding can be collected by a player.
     *
     * @return true if the finding is collectable, false otherwise
     */
    abstract public boolean isCollectable();


    // force API user to input a name
    abstract public void setFindingName(FindingName findingName);

    public FindingName getFindingName(){
        return findingName;
    }

    abstract public int getPoints();
}

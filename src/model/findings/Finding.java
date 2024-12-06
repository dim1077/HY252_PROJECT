package model.findings;


/**
 * This class represents a finding.
 * A finding could represent a Fresco, a rare finding or a snake goddess statue
 * Each finding has a name and rewards points (directly or indirectly) by its discovery
 * */
public interface Finding {


    /**
     * Collects the finding, adding to the player's inventory
     */
    void collectFinding();


    /**
     * Gets a description of the finding.
     * This includes the name, type, or other details about the finding.
     *
     * @return A string description of the finding.
     */
    String getDescription();

    /**
     * Determines whether the finding can be collected by a player.
     *
     * @return true if the finding is collectable, false otherwise
     */
    boolean isCollectable();
}

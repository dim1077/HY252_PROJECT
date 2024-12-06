package model.findings;


import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a finding.
 * A finding could represent a Fresco, a rare finding or a snake goddess statue
 * Each finding has a name and rewards points (directly or indirectly) by its discovery
 * */
public interface Finding {

    /*
    TODO:
    I was very troubled with the implementation here, I'm not sure if I should allow points to be passed
    in the constructor. Maybe I should have them in the hashMap along with the descriptions, or maybe the descriptions should
    be passed as well?
     */

//    /* A dictionary to map the name of a Finding to it's description  */
//    protected static final Map<String, String> descriptions = new HashMap<>();
//
//    static {
//        descriptions.put("", "");
//        // TODO
//    }
//
//    /** The name of the relic */
//    /**
//     * @return returns the description of the finding
//     * */
//    public String getDescription(){
//        return descriptions.get(name);
//    }

    void collectFinding();

    String getDescription();

    /**
     * Determines whether the finding can be collected by a player.
     *
     * @return true if the finding is collectable, false otherwise
     */
    boolean isCollectable();
}

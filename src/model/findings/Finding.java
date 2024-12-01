package model.findings;


import java.util.HashMap;
import java.util.Map;


public abstract class Finding {

    /*
    TODO: I was very troubled with the implementation here, I'm not sure if I should allow points to be passed
    in the constructor. Maybe I should have them in the hashMap along with the descriptions, or maybe the descriptions should
    be passed as well??
     */

    /** A dictionary to map the name of a Finding to it's description  */
    protected static final Map<String, String> descriptions = new HashMap<>();

    static {
        descriptions.put("", "");
        // TODO
    }

    /** The name of the relic */
    protected final String name;

    /** The points the player gains by its discovery */
    protected final int points;

    /***
     *
     * @param name The name of the relic
     * @param points The points the player gains by its discovery
     */
    public Finding(String name, int points)  {
        this.name = name;
        this.points = points;
    }
}

package model.findings;


import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

public class RareFinding extends Finding {
    /***
     *  @param name The name of the relic
     * @param points The points the player gains by its discovery
     */
    public RareFinding(String name, int points) {
        super(name, points);

        // The if statements unfortunately goes after the super() statement in this version of JDK
//        if (!rareFindingsNames.contains(name)) throw new IllegalArgumentException();
    }
}

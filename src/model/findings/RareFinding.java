package model.findings;


import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

public class RareFinding implements Finding {

//    private final int points;

    /***
     *  @param name The name of the relic
//     * @param points The points the player gains by its discovery
     */
    public RareFinding(RareFindingNames name) {
//        this.points = points;

        // The if statements unfortunately goes after the super() statement in this version of JDK
//        if (!rareFindingsNames.contains(name)) throw new IllegalArgumentException();
    }

    /**
     * @return returns the points of the finding
     */
//    public int getPoints() {
//        return points;
//    }
}

package model.findings;


import java.util.Arrays;

public class RareFinding implements Finding {

    private int points;

    /***
     *  @param name The name of the relic
     * @param points The points the player gains by its discovery
     */
    public RareFinding(RareFindingNames name, int points) {
        this.points = points;
        // The if statements unfortunately goes after the super() statement in this version of JDK


        boolean isValid = Arrays.stream(RareFindingNames.values())
                .anyMatch(finding -> finding == name);
        if (!isValid) throw new IllegalArgumentException("Invalid rare finding name: " + name);
    }

    @Override
    public void collectFinding() {

    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public boolean isCollectable() {
        return false;
    }

    /**
     * @return returns the points of the finding
     */
    public int getPoints() {
        return points;
    }
}

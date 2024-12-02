package model.findings;

class FrescoFinding extends Finding{

    private final int points;

    /***
     *  @param name The name of the relic
     * @param points The points the player gains by its discovery
     */
    public FrescoFinding(String name, int points) {
        super(name);
        this.points = points;
    }

    /**
     * @return returns the points of the finding
     */
    public int getPoints() {
        return points;
    }
}

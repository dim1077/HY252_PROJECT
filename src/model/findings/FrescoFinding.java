package model.findings;

class FrescoFinding implements Finding {

    private final int points;

    /***
     * @param points The points the player gains by its discovery
     */
    public FrescoFinding(int points) {
        this.points = points;
    }

    /**
     * @return returns the points of the finding
     */
    public int getPoints() {
        return points;
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
}

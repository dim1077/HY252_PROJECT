package model.paths;

import model.findings.RareFinding;
import model.positions.Position;



/**
 * This class represents a path, which is an array of 9 positions
 * that when completed marks the finding of the lost city
 * */
public abstract class Path {
    protected Position[] positions;
    protected final RareFinding rareFinding;
    protected final String name;


    public Path(String name, RareFinding rareFinding) {
        this.rareFinding = rareFinding;
        this.name = name;
        this.positions = new Position[9];
        initializePositions();
    }

    /**
     * As the name suggests, we force each class to define
     * which relics will be on the current path.
     */
    protected void initializePositions() {
    }

    ;


    /**
     * @return the name of the path, which is one of the four major ancient cities
     */
    public String getName() {
        return name;
    }

    /**
     * @return returns the (unique) rare finding of the path
     */
    public RareFinding getRareFinding() {
        return rareFinding;
    }


    /**
     * @return returns an array of 9 positions
     * */
    public Position[] getPositions() {
        return positions;
    }
}

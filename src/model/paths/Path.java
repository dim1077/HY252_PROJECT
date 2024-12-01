package model.paths;

import model.findings.RareFinding;
import model.positions.Position;

public abstract class Path {
    protected Position[] positions;
    protected final RareFinding rareFinding;
    protected final String name;



    public Path(String name, RareFinding rareFinding){
        this.rareFinding = rareFinding;
        this.name = name;
        this.positions = new Position[9];
        initializePositions();
    }

    /**
     * As the name suggests, we force each class to define
     * which relics will be on the current path.
     */
    protected void initializePositions(){
        // TODO: perhaps this could go in the constructor
    };


    /**
     * Retrieves the name of the path
     * @return the name of the path
     */
    public String getName(){
        return name;
    }

    /**
     * Retrieves the (unique) rare finding of the path
     *
     * @return the rare finding of the path
     */
    public RareFinding getRareFinding(){
        return rareFinding;
    }
}

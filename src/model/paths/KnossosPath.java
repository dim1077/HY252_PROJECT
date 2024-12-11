package model.paths;

import model.findings.RareFinding;
import util.PathName;


/**
 * This class represents the phaistos path, which is an array of 9 positions
 * that when completed marks the finding of the lost city
 */
public class KnossosPath extends Path {
    public KnossosPath(RareFinding rareFinding) {
        super(rareFinding);
    }

    @Override
    protected void initializeFindings() {
        // get rare findings here

        for (int position = 1; position < 10; position++) {
            if (numOfPositionsWithFindings.contains(position)) {
//                positions[position] = new FindingPosition();
            } else {
//                positions[position] = new SimplePosition();
            }
        }
    }

    @Override
    void setPathName() {
        pathName = PathName.KNOSSOS_PATH;
    }
}

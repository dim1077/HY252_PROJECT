package model.paths;

import model.findings.RareFinding;


/**
 * This class represents the phaistos path, which is an array of 9 positions
 * that when completed marks the finding of the lost city
 */
public class PhaistosPath extends Path {
    final PathNames name = PathNames.PHAISTOS_PATH;
    public PhaistosPath(RareFinding rareFinding) {
        super(rareFinding);
        super.pathIdx = 2;

    }

    @Override
    protected void initializeFindings() {

    }
}

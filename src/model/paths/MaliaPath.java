package model.paths;

import model.findings.RareFinding;


/**
 * This class represents the phaistos path, which is an array of 9 positions
 * that when completed marks the finding of the lost city
 */
public class MaliaPath extends Path {
    public MaliaPath(RareFinding rareFinding) {
        super(rareFinding);
    }

    @Override
    protected void initializeFindings() {

    }
}

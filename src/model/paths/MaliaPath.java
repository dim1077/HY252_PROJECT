package model.paths;

import model.findings.RareFinding;


/**
 * This class represents the phaistos path, which is an array of 9 positions
 * that when completed marks the finding of the lost city
 */
class MaliaPath extends Path {
    public MaliaPath() {
        super("Malia", new RareFinding("Hair jewelry", 25));
    }
}

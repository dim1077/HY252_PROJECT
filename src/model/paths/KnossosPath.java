package model.paths;

import model.findings.RareFinding;


/**
 * This class represents the phaistos path, which is an array of 9 positions
 * that when completed marks the finding of the lost city
 */
class KnossosPath extends Path {
    public KnossosPath() {
        super("Knossos", new RareFinding("Minos ring", 25));
    }
}

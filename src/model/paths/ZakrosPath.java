package model.paths;

import model.findings.RareFinding;


/**
 * This class represents the phaistos path, which is an array of 9 positions
 * that when completed marks the finding of the lost city
 */
public class ZakrosPath extends Path {
    final PathNames name = PathNames.ZAKROS_PATH;
    public ZakrosPath(RareFinding rareFinding) {
        super(rareFinding);
        super.pathIdx = 3;

    }

    @Override
    protected void initializeFindings() {

    }
}

package model.paths;

import model.findings.RareFinding;
import model.util.PathName;


/**
 * This class represents the phaistos path, which is an array of 9 positions
 * that when completed marks the finding of the lost city
 */
public class ZakrosPath extends Path {
    final PathName name = PathName.ZAKROS_PATH;
    public ZakrosPath(RareFinding rareFinding) {
        super(rareFinding);
    }

    @Override
    protected void initializeFindings() {

    }
}

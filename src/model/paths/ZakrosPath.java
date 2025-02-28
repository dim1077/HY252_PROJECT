package model.paths;

import model.findings.Finding;
import model.findings.RareFinding;
import util.PathName;


/**
 * This class represents the phaistos path, which is an array of 9 positions
 * that when completed marks the finding of the lost city
 */
public class ZakrosPath extends Path {
    public ZakrosPath(RareFinding rareFinding, Finding[] nonRareFindings) {
        super(rareFinding, nonRareFindings);
    }


    @Override
    void setPathName() {
        pathName = PathName.ZAKROS_PATH;
    }
}

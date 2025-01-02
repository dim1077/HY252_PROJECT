package view.componentT.centralContentT;

import util.PathName;
import util.PawnName;
import util.PlayerName;

import javax.swing.*;
import java.awt.*;

public class PathPanel extends JPanel {
    private PathCell[] cells;
    private PathName pathName;

    public PathPanel(PathName pathName) {
        this.cells = new PathCell[9];
        this.pathName = pathName;

        setOpaque(false);

        // I'll use FlowLayout to allow individual component sizes
        setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5)); // Left-aligned with spacing

        for (int i = 0; i < 8; i++) {
            PathCell cell = new PathCell(i, pathName);
            cells[i] = cell;
            cell.setPreferredSize(new Dimension(50, 50));
            add(cell);
        }

        // Add the last button which is slightly wider
        PathCell lastCell = new PathCell(8, pathName);
        cells[8] = lastCell;
        lastCell.setPreferredSize(new Dimension(70, 50));
        add(lastCell);
    }

    public void addPawnToCell(int cellIdx, PawnName pawnName, PlayerName playerName, boolean isRevealed) {
        if (cellIdx < 0 || cellIdx >= cells.length) throw new IllegalArgumentException("Invalid cell index: " + cellIdx);
        cells[cellIdx].addPawnToCell(pawnName, playerName, isRevealed);
    }

    public void removePawnFromCell(PathName pathName, int cellIdx, PlayerName playerName) {
        if (cellIdx < 0 || cellIdx >= cells.length) throw new IllegalArgumentException("Invalid cell index: " + cellIdx);
        cells[cellIdx].removePawnFromCell(pathName, cellIdx, playerName);
    }

    public PathName getPathName(){
        return pathName;
    }
}
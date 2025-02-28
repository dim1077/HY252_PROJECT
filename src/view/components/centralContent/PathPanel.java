package view.components.centralContent;

import util.PathName;
import util.PawnName;
import util.PlayerName;

import javax.swing.*;
import java.awt.*;

// https://stackoverflow.com/questions/72433396/code-standard-is-it-better-to-have-a-getter-setter-even-though-they-are-never
public class PathPanel extends JPanel {
    private PathCell[] cells;
    private final PathName pathName;

    /**
     * Constructs a PathPanel for the specified path name.
     * The panel is initialized with 9 cells, with the last cell slightly wider than the others.
     *
     * @param pathName The name of the path represented by this panel.
     */
    public PathPanel(PathName pathName) {
        this.cells = new PathCell[9];
        this.pathName = pathName;

        setOpaque(false);

        // I'll use FlowLayout to allow individual components sizes
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

    /**
     * Adds a pawn to a specific cell in the path.
     *
     * @param cellIdx    The index of the cell where the pawn is to be added (0-8).
     * @param pawnName   The name of the pawn to be added.
     * @param playerName The name of the player owning the pawn.
     * @param isRevealed Indicates whether the pawn is revealed.
     * @throws IllegalArgumentException If the cell index is invalid.
     */
    public void addPawnToCell(int cellIdx, PawnName pawnName, PlayerName playerName, boolean isRevealed) {
        if (cellIdx < 0 || cellIdx >= cells.length) throw new IllegalArgumentException("Invalid cell index: " + cellIdx);
        cells[cellIdx].addPawnToCell(pawnName, playerName, isRevealed);
    }

    /**
     * Removes a pawn from a specific cell in the path.
     *
     * @param cellIdx    The index of the cell from which the pawn is to be removed (0-8).
     * @param playerName The name of the player owning the pawn.
     * @throws IllegalArgumentException If the cell index is invalid.
     */
    public void removePawnFromCell(int cellIdx, PlayerName playerName) {
        if (cellIdx < 0 || cellIdx >= cells.length) throw new IllegalArgumentException("Invalid cell index: " + cellIdx);
        cells[cellIdx].removePawnFromCell(playerName);
    }

    /**
     * Returns the name of the path represented by this panel.
     *
     * @return The path name.
     */
    public PathName getPathName(){
        return pathName;
    }
}
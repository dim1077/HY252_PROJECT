package view.components.centralContent;

import javax.swing.*;
import java.awt.*;

public class CentralContent {

    // TODO: maybe this will have to in a different file
    public enum CellState {
        NONE,
        GREEN_PLAYER,
        RED_PLAYER
    }

    private JLayeredPane cenrtalContentHolder;
    private JButton takeStackCard;
    private JLabel stackDescription;
    private JPanel PathsGrid;
    private JLabel[][] gridLabels; // 2D array to manage grid labels dynamically
    private CellState[][] cellStates;

    public CentralContent() {

    }

    // Method to update the grid based on the game state
    public void updateGrid(boolean[][] newGameState) {

    }

    // Getter for the layered pane
    public JLayeredPane getLayeredPane() {
        return cenrtalContentHolder;
    }
}

package view.components.centralContent;

import javax.swing.*;
import java.awt.*;

public class CentralContent extends JLayeredPane {

    public enum CellState {
        NONE,
        GREEN_PLAYER,
        RED_PLAYER
    }

    private JPanel pathsGrid;
    private JLabel[][] gridLabels; // 2D array to manage grid labels dynamically
    private CellState[][] cellStates;
    private JLabel backgroundLabel;

    // Constructor
    public CentralContent() {
        this.setLayout(null); // Use absolute layout for custom positioning
        initializeGrid();
        addBackgroundImage("src/assets/images/background.jpg"); // Specify the path to your background image

        // Add a resize listener to dynamically adjust components
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                resizeComponents();
            }
        });
    }

    // Adds a background image scaled to the full size of CentralContent
    private void addBackgroundImage(String imagePath) {
        // Load the image
        ImageIcon imageIcon = new ImageIcon(imagePath);

        // Create a JLabel for the background
        backgroundLabel = new JLabel();
        backgroundLabel.setIcon(imageIcon);
        backgroundLabel.setBounds(0, 0, 0, 0); // Initial bounds, updated on resize

        // Add the background label to the lowest layer
        this.add(backgroundLabel, Integer.valueOf(0));
    }

    // Initializes the grid
    private void initializeGrid() {
        pathsGrid = new JPanel(null); // Use absolute layout for fine control
        pathsGrid.setBackground(new Color(0, 0, 0, 0)); // Transparent background

        gridLabels = new JLabel[4][9];
        cellStates = new CellState[4][9];

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 9; col++) {
                JLabel cellLabel = new JLabel("", SwingConstants.CENTER);
                cellLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cellLabel.setOpaque(true);
                cellLabel.setBackground(Color.WHITE);

                pathsGrid.add(cellLabel);
                gridLabels[row][col] = cellLabel;
                cellStates[row][col] = CellState.NONE;
            }
        }

        this.add(pathsGrid, Integer.valueOf(1)); // Add to layered pane at level 1
    }

    // Updates a specific cell in the grid
    public void updateCell(int row, int col, CellState state) {
        if (row < 0 || row >= 4 || col < 0 || col >= 9) {
            throw new IllegalArgumentException("Invalid cell coordinates");
        }

        cellStates[row][col] = state;

        JLabel cellLabel = gridLabels[row][col];
        switch (state) {
            case NONE -> cellLabel.setBackground(Color.WHITE);
            case GREEN_PLAYER -> cellLabel.setBackground(Color.GREEN);
            case RED_PLAYER -> cellLabel.setBackground(Color.RED);
        }
    }

    // Resets the grid to its initial state
    public void resetGrid() {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 9; col++) {
                updateCell(row, col, CellState.NONE);
            }
        }
    }

    // Gets the current state of the grid
    public CellState[][] getGridState() {
        return cellStates;
    }

    // Updates the grid with a new game state
    public void updateGrid(CellState[][] newGameState) {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 9; col++) {
                updateCell(row, col, newGameState[row][col]);
            }
        }
    }

    // Dynamically resize and reposition components
    private void resizeComponents() {
        Dimension contentSize = this.getSize();

        // Resize and reposition background to cover entire CentralContent
        if (backgroundLabel != null) {
            backgroundLabel.setBounds(0, 0, contentSize.width, contentSize.height);
            ImageIcon icon = (ImageIcon) backgroundLabel.getIcon();
            if (icon != null) {
                Image scaledImage = icon.getImage().getScaledInstance(contentSize.width, contentSize.height, Image.SCALE_SMOOTH);
                backgroundLabel.setIcon(new ImageIcon(scaledImage));
            }
        }

        // Resize and reposition grid
        int numRows = 4;
        int numCols = 9;

        int gapSmall = 5;
        int gapLarge = 10;

        int totalWidth = (int) (contentSize.width * 0.75);
        int totalHeight = (int) (contentSize.height * 0.75);

        int maxCellHeight = (totalHeight - (numRows - 1) * gapSmall) / numRows;
        int maxCellWidth = (totalWidth - (numCols - 1) * gapLarge) / numCols;

        int cellHeight = Math.min(maxCellHeight, (int) (maxCellWidth * 0.75));
        int cellWidth = (int) (cellHeight * (4.0 / 3.0));

        int gridWidth = (cellWidth * numCols) + gapLarge * (numCols - 1);
        int gridHeight = (cellHeight * numRows) + gapSmall * (numRows - 1);

        // Align grid to the right side of the container
        int gridX = contentSize.width - gridWidth - 20; // Add some padding (20px)
        int gridY = (contentSize.height - gridHeight) / 2;

        pathsGrid.setBounds(gridX, gridY, gridWidth, gridHeight);

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                JLabel cellLabel = gridLabels[row][col];
                int x = col * (cellWidth + gapLarge);
                int y = row * (cellHeight + gapSmall);
                cellLabel.setBounds(x, y, cellWidth, cellHeight);
            }
        }
    }
}

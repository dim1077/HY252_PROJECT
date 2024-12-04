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
        this.setLayout(null);
//        setPreferredSize(calculatePreferredSize());
        initializeGrid();

//        addBackgroundImage("src/assets/images/background.jpg");
        setupStaticLayout();
    }

    private Dimension calculatePreferredSize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width * 0.60);
        int height = (int) (screenSize.height * 0.60);
        return new Dimension(width, height);
    }

//    private void addBackgroundImage(String imagePath) {
//        ImageIcon imageIcon = new ImageIcon(imagePath);
//        Image scaledImage = imageIcon.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
//        imageIcon = new ImageIcon(scaledImage);
//
//        backgroundLabel = new JLabel();
//        backgroundLabel.setIcon(imageIcon);
//        backgroundLabel.setBounds(0, 0, this.getWidth(), this.getHeight());
//
//        this.add(backgroundLabel, Integer.valueOf(0)); // Add to the background layer
//    }


    // Initializes the grid
    private void initializeGrid() {
        pathsGrid = new JPanel(null); // Use absolute layout for fine control
        pathsGrid.setBackground(new Color(0, 0, 0, 0)); // Transparent background

        gridLabels = new JLabel[4][9];
        cellStates = new CellState[4][9];

        Color[] PathColors = {Color.RED, Color.YELLOW, Color.WHITE, Color.BLUE};

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 9; col++) {
                JLabel cellLabel = new JLabel("", SwingConstants.CENTER);
                cellLabel.setBorder(BorderFactory.createLineBorder(PathColors[row], 3));
                cellLabel.setOpaque(true);
                cellLabel.setBackground(Color.WHITE);

                pathsGrid.add(cellLabel);
                gridLabels[row][col] = cellLabel;
                cellStates[row][col] = CellState.NONE;
            }
        }

        this.add(pathsGrid, Integer.valueOf(1));
    }

    // Updates a specific cell in the grid
    public void updateCell(int row, int col, CellState state) {
        if (row < 0 || row >= 4 || col < 0 || col >= 9) {
            throw new IllegalArgumentException("Invalid cell coordinates");
        }

        cellStates[row][col] = state;

        JLabel cellLabel = gridLabels[row][col];
        switch (state) {
            case NONE:
                cellLabel.setBackground(Color.WHITE);
                break;
            case GREEN_PLAYER:
                cellLabel.setBackground(Color.GREEN);
                break;
            case RED_PLAYER:
                cellLabel.setBackground(Color.RED);
                break;
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

    private void setupStaticLayout() {
        int contentWidth = 1920;
        int contentHeight = 1080;
        this.setSize(contentWidth, contentHeight);

        // Set fixed size for the background
//        if (backgroundLabel != null) {
//            backgroundLabel.setBounds(0, 0, contentWidth, contentHeight);
//            ImageIcon icon = (ImageIcon) backgroundLabel.getIcon();
//            if (icon != null) {
//                Image scaledImage = icon.getImage().getScaledInstance(contentWidth, contentHeight, Image.SCALE_SMOOTH);
//                backgroundLabel.setIcon(new ImageIcon(scaledImage));
//            }
//        }

        // TODO: make those constants

        int numRows = 4;
        int numCols = 9;

        int cellWidth = 80;
        int cellHeight = 60;
        int gapBetweenCells = 5;
        int gapBetweenPaths = 10;



        pathsGrid.setBounds(1000 , 100, 800, 400);

        // Position the cells within the grid
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                JLabel cellLabel = gridLabels[row][col];
                int x = col * (cellWidth + gapBetweenCells);
                int y = row * (cellHeight + gapBetweenPaths);
                cellLabel.setBounds(x, y, cellWidth, cellHeight);
            }
        }
    }

}

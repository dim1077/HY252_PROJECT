package view.components.centralContent;

import controller.Controller;
import controller.GameButtonClickListener;
import util.GameConstants;
import util.PathName;
import util.PawnName;
import util.PlayerName;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


/**
 * The CentralContent class represents the main central panel in the game UI.
 * It displays the game's paths as a grid, a rejection stack button, and game-related information.
 * The panel is implemented using a {@link JLayeredPane} for flexible component layering.
 */
public class CentralContent extends JLayeredPane {
    private JLabel infoLabel;
    private JPanel pathGrid;
    private JLabel[][] gridCells = new JLabel[4][9];
    private JButton rejectionStack;
    private ImageIcon pathIcon;
    private GameButtonClickListener cardClickListener;

    /**
     * Constructs the CentralContent panel.
     */
    public CentralContent(int availableCards, int checkPoints, boolean isPlayerOnesTurn , GameButtonClickListener cardClickListener) {
        this.cardClickListener = cardClickListener;

        // Set up the JLayeredPane properties
        setBackground(Color.BLUE);
        setOpaque(true);
        setPreferredSize(new Dimension(800, 600)); // Increase preferred size
        initializePathGrid();
        displayRejectionStackButton();
        initializeInformationDisplay(availableCards, checkPoints, isPlayerOnesTurn);
    }

    private void initializePathGrid() {
        pathGrid = new JPanel(new GridLayout(4, 9, 10, 10));
        pathGrid.setOpaque(false);
        pathGrid.setBounds(800, 90, 950, 420);

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 9; col++) {
                JLabel label = new JLabel();
                label.setOpaque(true);
                label.setBackground(Color.WHITE);
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

                // Set the path image for this cell
                int index = (row * 9) + col; // Flattened index for file names
                String iconFileName = getPathIconFileName(index);
                ImageIcon rawIcon = new ImageIcon("src/assets/images/paths/" + iconFileName + ".jpg");
                Image scaledImage = rawIcon.getImage().getScaledInstance(92, 97, Image.SCALE_SMOOTH);
                pathIcon = new ImageIcon(scaledImage);
                label.setIcon(pathIcon);

                gridCells[row][col] = label; // Store the label in the 2D array
                pathGrid.add(label); // Add the label to the grid panel
            }
        }

//        addPawnToCell(PathName.KNOSSOS_PATH, 0, PawnName.ARCHEOLOGIST, PlayerName.PLAYER_RED, true);
//        removePawnFromCell(PathName.KNOSSOS_PATH, 0, PlayerName.PLAYER_RED);
//        addPawnToCell(0, 1, PawnName.THESEUS, true); // Example: Add Theseus to cell at (0, 1)
        add(pathGrid, JLayeredPane.DEFAULT_LAYER);
    }

    private void updatePathGrid(){

    }

    public void addPawnToCell(PathName pathName, int cellIdx, PawnName pawnName, PlayerName playerName, boolean isRevealed) {
        int row = pathName.getValue();
        int col = cellIdx;


        if (row < 0 || row >= 4 || col < 0 || col >= 9) {
            System.out.println("Invalid row or column: row = " + row + ", col = " + col);
            return;
        }

        JLabel cellLabel = gridCells[row][col];

        // Determine the number of existing components in the cell to offset the new pawn
        int existingPawnCount = cellLabel.getComponentCount();

        if (existingPawnCount == 2) throw new IllegalArgumentException("Cannot have more than two pawns on a cell");

        int xOffset = 10 + existingPawnCount * 30; // Starting position with 30px offset for each pawn

        // Create a new small JLabel for the pawn
        JLabel pawnLabel = new JLabel();
        pawnLabel.setBounds(xOffset, 15, 30, 60); // Position dynamically based on the number of pawns

        // Determine the image path for the pawn
        String imagePath = "";
        if (!isRevealed) imagePath += "src/assets/images/pionia/question.jpg";
        else if (pawnName == PawnName.ARCHEOLOGIST) imagePath += "src/assets/images/pionia/arch.jpg";
        else if (pawnName == PawnName.THESEUS) imagePath += "src/assets/images/pionia/theseus.jpg";
        else throw new IllegalArgumentException("Invalid pawn name: " + pawnName);

        // Load and scale the pawn image
        ImageIcon rawIcon = new ImageIcon(imagePath);
        Image scaledImage = rawIcon.getImage().getScaledInstance(30, 60, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        pawnLabel.setIcon(scaledIcon);

        if (playerName == PlayerName.PLAYER_GREEN) {
            pawnLabel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
        } else if (playerName == PlayerName.PLAYER_RED) {
            pawnLabel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        }

        // Optionally set a tooltip or text if needed
        pawnLabel.setToolTipText(pawnName.toString());

        // Add the new pawn to the cell
        cellLabel.add(pawnLabel);
        cellLabel.repaint(); // Refresh the cell to show the updated content
        cellLabel.revalidate(); // Revalidate to update the layout
    }


    public void removePawnFromCell(PathName pathName, int cellIdx, PlayerName playerName) {
        int row = pathName.getValue();
        int col = cellIdx;

        if (row < 0 || row >= 4 || col < 0 || col >= 9)
            throw new IllegalArgumentException("Invalid row or column: row = " + row + ", col = " + col);

        JLabel cellLabel = gridCells[row][col];
        if (cellLabel == null)
            throw new IllegalArgumentException("No cell found at the specified coordinates");

        // Determine the target border color based on playerName
        Color targetColor;
        if (playerName == PlayerName.PLAYER_GREEN) {
            targetColor = Color.GREEN;
        } else if (playerName == PlayerName.PLAYER_RED) {
            targetColor = Color.RED;
        } else {
            throw new IllegalArgumentException("Invalid player name: " + playerName);
        }

        // Find the component (pawn label) with the matching border color
        Component[] components = cellLabel.getComponents();
        for (Component comp : components) {
            if (comp instanceof JLabel) {
                JLabel pawnLabel = (JLabel) comp;

                // Check if the border color matches the target color
                if (pawnLabel.getBorder() instanceof LineBorder) {
                    LineBorder border = (LineBorder) pawnLabel.getBorder();
                    if (border.getLineColor().equals(targetColor)) {
                        cellLabel.remove(pawnLabel);
                        cellLabel.repaint(); // Refresh the cell to show changes
                        cellLabel.revalidate();
                        return; // Exit once the matching pawn is removed
                    }
                }
            }
        }
        throw new RuntimeException("No pawn found for player: " + playerName + " at path: " + pathName + " cell: " + cellIdx);
    }


    private void displayRejectionStackButton(){

        rejectionStack = new JButton("Click Me");
        rejectionStack.setBounds(100, 270, 100, 150); // Set position and size of the button


        rejectionStack.addActionListener(e -> cardClickListener.onCardRejectionClicked());
        add(rejectionStack);
    }

    private void initializeInformationDisplay(int availableCards, int checkPoints, boolean isPlayerOnesTurn) {
        // Create the JLabel
        infoLabel = new JLabel();
        infoLabel.setBounds(85, 450, 200, 100); // Adjust size to fit multi-line content
        infoLabel.setOpaque(true); // Make the background visible
        infoLabel.setBackground(Color.WHITE);
        infoLabel.setVerticalAlignment(SwingConstants.TOP); // Align text to the top of the label
        infoLabel.setHorizontalAlignment(SwingConstants.LEFT); // Align text to the left of the label

        // Set text with each sentence on a new line
        infoLabel.setText("<html>Available cards: " + availableCards + ".<br>Check points: " + checkPoints + ".<br>Turn: " + (isPlayerOnesTurn ? "Player 1 (red)" : "Player 2 (green)") + ".</html>");

        // Add the JLabel to the container
        setLayout(null); // Set layout manager to null if using absolute positioning
        add(infoLabel);
    }

    // Method to update the displayed information
    public void updateInformation(int cardsLeftInStack, int numOfCheckPoints, boolean isPlayerGreenTurn) {
        infoLabel.setText("<html>Cards Left: " + cardsLeftInStack
                + "<br>Checkpoints: " + numOfCheckPoints +
                "<br>"+ (isPlayerGreenTurn ? "Player 1 (red)" : "Player 2 (green)") + "</html>");
    }
    public void onCardRejectionClicked(ActionListener listener) {
        rejectionStack.addActionListener(listener);
    }

    // gridIndex is zero indexed
    private String getPathIconFileName(int gridIndex){
        String fileName = "";
        int pathSize = GameConstants.NUMBER_OF_PATH_CELLS;
        if (gridIndex >= 0 && gridIndex < pathSize) fileName += "knossos";
        else if (gridIndex >= pathSize && gridIndex < 2 * pathSize) fileName += "malia";
        else if (gridIndex >= 2 * pathSize && gridIndex < 3 * pathSize) fileName += "phaistos";
        else if (gridIndex >= 3 * pathSize && gridIndex < 4 * pathSize) fileName += "zakros";
        else throw new IllegalArgumentException();

        if (GameConstants.numOfPositionsWithFindings.contains(gridIndex % 9)) fileName += "2";
        return fileName;
    }
}
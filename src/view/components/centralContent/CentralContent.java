package view.components.centralContent;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * The CentralContent class represents the main central panel in the game UI.
 * It displays the game's paths as a grid, a rejection stack button, and game-related information.
 * The panel is implemented using a {@link JLayeredPane} for flexible component layering.
 */
public class CentralContent extends JLayeredPane {
    private JLabel infoLabel;
    private JPanel pathGrid;
    private JButton rejectionStack;
    private Controller controller;


    /**
     * Constructs the CentralContent panel.
     *
     * @param controller The game controller used to manage interactions with the central content.
     */
    public CentralContent(Controller controller) {
        // Set up the JLayeredPane properties
        setBackground(Color.BLUE);
        setOpaque(true);
        setPreferredSize(new Dimension(800, 600)); // Increase preferred size
        initializePathGrid();
        displayRejectionStackButton();
        initializeInformationDisplay();
    }

    private void initializePathGrid(){
        // Create a JPanel with a GridLayout (4 rows, 9 columns)
        pathGrid = new JPanel(new GridLayout(4, 9, 10, 10)); // 4x9 grid with 10 px gaps
        pathGrid.setOpaque(false); // Keep the blue background of the JLayeredPane visible
        pathGrid.setBounds(800, 90, 950, 420); // Adjust size to ensure square cells

        // Add JLabel components to the grid panel
        for (int i = 1; i <= 36; i++) { // 4x9 = 36 labels
            JLabel label = new JLabel("Label " + i, SwingConstants.CENTER);
            label.setOpaque(true); // Allow background color to be visible
            label.setBackground(Color.WHITE);
            label.setFont(new Font("Arial", Font.BOLD, 20)); // Set larger font size
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Thicker border

            // Force each label to be square
            pathGrid.add(label);
        }

        // Add the grid panel to the JLayeredPane
        add(pathGrid, JLayeredPane.DEFAULT_LAYER);
    }

    private void updatePathGrid(){

    }

    private void displayRejectionStackButton(){

        // Create a new button
        rejectionStack = new JButton("Click Me");
        rejectionStack.setBounds(100, 270, 100, 150); // Set position and size of the button

        // Add action listener to the button
        rejectionStack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.handleRejectionStackClick();
            }
        });
        add(rejectionStack);
    }

    private void initializeInformationDisplay() {
        // Create the JLabel
        infoLabel = new JLabel();
        infoLabel.setBounds(85, 450, 200, 100); // Adjust size to fit multi-line content
        infoLabel.setOpaque(true); // Make the background visible
        infoLabel.setBackground(Color.WHITE);
        infoLabel.setVerticalAlignment(SwingConstants.TOP); // Align text to the top of the label
        infoLabel.setHorizontalAlignment(SwingConstants.LEFT); // Align text to the left of the label

        // Add the JLabel to the container
        setLayout(null); // Set layout manager to null if using absolute positioning
        add(infoLabel);
    }

    // Method to update the displayed information
    private void updateInformation(int cardsLeftInStack, int numOfCheckPoints, boolean isPlayerGreenTurn) {
        // Update the JLabel text
        infoLabel.setText("<html>Cards Left: " + cardsLeftInStack
                + "<br>Checkpoints: " + numOfCheckPoints
                + "<br>Player Green Turn: " + isPlayerGreenTurn + "</html>");
    }
}
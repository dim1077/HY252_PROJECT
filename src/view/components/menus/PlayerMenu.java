package view.components.menus;

import javax.swing.*;
import java.awt.*;

public class PlayerMenu extends JLayeredPane {
    // Components for the left menu
    private JButton[] deckOfCardsButtons; // Array for 9 buttons
    private JLabel availablePawnsLabel;

    // Components for the right menu
    private JLabel[] lastCardInPathLabels; // Array for 4 labels
    private JButton playerFrescoButton;
    private JLabel playerScoreLabel;
    private JLabel frescoBelowLabel;

    public PlayerMenu() {
        // Optional: Initialize components here
        setOpaque(true); // Ensure the JLayeredPane can be painted
//        setPreferredSize(calculatePreferredSize()); // Set initial size
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Set the background to red
        g.setColor(Color.RED);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    // Calculate size as 25% of the screen
    private Dimension calculatePreferredSize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width * 0.20);
        int height = (int) (screenSize.height * 0.20);
        return new Dimension(width, height);
    }

    @Override
    public Dimension getPreferredSize() {
        return calculatePreferredSize();
    }

    // Updates the label for available pawns
    public void updateAvailablePawns(int count) {}

    // Sets a card in the deck to a new state
    public void updateDeckCard(int index, String newLabel) {}

    // Updates the player's score
    public void updateScore(int score) {}

    // Updates the label below the fresco
    public void updateFrescoLabel(String newLabel) {}

    // Updates the last card in a path
    public void updateLastCardInPath(int index, String cardDescription) {}

    // Resets the menu to its initial state
    public void resetMenu() {}

    // Getters for components (if dynamic updates are needed externally)
    public JButton[] getDeckOfCardsButtons() {
        return null;
    }

    public JLabel getAvailablePawnsLabel() {
        return null;
    }

    public JLabel[] getLastCardInPathLabels() {
        return null;
    }

    public JButton getPlayerFrescoButton() {
        return null;
    }

    public JLabel getPlayerScoreLabel() {
        return null;
    }

    public JLabel getFrescoBelowLabel() {
        return null;
    }
}

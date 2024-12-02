package view.components.menus;

import javax.swing.*;
import java.awt.*;

public class PlayerMenu extends JPanel {
    // Components for the left menu
    private JButton[] deckOfCardsButtons; // Array for 9 buttons
    private JLabel availablePawnsLabel;

    // Components for the right menu
    private JLabel[] lastCardInPathLabels; // Array for 4 labels
    private JButton playerFrescoButton;
    private JLabel playerScoreLabel;
    private JLabel frescoBelowLabel;

    public PlayerMenu() {

    }

    private JPanel createLeftMenu() {
        return null;
    }

    private JPanel createRightMenu() {
        return null;
    }

    // Getters for dynamic updates (if needed)
    public JButton[] getDeckOfCardsButtons() {
        return deckOfCardsButtons;
    }

    public JLabel getAvailablePawnsLabel() {
        return availablePawnsLabel;
    }

    public JLabel[] getLastCardInPathLabels() {
        return lastCardInPathLabels;
    }

    public JButton getPlayerFrescoButton() {
        return playerFrescoButton;
    }

    public JLabel getPlayerScoreLabel() {
        return playerScoreLabel;
    }

    public JLabel getFrescoBelowLabel() {
        return frescoBelowLabel;
    }
}

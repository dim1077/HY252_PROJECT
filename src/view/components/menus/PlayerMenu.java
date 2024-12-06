package view.components.menus;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ConcurrentLinkedDeque;

public class PlayerMenu extends JLayeredPane {
    public PlayerMenu() {
        setBackground(Color.RED);
        setOpaque(true);
        setPreferredSize(new Dimension(800, 230));
        displayCardDeck();
    }

    private void displayCardDeck() {
        // Create a panel to hold the buttons
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        buttonPanel.setBounds(0, 0, 1400, 1400); // TODO: the width and height are not correct
        buttonPanel.setOpaque(false);

        // Add buttons to the panel
        for (int cardId = 1; cardId <= 8; cardId++) {
            JButton button = new JButton("Button " + cardId);
            button.setOpaque(true); // Allow background color to be visible
            button.setBackground(Color.WHITE);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            button.setPreferredSize(new Dimension(100, 170));
            buttonPanel.add(button);


            final int buttonIndex = cardId; // Use a final variable to capture the button index
            button.addActionListener(e -> {
                System.out.println("Button " + buttonIndex + " clicked");
                Controller.handleCardClick(cardId);
            });
        }

        // Add the panel to the JLayeredPane
        add(buttonPanel, JLayeredPane.DEFAULT_LAYER);
    }

    private void updateCardDeck(){

    }

    private void availablePawns(){

    }

    private void lastCardInPath(){

    }

    // BoxLayout
    private void playerInformation(){

    }
}

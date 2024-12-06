package view.components.menus;

import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class PlayerMenu extends JLayeredPane {
    private Controller controller;


    /**
     * Constructs a PlayerMenu.
     *
     * @param controller The game controller used to handle player actions.
     */
    public PlayerMenu(Controller controller) {
        this.controller = controller;
        setBackground(Color.RED);
        setOpaque(true);
        setPreferredSize(new Dimension(800, 230));
        displayCardDeck();
    }


    /*
     * Displays the player's card deck in the menu as a series of buttons.
     * Each button represents a card and is clickable to perform an action.
     */
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


            int buttonIndex = cardId; // Use a final variable to capture the button index
            button.addActionListener(e -> {
                System.out.println("Button " + buttonIndex + " clicked");
                controller.onCardInDeckClicked(buttonIndex);
            });
        }

        // Add the panel to the JLayeredPane
        add(buttonPanel, JLayeredPane.DEFAULT_LAYER);
    }

    /**
     * Updates the card deck display in the menu.
     * This method should refresh the card deck visuals based on the current state of the game.
     */
    private void updateCardDeck(){

    }

    /**
     * Displays the available pawns for the player.
     */
    private void availablePawns(){

    }

    /**
     * Displays the last card in the path.
     */
    private void lastCardInPath(){

    }

    /**
     * Displays player information, such as score and remaining moves.
     */
    private void playerInformation(){
        // Box layout
    }


}

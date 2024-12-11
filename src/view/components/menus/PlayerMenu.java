package view.components.menus;

import util.CardName;
import util.GameConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PlayerMenu extends JLayeredPane {
    private CardView[] cardDeck;

    /**
     * Constructs a PlayerMenu.
     */
    public PlayerMenu(CardView[] initialCards) {
        this.cardDeck = initialCards;

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
        buttonPanel.setBounds(0, 0, 1400, 1400); // TODO: adjust dimensions as needed
        buttonPanel.setOpaque(false);

//        // Add cards to the panel
        for (int cardId = 1; cardId <= GameConstants.NUMBER_OF_DECK_CARDS; cardId++) {
//
//
//            CardView card = new CardView(CardName.MINOTAUR_CARD);
//
//            JButton button = card.getButton();
//
//            cardDeck[cardId - 1] = card;

            buttonPanel.add(cardDeck[cardId - 1].getButton());
//
//            int buttonIndex = cardId; // Use a final variable to capture the button index
//            button.addActionListener(e -> {
//                System.out.println("Card " + "bruh" + " clicked");
//                // controller.onCardInDeckClicked();
//            });
        }

        // Add the panel to the JLayeredPane
        add(buttonPanel, JLayeredPane.DEFAULT_LAYER);
    }

    /**
     * Allows setting an ActionListener for a specific card button.
     *
     * @param cardId   The ID of the card (1-8).
     * @param listener The ActionListener to attach to the button.
     */
    public void setCardButtonActionListener(int cardId, ActionListener listener) {
        if (cardId >= 1 && cardId <= cardDeck.length) {
            cardDeck[cardId - 1].getButton().addActionListener(listener);
        } else {
            throw new IllegalArgumentException("Invalid card ID: " + cardId);
        }
    }

    /**
     * Updates the card deck display in the menu.
     * This method should refresh the card deck visuals based on the current state of the game.
     */
    private void updateCardDeck() {
        // Logic for updating card visuals
    }

    /**
     * Displays the available pawns for the player.
     */
    private void availablePawns() {
        // Logic for displaying pawns
    }

    /**
     * Displays the last card in the path.
     */
    private void lastCardInPath() {
        // Logic for displaying the last card
    }

    /**
     * Displays player information, such as score and remaining moves.
     */
    private void playerInformation() {
        // Box layout or other UI logic for player information
    }
}

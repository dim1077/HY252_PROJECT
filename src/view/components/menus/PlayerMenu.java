package view.components.menus;

import controller.Controller;
import controller.GameButtonClickListener;
import model.players.Player;
import util.CardName;
import util.GameConstants;
import util.PlayerName;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerMenu extends JLayeredPane {
    private CardView[] cardDeck;
    private GameButtonClickListener cardClickListener;
    private PlayerName playerName; // whose menu this is
    boolean cardsClickable;

    /**
     * Constructs a PlayerMenu.
     */
    public PlayerMenu(CardView[] initialCards, GameButtonClickListener cardClickListener, PlayerName playerName, boolean cardsClickable) {
        this.playerName = playerName;
        this.cardDeck = initialCards;
        this.cardsClickable = cardsClickable;
        this.cardClickListener = cardClickListener;

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
        removeAll();

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        buttonPanel.setBounds(0, 0, 1400, 1400);
        buttonPanel.setOpaque(false);

        for (int cardIdx = 0; cardIdx < cardDeck.length; cardIdx++) {
            JButton currentButton = cardDeck[cardIdx].getButton();
            buttonPanel.add(currentButton);

            final int index = cardIdx;
            currentButton.addActionListener(e -> {
                cardClickListener.onCardInDeckClicked(cardDeck, index, playerName);
            });
        }

        add(buttonPanel, JLayeredPane.DEFAULT_LAYER);

        setButtonsClickable(cardsClickable);
        // Refreshes the UI
        revalidate();
        repaint();
    }


    public void updatePlayerMenu(int cardIdx, CardView newCard) {
        // Update the card in the data model
        cardDeck[cardIdx] = newCard;

        // Locate the button panel
        Component[] components = getComponents();
        JPanel buttonPanel = null;
        for (Component component : components) {
            if (component instanceof JPanel) {
                buttonPanel = (JPanel) component;
                break;
            }
        }

        if (buttonPanel != null) {
            // Replace the specific button in the panel
            JButton newButton = newCard.getButton();
            buttonPanel.remove(cardIdx); // Remove old button at the index
            buttonPanel.add(newButton, cardIdx); // Add new button at the same index

            // Add listener to the new button
            newButton.addActionListener(e -> cardClickListener.onCardInDeckClicked(cardDeck, cardIdx, playerName));

            // Refresh the UI
            buttonPanel.revalidate();
            buttonPanel.repaint();
        }
    }

    public void setButtonsClickable(boolean clickable) {
        for (CardView card : cardDeck) {
            JButton button = card.getButton();
            button.setEnabled(clickable);
        }

        // Refresh the UI
        revalidate();
        repaint();
    }


    /**
     * Updates the card deck display in the menu.
     * This method should refresh the card deck visuals based on the current state of the game.
     */
    @Deprecated // You should use updatePlayerMenu instead
    public void updateCardInDeck(int cardIdx, CardView card) {
        cardDeck[cardIdx] = card;
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

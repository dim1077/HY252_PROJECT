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

    /**
     * Constructs a PlayerMenu.
     */
    public PlayerMenu(CardView[] initialCards, GameButtonClickListener cardClickListener, PlayerName playerName) {
        this.playerName = playerName;
        this.cardDeck = initialCards;
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
        // Create a panel to hold the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        buttonPanel.setBounds(0, 0, 1400, 1400); // TODO: adjust dimensions as needed
        buttonPanel.setOpaque(false);

//        // Add cards to the panel
        for (int cardId = 1; cardId <= GameConstants.NUMBER_OF_DECK_CARDS; cardId++) {
//
            int cardIdx = cardId - 1;
//            CardView card = new CardView(CardName.MINOTAUR_CARD);
//
//            JButton button = card.getButton();
//
//            cardDeck[cardId - 1] = card;
            JButton currentButton = cardDeck[cardIdx].getButton();


            buttonPanel.add(currentButton);

//
//            int buttonIndex = cardId; // Use a final variable to capture the button index
            currentButton.addActionListener(e -> {
                cardClickListener.onCardInDeckClicked(cardDeck, cardIdx, playerName);
            });
        }

        // Add the panel to the JLayeredPane
        add(buttonPanel, JLayeredPane.DEFAULT_LAYER);
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

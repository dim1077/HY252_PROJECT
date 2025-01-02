package view.components.menus;

import controller.GameButtonClickListener;
import util.GameConstants;
import util.PlayerName;

import javax.swing.*;
import java.awt.*;


public class PlayerMenu extends JLayeredPane {
    private CardView[] cardDeck;
    private GameButtonClickListener cardClickListener;
    private PlayerName playerName; // whose menu this is
    private JPanel cardDeckPanel;     // Main card deck panel
    private JPanel lastCardsPanel;   // Panel for displaying last cards
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
        setPreferredSize(new Dimension((int)(0.41 * GameConstants.WIDTH), (int)(0.21 * GameConstants.HEIGHT))); // the coefficients where arbitrarily chosen according to my likings
        initializeCardDeck();
        initializeLastCardLabels();
//        displayLastCards(new CardView[]{null, null, null, null});
    }

    /*
     * Displays the player's card deck in the menu as a series of buttons.
     * Each button represents a card and is clickable to perform an action.
     */
    private void initializeCardDeck() {
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


    private void initializeLastCardLabels(){

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

    public void displayLastCards(CardView[] lastCards) {
        // Remove any existing components from the lower layer (if needed)
        removeAll(); // Clears the previous display

        // Create a panel for the last cards
        JPanel lastCardsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        lastCardsPanel.setBounds(100, 160, 1400, 140); // Positioned below the main deck
        lastCardsPanel.setOpaque(false);

        // Iterate through the last cards and add them as buttons
        for (CardView card : lastCards) {
            JButton button = card != null ? card.getButton() : new JButton(); // Empty button if null
            button.setEnabled(false); // Make cards non-clickable
            lastCardsPanel.add(button); // Add button to the panel
        }

        // Add the panel to the layered pane
        add(lastCardsPanel, JLayeredPane.DEFAULT_LAYER);

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

package view.components.playerMenu;

import controller.listeners.GameButtonClickListener;
import util.PlayerName;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


/**
 * Represents a panel displaying the current deck of cards for a player.
 * The panel manages card interactions and updates dynamically based on game actions.
 */
public class CardDeckPanel extends JPanel {
    private CardView[] currentCards;
    private GameButtonClickListener cardClickListener;
    private PlayerName playerName;


    /**
     * Constructs a CardDeckPanel with an initial set of cards.
     *
     * @param initialCards       The initial set of cards for the deck.
     * @param cardClickListener  The listener for card click events.
     * @param playerName         The name of the player who owns this deck.
     */
    public CardDeckPanel(CardView[] initialCards, GameButtonClickListener cardClickListener, PlayerName playerName) {
        this.currentCards = initialCards;
        this.cardClickListener = cardClickListener;
        this.playerName = playerName;
        assert(currentCards.length == 8);

        setLayout(new FlowLayout());

        initializeCardDeck();
    }

    private void initializeCardDeck(){
        removeAll();

        for (int cardIdx = 0; cardIdx < currentCards.length; cardIdx++) {
            JButton currentButton = currentCards[cardIdx].getButton();
            add(currentButton);

            final int index = cardIdx;
            currentButton.addActionListener(e -> {
                cardClickListener.onCardInDeckClicked(currentCards, index, playerName);
            });
        }

        revalidate();
        repaint();
    }

    /**
     * Updates a specific card in the deck and refreshes the panel.
     *
     * @param cardIdx The index of the card to be updated.
     * @param newCard The new card to replace the existing card.
     */
    public void updateCardDeck(int cardIdx, CardView newCard) {

        currentCards[cardIdx] = newCard;

        removeAll();

        // Rebuild the card deck
        for (int i = 0; i < currentCards.length; i++) {
            JButton currentButton = currentCards[i].getButton();

            // Remove all existing action listeners to avoid duplicates
            for (ActionListener al : currentButton.getActionListeners()) {
                currentButton.removeActionListener(al);
            }

            final int index = i;
            currentButton.addActionListener(e -> {
                cardClickListener.onCardInDeckClicked(currentCards, index, playerName);
            });

            add(currentButton);
        }

        // Refresh the UI
        revalidate();
        repaint();
    }

    /**
     * Sets the clickable state of all buttons in the deck.
     *
     * @param clickable True to enable buttons, false to disable them.
     */
    public void setButtonsClickable(boolean clickable) {
        for (CardView card : currentCards) {
            JButton button = card.getButton();
            button.setEnabled(clickable);
        }

        revalidate();
        repaint();
    }
}

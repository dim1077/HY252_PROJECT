package view.componentT.playerMenuT;

import controller.GameButtonClickListener;
import util.PlayerName;
import view.components.menus.CardView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CardDeckPanel extends JPanel {
    private CardView[] currentCards;
    private GameButtonClickListener cardClickListener;
    private PlayerName playerName;



    public CardDeckPanel(CardView[] initialCards, GameButtonClickListener cardClickListener, PlayerName playerName) {
        this.currentCards = initialCards;
        this.cardClickListener = cardClickListener;
        this.playerName = playerName;
        assert(currentCards.length == 8);

        setLayout(new FlowLayout());
        setBackground(Color.LIGHT_GRAY);

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

    public void updateCardDeck(int cardIdx, CardView newCard) {
        // Update the card in the data model
        currentCards[cardIdx] = newCard;

        // Clear the panel visually
        removeAll();

        // Rebuild the card deck
        for (int i = 0; i < currentCards.length; i++) {
            JButton currentButton = currentCards[i].getButton();

            // Remove all existing action listeners to avoid duplicates
            for (ActionListener al : currentButton.getActionListeners()) {
                currentButton.removeActionListener(al);
            }

            // Attach the new listener
            final int index = i; // Capture index for listener
            currentButton.addActionListener(e -> {
                cardClickListener.onCardInDeckClicked(currentCards, index, playerName);
            });

            // Add the button back to the panel
            add(currentButton);
        }

        // Refresh the UI
        revalidate();
        repaint();
    }


    public void setButtonsClickable(boolean clickable) {
        for (CardView card : currentCards) {
            JButton button = card.getButton();
            button.setEnabled(clickable);
        }

        // Refresh the UI
        revalidate();
        repaint();
    }
}

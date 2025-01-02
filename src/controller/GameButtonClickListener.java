package controller;


import util.PlayerName;
import view.components.menus.CardView;

/**
 * This interface defines the listener for various button click events in the game.
 * Implementations of this interface handle the specific actions to be taken
 * when game-related buttons or elements are clicked.
 */
public interface GameButtonClickListener {

    /**
     * Triggered when the "Reject Card" button is clicked.
     */
    void onCardRejectionClicked();

    /**
     * Triggered when a card in the deck is clicked.
     */
    void onCardInDeckClicked(CardView[] cardView, int cardIdx, PlayerName playerName);

    /**
     * Triggered when the "Give Up" button is clicked.
     */
    void onGiveUpClicked();
}

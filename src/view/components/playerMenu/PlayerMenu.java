package view.components.playerMenu;

import controller.listeners.GameButtonClickListener;
import util.*;

import javax.swing.*;
import java.util.List;
import java.awt.*;

/**
 * Represents the player menu UI, which contains the card deck, last cards played,
 * player information, and available pawns.
 */

public class PlayerMenu extends JLayeredPane {
    private CardDeckPanel cardDeckPanel;
    private LastCardsPanel lastCardsPanel;
    private InformationPanel informationPanel;
    private PlayerName playerName;
    private JLabel availablePawnsLabel;
    private GameButtonClickListener cardClickListener;


    /**
     * Constructs a PlayerMenu for a given player.
     *
     * @param initialCards     The initial set of cards in the player's deck.
     * @param cardClickListener The listener to handle card interactions.
     * @param playerName       The name of the player.
     */
    public PlayerMenu(CardView[] initialCards, GameButtonClickListener cardClickListener, PlayerName playerName) {
        setLayout(null);
        setBorder(BorderFactory.createLineBorder(GameConstants.COLOR_FOR_EACH_PLAYER.get(playerName), 2));

        this.playerName = playerName;
        this.cardClickListener = cardClickListener;
        // Card deck
        cardDeckPanel = new CardDeckPanel(initialCards, cardClickListener, playerName);
        cardDeckPanel.setBounds(10, 10, 450, 80);
        add(cardDeckPanel);

        lastCardsPanel = new LastCardsPanel();
        lastCardsPanel.setBounds(480, 10, 300, 98); // (98 and not 100 because I need 2px for the borderline)
        add(lastCardsPanel);

        availablePawnsLabel = new JLabel("");
        availablePawnsLabel.setBounds(10, 95, 450, 15);
        availablePawnsLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        add(availablePawnsLabel);
        showAvailablePawns(new int[]{0, 0});

        informationPanel = new InformationPanel(0, 0);
        informationPanel.setBounds(800, 10, 140, 98);
        add(informationPanel);

        setPreferredSize(new Dimension(800, 110));
    }

    /**
     * Enables or disables the player's card deck buttons.
     *
     * @param clickable True to enable the buttons, false to disable.
     */
    public void setDeckButtonsClickable(boolean clickable){
        cardDeckPanel.setButtonsClickable(clickable);
    }


    /**
     * Updates the player's menu with new data, including the card deck, last cards played,
     * player information, and available pawns.
     *
     * @param cardIdx         The index of the card to update in the card deck.
     * @param newCard         The new card to replace the existing one.
     * @param lastCards       The array of the last cards played for each path.
     * @param rareFindingName The name of the rare finding discovered (if any).
     * @param rareFindingPathName The path where the rare finding was discovered.
     * @param pawnsUsed       Array representing the number of pawns used (Theseus and Archeologists).
     * @param newScore        The player's updated score.
     * @param newStatuesNum   The player's updated number of Snake Goddess statues.
     * @param newFrescoes     List of frescoes collected by the player.
     */
    // TODO: simplify this function: too many arguments!
    public void updatePlayerMenu(int cardIdx, CardView newCard, CardView[] lastCards, FindingName rareFindingName, PathName rareFindingPathName, int[] pawnsUsed, int newScore, int newStatuesNum, List<FindingName>  newFrescoes) {
        cardDeckPanel.updateCardDeck(cardIdx, newCard);
        lastCardsPanel.updateLastCards(lastCards, rareFindingName, rareFindingPathName);
        informationPanel.updateInformationPanel(newScore, newStatuesNum, newFrescoes);
        showAvailablePawns(pawnsUsed);
    }

    private void showAvailablePawns(int[] pawnsUsed){
        int numOfTheseus = pawnsUsed[PawnName.THESEUS.getValue()];
        int numOfArcheologist = pawnsUsed[PawnName.ARCHEOLOGIST.getValue()];

        int availableTheseus = GameConstants.NUMBER_OF_THESEUS - numOfTheseus;
        int availableArcheologist = GameConstants.NUMBER_OF_ARCHEOLOGIST - numOfArcheologist;

        String availablePawnsDesc = playerName.toString() + '-' + "Available pawns: " + availableTheseus + " Theseus and " + availableArcheologist + " archeologists";
        availablePawnsLabel.setText(availablePawnsDesc);
    }
}

package view.componentT.playerMenuT;

import controller.GameButtonClickListener;
import util.*;
import view.components.menus.CardView;

import javax.swing.*;
import java.util.List;
import java.awt.*;

public class PlayerMenuT extends JLayeredPane {
    private CardDeckPanel cardDeckPanel;
    private LastCardsPanel lastCardsPanel;
    private InformationPanel informationPanel;
    private PlayerName playerName;
    private JLabel availablePawnsLabel;

    private GameButtonClickListener cardClickListener;



    public PlayerMenuT(CardView[] initialCards, GameButtonClickListener cardClickListener, PlayerName playerName) {
        setLayout(null); // Null layout for manual positioning
        setBorder(BorderFactory.createLineBorder(GameConstants.COLOR_FOR_EACH_PLAYER.get(playerName), 2));

        this.playerName = playerName;
        this.cardClickListener = cardClickListener;
        // Card deck
        cardDeckPanel = new CardDeckPanel(initialCards, cardClickListener, playerName);
        cardDeckPanel.setBounds(10, 10, 450, 80); // x, y, width, height
        add(cardDeckPanel);

        lastCardsPanel = new LastCardsPanel();
        lastCardsPanel.setBounds(480, 10, 300, 98); // (98 and not 100 because I need 2px for the borderline)
        add(lastCardsPanel);

        availablePawnsLabel = new JLabel("");
        availablePawnsLabel.setBounds(10, 95, 450, 15); // Positioned below cardDeckPanel
        availablePawnsLabel.setFont(new Font("Arial", Font.PLAIN, 12)); // Optional styling
        add(availablePawnsLabel); // Add label to panel
        showAvailablePawns(new int[]{0, 0});

        informationPanel = new InformationPanel(0, 0);
        informationPanel.setBounds(800, 10, 140, 98);
        add(informationPanel);

        // Explicit size for PlayerMenuT
        setPreferredSize(new Dimension(800, 110)); // Size defined explicitly
    }

    public void setDeckButtonsClickable(boolean clickable){
        cardDeckPanel.setButtonsClickable(clickable);
    }

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

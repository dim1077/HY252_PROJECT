package view.components.menus;

import util.CardName;
import util.PathName;

import javax.swing.*;
import java.awt.*;

public class CardView {
    private final CardName cardName;
    private final PathName pathName;
    private final ImageIcon cardIcon;
    private JButton button;
    private String number;

    public CardView(CardName cardName, PathName pathName) {
        this(cardName, pathName, null);
    }

    public CardView(CardName cardName, PathName pathName, String number) {
        this.cardName = cardName;
        this.pathName = pathName;
        this.number = number;


        // Create a card icon (placeholder for now)
        String iconFileName = getCardIconFileName();
        cardIcon = new ImageIcon("src/assets/images/cards/" + iconFileName);
        button = new JButton("Button " , cardIcon);
        button.setOpaque(true);
        button.setBackground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        button.setPreferredSize(new Dimension(100, 170));
    }

    public CardName getCardName() {
        return cardName;
    }
    public ImageIcon getCardIcon() {
        return cardIcon;
    }

    public JButton getButton() {
        return button;
    }

    public String getCardIconFileName() {
        if (cardName == CardName.NUMBER_CARD && number == null) throw new IllegalArgumentException();

        String fileName = "";
        if (pathName == PathName.KNOSSOS_PATH) fileName += "knossos";
        else if (pathName == PathName.PHAISTOS_PATH) fileName += "phaistos";
        else if (pathName == PathName.MALIA_PATH) fileName += "malia";
        else if (pathName == PathName.ZAKROS_PATH) fileName += "zakros";
        else throw new IllegalArgumentException();

        if (cardName == CardName.NUMBER_CARD) fileName += number;
        else if (cardName == CardName.MINOTAUR_CARD) fileName += "Min";
        else if (cardName == CardName.ARIADNE_CARD) fileName += "Ari";
        else throw new IllegalArgumentException();

        fileName += ".jpg";
        return fileName;
    }
}

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

        // Load and scale the card icon
        String iconFileName = getCardIconFileName();
        ImageIcon rawIcon = new ImageIcon("src/assets/images/cards/" + iconFileName);
        Image scaledImage = rawIcon.getImage().getScaledInstance(80, 120, Image.SCALE_SMOOTH);
        cardIcon = new ImageIcon(scaledImage);

        // Create the button with only the icon
        button = new JButton(cardIcon);
        button.setOpaque(true);
        button.setBackground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        button.setPreferredSize(new Dimension(90, 130));

        // Remove any default padding for text
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.CENTER);
    }

    public CardName getCardName() {
        return cardName;
    }
    public ImageIcon getCardIcon() {
        return cardIcon;
    }

    public PathName getPathName() {
        return pathName;
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

    public String getNumber() {
        return number;
    }
}

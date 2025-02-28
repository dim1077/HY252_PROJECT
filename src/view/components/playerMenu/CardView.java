package view.components.playerMenu;

import util.CardName;
import util.PathName;

import javax.swing.*;
import java.awt.*;


/**
 * Represents a visual representation of a card in the player's menu.
 * A CardView encapsulates the card's attributes, including its name, path,
 * icon, and a clickable button.
 */
public class CardView {
    private final CardName cardName;
    private final PathName pathName;
    private final ImageIcon cardIcon;
    private JButton button;
    private String number;


    /**
     * Constructs a CardView for a card without a specific number (e.g., Minotaur or Ariadne card).
     *
     * @param cardName The name of the card.
     * @param pathName The path associated with the card.
     */
    public CardView(CardName cardName, PathName pathName) {
        this(cardName, pathName, null);
    }

    /**
     * Constructs a CardView for a numbered card (e.g., NumberCard).
     *
     * @param cardName The name of the card.
     * @param pathName The path associated with the card.
     * @param number   The number associated with the card, if applicable.
     */
    public CardView(CardName cardName, PathName pathName, String number) {
        this.cardName = cardName;
        this.pathName = pathName;
        this.number = number;

        String iconFileName = getCardIconFileName();
        ImageIcon rawIcon = new ImageIcon("src/assets/images/cards/" + iconFileName);
        Image scaledImage = rawIcon.getImage().getScaledInstance(40, 60, Image.SCALE_SMOOTH);
        cardIcon = new ImageIcon(scaledImage);

        // Create the button with only the icon
        button = new JButton(cardIcon);
        button.setOpaque(true);
        button.setBackground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        button.setPreferredSize(new Dimension(45, 65));

        // Remove any default padding for text
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.CENTER);
    }

    /**
     * Retrieves the name of the card.
     *
     * @return The card's name.
     */
    public CardName getCardName() {
        return cardName;
    }

    /**
     * Retrieves the icon of the card.
     *
     * @return The card's icon as an ImageIcon.
     */
    public ImageIcon getCardIcon() {
        return cardIcon;
    }

    /**
     * Retrieves the path name associated with the card.
     *
     * @return The card's associated path name.
     */
    public PathName getPathName() {
        return pathName;
    }


    /**
     * Retrieves the button representing the card.
     *
     * @return The button component of the card.
     */
    public JButton getButton() {
        return button;
    }


    /**
     * Constructs the filename for the card's icon based on its card type, path, and number.
     *
     * @return A string representing the file name of the card's icon.
     * @throws IllegalArgumentException if the card type or path is invalid.
     */
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


    /**
     * Retrieves the number associated with the card, if applicable.
     *
     * @return The card's number as a string, or null if no number is associated.
     */
    public String getNumber() {
        return number;
    }
}
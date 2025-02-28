package view.components.playerMenu;

import util.FindingName;
import util.FindingsInfo;
import util.GameConstants;
import util.PathName;

import javax.swing.*;
import java.awt.*;


/**
 * Represents a panel that displays the last cards played for each path
 * along with any rare items found on that path.
 */
public class LastCardsPanel extends JPanel {
    private CardView[] lastCards;
    private JPanel[] cardContainers;
    private FindingName[] rareItemsFound;
    final int numOfLastCards = 4;


    /**
     * Constructs a LastCardsPanel with placeholders for each path's last card and rare items.
     */
    public LastCardsPanel() {
        lastCards = new CardView[]{null, null, null, null};

        cardContainers = new JPanel[numOfLastCards];
        rareItemsFound = new FindingName[numOfLastCards];

        setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
//        setBackground(Color.LIGHT_GRAY);

        initializeLastCards();
    }

    private void initializeLastCards() {
        for (PathName path : PathName.values()) {
            addEmptyCard(path);
        }
    }


    /**
     * Updates the last cards played and the rare items found for each path.
     *
     * @param newLastCards      Array of the new last cards played for each path.
     * @param rareFindingFound  The rare finding discovered, if any.
     * @param rareFindingPathName The path where the rare finding was discovered.
     */
    public void updateLastCards(CardView[] newLastCards, FindingName rareFindingFound, PathName rareFindingPathName) {
        this.lastCards = newLastCards;
        this.rareItemsFound[rareFindingPathName.getValue()] = rareFindingFound;

        for (PathName path : PathName.values()) {
            JPanel cardContainer = cardContainers[path.getValue()];
            cardContainer.removeAll();

            JLabel cardImage;
            if (lastCards[path.getValue()] != null) {
                String filePath = "src/assets/images/cards/" + lastCards[path.getValue()].getCardIconFileName();
                ImageIcon rawIcon = new ImageIcon(filePath);
                Image scaledImage = rawIcon.getImage().getScaledInstance(45, 65, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);

                cardImage = new JLabel(scaledIcon);
                cardImage.setBorder(BorderFactory.createLineBorder(GameConstants.Paths.COLOR_FOR_EACH_PATH.get(path), 2));

            } else {
                // Placeholder for missing cards
                cardImage = new JLabel(path.toString(), SwingConstants.CENTER);
                cardImage.setFont(new Font("Arial", Font.PLAIN, 5)); // Smaller font size


                cardImage.setOpaque(true);
                cardImage.setBackground(Color.WHITE);
                cardImage.setBorder(BorderFactory.createLineBorder(GameConstants.Paths.COLOR_FOR_EACH_PATH.get(path), 2));
            }

            cardImage.setPreferredSize(new Dimension(45, 65));
            cardImage.setHorizontalAlignment(SwingConstants.CENTER);
            cardContainer.add(cardImage, BorderLayout.CENTER);

            JLabel iconLabel;
            if (rareItemsFound[path.getValue()] != null) {
                String iconFilePath = FindingsInfo.getFindingImagePath(rareItemsFound[path.getValue()]);
                ImageIcon rawIcon = new ImageIcon(iconFilePath);
                Image scaledImage = rawIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                iconLabel = new JLabel(scaledIcon);
            } else {
                iconLabel = new JLabel("?", SwingConstants.CENTER);
            }

            iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
            iconLabel.setPreferredSize(new Dimension(45, 20));
            cardContainer.add(iconLabel, BorderLayout.SOUTH);

            cardContainer.revalidate();
            cardContainer.repaint();
        }
    }



    private void addEmptyCard(PathName pathName){
        JPanel cardContainer = new JPanel(new BorderLayout());

        // Placeholder for missing cards
        JLabel placeholderCard = new JLabel(pathName.toString(), SwingConstants.CENTER);
        placeholderCard.setBorder(BorderFactory.createLineBorder(GameConstants.Paths.COLOR_FOR_EACH_PATH.get(pathName), 2));

        placeholderCard.setOpaque(true);
        placeholderCard.setBackground(Color.WHITE);
        placeholderCard.setBorder(BorderFactory.createLineBorder(GameConstants.Paths.COLOR_FOR_EACH_PATH.get(pathName), 2));
        placeholderCard.setPreferredSize(new Dimension(45, 65));
        placeholderCard.setFont(new Font("Arial", Font.PLAIN, 5));

        cardContainer.add(placeholderCard, BorderLayout.CENTER);

        JLabel placeholderIcon = new JLabel("?", SwingConstants.CENTER);
        cardContainer.add(placeholderIcon, BorderLayout.SOUTH);//

        add(cardContainer);
        cardContainers[pathName.getValue()] = cardContainer;
    }

}

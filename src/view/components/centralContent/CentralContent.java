package view.components.centralContent;
import controller.listeners.GameButtonClickListener;
import util.GameConstants;
import util.PathName;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;


/**
 * CentralContent represents the main area of the game board,
 * displaying paths, the rejection stack button, game status information, and background.
 */
public class CentralContent extends JLayeredPane {
    private JPanel pathGridPanel;
    private JLabel backgroundLabel;
    private JButton rejectionStack;
    private JLabel infoLabel;
    private GameButtonClickListener cardClickListener;



    /**
     * Constructs the CentralContent area of the game.
     *
     * @param availableCards   Number of available cards in the deck.
     * @param checkPoints      Number of checkpoints passed in the game.
     * @param isPlayerOnesTurn Indicates whether it is player one's turn.
     * @param cardClickListener Listener to handle game button clicks.
     */
    public CentralContent(int availableCards, int checkPoints, boolean isPlayerOnesTurn, GameButtonClickListener cardClickListener) {
        this.cardClickListener = cardClickListener;

        setLayout(null);

        initializeBackground();


        initializeInformationDisplay(availableCards, checkPoints, isPlayerOnesTurn);
        displayRejectionStackButton();

        initializePathGridPanel();
    }

    private void initializePathGridPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setOpaque(false);
        mainPanel.setBounds(350, 25, 600, 250);

        pathGridPanel = new JPanel(new GridLayout(4, 1, 3, 3));
        pathGridPanel.setOpaque(false);

        pathGridPanel.add(new PathPanel(PathName.KNOSSOS_PATH));
        pathGridPanel.add(new PathPanel(PathName.MALIA_PATH));
        pathGridPanel.add(new PathPanel(PathName.PHAISTOS_PATH));
        pathGridPanel.add(new PathPanel(PathName.ZAKROS_PATH));

        mainPanel.add(pathGridPanel, BorderLayout.CENTER);
        add(mainPanel, JLayeredPane.PALETTE_LAYER);
        addPointsLabelsAbsolute();
    }


    private void addPointsLabelsAbsolute() {
        String[] points = new String[GameConstants.Paths.NUMBER_OF_PATH_CELLS];
        for (int i = 0; i < GameConstants.Paths.NUMBER_OF_PATH_CELLS; i++) {
            points[i] = "<html>" + GameConstants.Rewards.REWARD_PATH_FOR_ITH_CELL[i] + " points" +
                    (i == GameConstants.Paths.CHECKPOINT_INDEX ? "<br>checkpoint!" : "") + "</html>";
        }

        int baseX = 350;
        int baseY = 10;
        for (int i = 0; i < points.length; i++) {
            JLabel label = new JLabel(points[i], SwingConstants.CENTER);
            label.setForeground(Color.BLACK);
            label.setFont(new Font("Arial", Font.BOLD, 8));

            int xPos = baseX + i * 55;
            label.setBounds(xPos, baseY, 60, 20);

            add(label, JLayeredPane.PALETTE_LAYER);
        }
    }



    /**
     * Returns the panel containing the paths grid.
     *
     * @return The path grid panel.
     */
    public JPanel getPathGrid(){
        return pathGridPanel;
    }

    /**
     * Returns a specific path panel by its index.
     * This method is deprecated; prefer using {@link #getPathPanel(PathName)}.
     *
     * @param index The index of the desired path panel.
     * @return The path panel at the specified index.
     */
    @Deprecated // the user of this API should not know about the indexes
    public PathPanel getPathPanel(int index){
        return (PathPanel) pathGridPanel.getComponent(index);
    }


    /**
     * Returns a specific path panel by its name.
     *
     * @param pathName The name of the desired path.
     * @return The path panel corresponding to the given name.
     * @throws IllegalArgumentException If the specified path name is not found.
     */
    public PathPanel getPathPanel(PathName pathName) {
        for (Component component : pathGridPanel.getComponents()) {
            if (component instanceof PathPanel pathPanel) {
                if (pathPanel.getPathName().equals(pathName)) {
                    return pathPanel;
                }
            }
        }
        throw new IllegalArgumentException("Path not found: " + pathName);
    }

    private void displayRejectionStackButton(){
        ImageIcon originalIcon = new ImageIcon("src/assets/images/cards/backCard.jpg");


        int desiredWidth = 50;
        int desiredHeight = 75;

        Image scaledImage = originalIcon.getImage()
                .getScaledInstance(desiredWidth, desiredHeight,
                        Image.SCALE_SMOOTH);

        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        rejectionStack = new JButton(scaledIcon);
        rejectionStack.setBounds(50, 125, 50, 75);


        rejectionStack.addActionListener(e -> cardClickListener.onCardRejectionClicked());
        add(rejectionStack, JLayeredPane.PALETTE_LAYER);
    }

    private void initializeBackground() {
        ImageIcon originalIcon = new ImageIcon("src/assets/images/background.jpg");

        int desiredWidth = 960;
        int desiredHeight = 280;

        Image scaledImage = originalIcon.getImage()
                .getScaledInstance(desiredWidth, desiredHeight,
                        Image.SCALE_SMOOTH);

        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        backgroundLabel = new JLabel(scaledIcon);

        backgroundLabel.setBounds(0, 0, desiredWidth, desiredHeight);

        add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);
    }



    private void initializeInformationDisplay(int availableCards, int checkPoints, boolean isPlayerOnesTurn) {
        infoLabel = new JLabel();
        infoLabel.setBounds(50, 200, 100, 45);
        infoLabel.setOpaque(true);
        infoLabel.setBackground(Color.WHITE);
        infoLabel.setBorder(new LineBorder(Color.BLACK));
        infoLabel.setVerticalAlignment(SwingConstants.TOP);
        infoLabel.setHorizontalAlignment(SwingConstants.LEFT);

        infoLabel.setText("<html>Available cards: " + availableCards + ".<br>Check points: " + checkPoints + ".<br>Turn: " + (isPlayerOnesTurn ? "Player 1 (red)" : "Player 2 (green)") + ".</html>");
        infoLabel.setSize(infoLabel.getPreferredSize());

        add(infoLabel, JLayeredPane.PALETTE_LAYER);
    }

    /**
     * Updates the information display with the current game state.
     *
     * @param cardsLeftInStack Number of cards left in the deck.
     * @param numOfCheckPoints Number of checkpoints passed.
     * @param isPlayerGreenTurn Indicates whether it is player green's turn.
     */
    public void updateInformation(int cardsLeftInStack, int numOfCheckPoints, boolean isPlayerGreenTurn) {
        infoLabel.setText("<html>Cards Left: " + cardsLeftInStack
                + "<br>Checkpoints: " + numOfCheckPoints +
                "<br>"+ (isPlayerGreenTurn ? "Player 1 (red)" : "Player 2 (green)") + "</html>");
    }
}

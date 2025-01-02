package view.componentT.centralContentT;
import controller.GameButtonClickListener;
import util.GameConstants;
import util.PathName;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class CentralContentT extends JLayeredPane {
    private JPanel pathGridPanel;
    private JLabel backgroundLabel;
    private JButton rejectionStack;
    private JLabel infoLabel;
    private GameButtonClickListener cardClickListener;





    public CentralContentT(int availableCards, int checkPoints, boolean isPlayerOnesTurn, GameButtonClickListener cardClickListener) {
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
        mainPanel.setBounds(350, 38, 600, 250);

        // We no longer add pointsLabelPanel in the NORTH section
        // Instead, we'll just add the pathGridPanel in the CENTER

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
        String[] points = new String[GameConstants.NUMBER_OF_PATH_CELLS];
        for (int i = 0; i < GameConstants.NUMBER_OF_PATH_CELLS; i++) {
            points[i] = "<html>" + GameConstants.REWARD_PATH_FOR_ITH_CELL[i] + " points" +
                    (i == GameConstants.checkPointIdx ? "<br>checkpoint!" : "") + "</html>";
        }

        int baseX = 350;
        int baseY = 20;
        for (int i = 0; i < points.length; i++) {
            JLabel label = new JLabel(points[i], SwingConstants.CENTER);
            label.setForeground(Color.BLACK);
            label.setFont(new Font("Arial", Font.BOLD, 8));

            int xPos = baseX + i * 55;
            label.setBounds(xPos, baseY, 60, 20);

            add(label, JLayeredPane.PALETTE_LAYER);
        }
    }




    public JPanel getPathGrid(){
        return pathGridPanel;
    }

    @Deprecated // the user of this API should not know about the indexes
    public PathPanel getPathPanel(int index){
        return (PathPanel) pathGridPanel.getComponent(index);
    }

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
        rejectionStack = new JButton("Click Me");
        rejectionStack.setBounds(50, 125, 50, 75); // Set position and size of the button


        rejectionStack.addActionListener(e -> cardClickListener.onCardRejectionClicked());
        add(rejectionStack, JLayeredPane.PALETTE_LAYER);
    }

    private void initializeBackground() {
        // 1. Load the original image icon
        ImageIcon originalIcon = new ImageIcon("src/assets/images/background.jpg");

        // 2. Decide on the width & height you want to scale to.
        //    You can hardcode them or use your panel/frame dimensions.
        int desiredWidth = 960;   // example width
        int desiredHeight = 280;  // example height

        // 3. Extract the raw Image and scale it
        Image scaledImage = originalIcon.getImage()
                .getScaledInstance(desiredWidth, desiredHeight,
                        Image.SCALE_SMOOTH);

        // 4. Create a new ImageIcon from the scaled Image
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        // 5. Create a JLabel with the scaled icon
        backgroundLabel = new JLabel(scaledIcon);

        // 6. Set the bounds of the label to match the scaled dimensions
        backgroundLabel.setBounds(0, 0, desiredWidth, desiredHeight);

        // 7. Add the background label to your panel or layered pane
        add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);
    }



    private void initializeInformationDisplay(int availableCards, int checkPoints, boolean isPlayerOnesTurn) {
        // Create the JLabel
        infoLabel = new JLabel();
        infoLabel.setBounds(50, 200, 100, 45); // Adjust size to fit multi-line content
        infoLabel.setOpaque(true); // Make the background visible
        infoLabel.setBackground(Color.WHITE);
        infoLabel.setBorder(new LineBorder(Color.BLACK));
        infoLabel.setVerticalAlignment(SwingConstants.TOP); // Align text to the top of the label
        infoLabel.setHorizontalAlignment(SwingConstants.LEFT); // Align text to the left of the label

        // Set text with each sentence on a new line
        infoLabel.setText("<html>Available cards: " + availableCards + ".<br>Check points: " + checkPoints + ".<br>Turn: " + (isPlayerOnesTurn ? "Player 1 (red)" : "Player 2 (green)") + ".</html>");
        infoLabel.setSize(infoLabel.getPreferredSize()); // Adjust size dynamically


        // Add the JLabel to the container
        add(infoLabel, JLayeredPane.PALETTE_LAYER);
    }


    public void updateInformation(int cardsLeftInStack, int numOfCheckPoints, boolean isPlayerGreenTurn) {
        infoLabel.setText("<html>Cards Left: " + cardsLeftInStack
                + "<br>Checkpoints: " + numOfCheckPoints +
                "<br>"+ (isPlayerGreenTurn ? "Player 1 (red)" : "Player 2 (green)") + "</html>");
    }
}

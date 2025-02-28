package view.components.playerMenu;

import util.FindingName;
import util.FindingsInfo;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Represents an information panel displaying the player's score,
 * the number of Snake Goddess statues collected, and a button to view frescoes.
 */
public class InformationPanel extends JPanel {
    private List<FindingName> frescoesNames;
    private int score;
    private int snakeGoddessStatues;

    private JLabel northLabel;
    private JLabel southLabel;


    /**
     * Constructs an InformationPanel with the player's initial score and statues count.
     *
     * @param score              The initial score of the player.
     * @param snakeGoddessStatues The initial number of Snake Goddess statues collected.
     */
    public InformationPanel(int score, int snakeGoddessStatues) {
            this.score = score;
        this.snakeGoddessStatues = snakeGoddessStatues;

        setLayout(new BorderLayout(0, 10));

        String playerScoreText = "Your score: " + score + " points";
         northLabel = new JLabel(playerScoreText, SwingConstants.CENTER);

        JButton centerButton = new JButton("My frescoes");

        centerButton.addActionListener(e -> {
            showFrescoes();
        });

        String playerStatuesNumberText = "Statues number: " + snakeGoddessStatues;
        southLabel = new JLabel(playerStatuesNumberText, SwingConstants.CENTER);

        add(northLabel, BorderLayout.NORTH);
        add(centerButton, BorderLayout.CENTER);
        add(southLabel, BorderLayout.SOUTH);
    }

    private void showFrescoes() {
        if (frescoesNames == null || frescoesNames.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No frescoes to display!", "Frescoes", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        JDialog frescoesDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "My Frescoes", true);
        frescoesDialog.setLayout(new BorderLayout());

        JPanel frescoesPanel = new JPanel();
        frescoesPanel.setLayout(new GridLayout(0, 3, 10, 10));

        for (FindingName fresco : frescoesNames) {
            ImageIcon imageIcon = new ImageIcon(FindingsInfo.getFindingImagePath(fresco));
            Image image = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(image)); // Display image

            frescoesPanel.add(imageLabel);
        }

        JScrollPane scrollPane = new JScrollPane(frescoesPanel);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> frescoesDialog.dispose());

        frescoesDialog.add(scrollPane, BorderLayout.CENTER);
        frescoesDialog.add(closeButton, BorderLayout.SOUTH);

        frescoesDialog.setSize(500, 500);
        frescoesDialog.setLocationRelativeTo(this);
        frescoesDialog.setVisible(true);
    }


    /**
     * Updates the panel with the player's new score, statue count, and frescoes list.
     *
     * @param newScore              The updated score of the player.
     * @param newSnakeGoddessStatues The updated number of Snake Goddess statues.
     * @param newFrescoesNames      The updated list of frescoes collected by the player.
     */
    public void updateInformationPanel(int newScore, int newSnakeGoddessStatues, List<FindingName> newFrescoesNames) {
        this.score = newScore;
        this.snakeGoddessStatues = newSnakeGoddessStatues;
        this.frescoesNames = newFrescoesNames;

        northLabel.setText("Your score: " + newScore + " points");
        southLabel.setText("Statues number: " + newSnakeGoddessStatues);
    }
}

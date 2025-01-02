package view.componentT.playerMenuT;

import util.FindingName;
import util.FindingsInfo;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InformationPanel extends JPanel {
    private List<FindingName> frescoesNames;
    private int score;
    private int snakeGoddessStatues;

    private JLabel northLabel;
    private JLabel southLabel;

    public InformationPanel(int score, int snakeGoddessStatues) {
        this.score = score;
        this.snakeGoddessStatues = snakeGoddessStatues;

        setLayout(new BorderLayout(0, 10));
        setBackground(Color.LIGHT_GRAY);

        String playerScoreText = "Your score: " + score + " points";
         northLabel = new JLabel(playerScoreText, SwingConstants.CENTER);

        JButton centerButton = new JButton("My frescoes");

        centerButton.addActionListener(e -> {
            showFrescoes();
        });

        String playerStatuesNumberText = "Statues number: " + snakeGoddessStatues;
        southLabel = new JLabel(playerStatuesNumberText, SwingConstants.CENTER);

        // Add components to the panel
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


    public void updateInformationPanel(int newScore, int newSnakeGoddessStatues, List<FindingName> newFrescoesNames) {
        this.score = newScore;
        this.snakeGoddessStatues = newSnakeGoddessStatues;
        this.frescoesNames = newFrescoesNames;

        northLabel.setText("Your score: " + newScore + " points");
        southLabel.setText("Statues number: " + newSnakeGoddessStatues);
    }
}

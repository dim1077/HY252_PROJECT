package view.componentT.centralContentT;

import util.GameConstants;
import util.PathName;
import util.PawnName;
import util.PlayerName;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PathCell extends JPanel {
    private int cellIndex;
    private PathName pathName;


    public PathCell(int index, PathName pathName) {
        this.cellIndex = index;
        this.pathName = pathName;

        setLayout(null);
        setPreferredSize(new Dimension(50, 50)); // Fixed size
        setBorder(BorderFactory.createLineBorder(GameConstants.COLOR_FOR_EACH_PATH.get(pathName)));
        setBackground(Color.WHITE); // Default background color


        setPathIcon(cellIndex);
    }

    private String getPathIconFileName(int gridIndex){
        String fileName = "";
        if (pathName == PathName.KNOSSOS_PATH) fileName += "knossos";
        else if (pathName == PathName.MALIA_PATH) fileName += "malia";
        else if (pathName == PathName.PHAISTOS_PATH) fileName += "phaistos";
        else if (pathName == PathName.ZAKROS_PATH) fileName += "zakros";
        else throw new IllegalArgumentException();

        if (gridIndex == 8) fileName += "Palace";
        else if (GameConstants.numOfPositionsWithFindings.contains((gridIndex + 1) % 9)) fileName += "2"; // gridIndex + 1 because numOfPositionsWithFindings is 1-indexed

        return fileName;
    }

    private void setPathIcon(int index) {
        try {
            // Build the file path and load the image
            String iconFileName = getPathIconFileName(index);
            ImageIcon rawIcon = new ImageIcon("src/assets/images/paths/" + iconFileName + ".jpg");

            // Scale the image smoothly
            Image scaledImage;
            if (index == 8)
                scaledImage = rawIcon.getImage().getScaledInstance(69, 48, Image.SCALE_SMOOTH);
            else
                scaledImage = rawIcon.getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH);
            ImageIcon pathIcon = new ImageIcon(scaledImage);

            // Use absolute positioning

            // Create and position the path icon label
            JLabel iconLabel = new JLabel(pathIcon);
            if (index == 8)
                iconLabel.setBounds(0, 0, 69, 48); // Custom size for index 8
            else
                iconLabel.setBounds(0, 0, 50, 50); // Default size for other indices

            add(iconLabel); // Add it first so it stays at the back layer



        } catch (Exception e) {
            e.printStackTrace(); // Log any errors during loading
        }
    }


    public void addPawnToCell(PawnName pawnName, PlayerName playerName, boolean isRevealed) {
        String imagePath = "";
        try {
            if (!isRevealed) imagePath += "src/assets/images/pionia/question.jpg";
            else if (pawnName == PawnName.ARCHEOLOGIST) imagePath += "src/assets/images/pionia/arch.jpg";
            else if (pawnName == PawnName.THESEUS) imagePath += "src/assets/images/pionia/theseus.jpg";
            else throw new IllegalArgumentException("Invalid pawn name: " + pawnName);

        } catch (Exception e) {
            e.printStackTrace();
        }


        // Load the image
        ImageIcon icon = new ImageIcon(imagePath); // Replace with your image path

        // Resize the image if needed
        Image scaledImage = icon.getImage().getScaledInstance(15, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        // Create JLabel with the image
        JLabel imageLabel = new JLabel(scaledIcon);
        if (getPawnImageCount() == 2)  imageLabel.setBounds(25, 10, 15, 30);
        else imageLabel.setBounds(5, 10, 15, 30); // Set position and size

        Color playerColor = GameConstants.COLOR_FOR_EACH_PLAYER.get(playerName);
        imageLabel.setBorder(BorderFactory.createLineBorder(playerColor, 2));

        add(imageLabel);
        setComponentZOrder(imageLabel, 0);

        revalidate();
        repaint();
    }

    public void removePawnFromCell(PathName pathName, int cellIdx, PlayerName playerName) {
        try {
            // Iterate through the components in the cell
            for (Component component : getComponents()) {
                if (component instanceof JLabel) {
                    JLabel label = (JLabel) component;

                    // Check if the border matches the player's color: And yes this is a terrible implementation but guess what? I'm not going to be a frontend engineer
                    if ((playerName == PlayerName.PLAYER_GREEN && label.getBorder() instanceof LineBorder
                            && ((LineBorder) label.getBorder()).getLineColor().equals(GameConstants.COLOR_FOR_EACH_PLAYER.get(PlayerName.PLAYER_GREEN)))
                            || (playerName == PlayerName.PLAYER_RED && label.getBorder() instanceof LineBorder
                            && ((LineBorder) label.getBorder()).getLineColor().equals(GameConstants.COLOR_FOR_EACH_PLAYER.get(PlayerName.PLAYER_RED)))) {

                        // Remove the matching label
                        remove(label);
                        break;
                    }
                }
            }

            // Refresh the layout to reflect the changes
            revalidate();
            repaint();
        } catch (Exception e) {
            e.printStackTrace(); // Log any errors
        }
    }




    private int getPawnImageCount() {
        int count = 0;
        for (Component component : getComponents()) {
            if (component instanceof JLabel) {
                count++;
            }
        }
        return count;
    }

}
package view.components.centralContent;

import util.GameConstants;
import util.PathName;
import util.PawnName;
import util.PlayerName;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Represents an individual cell within a path panel.
 * Each cell may display a background icon and can hold pawns represented by images.
 */
public class PathCell extends JPanel {
    private final int cellIndex;
    private final PathName pathName;

    /**
     * Constructs a PathCell with the specified index and path name.
     *
     * @param index    The index of the cell in the path (0-8).
     * @param pathName The name of the path to which this cell belongs.
     */
    public PathCell(int index, PathName pathName) {
        this.cellIndex = index;
        this.pathName = pathName;

        setLayout(null);
        setPreferredSize(new Dimension(50, 50)); // Fixed size
        setBorder(BorderFactory.createLineBorder(GameConstants.Paths.COLOR_FOR_EACH_PATH.get(pathName)));
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
        else if (GameConstants.Paths.POSITIONS_WITH_FINDINGS.contains((gridIndex + 1) % 9)) fileName += "2"; // gridIndex + 1 because numOfPositionsWithFindings is 1-indexed

        return fileName;
    }

    private void setPathIcon(int index) {
        try {
            String iconFileName = getPathIconFileName(index);
            ImageIcon rawIcon = new ImageIcon("src/assets/images/paths/" + iconFileName + ".jpg");

            Image scaledImage;
            if (index == 8)
                scaledImage = rawIcon.getImage().getScaledInstance(69, 48, Image.SCALE_SMOOTH);
            else
                scaledImage = rawIcon.getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH);
            ImageIcon pathIcon = new ImageIcon(scaledImage);


            JLabel iconLabel = new JLabel(pathIcon);
            if (index == 8)
                iconLabel.setBounds(0, 0, 69, 48);
            else
                iconLabel.setBounds(0, 0, 50, 50);

            add(iconLabel);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a pawn image to the cell.
     *
     * @param pawnName   The name of the pawn being added (Theseus or Archeologist).
     * @param playerName The name of the player owning the pawn (Green or Red).
     * @param isRevealed Indicates if the pawn's identity is revealed.
     */
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


        ImageIcon icon = new ImageIcon(imagePath);

        Image scaledImage = icon.getImage().getScaledInstance(15, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel imageLabel = new JLabel(scaledIcon);

        if (playerName == PlayerName.PLAYER_GREEN) imageLabel.setBounds(25, 10, 15, 30);
        else imageLabel.setBounds(5, 10, 15, 30);


        Color playerColor = GameConstants.PlayerVisuals.COLOR_FOR_EACH_PLAYER.get(playerName);
        imageLabel.setBorder(BorderFactory.createLineBorder(playerColor, 2));

        add(imageLabel);
        setComponentZOrder(imageLabel, 0);

        revalidate();
        repaint();
    }

    /**
     * Removes a pawn image from the cell based on the player's name.
     *
     * @param playerName The name of the player whose pawn is to be removed.
     */
    public void removePawnFromCell(PlayerName playerName) {

        try {
            // Iterate through the components in the cell
            for (Component component : getComponents()) {
                if (component instanceof JLabel) {
                    JLabel label = (JLabel) component;

                    // Check if the border matches the player's color: And yes this is a terrible implementation but guess what? I'm not going to be a frontend engineer
                    if ((playerName == PlayerName.PLAYER_GREEN && label.getBorder() instanceof LineBorder
                            && ((LineBorder) label.getBorder()).getLineColor().equals(GameConstants.PlayerVisuals.COLOR_FOR_EACH_PLAYER.get(PlayerName.PLAYER_GREEN)))
                            || (playerName == PlayerName.PLAYER_RED && label.getBorder() instanceof LineBorder
                            && ((LineBorder) label.getBorder()).getLineColor().equals(GameConstants.PlayerVisuals.COLOR_FOR_EACH_PLAYER.get(PlayerName.PLAYER_RED)))) {

                        remove(label);
                        break;
                    }
                }
            }

            revalidate();
            repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
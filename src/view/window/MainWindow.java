package view.window;

import controller.listeners.GameButtonClickListener;
import util.*;
import view.components.centralContent.CentralContent;
import view.components.playerMenu.CardView;
import view.components.playerMenu.PlayerMenu;

import javax.swing.*;
import java.awt.*;


/**
 * Main window for the game application. Manages the layout, UI components,
 * and user interactions for the game.
 */
public class MainWindow extends JFrame {
    private CentralContent centralContent;
    private PlayerMenu greenPlayerMenu;
    private PlayerMenu redPlayerMenu;


    /**
     * Constructs the main window for the game, setting up all the UI components.
     *
     * @param initialCardsRed   Array of card views for the red player.
     * @param initialCardsGreen Array of card views for the green player.
     * @param cardClickListener Listener for handling card click events.
     * @param availableCards    Number of cards remaining in the deck.
     * @param checkPoints       Number of checkpoints passed in the game.
     * @param isPlayerOnesTurn  Indicates whether it's player one's turn.
     */
    public MainWindow(CardView[] initialCardsRed, CardView[] initialCardsGreen, GameButtonClickListener cardClickListener, int availableCards, int checkPoints, boolean isPlayerOnesTurn) {

        setTitle("Lost Cities");
        setSize(GameConstants.Screen.WIDTH / 2, GameConstants.Screen.HEIGHT / 2); // TODO I don't think this is needed or makes sense
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(GameConstants.Screen.WIDTH, GameConstants.Screen.HEIGHT));


        setJMenuBar(new GameMenu(cardClickListener));

        // bottom menu
        greenPlayerMenu = new PlayerMenu(initialCardsGreen, cardClickListener, PlayerName.PLAYER_GREEN);

        // top menu
        redPlayerMenu = new PlayerMenu(initialCardsRed, cardClickListener, PlayerName.PLAYER_RED);


        centralContent = new CentralContent(availableCards, checkPoints,  isPlayerOnesTurn, cardClickListener);

        add(redPlayerMenu, BorderLayout.NORTH);
        add(greenPlayerMenu, BorderLayout.SOUTH);
        add(centralContent, BorderLayout.CENTER);

        setVisible(true);
    }

    /**
     * Displays an error popup when an invalid Ariadne card is played.
     */
    public void noAriadneCardPopUp(){
        JOptionPane.showMessageDialog(null, "Cannot use Ariadne cards without having played any other card in that path before that", "Invalid card", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Displays a notification that the save was saved.
     */
    public void showGameSavedMessage(){
        JOptionPane.showMessageDialog(null, "Game saved! ", "Saved", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Displays an error popup when an invalid Minotaur card is played.
     */
    public void noMinotaurCardPopUp(){
        JOptionPane.showMessageDialog(null, "Cannot use minotaur card: Opponent has passed the checkpoint or haven't started yet", "Invalid card", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Displays an error popup when a pawn is immobilized.
     */
    public void PawnImmobilized(){
        JOptionPane.showMessageDialog(null, "Cannot use pawn: Currently immobilized", "Invalid card", JOptionPane.ERROR_MESSAGE);
    }

    public void showNoTimePopUp(){
        JOptionPane.showMessageDialog(null, "Time's up, you lost your turn.", "No time left!", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Prompts the user to select which pawn to use (Theseus or Archeologist).
     *
     * @param allowTheseus      Whether the Theseus pawn can be selected.
     * @param allowArcheologist Whether the Archeologist pawn can be selected.
     * @return The selected pawn name.
     */
    public PawnName askUserForPawn(boolean allowTheseus, boolean allowArcheologist) {
        JDialog dialog = new JDialog(this, "Choose Your Path", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(this);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        JLabel messageLabel = new JLabel("Choose your role:", SwingConstants.CENTER);
        dialog.add(messageLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton theseusButton = new JButton("Theseus");
        JButton archeologistButton = new JButton("Archeologist");

        theseusButton.setEnabled(allowTheseus);
        archeologistButton.setEnabled(allowArcheologist);

        buttonPanel.add(theseusButton);
        buttonPanel.add(archeologistButton);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        final PawnName[] result = {null};
        theseusButton.addActionListener(e -> {
            result[0] = PawnName.THESEUS;
            dialog.dispose();
        });

        archeologistButton.addActionListener(e -> {
            result[0] = PawnName.ARCHEOLOGIST;
            dialog.dispose();
        });

        // Show the dialog and wait for user interaction
        dialog.setVisible(true);

        // Return the selected option
        return result[0];
    }


    /**
     * Prompts the user with an interaction for a finding, allowing them to extract or ignore it.
     *
     * @param title       Title of the dialog.
     * @param description Description of the finding.
     * @param findingName Name of the finding.
     * @param pawnName    Name of the interacting pawn.
     * @return True if the finding is ignored, false otherwise.
     */
    public boolean askUserForFindingInteraction(String title, String description, FindingName findingName, PawnName pawnName){
        final boolean[] result = {false};

        // Format the description with HTML for better text wrapping
        String formattedDescription = "<html><body style='width: 300px;'>" + description + "</body></html>";

        ImageIcon icon = new ImageIcon(FindingsInfo.getFindingImagePath(findingName));

        JPanel panel = new JPanel(new BorderLayout());
        JLabel textLabel = new JLabel(formattedDescription);
        JLabel imageLabel = new JLabel(icon);

        panel.add(imageLabel, BorderLayout.WEST);
        panel.add(textLabel, BorderLayout.CENTER);

        // Custom button labels
        String action = (pawnName == PawnName.ARCHEOLOGIST) ? "Extract" : "Destroy";
        String[] options = {action, "Ignore"};

        // Show the dialog synchronously and capture the result
        int response = JOptionPane.showOptionDialog(
                null,
                panel,
                title,
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]
        );

        return (response == 1);
    }

    /**
     * Displays a dialog announcing the winner of the game.
     *
     * @param playerName The name of the winning player.
     */
    public void announceWinner(PlayerName playerName) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Winner Announcement");
        dialog.setSize(350, 200);
        dialog.setLocationRelativeTo(null);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);  // close on exit

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(GameConstants.PlayerVisuals.COLOR_FOR_EACH_PLAYER.get(playerName), 4)); // Green border with 4px thickness
        panel.setLayout(new BorderLayout(10, 10));

        JLabel messageLabel = new JLabel("Congratulations, " + playerName.toString() + "! You are the winner!", JLabel.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(messageLabel, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close Game");
        closeButton.setFont(new Font("Arial", Font.PLAIN, 12));

        closeButton.addActionListener(e -> {
            System.exit(0);
        });

        panel.add(closeButton, BorderLayout.SOUTH);

        dialog.add(panel);
        dialog.setVisible(true);
    }




    // Getters:

    /**
     * Retrieves the central content component of the main window.
     *
     * @return The central content component.
     */
    public CentralContent getCentralContent(){
        return centralContent;
    }

    /**
     * Retrieves the green player's menu component.
     *
     * @return The green player's menu.
     */
    public PlayerMenu getGreenPlayerMenu(){
        return greenPlayerMenu;
    }

    /**
     * Retrieves the red player's menu component.
     *
     * @return The red player's menu.
     */
    public PlayerMenu getRedPlayerMenu(){
        return redPlayerMenu;
    }
}

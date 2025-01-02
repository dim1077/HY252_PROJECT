package view.componentT;

import controller.GameButtonClickListener;
import util.*;
import view.componentT.centralContentT.CentralContentT;
import view.componentT.playerMenuT.PlayerMenuT;
import view.components.menus.CardView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindowT extends JFrame {
    private CentralContentT centralContent;
    private PlayerMenuT greenPlayerMenu;
    private PlayerMenuT redPlayerMenu;
    private GameButtonClickListener cardClickListener;



    /**
     * Constructs the main window for the application.
     * */
    public MainWindowT(CardView[] initialCardsRed, CardView[] initialCardsGreen, GameButtonClickListener cardClickListener, int availableCards, int checkPoints, boolean isPlayerOnesTurn) {
        this.cardClickListener = cardClickListener;

        setTitle("Lost Cities");
        setSize(GameConstants.WIDTH / 2, GameConstants.HEIGHT / 2); // TODO I don't think this is needed or makes sense
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(GameConstants.WIDTH, GameConstants.HEIGHT));

        // bottom menu
        greenPlayerMenu = new PlayerMenuT(initialCardsGreen, cardClickListener, PlayerName.PLAYER_GREEN);

        // top menu
        redPlayerMenu = new PlayerMenuT(initialCardsRed, cardClickListener, PlayerName.PLAYER_RED);


        centralContent = new CentralContentT(availableCards, checkPoints,  isPlayerOnesTurn, cardClickListener);

        add(redPlayerMenu, BorderLayout.NORTH);
        add(greenPlayerMenu, BorderLayout.SOUTH);
        add(centralContent, BorderLayout.CENTER);

        setVisible(true);
    }

    public void noAriadneCardPopUp(){
        JOptionPane.showMessageDialog(null, "Cannot use Ariadne cards without having played any other card in that path before that", "Invalid card", JOptionPane.ERROR_MESSAGE);
    }

    public void noMinotaurCardPopUp(){
        JOptionPane.showMessageDialog(null, "Cannot use minotaur card: Opponent has passed the checkpoint or haven't started yet", "Invalid card", JOptionPane.ERROR_MESSAGE);
    }

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

        // Set up button actions
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

    public void announceWinner(PlayerName playerName) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Winner Announcement");
        dialog.setSize(350, 200); // Set dialog size
        dialog.setLocationRelativeTo(null); // Center the dialog
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // Close on exit

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(GameConstants.COLOR_FOR_EACH_PLAYER.get(playerName), 4)); // Green border with 4px thickness
        panel.setLayout(new BorderLayout(10, 10)); // Padding for layout

        JLabel messageLabel = new JLabel("Congratulations, " + playerName.toString() + "! You are the winner!", JLabel.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 14)); // Set font style
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

    public CentralContentT getCentralContent(){
        return centralContent;
    }

    public PlayerMenuT getGreenPlayerMenu(){
        return greenPlayerMenu;
    }

    public PlayerMenuT getRedPlayerMenu(){
        return redPlayerMenu;
    }
}

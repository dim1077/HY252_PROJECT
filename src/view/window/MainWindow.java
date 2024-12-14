package view.window;

import controller.Controller;
import controller.GameButtonClickListener;
import util.GameConstants;
import util.PawnName;
import util.PlayerName;
import view.components.centralContent.CentralContent;
import view.components.menus.CardView;
import view.components.menus.PlayerMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * The main window of the application, representing the game's graphical user interface.
 * This window is built using a {@link JFrame} and contains:
 * <ul>
 *     <li>A central content area for displaying game-related visuals and actions.</li>
 *     <li>Two menus for the red and green players located at the top and bottom of the window, respectively.</li>
 * </ul>
 */
public class MainWindow extends JFrame {
    private CentralContent centralContent;
    private PlayerMenu GreenPlayerMenu;
    private PlayerMenu RedPlayerMenu;
    private GameButtonClickListener cardClickListener;



    /**
     * Constructs the main window for the application.
     * */
    public MainWindow(CardView[] initialCardsRed, CardView[] initialCardsGreen, GameButtonClickListener cardClickListener) {
        this.cardClickListener = cardClickListener;

        setTitle("Lost Cities");
        setSize(GameConstants.WIDTH, GameConstants.HEIGHT); // TODO I don't think this is needed or makes sense
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(GameConstants.WIDTH, GameConstants.HEIGHT));

        // bottom menu
        GreenPlayerMenu = new PlayerMenu(initialCardsGreen, cardClickListener, PlayerName.PLAYER_GREEN, true);
        // top menu
        RedPlayerMenu = new PlayerMenu(initialCardsRed, cardClickListener, PlayerName.PLAYER_RED, false);

        centralContent = new CentralContent(cardClickListener);

        add(RedPlayerMenu, BorderLayout.NORTH);
        add(GreenPlayerMenu, BorderLayout.SOUTH);
        add(centralContent, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }

    public PawnName askUserForPawn() {
        // Create the dialog
        JDialog dialog = new JDialog(this, "Choose Your Path", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(this);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);


        // Message Label
        JLabel messageLabel = new JLabel("Choose your role:", SwingConstants.CENTER);
        dialog.add(messageLabel, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        JButton theseusButton = new JButton("Theseus");
        JButton archeologistButton = new JButton("Archeologist");

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

    public void noAriadneCardPopUp(){
        JOptionPane.showMessageDialog(null, "Cannot use Ariadne cards without having played any other card in that path before that", "Invalid card", JOptionPane.ERROR_MESSAGE);
    }


    /**
     * Initializes the components of the window.
     * This method is currently empty but could be used for additional initialization.
     */
    private void initializeComponents() {}

    /**
     * Sets up the layout of the window components.
     * This method is currently empty but could be used for layout adjustments.
     */
    private void setupLayout() {}


    /**
     * Gets the central content area of the window.
     *
     * @return The {@link CentralContent} component displayed in the center of the window.
     */
    public CentralContent getCentralContent() {
        return centralContent;
    }

    /**
     * Gets the menu for the red player.
     *
     * @return The {@link PlayerMenu} component for the red player, located at the top of the window.
     */
    public PlayerMenu getRedPlayerMenu() {
        return RedPlayerMenu;
    }

    /**
     * Gets the menu for the green player.
     *
     * @return The {@link PlayerMenu} component for the green player, located at the bottom of the window.
     */
    public PlayerMenu getGreenPlayerMenu() {
        return GreenPlayerMenu;
    }

}

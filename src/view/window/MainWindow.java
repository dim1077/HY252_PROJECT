package view.window;

import controller.Controller;
import util.GameConstants;
import view.components.centralContent.CentralContent;
import view.components.menus.PlayerMenu;

import javax.swing.*;
import java.awt.*;


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
    final static int WINDOW_WIDTH = 1920;
    final static int WINDOW_HEIGHT = 1080;


    /**
     * Constructs the main window for the application.
     *
     * @param controller The game controller used to manage interactions between the view and the underlying logic.
     */
    public MainWindow(Controller controller) {
        setTitle("Lost Cities");
        setSize(GameConstants.WIDTH, GameConstants.HEIGHT); // TODO I don't think this is needed or makes sense
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(GameConstants.WIDTH, GameConstants.HEIGHT));

        // bottom menu
        GreenPlayerMenu = new PlayerMenu(controller);
        // top menu
        RedPlayerMenu = new PlayerMenu(controller);
        centralContent = new CentralContent(controller);

        add(RedPlayerMenu, BorderLayout.NORTH);
        add(GreenPlayerMenu, BorderLayout.SOUTH);
        add(centralContent, BorderLayout.CENTER);

        pack();
        setVisible(true);
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

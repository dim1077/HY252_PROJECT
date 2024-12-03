package view.window;

import view.components.centralContent.CentralContent;
import view.components.menus.PlayerMenu;

import javax.swing.*;
import java.awt.*;


public class MainWindow extends JFrame {
    private CentralContent centralContent;
    private PlayerMenu topPlayerMenu;
    private PlayerMenu bottomPlayerMenu;
    final static int WINDOW_WIDTH = 1920;
    final static int WINDOW_HEIGHT = 1080;

    public MainWindow() {
        setTitle("Lost Cities");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Player's red menu
        topPlayerMenu = new PlayerMenu();
        // Player's green menu
        bottomPlayerMenu = new PlayerMenu();
        centralContent = new CentralContent();

        add(topPlayerMenu, BorderLayout.NORTH);
        add(bottomPlayerMenu, BorderLayout.SOUTH);
        add(centralContent, BorderLayout.CENTER);

        setVisible(true);
    }

    private void initializeComponents() {}

    private void setupLayout() {}

    public CentralContent getCentralContent() {
        return centralContent;
    }

    public PlayerMenu getTopPlayerMenu() {
        return topPlayerMenu;
    }

    public PlayerMenu getBottomPlayerMenu() {
        return bottomPlayerMenu;
    }

}

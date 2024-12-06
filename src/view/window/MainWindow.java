package view.window;

import view.components.centralContent.CentralContent;
import view.components.menus.PlayerMenu;

import javax.swing.*;
import java.awt.*;


public class MainWindow extends JFrame {
    private CentralContent centralContent;
    private PlayerMenu GreenPlayerMenu;
    private PlayerMenu RedPlayerMenu;
    final static int WINDOW_WIDTH = 1920;
    final static int WINDOW_HEIGHT = 1080;

    public MainWindow(int cardsLeftInStack, int NumOfCheckPoints, boolean IsPlayerGreenTurn) {
        setTitle("Lost Cities");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

        // bottom menu
        GreenPlayerMenu = new PlayerMenu();
        // top menu
        RedPlayerMenu = new PlayerMenu();
        centralContent = new CentralContent();

        add(RedPlayerMenu, BorderLayout.NORTH);
        add(GreenPlayerMenu, BorderLayout.SOUTH);
        add(centralContent, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }

    private void initializeComponents() {}

    private void setupLayout() {}

    public CentralContent getCentralContent() {
        return centralContent;
    }

    public PlayerMenu getRedPlayerMenu() {
        return RedPlayerMenu;
    }

    public PlayerMenu getGreenPlayerMenu() {
        return GreenPlayerMenu;
    }

}

package view.window;

import controller.listeners.GameButtonClickListener;

import javax.swing.*;
import java.awt.*;

public class GameMenu extends JMenuBar {

    public GameMenu(GameButtonClickListener cardClickListener) {

        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        setBackground(Color.LIGHT_GRAY);

        Font smallerFont = new Font("Arial", Font.PLAIN, 10);


        JMenuItem newGameItem = new JMenuItem("New Game (defunct)");
        JMenuItem saveGameItem = new JMenuItem("Save Game");
        JMenuItem savedGameItem = new JMenuItem("Saved Game");
        JMenuItem exitGameItem = new JMenuItem( "mute/unmute music");

        newGameItem.setFont(smallerFont);
        saveGameItem.setFont(smallerFont);
        savedGameItem.setFont(smallerFont);
        exitGameItem.setFont(smallerFont);

        newGameItem.addActionListener(e -> System.out.println("TODO"));
        saveGameItem.addActionListener(e -> cardClickListener.onSaveGameClicked());
//        savedGameItem.addActionListener(e -> cardClickListener.openSavedGame());
        exitGameItem.addActionListener(e -> cardClickListener.onMusicSoundClicked());

        add(newGameItem);
        add(saveGameItem);
        add(savedGameItem);
        add(exitGameItem);
    }
}

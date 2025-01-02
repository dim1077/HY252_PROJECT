package sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;


// https://stackoverflow.com/questions/47611068/where-to-put-sounds-in-mvc-java-game
/**
 * Handles audio playback for the game.
 */
public class AudioPlayer {
    private Clip clip;
    private final String greenTurnAudio = "src/assets/music/Player1.wav";
    private final String redTurnAudio = "src/assets/music/Player2.wav";

    /**
     * Plays audio for the current player's turn.
     *
     * @param isGreenTurn True for green player's turn, false for red.
     */
    public void playMusicForTurn(boolean isGreenTurn) {
//        try {
//            stopMusic();
//
//            String filePath = isGreenTurn ? greenTurnAudio : redTurnAudio;
//
//            File audioFile = new File(filePath);
//            if (!audioFile.exists()) {
//                System.err.println("Audio file not found: " + filePath);
//                return;
//            }
//
//            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
//            clip = AudioSystem.getClip();
//            clip.open(audioStream);
//            clip.loop(Clip.LOOP_CONTINUOUSLY); // Loop the audio until changed
//            clip.start(); // Start playback
//
//        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * Stops any currently playing audio.
     */
    public void stopMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }
}

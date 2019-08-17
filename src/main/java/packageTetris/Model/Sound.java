package main.java.packageTetris.Model;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sound  {
    private Clip clip;
    public boolean isRunning = false;

    public Sound(String soundName)  {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void playSound() {
        isRunning = true;
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stopSound() {
        isRunning = false;
        clip.stop();
    }

    public void restartSound() {
        clip.stop();
        clip.setFramePosition(0);
        clip.start();
    }

}

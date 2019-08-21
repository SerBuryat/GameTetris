package main.java.packageTetris.Model;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Sound  {
    private Clip clip;
    public boolean isRunning = false;

    public Sound(String soundName)  {
        try{
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getClassLoader().getResource("main/resources/sounds/" + soundName));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception ex1) {
            try{
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getClassLoader().getResource("sounds/" + soundName));
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
            } catch (Exception ex2) {
                ex1.printStackTrace();
                ex2.printStackTrace();
            }
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

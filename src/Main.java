//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.SwingUtilities;

import view.BeginFrame;

import java.io.File;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BeginFrame beginFrame = new BeginFrame();
            beginFrame.setVisible(true);
            playMusic("resource/sound/bgm.wav");
        });
    }

    public static void playMusic(String musicLocation) {
try {
            File musicPath = new File(musicLocation);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                System.out.println("Can't find file");
            }
        } catch (Exception var4) {
            var4.printStackTrace();
        }
    }
}

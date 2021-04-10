import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Audio {
	
    private Clip clip;

    public Audio(String fileName) {
    	try {
            File musicPath = new File(fileName);
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
            clip = AudioSystem.getClip();
            clip.open(audioInput);
        }
        catch (Exception e) {
            System.out.println("Error");
        }
    }

    public void restart() {
        clip.setMicrosecondPosition(0);
    }
    
    public void loop() {
    	clip.loop(clip.LOOP_CONTINUOUSLY);
    }
    public void play() {
        clip.start();
    }
    
    public void stop() {
        clip.stop();
    }
}



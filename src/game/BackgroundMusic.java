package game;

import java.io.File;

import javax.sound.sampled.*;

import javafx.scene.image.Image;

@SuppressWarnings("unused")
public class BackgroundMusic {
    private Clip clip;
    public static BackgroundMusic backgroundmusic = getFile("backgroundmusic.wav");
    public static BackgroundMusic collectedgem = getFile("collectedgem.wav");
    public static BackgroundMusic chestopen = getFile("chestopen.wav");
    public static BackgroundMusic keycollected = getFile("chestopen.wav");
    
    /* Constructor */
    public BackgroundMusic (String fileName) {
    	try {
            File file = new File(fileName);
            if (file.exists()) {
                AudioInputStream sound = AudioSystem.getAudioInputStream(file);
                clip = AudioSystem.getClip();
                clip.open(sound);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }  
    /* Gets the correct soundfile regardless of what operating system you are using */
    private static BackgroundMusic getFile(String filename) {
		String file;
		if (System.getProperty("os.name").startsWith("Windows")) {
			file = "sounds\\" + filename;
		} else {
			file = "sounds//" + filename;
		}
		BackgroundMusic sound = new BackgroundMusic(file);
		return sound;
	}
    /* Plays the .wav file */
    public void play() {
    try {
        if (clip != null) {
                new Thread() {
                    public void run() {
                        synchronized (clip) {
                            clip.stop();
                            clip.setFramePosition(0);
                            clip.start();
                        }
                    }
                }.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /* Stops the .wav file */
    public void stop(){
        if(clip == null) return;
        clip.stop();
    }
    /* Loops through the .wav file */
    public void loop() {
        try {
            if (clip != null) {
                new Thread() {
                    public void run() {
                        synchronized (clip) {
                            clip.stop();
                            clip.setFramePosition(0);
                            clip.loop(Clip.LOOP_CONTINUOUSLY);
                        }
                    }
                }.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /* Checks if clip is currently playing */
    public boolean isActive(){
        return clip.isActive();
    }
}
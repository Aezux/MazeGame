package game;

import javax.sound.sampled.*;

import javafx.scene.image.Image;

@SuppressWarnings("unused")
public class BackgroundMusic {
    private Clip clip;
    public static BackgroundMusic backgroundmusic = new BackgroundMusic("backgroundmusic.wav");
    public static BackgroundMusic collectedgem = new BackgroundMusic("collectedgem.wav");
    public BackgroundMusic (String fileName) {
    try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(BackgroundMusic.class.getResource(fileName));
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }  
    
    private static BackgroundMusic getFile(String type) {
		String file;
		if (System.getProperty("os.name").startsWith("Windows")) {
			file = "MazeGame\\sounds\\" + type;
		} else {
			file = "MazeGame//sounds//" + type;
		}
		System.out.println(file);
		
		BackgroundMusic sound1 = new BackgroundMusic(file);
		return sound1;
	}
    
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
    public void stop(){
        if(clip == null) return;
        clip.stop();
    }
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
    public boolean isActive(){
        return clip.isActive();
    }
}
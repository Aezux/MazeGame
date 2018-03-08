package game;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Door implements Runnable {
	
	private ImageView doorImage;
	private int scale = 25;
	
	/* Door constructor */
	public Door(DungeonMap map) {
		doorImage = new ImageView(getImage("door1.png"));
		doorImage.setX(map.getExitLocation().x * scale);
		doorImage.setY(map.getExitLocation().y * scale);
	}
	
	/* Adds the door to the pane */
	public void addToPane(ObservableList<Node> sceneGraph) {
		sceneGraph.add(doorImage);
	}

	public void run() {
		for (int i=0; i<5; ++i) {
			
			try {/* Put the thread to sleep */
				Thread.sleep(100);
			} catch (Exception e) {}
			
			/* Slowly opens the door */
			doorImage.setImage(getImage("door" + (i+2) + ".png"));
		}
	}
	
	/* Gets the correct image regardless of what operating system you are using */
	protected Image getImage(String type) {
		String file;
		if (System.getProperty("os.name").startsWith("Windows")) {
			file = "file:images\\" + type;
		} else {
			file = "file:images//" + type;
		}
		Image image = new Image(file, scale, scale, true, true);
		return image;
	}

}

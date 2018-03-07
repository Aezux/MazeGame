package game;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class Enemy {
	protected int scale = 50;
	protected boolean gameShouldRun = true;
	protected ExecutorService threads = Executors.newFixedThreadPool(10);
	
	
	/* Gets the correct image regardless of what operating system you are using */
	protected Image getImage(String type) {
		String file;
		if (System.getProperty("os.name").startsWith("Windows")) {
			file = "file:images\\" + type;
		} else {
			file = "file:images//" + type;
		}
		Image image = new Image(file, 50, 50, true, true);
		return image;
	}
	
	/* Launches the threads */
	public void startMoving() {}
	
	/* Stops the threads */
	public void stopMoving() {}

	/* Adds enemies to the pane */
	public void addToPane(ObservableList<Node> children) {}
}
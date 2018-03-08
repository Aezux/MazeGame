package game;

import java.util.Observer;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;

public abstract class Enemy implements Runnable, Observer {
	protected int scale = 25;
	protected boolean gameShouldRun = true;
	
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

	/* Adds enemies to the pane */
	public void addToPane(ObservableList<Node> children) {}
}
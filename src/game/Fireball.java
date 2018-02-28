package game;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.image.Image;

public class Fireball implements Observer {

	private Point currentLocation;
	private Movement movement;
	private Image image;
	
	/* Constructor */
	public Fireball(DungeonMap map, Observable player) {
		player.addObserver(this);
		this.currentLocation = map.getFireLocation();
		this.movement = new Chase(map, currentLocation);
	}
	
	/* Sets the movement */
	public void changeMovement(Movement movement) {
		this.movement = movement;
	}
	
	/* Returns the ship's location */
	public Point getFireLocation() {
		return currentLocation;
	}
	
	/* Gets the new image of the fireball */
	public Image newImage() {
		return image;
	}
	
	/* Updates the fireball image */
	private void updateImage(int oldX, int newX, int oldY, int newY) {
		if (newX > oldX) {
			image = getImage("fire_right.png");
		} else if (newX < oldX) {
			image = getImage("fire_left.png");
		} else if (newY > oldY) {
			image = getImage("fire_front.png");
		} else if (newY < oldY) {
			image = getImage("fire_back.png");
		}
	}

	/* If the player moves then so does the fireball */
	public void update(Observable obs, Object arg) {
		if (obs instanceof Player) {
			Point move = movement.nextPoint(currentLocation);
			updateImage(currentLocation.x, move.x, currentLocation.y, move.y);
			currentLocation.setLocation(move.x, move.y);
		}
	}
	
	/* Gets the correct image regardless of what operating system you are using */
	private Image getImage(String type) {
		String file;
		if (System.getProperty("os.name").startsWith("Windows")) {
			file = "file:images\\" + type;
		} else {
			file = "file:images//" + type;
		}
		Image image = new Image(file, 50, 50, true, true);
		return image;
	}
}

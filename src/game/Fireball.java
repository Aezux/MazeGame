package game;

import java.awt.Point;
import javafx.scene.Node;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* The fireball class */
public class Fireball {

	private ExecutorService threads;
	private Point currentLocation;
	private Movement movement;
	private Image image;
	private ImageView fireImage;
	private int scale = 50;
	
	/* Constructor */
	public Fireball(DungeonMap map) {
		this.currentLocation = map.getFireLocation();
		this.movement = new Chase(map, currentLocation);
		this.threads = Executors.newFixedThreadPool(1);
		setImage();
	}
	
	/* Sets the movement */
	public void changeMovement(Movement movement) {
		this.movement = movement;
	}
	
	/* Starting position for the fireball */
	private void setImage() {
		image = getImage("fire_front.png");
		fireImage = new ImageView(image);
		fireImage.setX(currentLocation.x * scale);
		fireImage.setY(currentLocation.y * scale);
	}
	
	/* Adds the fireball to the pane */
	public void addToPane(ObservableList<Node> sceneGraph) {
		sceneGraph.add(fireImage);
	}
	
	/* Launches the fireball tread */
	public void startMoving() {
		threads.submit(() -> update());
	}
	
	/* Updates the movement */
	private void update() {
		while (true) {
			
			try { // Put the thread to sleep
				Thread.sleep(100);
			} catch (InterruptedException e) {}
			
			/* Moves around */
			Point move = movement.nextPoint(currentLocation);
			updateImage(currentLocation.x, move.x, currentLocation.y, move.y);
			currentLocation.setLocation(move.x, move.y);
			fireImage.setX(move.x * scale);
			fireImage.setY(move.y * scale);
		}
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
		fireImage.setImage(image);
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

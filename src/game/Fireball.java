package game;

import java.awt.Point;
import javafx.scene.Node;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/* The fireball class */
public class Fireball extends Enemy{

	private Point currentLocation;
	private Movement movement;
	private Image image;
	private ImageView fireImage;
	private int sleepTime;
	
	/* Constructor */
	public Fireball(DungeonMap map, Point currentLocation) {
		this.currentLocation = currentLocation;
		this.movement = new Chase(map, currentLocation);
//		this.threads = Executors.newFixedThreadPool(10);
		this.sleepTime = 400;
		setImage();
	}
	
	/* Adds the fireball to the pane */
	public void addToPane(ObservableList<Node> sceneGraph) {
		sceneGraph.add(fireImage);
	}
	
	/* Launches the fireball thread */
	public void startMoving() {
		threads.submit(() -> update());
	}
	
	/* Stops the thread */
	public void stopMoving() {
		gameShouldRun = false;
		threads.shutdownNow();
	}
	
	/* Sets the movement */
	public void changeMovement(Movement movement) {
		this.movement = movement;
	}
	
	/* Updates the movement */
	private void update() {		
		while (gameShouldRun) {
			try {/* Put the thread to sleep */
				Thread.sleep(sleepTime);
			} catch (Exception e) {}
			
			/* Moves around */
			Point move = movement.nextPoint(currentLocation);
			updateImage(currentLocation.x, move.x, currentLocation.y, move.y);
			currentLocation.setLocation(move.x, move.y);
			fireImage.setX(move.x * scale);
			fireImage.setY(move.y * scale);
		}
	}
	
	/* Starting position for the fireball */
	private void setImage() {
		image = getImage("fire_front.png");
		fireImage = new ImageView(image);
		fireImage.setX(currentLocation.x * scale);
		fireImage.setY(currentLocation.y * scale);
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

}

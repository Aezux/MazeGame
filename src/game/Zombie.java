package game;

import java.awt.Point;
import javafx.scene.Node;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/* The zombie class */
public class Zombie extends Enemy{

	private Point currentLocation;
	private Movement movement;
	private Image image;
	private ImageView zombieImage;
	private int sleepTime;
	
	/* Constructor */
	public Zombie(DungeonMap map, Point currentLocation) {
		this.currentLocation = currentLocation;
		this.movement = new Patrol(map, currentLocation);
//		this.threads = Executors.newFixedThreadPool(10);
		this.sleepTime = 300;
		setImage();
	}
	
	/* Adds the zombie to the pane */
	public void addToPane(ObservableList<Node> sceneGraph) {
		sceneGraph.add(zombieImage);
	}
	
	/* Launches the zombie thread */
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
			zombieImage.setX(move.x * scale);
			zombieImage.setY(move.y * scale);
		}
	}
	
	/* Starting position for the fireball */
	private void setImage() {
		image = getImage("zombie_front.png");
		zombieImage = new ImageView(image);
		zombieImage.setX(currentLocation.x * scale);
		zombieImage.setY(currentLocation.y * scale);
	}
	
	/* Updates the zombie image */
	private void updateImage(int oldX, int newX, int oldY, int newY) {
		if (newX > oldX) {
			image = getImage("zombie_right.png");
		} else if (newX < oldX) {
			image = getImage("zombie_left.png");
		} else if (newY > oldY) {
			image = getImage("zombie_front.png");
		} else if (newY < oldY) {
			image = getImage("zombie_back.png");
		}
		zombieImage.setImage(image);
	}
	
}

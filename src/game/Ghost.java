package game;

import java.awt.Point;
import javafx.scene.Node;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;

/* The ghost class */
public class Ghost extends Enemy{

	private Point currentLocation;
	private Movement movement;
	private Image image;
	private ImageView ghostImage;
	private int sleepTime;
	
	/* Constructor */
	public Ghost(DungeonMap map, Point currentLocation) {
		this.currentLocation = currentLocation;
		this.movement = new Patrol2(map, currentLocation);
//		this.threads = Executors.newFixedThreadPool(10);
		this.sleepTime = 150;
		setImage();
	}
	
	/* Adds the ghost to the pane */
	public void addToPane(ObservableList<Node> sceneGraph) {
		sceneGraph.add(ghostImage);
	}
	
	/* Launches the ghost thread */
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
		ArrayList<Point> moves = movement.getPath(currentLocation);
		while (gameShouldRun) {
			/* Moves around */
			for (Point move : moves) {
				try {/* Put the thread to sleep */
					Thread.sleep(sleepTime);
				} catch (Exception e) {}
				updateImage(currentLocation.x, move.x, currentLocation.y, move.y);
				currentLocation.setLocation(move.x, move.y);
				ghostImage.setX(move.x * scale);
				ghostImage.setY(move.y * scale);
			}
		}
	}
	
	/* Starting position for the ghost */
	private void setImage() {
		image = getImage("ghost_front.png");
		ghostImage = new ImageView(image);
		ghostImage.setX(currentLocation.x * scale);
		ghostImage.setY(currentLocation.y * scale);
	}
	
	/* Updates the ghost image */
	private void updateImage(int oldX, int newX, int oldY, int newY) {
		if (newX > oldX) {
			image = getImage("ghost_right.png");
		} else if (newX < oldX) {
			image = getImage("ghost_left.png");
		} else if (newY > oldY) {
			image = getImage("ghost_front.png");
		} else if (newY < oldY) {
			image = getImage("ghost_back.png");
		}
		ghostImage.setImage(image);
	}

}

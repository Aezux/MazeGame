package game;

import java.awt.Point;
import javafx.scene.Node;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/* The trap class */
public class Trap extends Enemy{

	private Point currentLocation;
	private Movement movement;
	private Image image;
	private ImageView trapImage;
	private int sleepTime;
	
	/* Constructor */
	public Trap(DungeonMap map, Point currentLocation) {
		this.currentLocation = currentLocation;
		this.movement = new Arbitrary(map, currentLocation);
//		this.threads = Executors.newFixedThreadPool(10);
		this.sleepTime = 4000;
		setImage();
	}
	
	/* Adds the trap to the pane */
	public void addToPane(ObservableList<Node> sceneGraph) {
		sceneGraph.add(trapImage);
	}
	
	/* Launches the trap thread */
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
			try {
				/* Put the thread to sleep */
				Thread.sleep(sleepTime);
				/* Moves around */
				Point move = movement.nextPoint(currentLocation);
				currentLocation.setLocation(move.x, move.y);
				trapImage.setX(move.x * scale);
				trapImage.setY(move.y * scale);
			} catch (Exception e) {}
		}
	}
	
	/* Starting position for the trap */
	private void setImage() {
		image = getImage("trap.png");
		trapImage = new ImageView(image);
		trapImage.setX(currentLocation.x * scale);
		trapImage.setY(currentLocation.y * scale);
	}
	
	 /* Overwritten the equals method */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Trap other = (Trap) obj;
		return (other.currentLocation.equals(currentLocation));
	}
		
	/* Overwritten the hashCode method */
	public int hashCode() {
		int result = 0x12CD;
		result = 0xF7F * result + currentLocation.hashCode();
		return result;
	}
		
	/* Overwritten the toString method */
	public String toString() {
		return "";
	}

}

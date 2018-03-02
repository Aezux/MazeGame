package game;

import java.awt.Point;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/* The trap class */
public class Trap {
	
	private ExecutorService threads;
	private Point currentLocation;
	private Movement movement;
	private Image image;
	private ImageView trapImage;
	private boolean gameShouldRun;
	private int scale = 50;
	
	public Trap(DungeonMap map, Point currentLocation) {
		this.currentLocation = currentLocation;
		this.movement = new Arbitrary(map, currentLocation);
		this.threads = Executors.newFixedThreadPool(1);
		this.gameShouldRun = true;
		setImage();
	}
	
	/* Gets the new image of the trap */
	public Image newImage() {
		return image;
	}
	
	/* Sets the movement */
	public void changeMovement(Movement movement) {
		this.movement = movement;
	}
	
	/* Starting position for the trap */
	private void setImage() {
		image = getImage("trap.png");
		trapImage = new ImageView(image);
		trapImage.setX(currentLocation.x * scale);
		trapImage.setY(currentLocation.y * scale);
	}
	
	/* Adds the trap to the pane */
	public void addToPane(ObservableList<Node> sceneGraph) {
		sceneGraph.add(trapImage);
	}
	
	/* Launches the trap tread */
	public void startMoving() {
		threads.submit(() -> update());
	}
	
	/* Stops the thread */
	public void stopMoving() {
		gameShouldRun = false;
		threads.shutdownNow();
	}
	
	/* Updates the movement */
	private void update() {
		while (gameShouldRun) {
			
			try { // Put the thread to sleep
				Thread.sleep(5000);
			} catch (InterruptedException e) {}
			
			/* Moves around */
			Point move = movement.nextPoint(currentLocation);
			currentLocation.setLocation(move.x, move.y);
			trapImage.setX(move.x * scale);
			trapImage.setY(move.y * scale);
		}
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

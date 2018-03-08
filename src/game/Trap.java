package game;

import java.awt.Point;
import java.util.Observable;

import javafx.scene.Node;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/* The trap class */
public class Trap extends Enemy{

	private Point currentLocation;
	private Observable player;
	private DungeonMap map;
	private Movement movement;
	private Image image;
	private ImageView trapImage;
	private int sleepTime;
	
	/* Constructor */
	public Trap(DungeonMap map, Observable player, Point currentLocation) {
		this.map = map;
		player.addObserver(this);
		this.player = player;
		this.currentLocation = currentLocation;
		this.movement = new Arbitrary(map, currentLocation);
		this.sleepTime = 4000;
		setImage();
	}
	
	/* Adds the trap to the pane */
	public void addToPane(ObservableList<Node> sceneGraph) {
		sceneGraph.add(trapImage);
	}
	
	/* Sets the movement */
	public void changeMovement(Movement movement) {
		this.movement = movement;
	}
	
	/* Launches the trap thread */
	public void run() {
		while (gameShouldRun) {
			try {/* Put the thread to sleep */
				Thread.sleep(sleepTime);
			} catch (Exception e) {}
			
			/* Moves around */
			Point move = movement.nextPoint(currentLocation);
			
			/* Gets players location and update coordinates */
			Point playerLocation = map.getPlayerLocation();
			currentLocation.setLocation(move.x, move.y);
			trapImage.setX(move.x * scale);
			trapImage.setY(move.y * scale);
			
			/* If enemy touches player then take away one life */
			if (playerLocation.equals(move)) {
				Player user = (Player)player;
				user.loseLife();
			}
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

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}

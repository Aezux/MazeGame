package game;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.image.Image;

public class Trap implements Observer {
	
	private Point currentLocation;
	private Movement movement;
	private Image image;
	
	public Trap(DungeonMap map, Observable player, Point currentLocation) {
		this.image = getImage("trap.png");
		this.movement = new Arbitrary(map);
		this.currentLocation = currentLocation;
	}
	
	/* Gets the new image of the trap */
	public Image newImage() {
		return image;
	}
	
	public Point getLocation() {
		return currentLocation;
	}
		
	/* If the player moves then so does the trap */
	public void update(Observable obs, Object arg) {
		if (obs instanceof Player) {
			Point move = movement.nextPoint(currentLocation);
			currentLocation.setLocation(move.x, move.y);
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

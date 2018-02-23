package game;
import java.awt.Point;
 
@SuppressWarnings("unused")
public class Player {

	private int[][] map;
	private Point currentLocation;
	private int X, Y;
	private boolean hasKey = false;
	
	
	/* Constructor */
	public Player(DungeonMap map) {
		this.map = map.getMap();
		this.currentLocation = map.getPlayerLocation();
		updateCoordinates();
	}
	
	/* Updates the variables X and Y */
	private void updateCoordinates() {
		this.X = (int) currentLocation.getX();
		this.Y = (int) currentLocation.getY();
	}

	/* Returns the player's location */
	public Point getPlayerLocation() {
		return currentLocation;
	}
	
	/* Checks if the player is in bounds */
	private boolean checkBounds(int x, int y, int size) {
        return ((x-1 >= -1) && (x + 1 <= size) && (y-1 >= -1) && (y+1 <= size));
    }
	
	/* Checks if there is a wall */
	private boolean checkWall(int x, int y) {
		return map[x][y] == 1;
	}
	
	/* Move the player */
	public void move(String direction) {
		updateCoordinates();
		switch (direction) {
			case "North": --Y; break;
			case "South": ++Y; break;
			case "East":  ++X; break;
			case "West":  --X; break;
		}
		
		if ( checkBounds(X, Y, map.length) && !checkWall(X, Y) ) {
			currentLocation.setLocation(X, Y);
		}
	}

}

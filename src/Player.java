import java.awt.Point;

public class Player {

	private int[][] map;
	private Point currentLocation;
	private int X, Y;
	
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

	/* Returns the ship's location */
	public Point getPlayerLocation() {
		return currentLocation;
	}
	
	private boolean checkWall(int x, int y) {
		int n = map[x][y];
		return (n == 1 || n == 2 || n == 3);
	}
	
	/* Move the player */
	public void move(String direction) {
		updateCoordinates();
		switch (direction) {
			case "North":
				if (!checkWall(X, Y-1)) {
					currentLocation.setLocation(X, Y-1);
				}
			break;
			case "South":
				if (!checkWall(X, Y+1)) {
					currentLocation.setLocation(X, Y+1);
				}
			break;
			case "East":
				if (!checkWall(X+1, Y)) {
					currentLocation.setLocation(X+1, Y);
				}
			break;
			case "West":
				if (!checkWall(X-1, Y)) {
					currentLocation.setLocation(X-1, Y);
				}
			break;
		}
	}

}

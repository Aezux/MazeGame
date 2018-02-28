package powerups;

import java.awt.Point;
import java.util.Random;
import game.DungeonMap;

@SuppressWarnings("unused")
public class Speed {
	private int[][] map;
	private Point SpeedLocation;
	private int X, Y;
	private Random rand = new Random();

	public Speed(DungeonMap map) {
		this.map = map.getMap();
		this.SpeedLocation = map.getSpeedLocation();
		updateCoordinates();
	}

	/* Updates Speed Location Coordinates */
	private void updateCoordinates() {
		this.X = (int) SpeedLocation.getX();
		this.Y = (int) SpeedLocation.getY();

	}

	public void activatePower()
	{
		//INCREASE SPEED
	}
}

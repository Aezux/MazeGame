package powerups;

import java.awt.Point;
import java.util.Random;

import game.DungeonMap;
import javafx.scene.image.Image;

@SuppressWarnings("unused")
public class Key {	
	private int[][] map;
	private Point KeyLocation;
	private int X, Y;
	private Random rand = new Random();
	
	public Key(DungeonMap map) {
		this.map = map.getMap();
		this.KeyLocation = map.getKeyLocation();
		updateCoordinates();
	}
	/* Updates Key Location Coordinates */
	private void updateCoordinates() {
		this.X = (int) KeyLocation.getX();
		this.Y = (int) KeyLocation.getY();
	}
	
	public void activatePower()
	{
		//USE KEY ON CHEST
	}
}

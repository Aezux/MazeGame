package powerups;

import java.awt.Point;
import java.util.Random;
import game.DungeonMap;
import game.Player;

@SuppressWarnings("unused")
public class Invisible{
	private Player player;
	private int[][] map;
	private Point InvisibleLocation;
	private int X, Y;
	private Random rand = new Random();
	
	public Invisible(DungeonMap map) {
		this.map = map.getMap();
		this.InvisibleLocation = map.getInvisibleLocation();
		updateCoordinates();
	}
	/* Updates Invisible Location Coordinates */
	private void updateCoordinates() {
		this.X = (int) InvisibleLocation.getX();
		this.Y = (int) InvisibleLocation.getY();
	}
	

	public void activatePower()
	{
		//MAKE INVISIBLE FOR A WHILE
	}
	
	}

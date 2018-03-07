package powerups;

import java.awt.Point;
import java.util.Random;
import game.DungeonMap;
import game.Player;

@SuppressWarnings("unused")
public class Invisible{
	private int[][] map;
	private Point InvisibleLocation;
	private int X, Y;
	private Random rand = new Random();
	private int uses = 3;
	private Player player;
	
	public Invisible(DungeonMap map, Player p) {
		this.player = p;
		this.map = map.getMap();
		this.InvisibleLocation = map.getInvisibleLocation();
//		updateCoordinates();
	}
	/* Updates Invisible Location Coordinates */
//	private void updateCoordinates() {
//		this.X = (int) InvisibleLocation.getX();
//		this.Y = (int) InvisibleLocation.getY();
//	}
	

	public void activatePower()
	{
		if(uses > 0)
		{
			//MAKE INVISIBLE FOR A WHILE
			
			
			uses--;
			System.out.println("Using Invisible, " + uses + " uses remaining.");
		}
		else
		{
			System.out.println("No uses remaining.");
		}
	}
	
	}

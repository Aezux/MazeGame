package powerups;

import java.awt.Point;
import java.util.Random;
import game.DungeonMap;
import game.Player;

@SuppressWarnings("unused")
public class Speed {
	private int[][] map;
	private Point SpeedLocation;
	private int X, Y;
	private Random rand = new Random();
	private int uses = 3;
	private Player player;

	public Speed(DungeonMap map, Player p) {
		this.player = p;
		this.map = map.getMap();
		this.SpeedLocation = map.getSpeedLocation();
<<<<<<< HEAD
//		updateCoordinates();
	}

	/* Updates Speed Location Coordinates */
//	private void updateCoordinates() {
//		this.X = (int) SpeedLocation.getX();
//		this.Y = (int) SpeedLocation.getY();
//
//	}
=======
	}
>>>>>>> master_with_toolbar

	public void activatePower()
	{
		if(uses > 0)
		{
			//INCREASE SPEED
			
			
			uses--;
			System.out.println("Using Speed, " + uses + " uses remaining.");
		}
		else
		{
			System.out.println("No uses remaining.");
		}
	}
}

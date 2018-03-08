package powerups;

import java.awt.Point;
import java.util.Random;

import game.DungeonMap;
import game.Player;
import javafx.scene.image.Image;

@SuppressWarnings("unused")
public class Armor{	
	private int[][] map;
	private Point ArmorLocation;
	private int X, Y;
	private Random rand = new Random();
	private int uses = 3;
	private Player player;
	
	public Armor(DungeonMap map, Player p) {
		this.player = p;
		this.map = map.getMap();
		this.ArmorLocation = map.getArmorLocation();
<<<<<<< HEAD
//		updateCoordinates();
	}
	/* Updates Armor Location Coordinates */
//	private void updateCoordinates() {
//		this.X = (int) ArmorLocation.getX();
//		this.Y = (int) ArmorLocation.getY();
//	}
	
=======
	}
>>>>>>> master_with_toolbar
	public void activate()
	{
		if(uses > 0)
		{
			player.addLife();
<<<<<<< HEAD
			
			
=======
>>>>>>> master_with_toolbar
			uses--;
		}
	}
}

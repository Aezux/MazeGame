package powerups;

import java.awt.Point;
import java.util.Random;

import game.DungeonMap;
import game.Game;
import game.Player;
import javafx.scene.image.Image;

@SuppressWarnings("unused")
public class Key {	
	private int[][] map;
	private Point KeyLocation;
	private int X, Y;
	private Random rand = new Random();
	private int uses = 1;
	private Player player;
	
	public Key(DungeonMap map, Player p) {
		this.player = p;
		this.map = map.getMap();
		this.KeyLocation = map.getKeyLocation();
<<<<<<< HEAD
//		updateCoordinates();
	}
	/* Updates Key Location Coordinates */
//	private void updateCoordinates() {
//		this.X = (int) KeyLocation.getX();
//		this.Y = (int) KeyLocation.getY();
//	}
=======
	}
>>>>>>> master_with_toolbar
	
	public void activatePower()
	{
		if(uses > 0)
		{
			//USE KEY ON CHEST
			if(player.atChest())
			{
				uses--;
				System.out.println("Using Key to open chest.");
				player.openChest();
				if(uses == 0)
					player.removeKey();
			}
			else
			{
				System.out.println("This key could unlock something...");
			}
			
		}
		else
		{
			System.out.println("Already used this key.");
		}
	}
}
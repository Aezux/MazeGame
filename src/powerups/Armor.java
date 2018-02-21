package powerups;

import java.awt.Point;
import java.util.Random;

import game.DungeonMap;
import javafx.scene.image.Image;

@SuppressWarnings("unused")
public class Armor extends PowerUps {	
	private int[][] map;
	private Point ArmorLocation;
	private int X, Y;
	private Random rand = new Random();
	
	public Armor(DungeonMap map) {
		this.map = map.getMap();
		this.ArmorLocation = map.getArmorLocation();
		updateCoordinates();
	}
	/* Updates Armor Location Coordinates */
	private void updateCoordinates() {
		this.X = (int) ArmorLocation.getX();
		this.Y = (int) ArmorLocation.getY();
	}
}

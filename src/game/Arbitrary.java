package game;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Arbitrary extends Move {

	private DungeonMap map;
	private int[][] map2d;
	Random rand = new Random();

    /* Constructor */
    public Arbitrary(DungeonMap map, Point enemy) {
         this.map = map;
         this.map2d = map.getMap();
    }
    
    /* Generates a random point that is open on the map*/
	public Point nextPoint(Point location) {
		int openSpot, x, y;
		do {
			ArrayList<Integer> positions = map.getOpenLocations();
			openSpot = getRandomNumber(positions.size());			
			Integer position = positions.get(openSpot);
			x = position / map2d.length;
			y = position - (x * map2d.length);
		} while (!openSpot(x, y, map2d));
		
		return new Point(x, y);
	}
	
	/* Generates a random number */
	private int getRandomNumber(int length) {
		return rand.nextInt(length);
	}
}

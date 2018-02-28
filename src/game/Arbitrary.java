package game;

import java.awt.Point;
import java.util.Random;

public class Arbitrary extends Move {

	int[][] map;
	Random rand = new Random();

    /* Constructor */
    public Arbitrary(DungeonMap map) {
         this.map = map.getMap();
    }
    
    /* Generates a random point that is open on the map*/
	public Point nextPoint(Point location) {
		int x, y;
		
		do {
			x = getRandomNumber(map.length * map.length);
			y = getRandomNumber(map.length * map.length);
		} while (!openSpot(x, y, map));
		
		return new Point(x, y);
	}
	
	/* Generates a random number */
	private int getRandomNumber(int length) {
		return rand.nextInt(length);
	}
}

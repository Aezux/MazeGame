package game;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Move implements Movement {
	
	Random rand = new Random();
	
	/* Generates a random number */
	protected int getRandomNumber(int length) {
		return rand.nextInt(length);
	}

    /* Checks if the player is in bounds */
    private boolean checkBounds(int x, int y, int size) {
        return ((x - 1 >= -1) && (x + 1 <= size) && (y - 1 >= -1) && (y + 1 <= size));
    }

    /* Checks if there is a wall */
    private boolean checkWall(int x, int y, int[][] map) {
        return map[x][y] == 1;
    }

    /* Checks if the spot is open */
    protected boolean openSpot(int x, int y, int[][] map) {
        return (checkBounds(x, y, map.length) && !checkWall(x, y, map));
    }

    /* These next two will get overwritten in other classes that extend this class */
    
    /* Gets the next point to go to */
    public Point nextPoint(Point currentLocation) {
		return null;
	}
    
    /* Gets a path of points to follow */
    public ArrayList<Point> getPath(Point currentLocation) {
    	return null;
    }
}

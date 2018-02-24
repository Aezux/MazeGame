package game;

import java.awt.Point;

public class Move implements Movement {

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

    /* Will get overwritten in other classes that extend this class */
    public Point nextPoint(Point location) {
		return null;
	}
}

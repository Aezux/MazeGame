package game;

import java.awt.Point;
import java.util.ArrayList;

public class Patrol2 extends Move {
    int[][] maze;
    DungeonMap map;
    ArrayList<Point> path;

    public Patrol2(DungeonMap map, Point currentLocation) {
    	this.map = map;
    }
    
    /* Get the path */
    public ArrayList<Point> getPath(Point currentLocation) {
    	path = new ArrayList<Point>();
    	Point player = map.getPlayerLocation();
    	
    	maze = buildMap(map.getMap());
    	maze[player.x][player.y] = 2;
    	
    	traverse(currentLocation.x, currentLocation.y);
    	
    	return path;
    }

    /* Walk around the maze */
    public void traverse(int x, int y) {

        /* Player found */
        if (maze[x][y] == 2) {
        	path.add(new Point(x, y));
            return;
        } else if (maze[x][y] == 0) {
        	
        	/* Add the point to the path and mark it as seen */
        	path.add(new Point(x, y));
        	maze[x][y] = 1;
            
        	/* Go in all directions */
            if(x < maze.length-1) {
                traverse(x+1, y);
            }
            if(y < maze[x].length-1) {
                traverse(x, y+1);
            }
            if(x > 0) {
                traverse(x-1, y);
            }
            if(y > 0) {
                traverse(x, y-1);
            }
        }
    }

}
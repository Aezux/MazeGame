package game;

import java.awt.Point;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Chase extends Move {

    private int[][] map;
    private Point playerLocation;
    private Point enemyLocation;

    /* No argument constructor */
    public Chase() {
    	this.map = new int[0][0];
    	this.playerLocation = new Point();
    	this.enemyLocation = new Point();
    }
    
    /* Regular constructor */
    public Chase(DungeonMap map, Point enemy) {
         this.map = map.getMap();
         this.enemyLocation = enemy;
         this.playerLocation = map.getPlayerLocation();
    }

    /* Given a point, it will return a linkedList of all the points around it */
    private LinkedList<Point> getAdjacent(Point current) {
    	LinkedList<Point> list = new LinkedList<Point>();
        int x = current.x, y = current.y;

        /* Checks up */
        if (openSpot(x, y+1, map))
            list.add(new Point(x, y+1));

        /* Checks down */
        if (openSpot(x, y-1, map))
            list.add(new Point(x, y-1));

        /* Checks left */
        if (openSpot(x+1, y, map))
            list.add(new Point(x+1, y));

        /* Checks right */
        if (openSpot(x-1, y, map))
            list.add(new Point(x-1, y));

        return list;
    }

    /* Finds the shortest path using a modified BFS algorithm */
    private int[][] findPath() {
    	int[][] newMap = new int[map.length][map.length];
    	
    	/* Fills all the elements in the new map with MAX_VALUE */
    	for (int i=0; i<map.length; ++i) {
    		Arrays.fill(newMap[i], Integer.MAX_VALUE);
    	}
    	
    	HashSet<Point> visited = new HashSet<Point>();
    	Queue<PointLocation> queue = new LinkedList<PointLocation>();
    	
    	/* Makes the player location be the starting point */
    	queue.add(new PointLocation(playerLocation, 0));
    	visited.add(playerLocation);
    	
    	/* BFS */
    	while(!queue.isEmpty()) {
    		PointLocation current = queue.poll();
    		Point location = current.getPoint();
    		
    		/* Updates newMap */
    		newMap[location.x][location.y] = current.getDistance();
    		
    		/* Gets a list of all the surrounding Points */
    		LinkedList<Point> list = getAdjacent(location);
    		
    		/* Adds all the surrounding Points to the queue */
    		for (Point x : list) {
    			if (!visited.contains(x)) {
    				queue.add(new PointLocation(x, current.getDistance()+1));
    				visited.add(x);
    			}
    		}
    	}
    	return newMap;
    }
    
    /* Finds the best point */
    public Point nextPoint(Point location) {
    	int[][] newMap = findPath();
    	LinkedList<Point> locations = getAdjacent(location);
    	
    	Point bestPoint = null;
    	int lowestDistance = Integer.MAX_VALUE;
    	
    	/* Goes through all the locations to find the best point */
    	for (Point current  : locations) {
    		int distance = newMap[current.x][current.y];
    		if (bestPoint == null) {
    			lowestDistance = distance;
    			bestPoint = current;
    		} else {
    			if (distance < lowestDistance) {
    				lowestDistance = distance;
    				bestPoint = current;
    			}
    		}
    	}
    	return bestPoint;
    }
    
    /* Overwritten the equals method */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Chase other = (Chase) obj;
		
		return (other.map == map && 
				other.playerLocation.equals(playerLocation) &&
				other.enemyLocation.equals(enemyLocation));
	}
	
	/* Overwritten the hashCode method */
	public int hashCode() {
		int result = 0x12CD;
		result = 0xF7F * result + map.length + playerLocation.hashCode() + enemyLocation.hashCode();
		return result;
	}
	
	/* Overwritten the toString method */
	public String toString() {
		return "mapSize: " + (map.length * map.length) + 
				"\nplayerLocation: " + "(" + playerLocation.x + "," + playerLocation.y + ")" +
				"\nenemyLocation: "  + "(" + enemyLocation.x  + "," + enemyLocation.y  + ")";
	}
}

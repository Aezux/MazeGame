import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class DungeonMap {
	
	private int[][] map;
	private ArrayList<Integer> openSpots;
	private Point playerLocation;
	private int walls;
	
	Random rand = new Random();
	
	/* Constructor */
	public DungeonMap(int dimensions, int walls) {
		this.map = new int[dimensions][dimensions];
		this.walls = walls;
		openSpots = new ArrayList<Integer>();
		buildMap();
		setLocations();
	}
	
	/* Returns map */
	public int[][] getMap() {
		return map;
	}
	
	/* Returns the players location */
	public Point getPlayerLocation() {
		return playerLocation;
	}
	
	/* Returns a Point where there is an empty spot */
	private Point generateLocation() {
		int N = getRandomNumber(openSpots.size());
		Integer pos = openSpots.get(N);
		openSpots.remove(pos);
		int x = pos / map.length;
		int y = pos - (x * map.length);
		return new Point(x, y);
	}
	
	/* Sets the walls and the player's location */
	private void setLocations() {
		playerLocation = generateLocation();
		for (int i=0; i<walls; ++i) {
			Point wallSpot = generateLocation();
			int x = wallSpot.x, y = wallSpot.y;
			Integer pos = x * map.length + y;
			openSpots.remove(pos);
			map[x][y] = 1;
		}
	}
	
	/* Creates the map */
	private void buildMap() {
		for (int i=0; i<map.length; ++i) {
			for (int j=0; j<map.length; ++j) {
				
				/* Top */
				if (j == 0    ) {
					map[i][j] = 1;
				}
				
				/* Sides */
				else if (i == 0 || i == map.length - 1) {
					map[i][j] = 1;
				}
				
				/* Bottom */
				else if (j == map.length - 1) {
					map[i][j] = 1;
				}
				
				else {
					map[i][j] = 0;
					Integer pos = i * map.length + j;
					openSpots.add(pos);
				}
			}
		}
	}
	
	/* Generates a random number */
	private int getRandomNumber(int length) {
		return rand.nextInt(length);
	}

}

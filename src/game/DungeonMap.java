package game;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/* 
 *    Map Creation Codes:
 *    0: Floor
 *    1: Wall
 *    2: tile with light on the bottom
 *    3: N/A
 *    4: tile with light on the left
 *    5: N/A
 *    6: tile with light on the right
 *    7: trap
 *    8: tile with light on the top
 *    9: N/A
 *    
 * */

public class DungeonMap {
	
	private int[][] map;
	private ArrayList<Integer> openSpots;
	private Point playerLocation;
	private Point treasureLocation;
	private Point armorLocation;
	private Point invisibleLocation;
	private Point speedLocation;
	private Point keyLocation;
	private int dimensions;
	private int walls;
	private int traps = 3;
	@SuppressWarnings("unused")
	private Point[] RandomItems;
	
	Random rand = new Random();
	
	/* Constructor */
	public DungeonMap(int dimensions, int walls) {
		this.map = new int[dimensions][dimensions];
		this.dimensions = dimensions;
		this.walls = walls;
		openSpots = new ArrayList<Integer>();
		buildMap();
	}
	
	/* Returns map */
	public int[][] getMap() {
		return map;
	}
	
	/* Returns the player's location */
	public Point getPlayerLocation() {
		return playerLocation;
	}
	
	/* Returns the treasure's location */
	public Point getTreasureLocation() {
		return treasureLocation;
	}
	/* Sets the treasure's location to null after picked up */
	public void setTreasuretoNull() {
		treasureLocation = null;
	}
	
	/* Returns the armor's location */
	public Point getArmorLocation() {
		return armorLocation;
	}
	/* Sets the armor's location to null after picked up */
	public void setArmortoNull() {
		armorLocation = null;
	}
	/* Returns the invisible's Location */
	public Point getInvisibleLocation() {
		return invisibleLocation;
	}
	/* Sets the invisible location to null after picked up */
	public void setInvisbletoNull() {
		invisibleLocation = null;
	}
	/* Returns the speed's Location */
	public Point getSpeedLocation() {
		return speedLocation;
	}
	/* Sets the speed's location to null after picked up */
	public void setSpeedtoNull() {
		speedLocation = null;
	}
	public Point getKeyLocation() {
		return keyLocation;
	}
	public void setKeytoNull() {
		keyLocation = null;
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
	
	/* Sets all trap and wall locations */
	private void setLocations() {
		int x, y;

		/* Generate the traps */
		for (int i=0; i<traps; ++i) {
			Point trapSpot = generateLocation();
			x = trapSpot.x; y = trapSpot.y;
			Integer pos = x * map.length + y;
			openSpots.remove(pos);
			map[x][y] = 7;
		}
		
		/* Generate the inner walls */
		for (int i=0; i<walls; ++i) {
			Point wallSpot = generateLocation();
			x = wallSpot.x; y = wallSpot.y;
			Integer pos = x * map.length + y;
			openSpots.remove(pos);
			map[x][y] = 1;
		}
	}
	
	/* Sets the players and treasures location */
	private void setObjectives() {
		playerLocation = generateLocation();
		treasureLocation = generateLocation();
		armorLocation = generateLocation();
		invisibleLocation = generateLocation();
		speedLocation = generateLocation();
		keyLocation = generateLocation();
		
	}
	
	/* Builds all the exits on the map */
	private void buildExits() {
		int size = dimensions-1;
		int half = size/2;
		
		/* Top */
		map[half][0] = 8;
		
		/* Bottom */
		map[half][size] = 2;
		
		/* Left */
		map[0][half] = 4;
		
		/* Right */
		map[size][half] = 6;
	}
	
	/* Creates the map */
	private void buildMap() {
		for (int i=0; i<map.length; ++i) {
			for (int j=0; j<map.length; ++j) {
				
				/* Top */
				if (j == 0) {
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
		buildExits();
		setLocations();
		setObjectives();
	}
	
	/* Generates a random number */
	private int getRandomNumber(int length) {
		return rand.nextInt(length);
	}

}

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
 *    5: exit
 *    6: tile with light on the right
 *    7: trap
 *    8: tile with light on the top
 *    9: N/A
 *    
 * */

@SuppressWarnings("unused")
public class DungeonMap {
	
	private int[][] map;
	private ArrayList<Integer> openSpots;
	private ArrayList<Point> trapLocations;
	private Point playerLocation;
	private Point fireLocation;
	private Point ghostLocation;
	private Point zombieLocation;
	private Point treasureLocation;
	private Point armorLocation;
	private Point invisibleLocation;
	private Point speedLocation;
	private Point keyLocation;
	private Boolean hasKey;
	private Point exitLocation;
	private int dimensions;
	private int walls;
	private int traps = 3;
	private Point[] RandomItems;
	Random rand = new Random();
	
	/* Constructor */
	public DungeonMap(int dimensions, int walls) {
		this.map = new int[dimensions][dimensions];
		this.dimensions = dimensions;
		this.walls = walls;
		openSpots = new ArrayList<Integer>();
		trapLocations = new ArrayList<Point>();
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
	
	/* Returns the fireball's location */
	public Point getFireLocation() {
		return fireLocation;
	}
	
	/* Returns the fireball's location */
	public Point getGhostLocation() {
		return ghostLocation;
	}
	
	/* Returns the fireball's location */
	public Point getZombieLocation() {
		return zombieLocation;
	}
	
	/* Returns the trap locations */
	public ArrayList<Point> getTrapLocations() {
		return trapLocations;
	}
	
	/* Updates the player's location */
	public void setPlayerLocation(Point playerLocation) {
		this.playerLocation = playerLocation;
	}
	
	/* Returns an ArrayList of all the open locations */
	public ArrayList<Integer> getOpenLocations() {
		return openSpots;
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
	
	/* Return's the key's location */
	public Point getKeyLocation() {
		return keyLocation;
	}
	/* Sets the key's location to null after being picked up */
	public void setKeytoNull() {
		keyLocation = null;
	}
	/* Sets hasKey to true */
	public void setKeytoTrue() {
		hasKey = true;
		System.out.println("Picked up Key");
	}
	/* Used to check if Player has picked up key */
	public Boolean checkifhasKey() {
		return hasKey;
	}
	/* Return's the exit location */
	public Point getExitLocation() {
		return exitLocation;
	}
<<<<<<< HEAD
=======
	
>>>>>>> master_with_toolbar
	
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
		/* Generate the traps */
		for (int i=0; i<traps; ++i) {
			Point trapSpot = generateLocation();
			trapLocations.add(trapSpot);
		}
		
		/* Generate the inner walls */
		for (int i=0; i<walls; ++i) {
			Point wallSpot = generateLocation();
			map[wallSpot.x][wallSpot.y] = 1;
		}
	}
	
	/* Sets the player, enemies, and item locations */
	private void setObjectives() {
		playerLocation = generateLocation();
		fireLocation = generateLocation();
		zombieLocation = generateLocation();
		ghostLocation = generateLocation();
		treasureLocation = generateLocation();
		armorLocation = generateLocation();
		invisibleLocation = generateLocation();
		speedLocation = generateLocation();
		keyLocation = generateLocation();
		exitLocation = new Point(7,0);
	} 
	
	/* Builds all the exits on the map */
	private void buildExits() {
		int size = dimensions-1;
		int half = size/2;
		
		/* Builds all the doorways */
		map[half][0] = 8;
		map[half][size] = 2;
		map[0][half] = 4;
		map[size][half] = 6;
		map[7][0] = 5;
	}
	
	/* Creates the map */
	private void buildMap() {
		for (int i=0; i<map.length; ++i) {
			for (int j=0; j<map.length; ++j) {

				/* Makes all the sides walls */
				if (j == 0 || (i == 0 || i == map.length-1) || (j == map.length-1)) {
					map[i][j] = 1;
				}
				
				/* Makes sure the outer area isn't covered */
				else if ((j == 1 && (i != 0 && i != map.length-1)) ||
						((i == 1 || i == map.length-2) && j != map.length-1) ||
						(j == map.length-2 && (i != 0 && i != map.length-1))) {
					map[i][j] = 0;
				}
				
				else { /* Makes the rest of the spots open */
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

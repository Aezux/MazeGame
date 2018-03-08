package game;

import java.awt.Point;
import javafx.scene.image.Image;
 
@SuppressWarnings("unused")
public class Player {

	private int[][] map;
	private DungeonMap dungeon;
	private Point currentLocation;
	private boolean hasKey = false;
	private int lives;
	private Image image;
	private int X, Y;
	private boolean atChest = false;
	private boolean openChest = false;
	
	/* Constructor */
	public Player(DungeonMap map) {
		this.lives = 1;
		this.dungeon = map;
		this.map = map.getMap();
		this.currentLocation = map.getPlayerLocation();
		updateCoordinates();
	}
	
	/* Updates the variables X and Y */
	private void updateCoordinates() {
		this.X = (int) currentLocation.getX();
		this.Y = (int) currentLocation.getY();
	}

	/* Returns the player's location */
	public Point getPlayerLocation() {
		return currentLocation;
	}
	
	/* Gives the player an extra life */
	public void addLife() {
		++lives;
	}
	
	/* Takes away a life from the player */
	public void loseLife() {
		--lives;
		if (lives <= 0) {
			System.out.println("You Lose!");
		}
	}
<<<<<<< HEAD
	public void openChest()
	{
=======
	public void openChest() {
>>>>>>> master_with_toolbar
		openChest = true;
	}
	
	public boolean hasOpenedChest()
	{
		return openChest;
	}
	
	public void addKey()
	{
		this.hasKey = true;
	}
	
	public void removeKey()
	{
		this.hasKey = false;
	}
	
	public boolean hasKey()
	{
		return this.hasKey;
	}
	
	public void setAtChest()
	{
		this.atChest = true;
	}
	
	public void setAwayChest()
	{
		this.atChest = false;
	}
	
	public boolean atChest()
	{
		return this.atChest;
	}
<<<<<<< HEAD
	
	
=======
>>>>>>> master_with_toolbar
	
	/* Checks if the player is in bounds */
	private boolean checkBounds(int x, int y, int size) {
        return ((x-1 >= -1) && (x + 1 <= size) && (y-1 >= -1) && (y+1 <= size));
    }
	
	/* Checks if there is a wall */
	private boolean checkWall(int x, int y) {
		if (map[x][y] == 5) {
			return !checkifHasKey();
		}
		return map[x][y] == 1;
	}
	/* Sets hasKey to true */
	public void setKeytoTrue() {
		hasKey = true;
		System.out.println("Picked up Key");
	}
	/* Used to check if Player has picked up key */
	public Boolean checkifHasKey() {
		return hasKey;
	}
	

	/* Gets the new image of the player */
	public Image newImage() {
		return image;
	}
	
	/* Move the player */
	public void move(String direction) {
		updateCoordinates();
		switch (direction) {
			case "North": --Y; image = getImage("backView.png"); break;
			case "South": ++Y; image = getImage("frontView.png"); break;
			case "East":  ++X; image = getImage("rightView.png"); break;
			case "West":  --X; image = getImage("leftView.png"); break;
		}
		
		if (checkBounds(X, Y, map.length) && !checkWall(X, Y)) {
			currentLocation.setLocation(X, Y);
			dungeon.setPlayerLocation(currentLocation);
		}
	}

	/* Gets the correct image regardless of what operating system you are using */
	private Image getImage(String type) {
		String file;
		if (System.getProperty("os.name").startsWith("Windows")) {
			file = "file:images\\" + type;
		} else {
			file = "file:images//" + type;
		}
		Image image = new Image(file, 50, 50, true, true);
		return image;
	}
}

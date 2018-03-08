package game;

import java.awt.Point;
import java.util.Observable;
import javafx.scene.image.Image;
 
public class Player extends Observable {

	private int[][] map;
	private int scale = 25;
	private int lives;
	private int X, Y;
	
	private DungeonMap dungeon;
	private Point currentLocation;
	private Image image;
	
	private boolean hasKey = false;
	private boolean atChest = false;
	private boolean openChest = false;
	private boolean winCondition = false;
	private boolean wonGame = false;
	private boolean lostGame = false;
	
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
			lostGame = true;
		}
	}
	
	/* Checks if the player has won */
	public boolean hasWon() {
		return wonGame;
	}
	
	/* Checks if the player lost the game */
	public boolean hasLost() {
		return lostGame;
	}

	public void openChest()
	{
		openChest = true;
	}
	
	public boolean hasOpenedChest()
	{
		return openChest;
	}
	
	public void usedChest() {
		openChest = false;
		winCondition = true;
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
	
	/* Checks if the player is in bounds */
	private boolean checkBounds(int x, int y, int size) {
        return ((x-1 >= -1) && (x + 1 <= size) && (y-1 >= -1) && (y+1 <= size));
    }
	
	/* Checks if there is a wall */
	private boolean checkWall(int x, int y) {
		if (map[x][y] == 5) {
			return !winCondition;
		}
		return map[x][y] == 1;
	}
	
	/* Sets hasKey to true */
	public void setKeytoTrue() {
		hasKey = true;
		alert("key");
	}
	
	/* Used to check if Player has picked up key */
	public Boolean checkifHasKey() {
		return hasKey;
	}

	/* Gets the new image of the player */
	public Image newImage() {
		return image;
	}
	
	/* Updates the Observers */
	public void alert(String argument) {
		setChanged();
		notifyObservers(argument);
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
		if (map[X][Y] == 5) {
			wonGame = true;
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
		Image image = new Image(file, scale, scale, true, true);
		return image;
	}
}

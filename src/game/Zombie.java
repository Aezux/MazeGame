package game;

import java.awt.Point;
import java.util.Observable;
import javafx.scene.Node;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/* The zombie class */
public class Zombie extends Enemy{

	private Point currentLocation;
	private Observable player;
	private DungeonMap map;
	private Movement movement;
	private Image image;
	private ImageView zombieImage;
	private int sleepTime;
	
	/* Constructor */
	public Zombie(DungeonMap map, Observable player, Point currentLocation) {
		this.map = map;
		player.addObserver(this);
		this.player = player;
		this.currentLocation = currentLocation;
		this.movement = new Patrol(map, currentLocation);
		this.sleepTime = 300;
		setImage();
	}
	
	/* Adds the zombie to the pane */
	public void addToPane(ObservableList<Node> sceneGraph) {
		sceneGraph.add(zombieImage);
	}
	
	/* Sets the movement */
	public void changeMovement(Movement movement) {
		this.movement = movement;
	}
	
	/* The player notifies the enemies */
	public void update(Observable o, Object arg) {
		if (o instanceof Player) {
			this.movement = new Chase(map, currentLocation);
			sleepTime = 100;
		}
	}
	
	/* Launches the zombie thread */
	public void run() {
		while (gameShouldRun) {
			try {/* Put the thread to sleep */
				Thread.sleep(sleepTime);
			} catch (Exception e) {}
			
			/* Moves around */
			Point move = movement.nextPoint(currentLocation);
			
			/* Gets players location and update coordinates */
			Point playerLocation = map.getPlayerLocation();
			updateImage(currentLocation.x, move.x, currentLocation.y, move.y);
			currentLocation.setLocation(move.x, move.y);
			zombieImage.setX(move.x * scale);
			zombieImage.setY(move.y * scale);
			
			/* If enemy touches player then take away one life */
			if (playerLocation.equals(move)) {
				Player user = (Player)player;
				user.loseLife();
			}
		}
	}
	
	/* Starting position for the fireball */
	private void setImage() {
		image = getImage("zombie_front.png");
		zombieImage = new ImageView(image);
		zombieImage.setX(currentLocation.x * scale);
		zombieImage.setY(currentLocation.y * scale);
	}
	
	/* Updates the zombie image */
	private void updateImage(int oldX, int newX, int oldY, int newY) {
		if (newX > oldX) {
			image = getImage("zombie_right.png");
		} else if (newX < oldX) {
			image = getImage("zombie_left.png");
		} else if (newY > oldY) {
			image = getImage("zombie_front.png");
		} else if (newY < oldY) {
			image = getImage("zombie_back.png");
		}
		zombieImage.setImage(image);
	}
	
}

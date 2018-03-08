package game;

import java.awt.Point;
import java.util.Observable;
import javafx.scene.Node;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/* The fireball class */
public class Fireball extends Enemy{

	private Point currentLocation;
	private Observable player;
	private DungeonMap map;
	private Movement movement;
	private Image image;
	private ImageView fireImage;
	private int sleepTime;
	
	/* Constructor */
	public Fireball(DungeonMap map, Observable player, Point currentLocation) {
		this.map = map;
		player.addObserver(this);
		this.player = player;
		this.currentLocation = currentLocation;
		this.movement = new Chase(map, currentLocation);
		this.sleepTime = 200;
		setImage();
	}
	
	/* Adds the fireball to the pane */
	public void addToPane(ObservableList<Node> sceneGraph) {
		sceneGraph.add(fireImage);
	}
	
	/* Sets the movement */
	public void changeMovement(Movement movement) {
		this.movement = movement;
	}
	
	/* The player notifies the enemies */
	public void update(Observable o, Object arg) {
		if (o instanceof Player) {
			switch (arg.toString()) {
				case "key": 
					this.movement = new Chase(map, currentLocation);
					sleepTime = 100;
				break;
				
				case "speed": 
					this.sleepTime = sleepTime * 2;
				break;
				
				case "patrol": 
					this.movement = new Patrol(map, currentLocation);
				break;
				
				default: break;
			}
		}
	}
	
	/* Launches the fireball thread */
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
			fireImage.setX(move.x * scale);
			fireImage.setY(move.y * scale);
			
			/* If enemy touches player then take away one life */
			if (playerLocation.equals(move)) {
				Player user = (Player)player;
				user.loseLife();
			}
		}
	}
	
	/* Starting position for the fireball */
	private void setImage() {
		image = getImage("fire_front.png");
		fireImage = new ImageView(image);
		fireImage.setX(currentLocation.x * scale);
		fireImage.setY(currentLocation.y * scale);
	}
	
	/* Updates the fireball image */
	private void updateImage(int oldX, int newX, int oldY, int newY) {
		if (newX > oldX) {
			image = getImage("fire_right.png");
		} else if (newX < oldX) {
			image = getImage("fire_left.png");
		} else if (newY > oldY) {
			image = getImage("fire_front.png");
		} else if (newY < oldY) {
			image = getImage("fire_back.png");
		}
		fireImage.setImage(image);
	}

}

package game;

import java.awt.Point;
import javafx.scene.Node;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.util.Observable;

/* The ghost class */
public class Ghost extends Enemy{

	private Point currentLocation;
	private Observable player;
	private DungeonMap map;
	private Movement movement;
	private Image image;
	private ImageView ghostImage;
	private int sleepTime;
	
	/* Constructor */
	public Ghost(DungeonMap map, Observable player, Point currentLocation) {
		this.map = map;
		player.addObserver(this);
		this.player = player;
		this.currentLocation = currentLocation;
		this.movement = new Patrol2(map, currentLocation);
		this.sleepTime = 150;
		setImage();
	}
	
	/* Adds the ghost to the pane */
	public void addToPane(ObservableList<Node> sceneGraph) {
		sceneGraph.add(ghostImage);
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
	
	/* Launches the ghost thread */
	public void run() {
		while (gameShouldRun) {
			ArrayList<Point> moves = movement.getPath(currentLocation);
			/* Moves around */
			for (Point move : moves) {
				try {/* Put the thread to sleep */
					Thread.sleep(sleepTime);
				} catch (Exception e) {}
				
				/* Gets players location and update coordinates */
				Point playerLocation = map.getPlayerLocation();
				updateImage(currentLocation.x, move.x, currentLocation.y, move.y);
				currentLocation.setLocation(move.x, move.y);
				ghostImage.setX(move.x * scale);
				ghostImage.setY(move.y * scale);
				
				/* If enemy touches player then take away one life */
				if (playerLocation.equals(move)) {
					Player user = (Player)player;
					user.loseLife();
				}
			}
		}
	}
	
	/* Starting position for the ghost */
	private void setImage() {
		image = getImage("ghost_front.png");
		ghostImage = new ImageView(image);
		ghostImage.setX(currentLocation.x * scale);
		ghostImage.setY(currentLocation.y * scale);
	}
	
	/* Updates the ghost image */
	private void updateImage(int oldX, int newX, int oldY, int newY) {
		if (newX > oldX) {
			image = getImage("ghost_right.png");
		} else if (newX < oldX) {
			image = getImage("ghost_left.png");
		} else if (newY > oldY) {
			image = getImage("ghost_front.png");
		} else if (newY < oldY) {
			image = getImage("ghost_back.png");
		}
		ghostImage.setImage(image);
	}

}

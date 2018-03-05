package game;

import java.awt.Point;
//import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public abstract class Enemy implements Observer{
	private Point Location;
	private String name;
	private Movement movement;
	private Image image;
	private ImageView enemyImage;
	private ExecutorService threads;
	private Point currentLocation;
	private int scale = 50;
	private boolean gameShouldRun;


	public String getName() { 
		return name; 
	}
	public void setName(String newName) {
		name = newName; 
	}

	public void followPlayer(){
		System.out.println(getName() + " is following the hero");
    }

	public void patrol() {
		System.out.println(getName() + " is patroling the map");
	}

	public Point getFireLocation() {
		return Location;
	}
	public void changeMovement(Movement movement) {
		this.movement = movement;
	}
	public Image newImage() {
		return image;
	}
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
	public void addToPane(ObservableList<Node> sceneGraph) {
		sceneGraph.add(enemyImage);
	}
	public void startMoving() {
		threads.submit(() -> update());
	}
	private void update() {
		int sleepTime = 175;
		while (gameShouldRun) {
			
			try { // Put the thread to sleep
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {}
			
			/* Moves around */
			Point move = movement.nextPoint(currentLocation);
			updateImage(currentLocation.x, move.x, currentLocation.y, move.y);
			currentLocation.setLocation(move.x, move.y);
			enemyImage.setX(move.x * scale);
			enemyImage.setY(move.y * scale);
		}
	}
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
		enemyImage.setImage(image);
	}
	
}
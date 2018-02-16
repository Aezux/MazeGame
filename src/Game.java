
import java.util.Random;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Game extends Application{
	
	Random rand = new Random();

	int[][] map;
	final int dimensions = 15;
	final int walls = 15;
	final int scale = 50;
	
	Player player;
	DungeonMap dungeonMap;
	
	
	ImageView playerView;
	ImageView treasureView;
	
	Stage mainStage;
	Scene Gamescene;
	Pane root;
	
	/* Launches the GUI */
	public static void main(String[] args) {
		launch(args);
	}

	/* Starts the whole program with the input scene */
	public void start(Stage game) throws Exception {
		mainStage = game;

		/* Creates the pane and randomizes the whole map */
		root = new AnchorPane();
		Gamescene = new Scene(root, scale*dimensions, scale*dimensions);
		
		dungeonMap = new DungeonMap(dimensions, walls);
		map = dungeonMap.getMap();
		player = new Player(dungeonMap);
		
		/* Places all the pictures on the pane and allows the player to control the ship */
		loadMap();
		movePlayer();
		
		/* Sets the title and displays the GUI */
		mainStage.setTitle("Maze Game");
		mainStage.setScene(Gamescene);
		mainStage.show();
	}
	
	/* The player controls the ship with the keyboard */
	public void movePlayer() {
		
		Gamescene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent ke) {
				
				/* Moves the ship using the keyboard */
				switch(ke.getCode()) {
					case UP:
						player.move("North"); 
						playerView.setImage(getImage("backView.png"));
					break;
					case DOWN:
						player.move("South");
						playerView.setImage(getImage("frontView.png"));
					break;
					case LEFT:
						player.move("West");
						playerView.setImage(getImage("leftView.png"));
						break;
					case RIGHT:
						player.move("East");
						playerView.setImage(getImage("rightView.png"));
						break;
					default: break;
				}
				
				/* Get and set the players coordinates */
				int playerX = player.getPlayerLocation().x;
				int playerY = player.getPlayerLocation().y;
				playerView.setX(playerX * scale);
				playerView.setY(playerY * scale);
			}
		});
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

	/* Adds pictures to the pane */
	public void loadMap() {
	
		/* Adds the wall and floor images */
		ImageView imageView;
		Image image;
		for (int i=0; i<dimensions; ++i) {
			for (int j=0; j<dimensions; ++j) {
				
				int rng = getRandomNumber(3) + 1;
				
				switch (map[i][j]) {
					case 0: image = getImage("floor" + rng + ".png"); break;
					case 1: image = getImage("wall" + rng + ".png"); break;
					case 2: image = getImage("downLight.png"); break;
//					case 3: break;
					case 4: image = getImage("leftLight.png"); break;
//					case 5: break;
					case 6: image = getImage("rightLight.png"); break;
					case 7: image = getImage("trap.png"); break;
					case 8: image = getImage("upLight.png"); break;
//					case 9: break;
					default: image = null; break;
				}
				
				imageView = new ImageView(image);
				imageView.setX(i * scale);
				imageView.setY(j * scale);
				root.getChildren().add(imageView);
			}
		}
		
		/* Adds the treasure image */
		treasureView = new ImageView(getImage("chest.png"));
		treasureView.setX(dungeonMap.getTreasureLocation().x * scale);
		treasureView.setY(dungeonMap.getTreasureLocation().y * scale);
		root.getChildren().add(treasureView);
		
		/* Adds the player image */
		playerView = new ImageView(getImage("backView.png"));
		playerView.setX(player.getPlayerLocation().x * scale);
		playerView.setY(player.getPlayerLocation().y * scale);
		root.getChildren().add(playerView);
	}
	
	/* Generates a random number */
	private int getRandomNumber(int length) {
		return rand.nextInt(length);
	}

}

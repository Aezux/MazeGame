package game;

import java.util.Random;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import powerups.*;

import java.awt.Point;

@SuppressWarnings("unused")
public class Game extends Application {

	Random rand = new Random();

	int[][] map;
	final int dimensions = 15;
	final int walls = 15;
	final int scale = 50;

	Player player;
	DungeonMap dungeonMap;

	ImageView playerView;
	ImageView treasureView;
	ImageView invisibleView;
	ImageView speedView;
	ImageView armorView;
	ImageView keyView;

	Stage mainStage;
	Scene Gamescene;
	Pane root;

	/* Launches the GUI */
	public static void main(String[] args) {
		launch(args);
	}

	/* Starts the whole program with the input scene */
	public void start(Stage game) throws Exception {
		/* Creates a menubar on the top containing File, Restart, Quit */
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("File");
		MenuItem restart = new MenuItem("Restart");
		MenuItem quit = new MenuItem("Quit");
		quit.setOnAction(e -> Platform.exit());
		menu.getItems().add(restart);
		menu.getItems().add(quit);
		menuBar.getMenus().add(menu);
		menuBar.setUseSystemMenuBar(true);
		BorderPane borderpane = new BorderPane();
		borderpane.setTop(menuBar);

		mainStage = game;
		/* Creates the pane and randomizes the whole map */
		root = new AnchorPane();
		/* Adds the menu bar to the root */
		root.getChildren().add(borderpane);
		Gamescene = new Scene(root, scale * dimensions, scale * dimensions);

		dungeonMap = new DungeonMap(dimensions, walls);
		map = dungeonMap.getMap();
		player = new Player(dungeonMap);

		/*
		 * Places all the pictures on the pane and allows the player to control the ship
		 */
		loadMap();
		movePlayer();

		/* Sets the title and displays the GUI */
		mainStage.setTitle("Maze Game");
		mainStage.setScene(Gamescene);
		mainStage.show();

	}

	/* The player controls the knight with the keyboard */
	public void movePlayer() {

		Gamescene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent ke) {

				/* Moves the ship using the keyboard */
				switch (ke.getCode()) {
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
				default:
					break;
				}

				/* Get and set the players coordinates */
				int playerX = player.getPlayerLocation().x;
				int playerY = player.getPlayerLocation().y;
				playerView.setX(playerX * scale);
				playerView.setY(playerY * scale);

				/*
				 * Check if playerlocation is same as gem, if so play collectedgem.wav and
				 * remove gem from dungeonMap, sets the invisibleLocation to null
				 */
				if (player.getPlayerLocation().equals(dungeonMap.getInvisibleLocation())) {
					BackgroundMusic.collectedgem.play();
					invisibleView.imageProperty().set(null);
					dungeonMap.setInvisbletoNull();
				}
				/*
				 * Check if playerlocation is same as armor, if so play collectedgem.wav and
				 * remove armor from dungeonMap, sets the ArmorLocation to null
				 */
				if (player.getPlayerLocation().equals(dungeonMap.getArmorLocation())) {
					BackgroundMusic.collectedgem.play();
					armorView.imageProperty().set(null);
					dungeonMap.setArmortoNull();
				}
				/*
				 * Check if playerlocation is same as speed, if so play collectedgem.wav and
				 * remove speed from dungeonMap, sets the SpeedLocation to null
				 */
				if (player.getPlayerLocation().equals(dungeonMap.getSpeedLocation())) {
					BackgroundMusic.collectedgem.play();
					speedView.imageProperty().set(null);
					dungeonMap.setSpeedtoNull();
				}
				/*
				 * Check if playerlocation is same as treasure, if so play chestopen.wav and
				 * remove treasure from dungeonMap, sets the TreasureLocation to null
				 */
				if (player.getPlayerLocation().equals(dungeonMap.getTreasureLocation())) {
					BackgroundMusic.chestopen.play();
					treasureView.imageProperty().set(null);
					dungeonMap.setTreasuretoNull();
				}
				
				/*
				 * Check if playerlocation is same as key, if so play keycollected.wav and
				 * remove key from dungeonMap, sets the KeyLocation to null
				 */
				if (player.getPlayerLocation().equals(dungeonMap.getKeyLocation())) {
					BackgroundMusic.chestopen.play();
					keyView.imageProperty().set(null);
					dungeonMap.setTreasuretoNull();
				}


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
		for (int i = 0; i < dimensions; ++i) {
			for (int j = 0; j < dimensions; ++j) {

				int rng = getRandomNumber(3) + 1;

				switch (map[i][j]) {
				case 0:
					image = getImage("floor" + rng + ".png");
					break;
				case 1:
					image = getImage("wall" + rng + ".png");
					break;
				case 2:
					image = getImage("downLight.png");
					break;
				// case 3: break;
				case 4:
					image = getImage("leftLight.png");
					break;
				// case 5: break;
				case 6:
					image = getImage("rightLight.png");
					break;
				case 7:
					image = getImage("trap.png");
					break;
				case 8:
					image = getImage("upLight.png");
					break;
				// case 9: break;
				default:
					image = null;
					break;
				}

				imageView = new ImageView(image);
				imageView.setX(i * scale);
				imageView.setY(j * scale);
				root.getChildren().add(imageView);

			}
		}

		/* Adds the treasure image */
		treasureView = new ImageView(getImage("chest1.png"));
		treasureView.setX(dungeonMap.getTreasureLocation().x * scale);
		treasureView.setY(dungeonMap.getTreasureLocation().y * scale);
		root.getChildren().add(treasureView);

		/* Adds the armor image */
		armorView = new ImageView(getImage("armor.png"));
		armorView.setX(dungeonMap.getArmorLocation().x * scale);
		armorView.setY(dungeonMap.getArmorLocation().y * scale);
		root.getChildren().add(armorView);

		/* Adds the invisible image */
		invisibleView = new ImageView(getImage("gem.png"));
		invisibleView.setX(dungeonMap.getInvisibleLocation().x * scale);
		invisibleView.setY(dungeonMap.getInvisibleLocation().y * scale);
		root.getChildren().add(invisibleView);

		/* Adds the speed image */
		speedView = new ImageView(getImage("speed.png"));
		speedView.setX(dungeonMap.getSpeedLocation().x * scale);
		speedView.setY(dungeonMap.getSpeedLocation().y * scale);
		root.getChildren().add(speedView);
		
		/* Adds the player image */
		playerView = new ImageView(getImage("backView.png"));
		playerView.setX(player.getPlayerLocation().x * scale);
		playerView.setY(player.getPlayerLocation().y * scale);
		root.getChildren().add(playerView);
		
		/* Adds the key image */
		keyView = new ImageView(getImage("key.png"));
		keyView.setX(dungeonMap.getKeyLocation().x * scale);
		keyView.setY(dungeonMap.getKeyLocation().y * scale);
		root.getChildren().add(keyView);

		/* Loops through Background Music */
		BackgroundMusic.backgroundmusic.loop();
	}

	/* Generates a random number */
	private int getRandomNumber(int length) {
		return rand.nextInt(length);
	}

}

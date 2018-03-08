package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
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
import javafx.stage.Screen;
import javafx.stage.Stage;
import powerups.*;

import java.awt.Point;

@SuppressWarnings("unused")
public class Game extends Application {

	Random rand = new Random();

	int[][] map;
	final int dimensions = 40;
	final int walls = 250;
	final int scale = 25;

	Player player;
	
	EnemyFactory enemyFactory;
	ArrayList<Enemy> traps;
	ArrayList<Enemy> fireballs;
	ArrayList<Enemy> zombies;
	ArrayList<Enemy> ghosts;
	
	DungeonMap dungeonMap;
	
	Toolbar toolbar;

	ImageView playerView;
	ImageView treasureView;
	ImageView invisibleView;
	ImageView speedView;
	ImageView armorView;
	ImageView keyView;
	ImageView exitView;
	
	ImageView ToolbarViewSlot1;
	ImageView ToolbarViewSlot2;
	ImageView ToolbarViewSlot3;
	ImageView ToolbarViewSlot4;
	
	ImageView keyViewToolbar;
	ImageView invisibleViewToolbar;
	ImageView speedViewToolbar;
	ImageView armorViewToolbar;

	Stage mainStage;
	Scene Gamescene;
	Pane root;
	
	Object[] Items = new Object[3];

	/* Launches the GUI */
	public static void main(String[] args) {
		launch(args);
		System.exit(0);
	}

	/* Starts the whole program with the input scene */
	public void start(Stage game) throws Exception {
		/* Creates a menu bar on the top containing File, Restart, Quit */
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
		

		//set toolbar and its slots
		toolbar = new Toolbar();
//		toolbar.setPower(1, new ArmorActivate(new Armor(dungeonMap)));
//		toolbar.setPower(2, new InvisibleActivate(new Invisible(dungeonMap)));
//		toolbar.setPower(3, new KeyActivate(new Key(dungeonMap)));
//		toolbar.setPower(4, new SpeedActivate(new Speed(dungeonMap)));
		
		player = new Player(dungeonMap);
		
		/* Creates the enemies */
		this.traps = new ArrayList<Enemy>();
		this.fireballs = new ArrayList<Enemy>();
		this.ghosts = new ArrayList<Enemy>();
		this.zombies = new ArrayList<Enemy>();
		
		enemyFactory = new EnemyFactory();
//		fireBall = enemyFactory.getEnemy("FIRE", dungeonMap, dungeonMap.getFireLocation());
//		ghost = enemyFactory.getEnemy("GHOST", dungeonMap, dungeonMap.getGhostLocation());
//		zombie = enemyFactory.getEnemy("ZOMBIE", dungeonMap, dungeonMap.getZombieLocation());

		/*
		 * Places all the pictures on the pane and allows the user to control the player
		 */
		loadMap();
		
		ArrayList<Point> trapPoints = dungeonMap.getTrapLocations();
		spawnEnemies("TRAP", trapPoints, traps);
		
		ArrayList<Point> firePoints = dungeonMap.getFireLocations();
		spawnEnemies("FIRE", firePoints, fireballs);
		
		ArrayList<Point> ghostPoints = dungeonMap.getGhostLocations();
		spawnEnemies("GHOST", ghostPoints, ghosts);
		
		ArrayList<Point> zombiePoints = dungeonMap.getZombieLocations();
		spawnEnemies("ZOMBIE", zombiePoints, zombies);

		movePlayer();
//		fireBall.stopMoving();
//		for (Trap trap : traps) {
//			trap.stopMoving();
//		}
		
//		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
//
//        //set Stage boundaries to visible bounds of the main screen
//		mainStage.setX(primaryScreenBounds.getMinX());
//		mainStage.setY(primaryScreenBounds.getMinY());
//		mainStage.setWidth(primaryScreenBounds.getWidth());
//		mainStage.setHeight(primaryScreenBounds.getHeight());
//		
//		/* Sets the title and displays the GUI */
//		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
//		mainStage.setX(primaryScreenBounds.getMinX());
//		mainStage.setY(primaryScreenBounds.getMinY());
//		mainStage.setWidth(primaryScreenBounds.getWidth());
//		mainStage.setHeight(primaryScreenBounds.getHeight());
		mainStage.setTitle("Maze Game");
		mainStage.setScene(Gamescene);
		mainStage.show();
	}

	public void spawnEnemies(String type, ArrayList<Point> enemyLocations, ArrayList<Enemy> enemyList) {
		for (Point location : enemyLocations) {
			Enemy enemy = enemyFactory.getEnemy(type, player, dungeonMap, location);
			enemy.addToPane(root.getChildren());
			enemyList.add(enemy);
			Thread enemyThread = new Thread(enemy);
			enemyThread.start();
		}
	}
	
//	//The key bindings for all of the toolbar items.
//	public void useToolbar()
//	{
//		Gamescene.setOnKeyPressed(new EventHandler<KeyEvent>() {
//			public void handle(KeyEvent event) {
//
//				/* Moves the ship using the keyboard */
//				switch (event.getCode()) {
//					//keep parameter same as digit key, handled offset in Toolbar
//					case DIGIT1: toolbar.useSlot(1); break;
//					case DIGIT2: toolbar.useSlot(2); break;
//					case DIGIT3: toolbar.useSlot(3); break;
//					case DIGIT4: toolbar.useSlot(4); break;
//					default: break;
//				}
//			}
//		});
//	}

	/* The player controls the knight with the keyboard */
	public void movePlayer() {

		Gamescene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {

				/* Moves the ship using the keyboard */
				switch (event.getCode()) {
					case UP: player.move("North"); break;
					case DOWN: player.move("South"); break;
					case LEFT: player.move("West"); break;
					case RIGHT: player.move("East"); break;
					//Toolbar keybinds here
					//keep parameter same as digit key, handled offset in Toolbar
					case DIGIT1: toolbar.useSlot(1); armorViewToolbar.imageProperty().set(null); break;
					case DIGIT2: toolbar.useSlot(2); invisibleViewToolbar.imageProperty().set(null); break;
					case DIGIT3: toolbar.useSlot(3); keyViewToolbar.imageProperty().set(null); break;
					case DIGIT4: toolbar.useSlot(4); speedViewToolbar.imageProperty().set(null); break;
					default: break;
				}
				
				/* Update the player */
				playerView.setImage(player.newImage());
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
					invisibleViewToolbar = new ImageView(getImage("gem.png"));
					invisibleViewToolbar.setX(50.0);
					invisibleViewToolbar.setY(705.00);
					root.getChildren().add(invisibleViewToolbar);
					toolbar.setPower(2, new InvisibleActivate(new Invisible(dungeonMap, player)));
				}
				/*
				 * Check if playerlocation is same as armor, if so play collectedgem.wav and
				 * remove armor from dungeonMap, sets the ArmorLocation to null
				 */
				if (player.getPlayerLocation().equals(dungeonMap.getArmorLocation())) {
					BackgroundMusic.collectedgem.play();
					armorView.imageProperty().set(null);
					dungeonMap.setArmortoNull();
					armorViewToolbar = new ImageView(getImage("armor.png"));
					armorViewToolbar.setX(0.0);
					armorViewToolbar.setY(705.00);
					root.getChildren().add(armorViewToolbar);
					toolbar.setPower(1, new ArmorActivate(new Armor(dungeonMap, player)));
				}
				/*
				 * Check if playerlocation is same as speed, if so play collectedgem.wav and
				 * remove speed from dungeonMap, sets the SpeedLocation to null
				 */
				if (player.getPlayerLocation().equals(dungeonMap.getSpeedLocation())) {
					BackgroundMusic.collectedgem.play();
					speedView.imageProperty().set(null);
					dungeonMap.setSpeedtoNull();
					speedViewToolbar = new ImageView(getImage("speed.png"));
					speedViewToolbar.setX(150.0);
					speedViewToolbar.setY(705.00);
					root.getChildren().add(speedViewToolbar);
					toolbar.setPower(4, new SpeedActivate(new Speed(dungeonMap, player)));
				}
				/*
				 * Check if playerlocation is same as treasure, if so play chestopen.wav and
				 * remove treasure from dungeonMap, sets the TreasureLocation to null
				 */
				if (player.getPlayerLocation().equals(dungeonMap.getTreasureLocation())) {
					player.setAtChest();
				}else {
					player.setAwayChest();
				}
				if (player.hasOpenedChest()) {
					Items[0] = new Armor(dungeonMap, player);
					Items[1] = new Invisible(dungeonMap, player);
					Items[2] = new Speed(dungeonMap, player);
					Object randomItem = Items[(int)(Math.random() * Items.length)];
					BackgroundMusic.chestopen.play();
					treasureView.imageProperty().set(null);
					dungeonMap.setTreasuretoNull();
				}
				
				/*
				 * Check if playerlocation is same as key, if so play keycollected.wav and
				 * remove key from dungeonMap, sets the KeyLocation to null
				 */
				if (player.getPlayerLocation().equals(dungeonMap.getKeyLocation())) {
					BackgroundMusic.keycollected.play();
					keyView.imageProperty().set(null);
					dungeonMap.setKeytoNull();
					player.setKeytoTrue();
					keyViewToolbar = new ImageView(getImage("key.png"));
					keyViewToolbar.setX(100.0);
					keyViewToolbar.setY(705.00);
					root.getChildren().add(keyViewToolbar);
					toolbar.setPower(3, new KeyActivate(new Key(dungeonMap, player)));
				}
				/*
				 * Check if player has the key and checks if playerLocation is the same as the
				 * exitLocation. Plays fade to white transition to exit the game back to intro
				 */
				if (player.checkifHasKey() == true && player.getPlayerLocation().equals(dungeonMap.getExitLocation())) {
					exitView.setImage(getImage("door2.png"));
					exitView.setImage(getImage("door3.png"));
					exitView.setImage(getImage("door4.png"));
					exitView.setImage(getImage("door5.png"));
					exitView.setImage(getImage("door6.png"));
				}
			}
		});
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
					case 0: image = getImage("floor" + rng + ".png"); break;
					case 1: image = getImage("wall" + rng + ".png"); break;
					case 2: image = getImage("downLight.png"); break;
					case 4: image = getImage("leftLight.png"); break;
					case 5: image = getImage("door1.png"); break;
					case 6: image = getImage("rightLight.png"); break;
					case 8: image = getImage("upLight.png"); break;
					default: image = null; break;
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
		
		/* Adds the key image */
		keyView = new ImageView(getImage("key.png"));
		keyView.setX(dungeonMap.getKeyLocation().x * scale);
		keyView.setY(dungeonMap.getKeyLocation().y * scale);
		root.getChildren().add(keyView);
		
		/* Adds the player image */
		playerView = new ImageView(getImage("backView.png"));
		playerView.setX(player.getPlayerLocation().x * scale);
		playerView.setY(player.getPlayerLocation().y * scale);
		root.getChildren().add(playerView);

		/* Adds the door image */
		exitView = new ImageView(getImage("door1.png"));
		exitView.setX(dungeonMap.getExitLocation().x * scale);
		exitView.setY(dungeonMap.getExitLocation().y * scale);
		root.getChildren().add(exitView);
	
		/* Adds a slot for Armor Activate  */
		Image ToolbarImage = getImage("Individual.png");
		ToolbarViewSlot1 = new ImageView(ToolbarImage);
		ToolbarViewSlot1.setSmooth(false);
		ToolbarViewSlot1.setFitWidth((int) ToolbarImage.getWidth());
		ToolbarViewSlot1.setFitHeight((int) ToolbarImage.getWidth());
		ToolbarViewSlot1.setX(0.0);
		ToolbarViewSlot1.setY(700.00);
		
		/* Adds a slot for Invisible Activate  */
		Image ToolbarImage2 = getImage("individual.png");
		ToolbarViewSlot2 = new ImageView(ToolbarImage);
		ToolbarViewSlot2.setSmooth(false);
		ToolbarViewSlot2.setFitWidth((int) ToolbarImage.getWidth());
		ToolbarViewSlot2.setFitHeight((int) ToolbarImage.getWidth());
		ToolbarViewSlot2.setX(50.0);
		ToolbarViewSlot2.setY(700.00);
		
		/* Adds a slot for Key Activate  */
		Image ToolbarImage3 = getImage("individual.png");
		ToolbarViewSlot3 = new ImageView(ToolbarImage);
		ToolbarViewSlot3.setSmooth(false);
		ToolbarViewSlot3.setFitWidth((int) ToolbarImage.getWidth());
		ToolbarViewSlot3.setFitHeight((int) ToolbarImage.getWidth());
		ToolbarViewSlot3.setX(100.0);
		ToolbarViewSlot3.setY(700.00);
		
		/* Adds a slot for Speed Activate  */
		Image ToolbarImage4 = getImage("individual.png");
		ToolbarViewSlot4 = new ImageView(ToolbarImage);
		ToolbarViewSlot4.setSmooth(false);
		ToolbarViewSlot4.setFitWidth((int) ToolbarImage.getWidth());
		ToolbarViewSlot4.setFitHeight((int) ToolbarImage.getWidth());
		ToolbarViewSlot4.setX(150.0);
		ToolbarViewSlot4.setY(700.00);

		/* Adds the Slots to the Root Pane  */
		root.getChildren().addAll(ToolbarViewSlot1, ToolbarViewSlot2, ToolbarViewSlot3, ToolbarViewSlot4);

		/* Loops through Background Music */
		BackgroundMusic.backgroundmusic.loop();
	}

	/* Generates a random number */
	private int getRandomNumber(int length) {
		return rand.nextInt(length);
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

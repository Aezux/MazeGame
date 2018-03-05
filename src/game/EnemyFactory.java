package game;


import java.awt.Point;

public class EnemyFactory {
	//private Observable player;
	//private DungeonMap map;
	
	public Enemy getEnemy(String enemyType, DungeonMap map, Point location) {
		if(enemyType == null) {
			return null;
		}
		if (enemyType.equalsIgnoreCase("FIRE")) {
			System.out.println("Making Fireball");
			return new Fireball(map);
		}
		else if (enemyType.equalsIgnoreCase("TRAP")) {
			return new Trap(map, location);
		}
		return null;
	}

}

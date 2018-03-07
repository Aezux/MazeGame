package game;

import java.awt.Point;

public class EnemyFactory {
	
	public Enemy getEnemy(String enemyType, DungeonMap map, Point location) {
		if(enemyType == null) {
			return null;
		}
		if (enemyType.equalsIgnoreCase("FIRE")) {
			return new Fireball(map, location);
		}
		else if (enemyType.equalsIgnoreCase("TRAP")) {
			return new Trap(map, location);
		}
		else if (enemyType.equalsIgnoreCase("GHOST")) {
			return new Ghost(map, location);
		}
		else if (enemyType.equalsIgnoreCase("ZOMBIE")) {
			return new Zombie(map, location);
		}
		return null;
	}

}

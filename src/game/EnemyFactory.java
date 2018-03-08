package game;

import java.awt.Point;
import java.util.Observable;

public class EnemyFactory {
	
	public Enemy getEnemy(String enemyType, Observable player, DungeonMap map, Point location) {
		if(enemyType == null) {
			return null;
		}
		if (enemyType.equalsIgnoreCase("FIRE")) {
			return new Fireball(map, player, location);
		}
		else if (enemyType.equalsIgnoreCase("TRAP")) {
			return new Trap(map, player, location);
		}
		else if (enemyType.equalsIgnoreCase("GHOST")) {
			return new Ghost(map, player, location);
		}
		else if (enemyType.equalsIgnoreCase("ZOMBIE")) {
			return new Zombie(map, player, location);
		}
		return null;
	}

}

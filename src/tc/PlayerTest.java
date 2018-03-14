package tc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import game.DungeonMap;
import game.Player;

@SuppressWarnings("unused")


public class PlayerTest {
	/* Tests the player gets Created on the map */
	@Test
	public void testPlayerCreated() {
		Player player = null;
		DungeonMap dungeonmap;
		dungeonmap = DungeonMap.getInstance(10, 1);
		player = new Player(dungeonmap);
		assertTrue(player !=null);
	}
	/* Tests the player lives decrement */
	@Test
	public void testPlayerLivesDecrement() {
		Player player = null;
		DungeonMap dungeonmap;
		dungeonmap = DungeonMap.getInstance(10, 1);
		player = new Player(dungeonmap);
		int beforedec = player.getLife();
		player.loseLife();
		assertTrue(player.getLife() ==  beforedec - 1);
	}
	/* Tests the player lives increment */
	@Test
	public void testPlayerLivesIncrement() {
		Player player = null;
		DungeonMap dungeonmap;
		dungeonmap = DungeonMap.getInstance(10, 1);
		player = new Player(dungeonmap);
		int beforeadd = player.getLife();
		player.addLife();
		assertTrue(player.getLife() ==  beforeadd+1);
	}
}
package tc;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import game.DungeonMap;


@SuppressWarnings("unused")
class DungeonMapTest {
	/* Tests that map gets created */
	@Test
	void dungeonMapCreated() {
		DungeonMap dungeonmap = DungeonMap.getInstance(10, 1);
		assertTrue(dungeonmap !=null);
	}
	/* Tests the equals method on DungeonMap */
	@Test
	void NoDuplicates() {
		DungeonMap dungeonmap = DungeonMap.getInstance(10, 1);
		DungeonMap dungeonmap2 = DungeonMap.getInstance(10, 1);
		assertTrue(dungeonmap.equals(dungeonmap2));
	}
}

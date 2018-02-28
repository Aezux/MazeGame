package game;

import java.awt.Point;

public class Patrol extends Move {

	private DungeonMap map;
	
	public Patrol(DungeonMap map) {
		this.map = map;
	}
	
	public Point nextPoint(Point location) {
			return null;
	}
}

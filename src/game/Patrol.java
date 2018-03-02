package game;

import java.awt.Point;

public class Patrol extends Move {

	private DungeonMap map;
	
	public Patrol(DungeonMap map, Point enemy) {
		this.map = map;
	}
	
	private Point randomDirection(Point current) {
		int direction = getRandomNumber(4);
		Point next = new Point();
		switch (direction) {
			case 0: next = new Point(current.x, current.y+1); break;
			case 1: next = new Point(current.x, current.y-1); break;
			case 2: next = new Point(current.x+1, current.y); break;
			case 3: next = new Point(current.x-1, current.y); break;
		}
		return next;
	}
	
	public Point nextPoint(Point location) {
		Point next;
		
		do {
			next = randomDirection(location);
		} while (!openSpot(next.x, next.y, map.getMap()));
		return next;
	}
}

package game;

import java.awt.Point;

public class PointLocation {
	private Point point;
	private int distance;
	
	/* No argument constructor */
	public PointLocation() {
		this.point = new Point();
		this.distance = 0;
	}
	
	/* Regular constructor */
	public PointLocation(Point point, int distance) {
		this.point = point;
		this.distance = distance;
	}
	
	/* Returns the Point */
	public Point getPoint() {
		return point;
	}
	
	/* Returns the distance */
	public int getDistance() {
		return distance;
	}
	
	/* Overwritten the equals method */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		PointLocation other = (PointLocation) obj;
		
		return (other.distance == distance && 
				other.point.equals(point));
	}
	
	/* Overwritten the hashCode method */
	public int hashCode() {
		int result = 0x12CD;
		result = 0xF7F * result + distance + point.hashCode();
		return result;
	}
	
	/* Overwritten the toString method */
	public String toString() {
		return "distance: " + distance + ", point location: (" + point.x + "," + point.y + ")";
	}
}

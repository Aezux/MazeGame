package game;

import java.awt.Point;
import java.util.ArrayList;

public interface Movement {
    public Point nextPoint(Point currentLocation);
    public ArrayList<Point> getPath(Point currentLocation);
}
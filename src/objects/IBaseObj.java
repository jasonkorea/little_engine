package objects;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

public interface IBaseObj {
	//Location
	Point2D.Double getCenterLocation();
	//Area
	Rectangle getRect();
	void setRectangle(Rectangle rectangle);
	void setLocation(double x, double y);
	Color getColor();
	void setColor(Color color);
	void update();
	boolean intersects(int x, int y);
	void setVisible(boolean visible);
	boolean getVisible();
	void act(IBaseObj obj);
}

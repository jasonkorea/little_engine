package objects;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;

public interface IBaseObj {
	//Location
	Point2D.Float getBaseLocation();
	void setBaseLocation(Float location);
	Point2D.Float getCenterLocation();
	//Area
	Rectangle getRect();
	void setRectangle(Rectangle rectangle);
	Color getColor();
	void setColor(Color color);
	void update();
	boolean intersects(int x, int y);
	void setVisible(boolean visible);
	boolean getVisible();
	void act(IBaseObj obj);
}

package objects;

import java.awt.geom.Point2D.Float;

public interface IMovable extends IBaseObj {
	void move(Float from, IBaseObj to);
	void move(float x, float y);
	void move(IBaseObj obj);
	void stop();
	void setSpeed(float speed);
	float getSpeed();
}

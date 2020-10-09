package objects;

public interface IMovable extends IBaseObj {
	void move(double fromX, double fromY, IBaseObj to);
	void move(double x, double y);
	void moveTo(IBaseObj obj);
	void move(IBaseObj fromObj, IBaseObj toObj);
	void stop();
	void setSpeed(double speed);
	double getSpeed();
}

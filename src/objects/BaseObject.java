package objects;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;

import Utils.CommonUtil;

public abstract class BaseObject implements IBaseObj {
	private Rectangle rectangle = new Rectangle(10, 10);
	private Color color;
	private boolean visible = true;

	private BaseObject() {};

	public BaseObject(Rectangle rectangle) {
		int randNum = CommonUtil.getRandInt(0, 3);
		switch (randNum) {
		case 0:
			this.color = Color.BLUE;
			break;
		case 1:
			this.color = Color.RED;
			break;
		case 2:
			this.color = Color.YELLOW;
			break;
		case 3:
			this.color = Color.GREEN;
			break;
		}
		
		this.rectangle = rectangle;
	}


	public BaseObject(Rectangle rectangle, Color color) {
		this.rectangle = rectangle;
		this.color = color;
	}

	@Override
	public Rectangle getRect() {
		return this.rectangle;
	}

	@Override
	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}

	@Override
	public Color getColor() {
		return this.color;
	}
	
	@Override
	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public abstract void update();

	@Override
	public boolean intersects(int x, int y) {
		return rectangle.intersects(x, y, 1, 1);
	}

	@Override
	public void setVisible(boolean visible) {
		this.visible = visible;
		
	}

	@Override
	public boolean getVisible() {
		return this.visible;
	}
	
	@Override
	public Double getCenterLocation() {
		return new Double(this.rectangle.getCenterX(), this.rectangle.getCenterY());
	}
	
	@Override
	public void setLocation(double x, double y) {
		//TODO : Replace Rectangle from Rectangle2D to support double
		getRect().setLocation((int)x, (int)y);
	}
}

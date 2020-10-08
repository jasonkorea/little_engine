package objects;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Point2D.Float;

import Utils.CommonUtil;

public abstract class BaseObject implements IBaseObj {
	private Rectangle rectangle = new Rectangle(10, 10);
	protected Float location;
	private Float centerLocation = new Float();
	private Color color;
	private boolean visible = true;

	private BaseObject() {};

	public BaseObject(Float location, Rectangle rectangle) {
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
		this.location = location;
	}


	public BaseObject(Float location, Rectangle rectangle, Color color) {
		this.location = location;
		this.rectangle = rectangle;
		this.color = color;
	}

	@Override
	public Float getBaseLocation() {
		return this.location;
	}

	@Override
	public void setBaseLocation(Float location) {
		this.location = location;
	}
	
	@Override
	public Float getCenterLocation() {
		centerLocation.x = this.location.x + this.rectangle.width/2;
		centerLocation.y = this.location.y + this.rectangle.height/2;
		return centerLocation;
	}

	@Override
	public Rectangle getRect() {
		//return new Rectangle((int)this.location.x, (int)this.location.y, this.rectangle.width, rectangle.height);
		this.rectangle.x = (int) this.location.x;
		this.rectangle.y = (int) this.location.y;
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
		rectangle.x = (int) location.x;
		rectangle.y = (int) location.y;
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
}

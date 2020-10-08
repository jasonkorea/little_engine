package objects;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Point2D.Float;

import constants.Constants;

public class Land implements IBaseObj {
	protected Float location;
	
	private Land() {};
	
	public Land(int x, int y) {
		this.location = new Float(x, y);
	}

	@Override
	public Float getBaseLocation() {
		return this.location;
	}

	@Override
	public Rectangle getRect() {
		return new Rectangle(Constants.MINIMUM_SIZE_OF_OBJECT, Constants.MINIMUM_SIZE_OF_OBJECT);
	}

	@Override
	public void setRectangle(Rectangle rectangle) {
	}

	@Override
	public Color getColor() {
		return null;
	}

	@Override
	public void setColor(Color color) {
		
	}

	@Override
	public void update() {
		
	}

	@Override
	public boolean intersects(int x, int y) {
		return false;
	}

	@Override
	public void setVisible(boolean visible) {
	}

	@Override
	public boolean getVisible() {
		return false;
	}

	@Override
	public void act(IBaseObj obj) {
	}

	@Override
	public void setBaseLocation(Float location) {
		this.location = location;
	}

	@Override
	public Float getCenterLocation() {
		return this.location;
	}

}

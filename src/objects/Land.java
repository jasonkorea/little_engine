package objects;

import java.awt.Color;
import java.awt.Rectangle;

import constants.Constants;

public class Land extends BaseObject {
	
	public Land(double x, double y) {
		super(new Rectangle((int)x, (int)y, 0, 0));
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
	public void setLocation(double x, double y) {
		// TODO Auto-generated method stub
		
	}

}

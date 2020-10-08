package objects;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Point2D.Float;

public abstract class SelectableObject extends BaseObject implements ISelectable {

	public SelectableObject(Float location, Rectangle rectangle) {
		super(location, rectangle);
	}

	public SelectableObject(Float location, Rectangle rectangle, Color color) {
		super(location, rectangle, color);
		// TODO Auto-generated constructor stub
	}

	private boolean bSelectedActor = false;
	@Override
	public void setSelected(boolean bSelectedActor) {
		this.bSelectedActor = bSelectedActor;
	}
	
	@Override
	public boolean isSelected() {
		return this.bSelectedActor;
	}

	
}

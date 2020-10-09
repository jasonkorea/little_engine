package objects;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Point2D.Float;

public abstract class SelectableObject extends BaseObject implements ISelectable {

	public SelectableObject(Rectangle rectangle) {
		super(rectangle);
	}

	public SelectableObject(Rectangle rectangle, Color color) {
		super(rectangle, color);
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

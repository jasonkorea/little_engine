package objects;

import java.awt.Rectangle;
import java.awt.geom.Point2D.Float;

public abstract class Attackable extends SelectableObject {
	public Attackable(Float location, Rectangle rectangle) {
		super(location, rectangle);
	}

	private int curHp = 100;
	private int maxHp = 100;
	private int defence = 10;
	
	void getHit(IFightable obj) {
		if (obj.getDamage() > this.defence) {
			this.curHp -= obj.getDamage() - this.defence;
		} else {
			this.curHp -= obj.getDamage() / 2;
		}
	}
}

package objects;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.Point2D.Float;
import java.util.Iterator;

import Utils.CommonUtil;
import constants.Constants;
import manager.ObjectManager;

public class RandomMovingUnit extends MovableUnit {

	private enum Direction {
		LEFT, RIGHT, TOP, BOTTOM, IDLE;
	}

	private final int INTERVAL_SEC = 1; // sec
	private long fpsCount = 0;

	private Direction direction = Direction.IDLE;

	public RandomMovingUnit(Rectangle rectangle) {
		super(rectangle, false);
	}

	public RandomMovingUnit(Rectangle rectangle, double speed) {
		super(rectangle, speed, false);
	}

	@Override
	public void update() {
		fpsCount++;
		if (fpsCount > 0 && fpsCount / Constants.FPS >= CommonUtil.getRandInt(1, 3)) {
			fpsCount = 0;
			int num = CommonUtil.getRandInt(0, 4);
			switch (num) {
			case 0:
				this.direction = Direction.LEFT;
				break;
			case 1:
				this.direction = Direction.RIGHT;
				break;
			case 2:
				this.direction = Direction.TOP;
				break;
			case 3:
				this.direction = Direction.BOTTOM;
				break;
			case 4:
				this.direction = Direction.IDLE;
				break;
			}
		}

		double futureX = 0;
		double futureY = 0;
		if (this.direction.equals(Direction.LEFT)) {
			futureX = -speed;
		} else if (this.direction.equals(Direction.RIGHT)) {
			futureX = speed;
		} else if (this.direction.equals(Direction.TOP)) {
			futureY = -speed;
		} else if (this.direction.equals(Direction.BOTTOM)) {
			futureY = speed;
		}

		Rectangle thisRectFuture = new Rectangle((int) (getRect().x + futureX), (int) (getRect().y + futureY),
				(int) getRect().width, (int) getRect().height);

		Iterator<IBaseObj> it = ObjectManager.getInstance().getObjects().iterator();
		boolean isIntersected = false;
		while (it.hasNext()) {
			IBaseObj obj = it.next();
			if (obj.getColor().equals(Color.RED)) continue;
			Rectangle curRect = new Rectangle((int)obj.getRect().x, (int)obj.getRect().y, (int)obj.getRect().getWidth(), (int)obj.getRect().getHeight());
			if (!obj.equals(this) && CommonUtil.checkcirclecollide(thisRectFuture, curRect) && obj.getVisible()) {
				isIntersected = true;
			}
		}
		
		if (this.direction.equals(Direction.LEFT)) {
			if (getRect().x > 0 && !isIntersected) {
				getRect().x -= speed;
			} else {
				this.direction = Direction.RIGHT;
			}
			return;
		}

		if (this.direction.equals(Direction.RIGHT)) {
			if (getRect().x + getRect().width < Toolkit.getDefaultToolkit().getScreenSize().width && !isIntersected) {
				getRect().x += speed;
			} else {
				this.direction = Direction.LEFT;
			}
			return;
		}
		

		if (this.direction.equals(Direction.TOP)) {
			if (getRect().y > 0 && !isIntersected) {
				getRect().y -= speed;
			} else {
				this.direction = Direction.BOTTOM;
			}
			return;
		}

		if (this.direction.equals(Direction.BOTTOM)) {
			if (getRect().y + getRect().height < Toolkit.getDefaultToolkit().getScreenSize().height && !isIntersected) {
				getRect().y += speed;
			} else {
				this.direction = Direction.TOP;
			}
			return;
		}

	}

	private boolean isIntersect(Rectangle r1, Rectangle r2) {
		if (r1 != null && r2 != null) {
			return r1.intersects(r2);
		} else {
			return false;
		}
	}
}

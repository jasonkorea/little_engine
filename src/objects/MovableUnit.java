package objects;

import java.awt.Rectangle;
import java.awt.geom.Point2D.Float;

import manager.ObjectManager;

public class MovableUnit extends SelectableObject implements IMovable {
	protected IBaseObj to;
	protected float speed = 1.0f;
	protected boolean bRemoveOnArrival = false;

	protected enum State {
		GOING, IDLE
	}

	State curState = State.IDLE;

	public MovableUnit(Float location, Rectangle rectangle, boolean removeOnArrival) {
		super(location, rectangle);
		this.bRemoveOnArrival = removeOnArrival;
	}

	public MovableUnit(Float location, Rectangle rectangle, float speed, boolean removeOnArrival) {
		super(location, rectangle);
		this.speed = speed;
		this.bRemoveOnArrival = removeOnArrival;
	}

	@Override
	public void stop() {
		this.curState = State.IDLE;
	}

	@Override
	public void update() {
		if (this.curState.equals(State.GOING)) {
			if (to == null) {
				this.curState = State.IDLE;
				if (this.bRemoveOnArrival) {
					ObjectManager.getInstance().removeObj(this);
				}
			}
			
			boolean xDone = false;
			boolean yDone = false;

			float deltaX = to.getCenterLocation().x - getCenterLocation().x;
			float deltaY = to.getCenterLocation().y - getCenterLocation().y;
			double mag = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
			deltaX = (float) (deltaX / mag * this.speed);
			deltaY = (float) (deltaY / mag * this.speed);

			if (Math.abs(getCenterLocation().x - to.getCenterLocation().x) > Math.abs(deltaX)) {
				location.x += deltaX;
			} else {
				xDone = true;
				location.x = (int) to.getCenterLocation().x - (this.getRect().width/2);
			}

			if (Math.abs(getCenterLocation().y - to.getCenterLocation().y) > Math.abs(deltaY)) {
				location.y += deltaY;
			} else {
				yDone = true;
				location.y = (int) to.getCenterLocation().y - (this.getRect().height/2);
			}

			if (xDone && yDone) {
				this.curState = State.IDLE;
				to = null;
				if (this.bRemoveOnArrival) {
					ObjectManager.getInstance().removeObj(this);
				}

//				//임시로 타깃 크기의 20%만큼 커지게 함
//				if (targetObj != null) {
//					rectangle.width += ((IBaseObj)targetObj).getRect().width *0.2;
//					rectangle.height += ((IBaseObj)targetObj).getRect().height *0.2;
//					//ObjectManager.getInstance().removeObj(targetObj);
//					targetObj = null;
//				}

			}
		}
	}
	
	@Override
	public void setSpeed(float speed) {
		this.speed = speed;
	}

	@Override
	public float getSpeed() {
		return this.speed;
	}

	@Override
	public void act(IBaseObj obj) {
		move(obj);
	}

	@Override
	public void move(IBaseObj obj) {
		move(this.getBaseLocation(), obj);		
	}
	
	@Override
	public void move(Float from, IBaseObj to) {
		this.location = from;
		this.curState = State.GOING;
		this.to = to;
	}

	@Override
	public void move(float x, float y) {
		move(this.location, new Land((int)x, (int)y));
	}
}

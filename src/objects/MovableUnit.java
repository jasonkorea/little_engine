package objects;

import java.awt.Rectangle;

import manager.ObjectManager;

public class MovableUnit extends SelectableObject implements IMovable {
	protected IBaseObj to;
	protected double speed = 1.0f;
	protected boolean bRemoveOnArrival = false;

	protected enum State {
		GOING, IDLE
	}

	State curState = State.IDLE;

	public MovableUnit(Rectangle rectangle, boolean removeOnArrival) {
		super(rectangle);
		this.bRemoveOnArrival = removeOnArrival;
	}

	public MovableUnit(Rectangle rectangle, double speed, boolean removeOnArrival) {
		super(rectangle);
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

			double deltaX = to.getCenterLocation().x - getCenterLocation().x;
			double deltaY = to.getCenterLocation().y - getCenterLocation().y;
			double mag = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
			deltaX = (double) (deltaX / mag * this.speed);
			deltaY = (double) (deltaY / mag * this.speed);

			if (Math.abs(getCenterLocation().x - to.getCenterLocation().x) > Math.abs(deltaX)) {
				getRect().x += deltaX;
			} else {
				xDone = true;
				getRect().x = (int) to.getCenterLocation().x - (this.getRect().width/2);
			}

			if (Math.abs(getCenterLocation().y - to.getCenterLocation().y) > Math.abs(deltaY)) {
				getRect().y += deltaY;
			} else {
				yDone = true;
				getRect().y = (int) to.getCenterLocation().y - (this.getRect().height/2);
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
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	@Override
	public double getSpeed() {
		return this.speed;
	}

	@Override
	public void act(IBaseObj obj) {
		moveTo(obj);
	}

	@Override
	public void moveTo(IBaseObj obj) {
		move(getRect().getCenterX(), getRect().getCenterY(), obj);		
	}
	
	@Override
	public void move(double fromX, double fromY, IBaseObj to) {
		this.curState = State.GOING;
		this.to = to;
	}

	@Override
	public void move(double x, double y) {
		move(this.getRect().getX(), this.getRect().getY(), new Land((int)x, (int)y));
	}

	@Override
	public void move(IBaseObj fromObj, IBaseObj toObj) {
		move(fromObj.getRect().getCenterX(), fromObj.getRect().getCenterY(), toObj); 
	}
}

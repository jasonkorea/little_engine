package objects;

import java.awt.Rectangle;
import java.awt.geom.Point2D.Float;

import constants.Constants;

public class Hunter extends MovableUnit implements IFightable {
	private int damage = 1; // 1회 공격시 데미지
	private int distance = 150; // 0:근접, -1:무한대
	private int intervalMs = 500;
	private long fpsCount = 0;
	private boolean bFire = false;
	private IBaseObj targetObj;

	public Hunter(Float location, Rectangle rectangle, float speed, boolean removeOnArrival) {
		super(location, rectangle, speed, removeOnArrival);
	}

	public Hunter(Float location, Rectangle rectangle, float speed, boolean removeOnArrival, int damage, int distance,
			int intervalMs) {
		super(location, rectangle, speed, removeOnArrival);
		this.damage = damage;
		this.distance = distance;
		this.intervalMs = intervalMs;
	}

	@Override
	public void attack(IAttackable target) {
		this.targetObj = target;
		move(this.getBaseLocation(), target);
	}

	@Override
	public void update() {
		super.update();

		if (this.targetObj != null && this.targetObj.getVisible() == true) {
			if (this.getCenterLocation().distance(targetObj.getCenterLocation()) - (targetObj.getRect().width/2) - (this.getRect().width/2) <= distance
					|| this.distance < 0) {
				System.out.println((targetObj.getRect().width/2));
				stop();
				bFire = true;
			} else {
				bFire = false;
				move(this.getBaseLocation(), targetObj);
			}

			if (bFire) {
				fpsCount++;
				if (fpsCount > 0 && fpsCount / Constants.FPS >= intervalMs / 1000) {
					fpsCount = 0;
					((IAttackable) targetObj).hit(this);
				}
			} else {
				fpsCount = 0;
			}
		} else {
			bFire = false;
		}
	}

	@Override
	public int getDamage() {
		return this.damage;
	}

	@Override
	public void cancelAttack(int x, int y) {
		this.targetObj = null;
		move(x, y);
	}

	@Override
	public void cancelAttack() {
		this.targetObj = null;
	}

	@Override
	public boolean isAttacking() {
		return this.targetObj != null;
	}

	@Override
	public void act(IBaseObj obj) {
		if (obj instanceof IAttackable) {
			attack((IAttackable) obj);
		} else {
			cancelAttack();
			super.act(obj);
		}
	}
}

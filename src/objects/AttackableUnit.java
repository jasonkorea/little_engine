package objects;

import java.awt.Color;
import java.awt.Rectangle;

import manager.ObjectManager;
public class AttackableUnit extends RandomMovingUnit implements IAttackable {

	private int maxHp = 10;
	private int curHp = 10;
	private int defence = 2;


	public AttackableUnit(Rectangle rectangle, double speed, int maxHp, int curHp, int defence) {
		super(rectangle, speed);
		this.maxHp = maxHp;
		this.curHp = curHp;
		this.defence = defence;
	}

	@Override
	public void hit(IFightable fighter) {
		final int bulletSize = 5;
		MovableUnit bullet = new MovableUnit(new Rectangle(getRect().x, getRect().y , bulletSize, bulletSize), true);
		bullet.setColor(Color.RED);
		bullet.setSpeed(20);
		ObjectManager.getInstance().addObject(bullet);
		bullet.move(this.getRect().getCenterX(), (float)(this.getRect().getY() + this.getRect().getCenterY()) - 5);

		int calculatedDamage;
		if (fighter.getDamage() > this.defence) {
			calculatedDamage = fighter.getDamage() - this.defence;
		} else {
			calculatedDamage = 1;
		}

		if (this.curHp > calculatedDamage) {
			this.curHp -= calculatedDamage;
			getRect().width = this.curHp;
			getRect().height = this.curHp;

			setSpeed(getSpeed() + 0.1f);

		} else {
			setVisible(false);
			ObjectManager.getInstance().removeObj(this);
		}
	}

	@Override
	public int getMaxHp() {
		return this.maxHp;
	}

	@Override
	public int getCurHp() {
		return this.curHp;
	}

	@Override
	public int getDefence() {
		return this.defence;
	}

}

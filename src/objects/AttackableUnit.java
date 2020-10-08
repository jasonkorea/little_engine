package objects;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Point2D.Float;

import manager.ObjectManager;
public class AttackableUnit extends RandomMovingUnit implements IAttackable {

	private int maxHp = 10;
	private int curHp = 10;
	private int defence = 2;

	public AttackableUnit(Float location, Rectangle rectangle, float speed, int maxHp, int curHp, int defence) {
		super(location, rectangle, speed);
		this.maxHp = maxHp;
		this.curHp = curHp;
		this.defence = defence;
	}

	@Override
	public void hit(IFightable fighter) {

		MovableUnit bullet = new MovableUnit(new Float((float) (fighter.getCenterLocation().x),
				fighter.getCenterLocation().y),
				new Rectangle(5, 5), true);
		bullet.setColor(Color.RED);
		bullet.setSpeed(20);
		ObjectManager.getInstance().addObject(bullet);
		bullet.move((float)(this.getBaseLocation().x + this.getRect().getWidth()/2) - 5, (float)(this.getBaseLocation().y + this.getRect().getHeight()/2) - 5);

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

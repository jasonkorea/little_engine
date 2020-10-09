package objects;

public interface IFightable extends IBaseObj {
	void attack(IAttackable target);
	boolean isAttacking();
	void cancelAttack(int x, int y);
	void cancelAttack();
	int getDamage();
}

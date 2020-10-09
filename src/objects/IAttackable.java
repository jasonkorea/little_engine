package objects;

public interface IAttackable extends IBaseObj {
	void hit(IFightable obj);
	int getMaxHp();
	int getCurHp();
	int getDefence();
}

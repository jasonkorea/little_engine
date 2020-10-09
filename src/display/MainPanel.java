package display;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;

import manager.ObjectManager;
import objects.DragArea;
import objects.IAttackable;
import objects.IBaseObj;
import objects.IFightable;
import objects.ISelectable;

public class MainPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	BasicStroke defaultStroke = new BasicStroke(3);
	BasicStroke selectedStroke = new BasicStroke(10);

	public void draw() {
		repaint();
	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		List<IBaseObj> objs = ObjectManager.getInstance().getObjects();
		if (objs.size() == 0) {
			return;
		}

		Iterator<IBaseObj> it = objs.iterator();
		while (it.hasNext()) {
			IBaseObj obj = it.next();
			if (obj.getVisible()) {
				Graphics2D g2 = (Graphics2D) g;
				if (obj instanceof ISelectable) {
					if (((ISelectable) obj).isSelected()) {
						g2.setStroke(selectedStroke);
					} else {
						g2.setStroke(defaultStroke);
					}
				} else {
					g2.setStroke(defaultStroke);
				}
				g.setColor(obj.getColor());
				if (obj instanceof DragArea) {
					g.drawRect((int) obj.getRect().getX(), (int) obj.getRect().getY(),
							(int) obj.getRect().getWidth(), (int) obj.getRect().getHeight());
				} else {
					g.drawOval((int) obj.getRect().getX(), (int) obj.getRect().getY(),
							(int) obj.getRect().getWidth(), (int) obj.getRect().getHeight());
				}
				String text = "";
				if (obj instanceof IAttackable) {
					IAttackable attackableObj = (IAttackable) obj;
					text = "Deer " + attackableObj.getCurHp() + "/" + attackableObj.getMaxHp() + " Def:"
							+ attackableObj.getDefence();
				} else if (obj instanceof IFightable) {
					text = "Hunter Dam:" + ((IFightable) obj).getDamage();
				}

				// text = "";
				g.drawString(text, (int) obj.getRect().getX(), (int) obj.getRect().getY());
			}
		}
	}
}

package manager;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import objects.DragArea;
import objects.IBaseObj;
import objects.ISelectable;
import objects.Land;

public class ObjectManager {
	private static ObjectManager instance = null;
	private List<IBaseObj> objects = new CopyOnWriteArrayList<>();
	private List<IBaseObj> preSelectedObjects = new CopyOnWriteArrayList<>();
	private IBaseObj dragArea;

	private ObjectManager() {
	};

	public List<IBaseObj> getpreSelectedObjects() {
		return preSelectedObjects;
	}

	public void clearSelectedObjects() {
		Iterator<IBaseObj> it = preSelectedObjects.iterator();
		while (it.hasNext()) {
			((ISelectable) it.next()).setSelected(false);
		}
		preSelectedObjects.clear();
	}

	public void setPreSelectedObjects(List<IBaseObj> objs) {
		Iterator<IBaseObj> it = preSelectedObjects.iterator();
		while (it.hasNext()) {
			((ISelectable) it.next()).setSelected(false);
		}
		preSelectedObjects.clear();

		this.preSelectedObjects.addAll(objs);
	}

	public void setPreSelectedObject(IBaseObj obj) {
		ObjectManager.getInstance().clearSelectedObjects();
		if (obj instanceof Land) {
			if (getDragArea() != null) {
				selectInRect(ObjectManager.getInstance().getDragArea().getRect());
				getDragArea().setVisible(false);
			}
		} else {
			if (obj instanceof ISelectable) {
				((ISelectable)obj).setSelected(true);
			this.preSelectedObjects.add(obj);
			}
		}
	}

	public void actSlectedObject(IBaseObj obj) {
		Iterator<IBaseObj> it = preSelectedObjects.iterator();
		while (it.hasNext()) {
			((IBaseObj) it.next()).act(obj);
		}
	}

	public boolean hasSelectedObject() {
		return preSelectedObjects.size() > 0;
	}

	public static ObjectManager getInstance() {
		if (instance == null) {
			instance = new ObjectManager();
		}
		return instance;
	}

	public void addObject(IBaseObj baseObject) {
		if (baseObject instanceof DragArea) {
			if (this.dragArea == null) {
				objects.add(baseObject);
			}
			this.dragArea = baseObject;
			return;
		}
		objects.add(baseObject);
	}
	
	public IBaseObj getDragArea() {
		return dragArea;
	}

	public void updateData() {
		Iterator<IBaseObj> it = objects.iterator();
		while (it.hasNext()) {
			it.next().update();
		}
	}

	public List<IBaseObj> getObjects() {
		return objects;
	}

	public IBaseObj findObjByLocation(int x, int y) {
		Iterator<IBaseObj> it = objects.iterator();
		while (it.hasNext()) {
			IBaseObj obj = it.next();
			if (obj.intersects(x, y)) {
				return obj;
			}
		}
		return new Land(x, y);
	}
	
	public List<IBaseObj> findObjsByRect(Rectangle rect) {
		List<IBaseObj> resList = new ArrayList<IBaseObj>();
		Iterator<IBaseObj> it = objects.iterator();
		while (it.hasNext()) {
			IBaseObj obj = it.next();
			if (obj.getRect().intersects(rect)) {
				resList.add(obj);
			}
		}
		return resList;
	}
	
	public void selectInRect(Rectangle rect) {
		clearSelectedObjects();
		Iterator<IBaseObj> it = objects.iterator();
		while (it.hasNext()) {
			IBaseObj obj = it.next();
			if (obj instanceof ISelectable && obj.getRect().intersects(rect)) {
				((ISelectable)obj).setSelected(true);
				preSelectedObjects.add(obj);
			}
		}
	}

	public boolean removeObj(IBaseObj obj) {
		if (objects.contains(obj)) {
			objects.remove(obj);
			return true;
		}
		return false;
	}
}

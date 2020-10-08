package input;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Point2D.Float;

import constants.Constants;
import manager.ObjectManager;
import objects.DragArea;
import objects.IBaseObj;

public class InputManager {
	private static InputManager instance = null;
	private IInput input;
	private Float dragStart;
	private int pressedButton;

	private InputManager() {
	}

	public static InputManager getInstance() {
		if (instance == null) {
			instance = new InputManager();
			instance.init();
		}
		return instance;
	}

	public IInput getInput() {
		return input;
	}

	private void init() {
		input = new IInput() {

			@Override
			public void released(int x, int y, int button) {
				System.out.println("released");
				System.out.println("clicked");
				System.out.println(button);
				IBaseObj selectedObj = ObjectManager.getInstance().findObjByLocation(x, y);
				System.out.println(selectedObj.getClass().getTypeName());
				if (button == Constants.MOUSE_BUTTON.LEFT) {
					ObjectManager.getInstance().setPreSelectedObject(selectedObj);
				} else if (button == Constants.MOUSE_BUTTON.RIGHT) {
					ObjectManager.getInstance().actSlectedObject(selectedObj);
				}
				
				dragStart = null;
				if (ObjectManager.getInstance().getDragArea() != null) {
					ObjectManager.getInstance().getDragArea().setVisible(false);
				}
			}

			@Override
			public void pressed(int x, int y, int button) {
				System.out.println("pressed");
				pressedButton = button;
			}

			@Override
			public void clicked(int x, int y, int button) {

			}

			@Override
			public void entered(int x, int y, int button) {

			}

			@Override
			public void exited(int x, int y, int button) {
				// TODO Auto-generated method stub

			}

			@Override
			public void drag(int x, int y) {
				if (dragStart == null) {
					dragStart = new Float(x, y);
					if (ObjectManager.getInstance().getDragArea() != null) {
						ObjectManager.getInstance().getDragArea().setVisible(true);
					}
				}
				IBaseObj dragArea = ObjectManager.getInstance().getDragArea();
				if (dragArea != null && dragStart != null) {
					dragArea.setBaseLocation(new Float(dragStart.x, dragStart.y));
					dragArea.setRectangle(new Rectangle((int) dragStart.x, (int) dragStart.y, (int) (x - dragStart.x),
							(int) (y - dragStart.y)));
				} else {
					IBaseObj obj = new DragArea(dragStart, new Rectangle(0, 0), Color.CYAN);
					ObjectManager.getInstance().addObject(obj);
				}
			}
		};
	}
}

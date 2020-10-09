package input;

import java.awt.Color;
import java.awt.Rectangle;

import constants.Constants;
import manager.ObjectManager;
import objects.DragArea;
import objects.IBaseObj;

public class InputManager {
	private static InputManager instance = null;
	private IInput input;
	private int pressedButton;
	private double[] dragStart = {-1, -1};
	
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
				System.out.println("button released " + button);
				IBaseObj selectedObj = ObjectManager.getInstance().findObjByLocation(x, y);
				System.out.println(selectedObj.getClass().getTypeName());
				if (button == Constants.MOUSE_BUTTON.LEFT) {
					ObjectManager.getInstance().setPreSelectedObject(selectedObj);
				} else if (button == Constants.MOUSE_BUTTON.RIGHT) {
					ObjectManager.getInstance().actSlectedObject(selectedObj);
				}
				
				dragStart[0] = dragStart[1] = -1;
				if (ObjectManager.getInstance().getDragArea() != null) {
					ObjectManager.getInstance().getDragArea().setVisible(false);
				}
			}

			@Override
			public void pressed(int x, int y, int button) {
				System.out.println("button pressed " + button);
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

			}

			@Override
			public void drag(int x, int y) {
				if (dragStart[0] == -1 && dragStart[1] == -1) {
					dragStart[0] = x;
					dragStart[1] = y;
					if (ObjectManager.getInstance().getDragArea() != null) {
						ObjectManager.getInstance().getDragArea().setVisible(true);
					}
				}
				IBaseObj dragArea = ObjectManager.getInstance().getDragArea();
				if (dragArea != null && (dragStart[0] != -1 && dragStart[1] != -1)) {
					dragArea.setRectangle(new Rectangle((int) dragStart[0], (int) dragStart[1], (int) (x - dragStart[0]),
							(int) (y - dragStart[1])));
				} else {
					IBaseObj obj = new DragArea(new Rectangle((int)dragStart[0], (int)dragStart[1], 0, 0), Color.CYAN);
					ObjectManager.getInstance().addObject(obj);
				}
			}
		};
	}
}

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.Point2D.Float;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import Utils.CommonUtil;
import constants.Constants;
import display.IDisplay;
import display.MainFrame;
import manager.ObjectManager;
import objects.AttackableUnit;
import objects.Hunter;
import objects.MovableUnit;

public class Main {
	public static void main(String[] args) {
		launch(createTestObjects(), new MainFrame());
	}

	private static void launch(ObjectManager objectManager, IDisplay display) {
		ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

		Runnable drawRunnable = new Runnable() {
			@Override
			public void run() {
				//long beginTime = System.currentTimeMillis();
				objectManager.updateData();
				display.draw();
				//long endTime = System.currentTimeMillis();
				//System.out.println("ellapsed time : " + (endTime - beginTime));
			}
		};

		executor.scheduleAtFixedRate(drawRunnable, 0, Constants.FPS, TimeUnit.MILLISECONDS);
	}

	private static ObjectManager createTestObjects() {
		//Hunter
		for (int i = 0; i < 5; i++) {
			int x1 = CommonUtil.getRandInt(0, Toolkit.getDefaultToolkit().getScreenSize().width);
			int y1 = CommonUtil.getRandInt(0, Toolkit.getDefaultToolkit().getScreenSize().height);
			Hunter hunter = new Hunter(
					new Float(x1, y1),
					new Rectangle(50, 50),
					CommonUtil.getRandInt(3, 5),
					false,
					CommonUtil.getRandInt(1, 2),
					CommonUtil.getRandInt(100, 150),
					CommonUtil.getRandInt(100, 200));
			// MovableUnit hunter = new MovableUnit(new Float(x1, y1), new Rectangle(50,
			// 50), 3f, false);
			hunter.setColor(Color.BLUE);
			ObjectManager.getInstance().addObject(hunter);
		}

		ArrayList<Rectangle> rects = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			int width = 0;
			int height = 0;
			int x = 0;
			int y = 0;

			Rectangle tempRect = null;
			boolean bCollision = false;
			do {
				bCollision = false;
				width = height = CommonUtil.getRandInt(40, 300);
				x = CommonUtil.getRandInt(0, Toolkit.getDefaultToolkit().getScreenSize().width);
				y = CommonUtil.getRandInt(0, Toolkit.getDefaultToolkit().getScreenSize().height);
				tempRect = new Rectangle(x, y, width, height);
				for (Rectangle aRect : rects) {
					bCollision = CommonUtil.checkcirclecollide(aRect.getX(), aRect.getY(), (float)aRect.getWidth(), tempRect.getX(), tempRect.getY(), (float)tempRect.getWidth());
					if (bCollision) {
						break;
					}
				}
			} while (bCollision);
			rects.add(tempRect);

			AttackableUnit randomMovingUnit = new AttackableUnit(new Float(x, y), new Rectangle(width, height),
					CommonUtil.getRandInt(2, 3), width, width, 1);

			randomMovingUnit.setColor(Color.ORANGE);
			ObjectManager.getInstance().addObject(randomMovingUnit);
			System.out.println("Created item : " + i);
		}

		return ObjectManager.getInstance();
	}

}

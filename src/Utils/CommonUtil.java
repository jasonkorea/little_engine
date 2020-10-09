package Utils;

import java.awt.Rectangle;

public class CommonUtil {
	public static int getRandInt(int min, int max) {
		return (int) (Math.random() * (max + 1)) + min;
	}

	public static boolean checkcirclecollide(double x1, double y1, float r1, double x2, double y2, float r2) {
		return Math.abs((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)) < (r1 + r2) * (r1 + r2);
	}
	
	public static boolean checkcirclecollide(Rectangle r1, Rectangle r2) {
		return checkcirclecollide((double)r1.getX(), (double)r1.getY(), (float)r1.getWidth()/2, (double)r2.getX(), (double)r2.getY(), (float)r2.getWidth()/2);
	}
	
	
}

package display;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import input.InputManager;

public class MainFrame extends JFrame implements IDisplay {
	private static final long serialVersionUID = 1L;
	MainPanel mainPanel;

	public MainFrame() {
		getContentPane().setLayout(new BorderLayout());
		setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
		getContentPane().setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
		mainPanel = new MainPanel();
		mainPanel.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
		mainPanel.setBackground(Color.BLACK);
		getContentPane().add(mainPanel);
		addMouseListner(mainPanel);
		setVisible(true);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void addMouseListner(JPanel jPanel) {
		jPanel.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				InputManager.getInstance().getInput().drag(e.getX(),  e.getY());
				System.out.println("drag mouse " + e.getButton());
			}
		});
		jPanel.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				InputManager.getInstance().getInput().released(e.getX(),  e.getY(), e.getButton());
			}

			@Override
			public void mousePressed(MouseEvent e) {
				InputManager.getInstance().getInput().pressed(e.getX(), e.getY(), e.getButton());
			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				InputManager.getInstance().getInput().clicked(e.getX(),  e.getY(), e.getButton());
			}

		});
	}
	
	@Override
	public void draw() {
		mainPanel.draw();

	}

	@Override
	public int[] getScreenSize() {
		int[] screenSize = {Toolkit.getDefaultToolkit().getScreenSize().width,
				Toolkit.getDefaultToolkit().getScreenSize().height};
		return screenSize;
	}
}

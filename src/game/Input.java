package game;

import java.awt.event.KeyEvent;

/**
 * 
 * @author Raj Ramsaroop, Greg Westerfield Jr.
 *
 */
public class Input {

	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int UP = 2;
	public static final int DOWN = 3;
	public boolean[] buttons = new boolean[4];
	public boolean[] oldButtons = new boolean[4];

	public void set(int key, boolean down) {
		int button = -1;
		if (key == KeyEvent.VK_LEFT)
			button = 0;
		if (key == KeyEvent.VK_RIGHT)
			button = 1;
		if (key == KeyEvent.VK_UP)
			button = 2;
		if (key == KeyEvent.VK_DOWN)
			button = 3;

		if (button >= 0)
			buttons[button] = down;
	}

	public void tick() {
		for (int i = 0; i < buttons.length; i++) {
			oldButtons[i] = buttons[i];
		}
	}
}

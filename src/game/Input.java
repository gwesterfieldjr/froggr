package game;

import java.awt.event.KeyEvent;

/**
 * This class keeps track of which keys the user is pressing from the keyboard.
 * 
 * @author Raj Ramsaroop, Greg Westerfield, Jr.
 *
 */
public class Input {

	/**
	 * Value for left movement.
	 */
	public static final int LEFT = 0;
	
	/**
	 * Value for right movement.
	 */
	public static final int RIGHT = 1;
	
	/**
	 * Value for up movement.
	 */
	public static final int UP = 2;
	
	/**
	 * Value for down movement.
	 */
	public static final int DOWN = 3;
	
	/**
	 * Current buttons being pressed.
	 */
	public boolean[] buttons = new boolean[4];
	
	/**
	 * Previous buttons that were pressed.
	 */
	public boolean[] oldButtons = new boolean[4];

	/**
	 * Keeps track of the keys being pressed.
	 * @param key The key on the keyboard.
	 * @param down Whether the key is down or not.
	 */
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

	/**
	 * Add the current buttons being pressed to old buttons array.
	 */
	public void tick() {
		for (int i = 0; i < buttons.length; i++) {
			oldButtons[i] = buttons[i];
		}
	}
}

package sprites;

import java.awt.Canvas;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

import game.Input;

/**
 * 
 * @author Raj
 * 
 */
public class Player extends Sprite {

	private int lives;
	Image image;

	public Player(int x, int y, int lives) {
		super(x, y);
		this.lives = lives;
	}

	public void createImage(Canvas c) {
		MediaTracker media = new MediaTracker(c);
		image = Toolkit.getDefaultToolkit().getImage("res/sprites/player.png");
		media.addImage(image, 0);
		try {
			media.waitForID(0);
		} catch (Exception e) {
			
		}
	}
	
	public Image getImage() {
		return image;
	}

	@Override
	public void tick(Input input) {
		if (input.buttons[Input.LEFT]) {
			setXPos(getXPos() - 1);
		}
		if (input.buttons[Input.RIGHT]) {
			setXPos(getXPos() + 1);
		}
		if (input.buttons[Input.UP]) {
			setYPos(getYPos() - 1);
		}
		if (input.buttons[Input.DOWN]) {
			setYPos(getYPos() + 1);
		}

	}

	/**
	 * @return the number of lives remaining
	 */
	public int getLives() {
		return lives;
	}

	/**
	 * @param lives
	 *            the number of lives player will have remaining
	 */
	public void setLives(int lives) {
		this.lives = lives;
	}
}

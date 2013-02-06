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

	public Player(int x, int y, int lives) {
		super(x, y);
		this.lives = lives;
		setImageURL("res/sprites/player.png");
	}

	@Override
	public void tick(Input input) {
		if (input.buttons[Input.LEFT]) {
			setXPos(getXPos() - 2);
		}
		if (input.buttons[Input.RIGHT]) {
			setXPos(getXPos() + 2);
		}
		if (input.buttons[Input.UP]) {
			setYPos(getYPos() - 2);
		}
		if (input.buttons[Input.DOWN]) {
			setYPos(getYPos() + 2);
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

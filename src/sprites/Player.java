package sprites;

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
		setLength(1);
		setHeight(1);
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

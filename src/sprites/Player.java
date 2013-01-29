package sprites;

import game.Input;

/**
 * 
 * @author Raj
 *
 */
public class Player extends Sprite {

	public Player(int x, int y) {
		super(x, y);
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
}

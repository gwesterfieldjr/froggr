package sprites;

import game.FroggrGame;
import game.Input;

import java.util.Arrays;

import util.SoundEffect;

/**
 * 
 * @author Raj Ramsaroop
 * 		   Greg Westerfield, Jr.
 * 
 */
public class Player extends Sprite {

	private int lives;
	private boolean alive;

	public Player(int x, int y, int lives) {
		super(x, y);
		this.lives = lives;

		setImage("res/sprites/player/player-idle.gif");

		this.setAlive(true);

		setLength(1);
		setHeight(1);
	}

	@Override
	public void tick(Input input) {
		if (isAlive() && FroggrGame.flysConsumed != 4) {
			int horizontalMovementAmount = 25;
			int verticalMovementAmount = 50;
			if (input.buttons[Input.LEFT]) {
				if (getXPos() - horizontalMovementAmount != -horizontalMovementAmount) {
					setXPos(getXPos() - horizontalMovementAmount);
					input.buttons[Input.LEFT] = false;
					setImage("res/sprites/player/player-left.gif");
					SoundEffect.play(SoundEffect.MOVE);
				}
			}
			if (input.buttons[Input.RIGHT]) {
				if (getXPos() + horizontalMovementAmount*2 != FroggrGame.GAME_WIDTH) {
					setXPos(getXPos() + horizontalMovementAmount);
					input.buttons[Input.RIGHT] = false;
					setImage("res/sprites/player/player-right.gif");
					SoundEffect.play(SoundEffect.MOVE);
				}
			}
			if (input.buttons[Input.UP]) {
				if (getYPos() - verticalMovementAmount != -verticalMovementAmount) {
					setYPos(getYPos() - verticalMovementAmount);
					input.buttons[Input.UP] = false;
					setImage("res/sprites/player/player-forward.gif");
					SoundEffect.play(SoundEffect.MOVE);
				}
			}
			if (input.buttons[Input.DOWN]) {
				if (getYPos() + verticalMovementAmount != FroggrGame.GAME_HEIGHT - verticalMovementAmount) {
					setYPos(getYPos() + verticalMovementAmount);
					input.buttons[Input.DOWN] = false;
					setImage("res/sprites/player/player-back.gif");
					SoundEffect.play(SoundEffect.MOVE);
				}
			}
		}

	}

	/**
	 * Returns the number of lives the player has remaining.
	 * @return the number of lives remaining
	 */
	public int getLives() {
		return lives;
	}

	/**
	 * Sets the number of lives the player has remaining.
	 * 
	 * @param lives
	 *            the number of lives player will have remaining
	 */
	public void setLives(int lives) {
		this.lives = lives;
	}

	/**
	 * Returns whether or not the player is on a platform or not.
	 * 
	 * @return the isOnPlatform
	 */
	public boolean isOnPlatform(Platform platform) {
		int buffer = 25; // Half of the player.
		if (getXPos() >= platform.getXPos() - buffer
				&& getXPos() + calculatePixelWidth() <= platform.getXPos()
						+ platform.calculatePixelWidth() + buffer) {
			return true;
		} else {
			return false;
		}

		// return isOnPlatform;
	}

	/**
	 * Kills the player
	 */
	public void kill() {
		if (isAlive()) {
			remove();
			setImage("res/sprites/player/player-death.gif");
			setLives(getLives() - 1);
			setAlive(false);
		}
	}

	/**
	 * Sails the frog on whichever platform he jumps on.
	 * 
	 * @param input
	 * @param platform
	 */
	public void sail(Input input, Platform platform) {
		if (platform.getDirection() == MovingObject.DIRECTION_LEFT) {
			setXPos(getXPos() - 1);
		} else if (platform.getDirection() == MovingObject.DIRECTION_RIGHT) {
			setXPos(getXPos() + 1);
		}

		if (getXPos() == platform.getOffScreenXPosition()) {
			kill();
		}
	}

	/**
	 * Returns whether or not the player is alive.
	 * @return the alive
	 */
	public boolean isAlive() {
		return alive;
	}

	/**
	 * Sets whether or not the player is alive.
	 * 
	 * @param alive
	 *            the alive to set
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
}

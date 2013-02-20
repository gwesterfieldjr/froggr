package sprites;

import game.FroggrGame;
import game.Input;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.print.attribute.standard.Media;

import util.SoundEffect;

/**
 * 
 * @author Raj
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
		if (isAlive()) {
			if (input.buttons[Input.LEFT]) {
				if (getXPos() - 50 != -50) {
					setXPos(getXPos() - 50);
					input.buttons[Input.LEFT] = false;
					setImage("res/sprites/player/player-left.gif");
					SoundEffect.play(SoundEffect.MOVE);
				}
			}
			if (input.buttons[Input.RIGHT]) {
				if (getXPos() + 50 != FroggrGame.GAME_WIDTH) {
					setXPos(getXPos() + 50);
					input.buttons[Input.RIGHT] = false;
					setImage("res/sprites/player/player-right.gif");
					SoundEffect.play(SoundEffect.MOVE);
				}
			}
			if (input.buttons[Input.UP]) {
				if (getYPos() - 50 != -50) {
					setYPos(getYPos() - 50);
					input.buttons[Input.UP] = false;
					setImage("res/sprites/player/player-forward.gif");
					SoundEffect.play(SoundEffect.MOVE);
				}
			}
			if (input.buttons[Input.DOWN]) {
				if (getYPos() + 50 != FroggrGame.GAME_HEIGHT - 50) {
					setYPos(getYPos() + 50);
					input.buttons[Input.DOWN] = false;
					setImage("res/sprites/player/player-back.gif");
					SoundEffect.play(SoundEffect.MOVE);
				}
			}
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

	/**
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
			//SoundEffect.play(SoundEffect.COLLISION);
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
	 * @return the alive
	 */
	public boolean isAlive() {
		return alive;
	}

	/**
	 * @param alive
	 *            the alive to set
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

}

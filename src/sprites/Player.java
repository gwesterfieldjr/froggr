package sprites;

import game.FroggrGame;
import game.Input;

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

	public Player(int x, int y, int lives) {
		super(x, y);
		this.lives = lives;
		setImage("res/sprites/player/player-idle.gif");
		setLength(1);
		setHeight(1);
	}

	@Override
	public void tick(Input input) {
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

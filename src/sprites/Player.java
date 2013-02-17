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
	public static final boolean ALIVE = false;
	public static final boolean DEAD = true;
	private boolean lifeState;

	public Player(int x, int y, int lives) {
		super(x, y);
		this.lives = lives;
		this.lifeState = Player.ALIVE;
		try {
			BufferedImage image = ImageIO.read(Player.class.getClassLoader().getResource( 
					"res/sprites/player/player-idle.gif"));
			setImage(image);
		} catch (IOException e) {
			System.out.println("ERROR: Could not load idle player sprite image.");
		} 
		setLength(1);
		setHeight(1);
	}

	@Override
	public void tick(Input input) {
		if (input.buttons[Input.LEFT] && checkLifeState() == Player.ALIVE) {
			if (getXPos() - 50 != -50) {
				setXPos(getXPos() - 50);
				input.buttons[Input.LEFT] = false;
				SoundEffect.play(SoundEffect.MOVE);
				try {
					BufferedImage image = ImageIO.read(Player.class.getClassLoader().getResource(
							"res/sprites/player/player-left.gif"));
					setImage(image);
				} catch (IOException e) {
					System.out.println("ERROR: Could not load left player sprite image.");
				} 
			}
		}
		if (input.buttons[Input.RIGHT] && checkLifeState() == Player.ALIVE) {
			if (getXPos() + 50 != FroggrGame.GAME_WIDTH) {
				setXPos(getXPos() + 50);
				input.buttons[Input.RIGHT] = false;
				SoundEffect.play(SoundEffect.MOVE);
				try {
					BufferedImage image = ImageIO.read(Player.class.getClassLoader().getResource(
							"res/sprites/player/player-right.gif"));
					setImage(image);
				} catch (IOException e) {
					System.out.println("ERROR: Could not load right player sprite image.");
				}
			}
		}
		if (input.buttons[Input.UP] && checkLifeState() == Player.ALIVE) {
			if (getYPos() - 50 != -50) {
				setYPos(getYPos() - 50);
				input.buttons[Input.UP] = false;
				SoundEffect.play(SoundEffect.MOVE);
				try {
					BufferedImage image = ImageIO.read(Player.class.getClassLoader().getResource(
							"res/sprites/player/player-forward.gif"));
					setImage(image);
				} catch (IOException e) {
					System.out.println("ERROR: Could not load forward player sprite image.");
				}
			}
		}
		if (input.buttons[Input.DOWN] && checkLifeState() == Player.ALIVE) {
			if (getYPos() + 50 != FroggrGame.GAME_HEIGHT - 50) {
				setYPos(getYPos() + 50);
				input.buttons[Input.DOWN] = false;
				SoundEffect.play(SoundEffect.MOVE);
				try {
					BufferedImage image = ImageIO.read(Player.class.getClassLoader().getResource(
							"res/sprites/player/player-back.gif"));
					setImage(image);
				} catch (IOException e) {
					System.out.println("ERROR: Could not load back player sprite image.");
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
	 * @return lifeState
	 * 			  checks if the player is living.
	 */
	public boolean checkLifeState() {
		return lifeState;
	}

	/**
	 * @param lifeState 
	 * 			  set whether the player is dead or alive.
	 */
	public void setLifeState(boolean lifeState) {
		this.lifeState = lifeState;
	}

	/**
	 *  Kills the player
	 */
	public void killPlayer() {
		try {
			BufferedImage image = ImageIO.read(Player.class.getClassLoader().getResource(
					"res/sprites/player/player-death.gif"));
			setImage(image);
		} catch (IOException e) {
			System.out.println("ERROR: Could not load dead player sprite image.");
		}
		
		if (checkLifeState() == Player.ALIVE){
			SoundEffect.play(SoundEffect.COLLISION);
			setLives(getLives() - 1);
			setLifeState(Player.DEAD);
		}
		
			

		
	}

}

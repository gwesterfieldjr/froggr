package sprites;

import game.FroggrGame;
import game.Input;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.print.attribute.standard.Media;

import util.SoundEffect;
import util.SoundPlayer;

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
		if (input.buttons[Input.LEFT]) {
			if (getXPos() - 50 != -50) {
				setXPos(getXPos() - 50);
				input.buttons[Input.LEFT] = false;
				try {
					Thread t = new Thread(new Runnable() {
						
						@Override
						public void run() {
							SoundEffect.MOVE.play();
							
						}
					});t.start();
					BufferedImage image = ImageIO.read(Player.class.getClassLoader().getResource(
							"res/sprites/player/player-left.gif"));
					setImage(image);
				} catch (IOException e) {
					System.out.println("ERROR: Could not load left player sprite image.");
				} 
			}
		}
		if (input.buttons[Input.RIGHT]) {
			if (getXPos() + 50 != FroggrGame.GAME_WIDTH) {
				setXPos(getXPos() + 50);
				input.buttons[Input.RIGHT] = false;
				try {
					BufferedImage image = ImageIO.read(Player.class.getClassLoader().getResource(
							"res/sprites/player/player-right.gif"));
					setImage(image);
				} catch (IOException e) {
					System.out.println("ERROR: Could not load right player sprite image.");
				}
			}
		}
		if (input.buttons[Input.UP]) {
			if (getYPos() - 50 != -50) {
				setYPos(getYPos() - 50);
				input.buttons[Input.UP] = false;
				try {
					BufferedImage image = ImageIO.read(Player.class.getClassLoader().getResource(
							"res/sprites/player/player-forward.gif"));
					setImage(image);
				} catch (IOException e) {
					System.out.println("ERROR: Could not load forward player sprite image.");
				}
			}
		}
		if (input.buttons[Input.DOWN]) {
			if (getYPos() + 50 != FroggrGame.GAME_HEIGHT - 50) {
				setYPos(getYPos() + 50);
				input.buttons[Input.DOWN] = false;
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

}

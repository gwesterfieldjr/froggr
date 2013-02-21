package sprites;

import game.FroggrGame;
import game.Input;

import java.util.Arrays;

import util.SoundEffect;

/**
 * 
 * @author Raj
 * 
 */
public class Player extends Sprite {

	private int lives;
	private boolean alive;
	private boolean snack = false;
	private boolean breakfast = false;
	private boolean lunch = false;
	private boolean dinner = false;
	public static int lunchLocation;
	

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
				if (getXPos() + horizontalMovementAmount != FroggrGame.GAME_WIDTH) {
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
	
	public boolean isEating(){
		int buffer = 15;
		int position = getXPos();
		boolean eating = false;

		if ( ( Math.abs(FroggrGame.FIRST_EATING_ZONE - position) <= buffer ) && getYPos() == 0  && snack == false ){
			eating = true;
			snack = true;
			lunchLocation = FroggrGame.FIRST_EATING_ZONE;

		} 
		if ( ( Math.abs(FroggrGame.SECOND_EATING_ZONE - position) <= buffer ) && getYPos() == 0 && breakfast == false  ){
			eating = true;
			breakfast = true;
			lunchLocation = FroggrGame.FIRST_EATING_ZONE;

		}
		if ( ( Math.abs(FroggrGame.THIRD_EATING_ZONE - position) <= buffer ) && getYPos() == 0 && lunch == false  ){
			eating = true;
			lunch = true;
			lunchLocation = FroggrGame.FIRST_EATING_ZONE;

		} 
		if ( ( Math.abs(FroggrGame.FOURTH_EATING_ZONE - position) <= buffer ) && getYPos() == 0 && dinner == false ){
			eating = true;
			dinner = true;
			lunchLocation = FroggrGame.FIRST_EATING_ZONE;

		}
		
		return eating;
	}
	

}

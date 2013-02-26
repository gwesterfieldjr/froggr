package sprites;

import game.Input;

/**
 * 
 * Lane Sprite for Froggr game.
 * 
 * @author Raj Ramsaroop
 * 		   Greg Westerfield, Jr.
 * 
 */
public class Lane extends Sprite {
	
	/**
	 * The rate at which to regenerate the lane elements.
	 */
	private int regeneration;
	
	/**
	 * The time that counts up, until it reaches the regeneration
	 * value.
	 */
	private int time;

	/**
	 * Create a new Lane at the specific coordinates.
	 * @param xPos The x coordinate.
	 * @param yPos The y coordinate.
	 */
	public Lane(int xPos, int yPos) {
		super(xPos, yPos);
	}

	@Override
	public void tick(Input input) {	
	}

	/**
	 * Returns the regeneration rate at which the lane is producing moving objects.
	 * @return regeneration The rate at which the lane regenerates moving sprites.
	 */
	public int getRegeneration() {
		return regeneration;
	}

	/**
	 * Set the regeneration rate at which the lane is to produce moving objects.
	 * @param regeneration The rate to set.
	 */
	public void setRegeneration(int regeneration) {
		this.regeneration = regeneration;
	}

	/**
	 * Returns the current count up time until lane regeneration.
	 * @return time The time value
	 */
	public int getTime() {
		return time;
	}

	/**
	 * Sets the count down up till the lane produces another moving object.
	 * @param time The time to set.
	 */
	public void setTime(int time) {
		this.time = time;
	}
}

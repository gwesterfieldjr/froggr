package sprites;

import game.Input;

/**
 * 
 * This is the lane class for the FroggrGame class.
 * 
 * @author Raj Ramsaroop
 * 		   Greg Westerfield, Jr.
 * 
 */
public class Lane extends Sprite {
	
	private int regeneration;
	private int time;

	public Lane(int xPos, int yPos) {
		super(xPos, yPos);
	}

	@Override
	public void tick(Input input) {	
	}

	/**
	 * Returns the regeneration rate at which the lane is producing moving objects
	 * @return regeneration
	 */
	public int getRegeneration() {
		return regeneration;
	}

	/**
	 * Set the regeneration rate at which the lane is to produce moving objects.
	 * @param regeneration
	 */
	public void setRegeneration(int regeneration) {
		this.regeneration = regeneration;
	}

	/**
	 * Returns the current count down time till the lane produces another moving object.
	 * @return time
	 */
	public int getTime() {
		return time;
	}

	/**
	 * Sets the count down time till the lane produces another moving object.
	 * @param time
	 */
	public void setTime(int time) {
		this.time = time;
	}
	
	
	

}

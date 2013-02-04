package sprites;

import game.Input;

/**
 * 
 * @author Raj
 *
 */
public abstract class Sprite {

	private int xPos;
	private int yPos;
	private boolean removed;

	public Sprite(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
		removed = false;
	}

	public int getXPos() {
		return xPos;
	}

	public int getYPos() {
		return yPos;
	}

	public void setXPos(int x) {
		this.xPos = x;
	}
	
	public void setYPos(int y) {
		this.yPos = y;
	}
	
	public boolean isRemoved() {
		return removed;
	}
	
	public void remove() {
		removed = true;
	}
	
	/**
	 * Checks if another sprite has collided with this sprite
	 * @param sprite
	 * @return true if collided, false otherwise
	 */
	public boolean hasCollidedWith(Sprite sprite) {
		//TODO: Check x,y position of this sprite, get length and compare with x,y position
		// with other sprite, check length. See if one is on top the other.
		return true;
	}

	/*
	 * Each sprite will change its state in every "tick"
	 */
	public abstract void tick(Input input);
}

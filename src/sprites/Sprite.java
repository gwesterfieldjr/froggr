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

	/*
	 * Each sprite will change its state in every "tick"
	 */
	public abstract void tick(Input input);
}

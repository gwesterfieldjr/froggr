package sprites;

import game.Input;

public class MovingObject extends Sprite {
	
	private int direction;
	private int length;

	public static final boolean SAFE = false;
	public static final int DIRECTION_LEFT = 0;
	public static final int DIRECTION_RIGHT = 1;

	public MovingObject(int xPos, int yPos, int length, int direction) {
		super(xPos, yPos);
		this.setLength(length);
		this.setDirection(direction);
	}

	/**
	 * @return whether the object is moving left or right
	 */
	public int getDirection() {
		return direction;
	}

	/**
	 * @param direction which direction the object will be moving
	 */
	public void setDirection(int direction) {
		this.direction = direction;
	}

	/**
	 * @return the length of the sprite (1,2 or 3)
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @param length the length of the sprite (usually 1,2 or 3)
	 */
	public void setLength(int length) {
		this.length = length;
	}

	@Override
	public void tick(Input input) {
		if (getDirection() == MovingObject.DIRECTION_LEFT) {
			setXPos(getXPos() - 1);
		} else if (getDirection() == MovingObject.DIRECTION_RIGHT) {
			setXPos(getXPos() + 1);
		}
		
		if (getXPos() == getOffScreenXPosition()) {
			remove();
		}
	}
	
	private int getOffScreenXPosition() {
		if (getDirection() == MovingObject.DIRECTION_LEFT) {
			return 0 - getPixelLength();
		} else {
			return 500;
		}
	}

	public int getPixelLength() {
		return getLength() * 50;
	}

}

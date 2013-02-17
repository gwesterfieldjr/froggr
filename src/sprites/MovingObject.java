package sprites;

import game.Input;

/**
 * 
 * @author Raj Ramsaroop
 * 		   Greg Westerfield, Jr.
 * 
 */
public class MovingObject extends Sprite {
	
	private int direction;

	public static final int DIRECTION_LEFT = 0;
	public static final int DIRECTION_RIGHT = 1;

	public MovingObject(int xPos, int yPos, int length, int direction) {
		super(xPos, yPos);
		setLength(length);
		setHeight(1);
		this.direction = direction;
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

	@Override
	public void tick(Input input) {
		if (getDirection() == DIRECTION_LEFT) {
			setXPos(getXPos() - 1);
		} else if (getDirection() == DIRECTION_RIGHT) {
			setXPos(getXPos() + 1);
		}
		
		if (getXPos() == getOffScreenXPosition()) {
			remove();
		}
	}
	
	private int getOffScreenXPosition() {
		if (getDirection() == MovingObject.DIRECTION_LEFT) {
			return 0 - calculatePixelWidth();
		} else {
			return 500;
		}
	}

}

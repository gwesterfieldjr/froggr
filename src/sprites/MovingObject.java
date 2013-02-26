package sprites;

import game.Input;

/**
 * 
 * This is the moving object class for the FroggrGame class. This class 
 * separates sprite's that move from those that do not.
 * 
 * @author Raj Ramsaroop
 * 		   Greg Westerfield, Jr.
 * 
 */
public class MovingObject extends Sprite {
	
	/**
	 * The direction the Sprite moves in.
	 */
	private int direction;

	/**
	 * Value for moving left.
	 */
	public static final int DIRECTION_LEFT = 0;
	
	/**
	 * Value for moving right.
	 */
	public static final int DIRECTION_RIGHT = 1;

	/**
	 * Default constructor creates a new Sprite and sets the length, direction
	 * and height.
	 * @param xPos The x coordinate.
	 * @param yPos The y coordinate.
	 * @param length The length of the Sprite.
	 * @param direction The direction of the Sprite.
	 */
	public MovingObject(int xPos, int yPos, int length, int direction) {
		super(xPos, yPos);
		setLength(length);
		setHeight(1);
		this.direction = direction;
	}

	/**
	 * @return whether the object is moving left or right.
	 * @return The direction value.
	 */
	public int getDirection() {
		return direction;
	}

	/**
	 * Sets which direction the object is moving.
	 * @param direction The direction value.
	 */
	public void setDirection(int direction) {
		this.direction = direction;
	}

	/**
	 * Moves the Sprite left or right. Removes the Sprite if it 
	 * goes off screen.
	 */
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
	
	/**
	 * Returns the off screen position of the moving object.
	 * @return offScreenPosition The x coordinate of an offscreen Sprite.
	 */
	public int getOffScreenXPosition() {
		if (getDirection() == MovingObject.DIRECTION_LEFT) {
			return 0 - calculatePixelWidth();
		} else {
			return 500;
		}
	}

}

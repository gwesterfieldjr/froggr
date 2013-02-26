package sprites;

/**
 * 
 * @author Raj Ramsaroop
 * 		   Greg Westerfield, Jr.
 * A platform is either a log, lily pad or a turtle. Unlike vehicles, Froggr (the player)
 * can stand on the platform which he will use to get to the win zone.
 *
 */
public class Platform extends MovingObject {

	/**
	 * The type of Platform.
	 */
	private int platformType;

	/**
	 * Log platform value.
	 */
	public static final int LOG = 0;
	
	/**
	 * Lily platform value.
	 */
	public static final int LILY = 1;
	
	/**
	 * Turtle platform value.
	 */
	public static final int TURTLE = 2;

	/**
	 * Constructs a new Platform at the specified coordinate with
	 * a specified length and direction.
	 * @param xPos The x coordinate.
	 * @param yPos The y coordinate.
	 * @param length The length.
	 * @param direction The direction value.
	 */
	public Platform(int xPos, int yPos, int length, int direction) {
		super(xPos, yPos, length, direction);
	}

	/**
	 * The type of platform.
	 * @return The type of platform value.
	 */
	public int getPlatformType() {
		return platformType;
	}

	/**
	 * Sets the type of platform a sets the image for the sprite.
	 * @param platformType
	 *            The type of platform (log, lily etc).
	 */
	public void setPlatformType(int platformType) {
		this.platformType = platformType;
		if (platformType == LOG) {
			setImage("res/sprites/platform/log.gif");
		} else if (platformType == LILY) {
			setImage("res/sprites/platform/lily-" + getDirection() + "-"
					+ getLength() + ".gif");
		} else if (platformType == TURTLE) {
			setImage("res/sprites/platform/turtle-" + getDirection() + "-"
					+ getLength() + ".gif");
		}
	}
	
	/**
	 * Returns whether or not the platform has collided with the player.
	 * @param The Sprite to check against.
	 * @return True if this sprite has collided with the specified sprite.
	 */
	@Override
	public boolean hasCollidedWith(Sprite sprite) {
		int xMin = getXPos();
		int xMax = getXPos() + calculatePixelWidth();
		int yMin = getYPos();

		if ( (xMax > sprite.getXPos() && xMin < sprite.getXPos() && yMin == sprite.getYPos() )
				|| (xMax > sprite.getXPos() && xMin < (sprite.getXPos() + sprite.calculatePixelWidth() ) && yMin == sprite.getYPos()) ){
			return true;
		} else {
			return false;
		}
	}

}

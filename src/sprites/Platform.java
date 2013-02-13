package sprites;

import java.util.Random;

import game.FroggrGame;
import game.Input;

/**
 * 
 * @author Raj
 * A platform is either a log, lily pad or a turtle. Unlike vehicles, Froggr (the player)
 * can stand on the platform which he will use to get to the win zone.
 *
 */
public class Platform extends MovingObject {
	
	private int length;
	private int platformType;
	
	public static final int LOG = 0;
	public static final int LILY = 1;
	public static final int TURTLE = 2;

	public Platform(int xPos, int yPos, int length, int direction) {
		super(xPos, yPos, length, direction);

	}
	
	/**
	 * @return the type of platform
	 */
	public int getPlatformType() {
		return platformType;
	}

	/**
	 * @param platformType the type of platform (log, lily etc)
	 */
	public void setPlatformType(int platformType) {
		this.platformType = platformType;
		if (platformType == LOG) {
			setImageURL("res/sprites/platform/log.gif");
		} else if (platformType == LILY) {
			setImageURL("res/sprites/platform/lily-" + getDirection() + "-" + getLength() + ".gif");
		} else if (platformType == TURTLE) {
			setImageURL("res/sprites/platform/turtle-" + getDirection() + "-" +getLength() + ".gif");
		}
	}

	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @param length the length to set (usually 1, 2 or 3)
	 */
	public void setLength(int length) {
		this.length = length;
	}

}

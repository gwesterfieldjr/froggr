package sprites;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

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
			try {
				BufferedImage image = ImageIO.read(Platform.class.getClassLoader().getResource(
						"res/sprites/platform/log.gif"));
				setImage(image);
			} catch (IOException e) {
				System.out.println("ERROR: Coud not load log sprite image.");
			}
		} else if (platformType == LILY) {
			try {
				BufferedImage image = ImageIO.read(Platform.class.getClassLoader().getResource(
						"res/sprites/platform/lily-" + getDirection() + "-" + getLength() + ".gif"));
				setImage(image);
			} catch (IOException e) {
				System.out.println("ERROR: Could not load lily sprite image.");
			}
		} else if (platformType == TURTLE) {
			try {
				BufferedImage image = ImageIO.read(Platform.class.getClassLoader().getResource(
						"res/sprites/platform/turtle-" + getDirection() + "-" +getLength() + ".gif"));
				setImage(image);
			} catch (IOException e) {
				System.out.println("ERROR: Could not load turtle sprite image.");
			}
		}
	}

}

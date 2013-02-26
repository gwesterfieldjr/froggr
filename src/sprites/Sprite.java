package sprites;

import java.awt.Canvas;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.Input;

/**
 * A Sprite is an object that needs to be tracked in the game. Sprites
 * are drawn on a Canvas and usually have an image associated with them.
 * @author Raj Ramsaroop 
 * 		   Greg Westerfield, Jr.
 * 
 */
public abstract class Sprite {

	/**
	 * The x coordinate.
	 */
	private int xPos;
	
	/**
	 * The y coordinate.
	 */
	private int yPos;
	
	/**
	 * Whether the Sprite is removed from the Canvas.
	 */
	private boolean removed;
	
	/**
	 * The Sprite's image.
	 */
	private Image image;
	
	/**
	 * Relative location of the image file.
	 */
	private String imageLocation;
	
	/**
	 * Unit length of the Sprite.
	 */
	private int length;
	
	/**
	 * Unit height of the Sprite.
	 */
	private int height;
	
	/**
	 * Used to convert length and width dimensions between 
	 * pixels and unit dimensions.
	 */
	private int pixelUnitSize;
	
	/**
	 * The Canvas the Sprite is drawn on.
	 */
	private Canvas canvas;
	
	/**
	 * Whether the Sprite is animated or not.
	 */
	private boolean animated = false;

	/**
	 * Constructs a Sprite and the specified coordinates.
	 * @param xPos The x coordinate.
	 * @param yPos The y coordinate.
	 */
	public Sprite(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
		removed = false;
		// Default pixel size for one unit.
		pixelUnitSize = 50;
	}

	/**
	 * Returns the x position of the sprite.
	 * @return The x coordinate.
	 */
	public int getXPos() {
		return xPos;
	}

	/**
	 * Returns the y position of the sprite.
	 * @return The y coordinate.
	 */
	public int getYPos() {
		return yPos;
	}

	/**
	 * Sets the x position of the sprite.
	 * @return The x coordinate to set.
	 */
	public void setXPos(int x) {
		this.xPos = x;
	}

	/**
	 * Sets the y position of the sprite.
	 * @return The y coordinate to set.
	 */
	public void setYPos(int y) {
		this.yPos = y;
	}

	/**
	 * Checks if the sprite is removed.
	 * @return boolean True if the removed.
	 */
	public boolean isRemoved() {
		return removed;
	}

	/**
	 * Removes the Sprite.
	 */
	public void remove() {
		removed = true;
	}

	/**
	 * Checks if another sprite has collided with this sprite
	 * Will override as needed.
	 * 
	 * @param sprite
	 *            The other sprite to check against
	 * @return True if collided.
	 */
	public boolean hasCollidedWith(Sprite sprite) {
		return false;
	}

	/**
	 * Each Sprite changes its state in every "tick".
	 * @param input The input from the Player.
	 */
	public abstract void tick(Input input);

	/**
	 * Sets the image of the sprite.
	 * @param imageLocation The relative location of the image.
	 */
	public void setImage(String imageLocation) {
		try {
			image = (BufferedImage) ImageIO.read(Sprite.class.getClassLoader()
					.getResource(imageLocation));
			this.imageLocation = imageLocation;
		} catch (IOException e) {
			System.err.println("Could not load " + imageLocation);
		}
	}

	/**
	 * Returns the image of the sprite.
	 * @return The Image object.
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * Returns the unit length of the Sprite.
	 * @return The length.
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Sets the unit length of the Sprite.
	 * @param length
	 *            The unit length to set.
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * The unit height.
	 * @return The unit height.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Sets the unit height of the Sprite.
	 * @param height
	 *            The height to set.
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Calculates the width of the Sprite in pixels.
	 * @return The pixel width of the sprite.
	 */
	public int calculatePixelWidth() {
		return length * pixelUnitSize;
	}

	/**
	 * Calculates and returns the height of the Sprite in pixels.
	 * @return The pixel height of the sprite.
	 */
	public int calculatePixelHeight() {
		return height * pixelUnitSize;
	}

	/**
	 * Set the pixel unit size of the sprite.
	 */
	public void setPixelUnitSize(int pixelUnitSize) {
		this.pixelUnitSize = pixelUnitSize;
	}

	/**
	 * Pixel unit size of the Sprite.
	 * @return The pixel unit size of the sprite.
	 */
	public int getPixelUnitSize() {
		return pixelUnitSize;
	}

	/**
	 * The Canvas the Graphics are being drawn on.
	 * @return The Canvas.
	 */
	public Canvas getCanvas() {
		return canvas;
	}

	/**
	 * Set the Canvas for the Graphics to be drawn on.
	 * @param canvas
	 *            The Canvas object.
	 */
	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}

	/**
	 * The relative location of the image file.
	 * @return The image location.
	 */
	public String getImageLocation() {
		return imageLocation;
	}

	/**
	 * Returns whether the sprite is animated or not.
	 * @return true if animated.
	 */
	public boolean isAnimated() {
		return animated;
	}

	/**
	 * Set this sprite to be animated or not.
	 * @param animated
	 *            True for animated.
	 */
	public void setAnimated(boolean animated) {
		this.animated = animated;
	}
}

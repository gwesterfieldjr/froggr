package sprites;

import java.awt.Canvas;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.Input;

/**
 * A Sprite is an object that needs to be tracked in the game.
 * @author Raj Ramsaroop 
 * 		   Greg Westerfield, Jr.
 * 
 */
public abstract class Sprite {

	private int xPos;
	private int yPos;
	private boolean removed;
	private Image image;
	private String imageLocation;
	private int length;
	private int height;
	private int pixelUnitSize;
	private Canvas canvas;
	private boolean animated = false;

	public Sprite(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
		removed = false;
		pixelUnitSize = 50;
	}

	/**
	 * Returns the x position of the sprite.
	 * @return
	 */
	public int getXPos() {
		return xPos;
	}

	/**
	 * Returns the y position of the sprite.
	 * @return
	 */
	public int getYPos() {
		return yPos;
	}

	/**
	 * Sets the x position of the sprite.
	 * @return
	 */
	public void setXPos(int x) {
		this.xPos = x;
	}

	/**
	 * Sets the y position of the sprite.
	 * @return
	 */
	public void setYPos(int y) {
		this.yPos = y;
	}

	/**
	 * Checks if the sprite is removed
	 * @return boolean removed
	 */
	public boolean isRemoved() {
		return removed;
	}

	/**
	 * Returns if the sprite is removed or not.
	 * @return boolean
	 */
	public void remove() {
		removed = true;
	}

	/**
	 * Checks if another sprite has collided with this sprite
	 * Will override as needed
	 * 
	 * @param sprite
	 *            the other sprite to check against
	 * @return true if collided, false otherwise
	 */
	public boolean hasCollidedWith(Sprite sprite) {
		return false;
	}

	/**
	 * Each sprite will change its state in every "tick"
	 */
	public abstract void tick(Input input);

	/**
	 * Sets the image of the sprite
	 * @param imageLocation
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
	 * Returns the image of the sprite
	 * @return
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @param length
	 *            the length to set
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * 
	 * @return the pixel width of the sprite
	 */
	public int calculatePixelWidth() {
		return length * pixelUnitSize;
	}

	/**
	 * 
	 * @return the pixel height of the sprite
	 */
	public int calculatePixelHeight() {
		return height * pixelUnitSize;
	}

	/**
	 * Set the pixel unit size of the sprite
	 */
	public void setPixelUnitSize(int pixelUnitSize) {
		this.pixelUnitSize = pixelUnitSize;
	}

	/**
	 * 
	 * @return the pixel unit size of the sprite
	 */
	public int getPixelUnitSize() {
		return pixelUnitSize;
	}

	/**
	 * @return the canvas
	 */
	public Canvas getCanvas() {
		return canvas;
	}

	/**
	 * @param canvas
	 *            the canvas to set
	 */
	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}

	/**
	 * @return the imageLocation
	 */
	public String getImageLocation() {
		return imageLocation;
	}

	/**
	 * @return the animated
	 */
	public boolean isAnimated() {
		return animated;
	}

	/**
	 * @param animated
	 *            the animated to set
	 */
	public void setAnimated(boolean animated) {
		this.animated = animated;
	}
}

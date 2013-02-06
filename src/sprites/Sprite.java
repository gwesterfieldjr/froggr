package sprites;

import java.awt.Canvas;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

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
	private Image image;
	private String imageURL;
	private int length;
	private int height;
	private int pixelUnitSize;

	public Sprite(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
		removed = false;
		pixelUnitSize = 50;
	}

	public void createImage(Canvas c) {
		MediaTracker media = new MediaTracker(c);
		image = Toolkit.getDefaultToolkit().getImage(imageURL);
		media.addImage(image, 0);
		try {
			media.waitForID(0);
		} catch (Exception e) {
			System.out.println("ERROR: Could not load " + imageURL + ".");
		}
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
	 * 
	 * @param sprite
	 * @return true if collided, false otherwise
	 */
	public boolean hasCollidedWith(Sprite sprite) {
		int xMin = getXPos();
		int xMax = getXPos() + calculatePixelWidth();
		int yMin = getYPos();
		int yMax = getYPos() + calculatePixelHeight();

		if (sprite.getXPos() >= xMin && sprite.getXPos() <= xMax
				&& sprite.getYPos() >= yMin && sprite.getYPos() <= yMax) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Each sprite will change its state in every "tick"
	 */
	public abstract void tick(Input input);

	/**
	 * @return the location of the image
	 */
	public String getImageURL() {
		return imageURL;
	}

	/**
	 * @param imageURL
	 *            the location of the image you want to use
	 */
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

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

	public int calculatePixelWidth() {
		return length * pixelUnitSize;
	}

	public int calculatePixelHeight() {
		return height * pixelUnitSize;
	}

	public void setPixelUnitSize(int pixelUnitSize) {
		this.pixelUnitSize = pixelUnitSize;
	}

	public int getPixelUnitSize() {
		return pixelUnitSize;
	}

}

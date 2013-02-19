package sprites;

import java.awt.Canvas;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import game.Input;

/**
 * 
 * @author Raj Ramsaroop
 * 		   Greg Westerfield, Jr.
 * 
 */
public abstract class Sprite {

	private int xPos;
	private int yPos;
	private boolean removed;
	private Image image;
	private int length;
	private int height;
	private int pixelUnitSize;
	private Canvas canvas;

	public Sprite(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
		removed = false;
		pixelUnitSize = 50;
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
		int buffer = 10;
		int xMin = getXPos();
		int xMax = getXPos() + calculatePixelWidth();
		int yMin = getYPos();
		int yMax = getYPos() + calculatePixelHeight();

		if ( (xMax > sprite.getXPos() && xMin < sprite.getXPos() + buffer && yMin == sprite.getYPos() )
				|| (xMax > sprite.getXPos() && xMin < (sprite.getXPos() + sprite.calculatePixelWidth() ) && yMin == sprite.getYPos()) ){
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Each sprite will change its state in every "tick"
	 */
	public abstract void tick(Input input);
	
	public void setImage(BufferedImage image) {
		this.image = image;
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

	/**
	 * @return the canvas
	 */
	public Canvas getCanvas() {
		return canvas;
	}

	/**
	 * @param canvas the canvas to set
	 */
	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}

}

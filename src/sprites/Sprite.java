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

	public Sprite(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
		removed = false;
	}
	
	public void createImage(Canvas c) {
		MediaTracker media = new MediaTracker(c);
		image = Toolkit.getDefaultToolkit().getImage(imageURL);
		media.addImage(image, 0);
		try {
			media.waitForID(0);
		} catch (Exception e) {
			
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
	 * @param sprite
	 * @return true if collided, false otherwise
	 */
	public boolean hasCollidedWith(Sprite sprite) {
		//TODO: Check x,y position of this sprite, get length and compare with x,y position
		// with other sprite, check length. See if one is on top the other.
		return true;
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
	 * @param imageURL the location of the image you want to use
	 */
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	
	public Image getImage() {
		return image;
	}
	
}

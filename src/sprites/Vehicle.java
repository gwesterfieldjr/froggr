package sprites;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * 
 * @author Raj Ramsaroop
 *         Greg Westerfield, Jr.
 * This is for the vehicle sprite. Can either be a car or truck. Cars be be length 1, 2 or 3
 * Cars of length >=2 are just multiple cars in a row
 * If Froggr touches a vehicle, he loses a life
 *
 */
public class Vehicle extends MovingObject {

	private int vehicleType;

	public static final int CAR = 0;
	public static final int TRUCK = 1;

	public Vehicle(int xPos, int yPos, int length, int direction) {
		super(xPos, yPos, length, direction);
	}

	/**
	 * Sets the vehicle type
	 * @param vehicleType
	 */
	public void setVehicleType(int vehicleType) {
		this.vehicleType = vehicleType;
		setImageURLForVehicle();
	}

	/**
	 * Sets the image URL
	 */
	private void setImageURLForVehicle() {
		if (vehicleType == CAR) {
			Random r = new Random();
			String color = (r.nextInt() % 2 == 0) ? "red" : "blue";
			setImage("res/sprites/vehicle/car-" + color + "-" + getDirection()
					+ "-" + getLength() + ".gif");
		} else if (vehicleType == TRUCK) {
			setImage("res/sprites/vehicle/truck-0-2.gif");
		}
	}

	/**
	 * Returns the vehicle type
	 * @return
	 */
	public int vehicleType() {
		return this.vehicleType;
	}
	
	/**
	 * Returns whether or not the vehicle has collided with the player.
	 */
	@Override
	public boolean hasCollidedWith(Sprite sprite) {
		int buffer = 10;
		int xMin = getXPos();
		int xMax = getXPos() + calculatePixelWidth();
		int yMin = getYPos();

		if ( (xMax > sprite.getXPos() + buffer && xMin < sprite.getXPos() && yMin == sprite.getYPos() )
				|| (xMax > (sprite.getXPos() + sprite.calculatePixelWidth()) && xMin < (sprite.getXPos() + sprite.calculatePixelWidth() - buffer ) && yMin == sprite.getYPos()) ){
			return true;
		} else {
			return false;
		}
	}
	
}

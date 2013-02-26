package sprites;

import java.util.Random;

/**
 * 
 * @author Raj Ramsaroop
 *         Greg Westerfield, Jr.
 * This is for the vehicle sprite. Can either be a car or truck. Cars be be 
 * length 1, 2 or 3. Cars of length >=2 are just multiple cars in a row.
 *
 */
public class Vehicle extends MovingObject {

	/**
	 * The type of vehicle.
	 */
	private int vehicleType;

	/**
	 * Value for car sprites.
	 */
	public static final int CAR = 0;
	
	/**
	 * Value for truck sprites.
	 */
	public static final int TRUCK = 1;

	/**
	 * Constructs a new Vehicle at the specified location with the specified
	 * length and direction.
	 * @param xPos The x coordinate.
	 * @param yPos The y coordinate.
	 * @param length The unit length.
	 * @param direction The direction the sprites move in.
	 */
	public Vehicle(int xPos, int yPos, int length, int direction) {
		super(xPos, yPos, length, direction);
	}

	/**
	 * Sets the vehicle type.
	 * @param vehicleType The value for the vehicle type.
	 */
	public void setVehicleType(int vehicleType) {
		this.vehicleType = vehicleType;
		setImageURLForVehicle();
	}

	/**
	 * Determines the location of the file based on the length and
	 * direction.
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
	 * Returns the vehicle type.
	 * @return The value for vehicle type.
	 */
	public int vehicleType() {
		return this.vehicleType;
	}
	
	/**
	 * Returns whether or not the vehicle has collided with the specified
	 * Sprite.
	 * @param The Sprite to check against.
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

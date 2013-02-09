package sprites;

import java.util.Random;

/**
 * 
 * @author Raj Ramsaroop
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
	
	public void setVehicleType(int vehicleType) {
		this.vehicleType = vehicleType;
		setImageURLForVehicle();
	}
	
	private void setImageURLForVehicle() {
		if (vehicleType == CAR) {
			Random r = new Random();
			String color = (r.nextInt()%2==0) ? "red" : "blue";
			setImageURL("res/sprites/vehicle/car-" + color + "-" + getDirection() + "-" + getLength() + ".gif");
		} else if (vehicleType == TRUCK) {
			setImageURL("res/sprites/vehicle/truck-0-2.gif");
		}
	}

	public int vehicleType() {
		return this.vehicleType;
	}

}

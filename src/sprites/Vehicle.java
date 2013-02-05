package sprites;

import java.util.Random;

import game.Input;
import game.FroggrGame;

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
	public static final boolean SAFE = false;
	
	public Vehicle(int xPos, int yPos, int length, int direction) {
		super(xPos, yPos, length, direction);
	}
	
	public void setVehicleType(int vehicleType) {
		this.vehicleType = vehicleType;
		setImageURLForVehicle();
	}
	
	private void setImageURLForVehicle() {
		if (vehicleType == CAR) {
			setImageURL("res/sprites/car.png");
		} else if (vehicleType == TRUCK) {
			setImageURL("res/sprites/truck.png");
		}
	}

	public int vehicleType() {
		return this.vehicleType;
	}

}

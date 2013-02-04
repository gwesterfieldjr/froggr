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
	
	public Vehicle(int xPos, int yPos, int length, int direction) {
		super(xPos, yPos, length, direction);
	}

	@Override
	public void tick(Input input) {
		Random r = new Random();
		if (r.nextInt()%2==0) {
			setXPos(getXPos() + 1);
		} else {
			setXPos(getXPos() - 1);
		}
		
		if (getXPos() > FroggrGame.WIDTH) {
			remove();
		} else if (getXPos() < 0) {
			remove();
		}
	}
	
	public void setVehicleType(int vehicleType) {
		this.vehicleType = vehicleType;
	}
	
	public int vehicleType() {
		return this.vehicleType;
	}

}

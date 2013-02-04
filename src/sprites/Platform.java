package sprites;

import java.util.Random;

import game.FroggrGame;
import game.Input;

/**
 * 
 * @author Raj
 * A platform is either a log, lily pad or a turtle. Unlike vehicles, Froggr (the player)
 * can stand on the platform which he will use to get to the win zone.
 *
 */
public class Platform extends MovingObject {
	
	public static final boolean SAFE = true;

	public Platform(int xPos, int yPos, int length, int direction) {
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

}

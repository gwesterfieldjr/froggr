package sprites;

import game.Input;

/**
 * A fly is the Froggr's lunch.
 * @author Greg Westerfield, Jr.
 * 		   Raj Ramsaroop
 *
 */
public class Fly extends Sprite{

	private boolean consumed;
	
	public Fly(int xPos, int yPos) {
		super(xPos, yPos);
		setImage("res/sprites/lane/fly.png");
		this.consumed = false;
	}

	@Override
	public void tick(Input input) {
	}

	/**
	 * Returns whether or not the fly has been consumed by Froggr.
	 * @return consumed
	 */
	public boolean isConsumed() {
		return consumed;
	}

	/**
	 * Sets whether or not the fly has been consumed by Froggr.
	 * @return consumed
	 */
	public void setConsumed(boolean consumed) {
		this.consumed = consumed;
	}
	
	/**
	 * Override of Sprite class. Checks if the player is within the plus or minus buffer. 
	 * If so the method will return true;
	 */
	@Override 
	public boolean hasCollidedWith(Sprite sprite){
		int buffer = 15;
		int position = sprite.getXPos();

		if ( ( Math.abs( getXPos() - position) <= buffer ) && sprite.getYPos() == 0 ){
			return true;
		} else {
			return false;
		}
	}
	

}

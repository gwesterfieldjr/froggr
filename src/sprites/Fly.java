package sprites;

import game.Input;

/**
 * A fly is the Froggr's lunch. (Win Sprite).
 * @author Greg Westerfield, Jr.
 * 		   Raj Ramsaroop
 *
 */
public class Fly extends Sprite{

	/**
	 * Whether the fly has been eaten or not.
	 */
	private boolean consumed;
	
	/**
	 * Initializes a Fly at a specific position and creates an image.
	 * Fly is not consumed by default.
	 * @param xPos The x coordinate.
	 * @param yPos The y coordinate.
	 */
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
	 * @return True if the Fly is consumed.
	 */
	public boolean isConsumed() {
		return consumed;
	}

	/**
	 * Sets whether or not the fly has been consumed by Froggr.
	 * Changes image for the Sprite depending on whether it is 
	 * consumed or not.
	 * @return Set to true to consume fly.
	 */
	public void setConsumed(boolean consumed) {
		this.consumed = consumed;
		if (consumed) {
			setImage("res/sprites/lane/fly-consumed.png");
		} else {
			setImage("res/sprites/lane/fly.png");
		}
		
	}
	
	/**
	 * Override of Sprite class. Checks if the player is within 
	 * the plus or minus buffer. If so the method will return true.
	 * @return True if the player is in the buffer.
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

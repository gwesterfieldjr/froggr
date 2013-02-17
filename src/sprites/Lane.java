package sprites;

import game.Input;

/**
 * 
 * @author Raj Ramsaroop
 * 		   Greg Westerfield, Jr.
 * 
 */
public class Lane extends Sprite {
	
	private int regeneration;
	private int time;

	public Lane(int xPos, int yPos) {
		super(xPos, yPos);
	}

	@Override
	public void tick(Input input) {	
	}

	public int getRegeneration() {
		return regeneration;
	}

	public void setRegeneration(int regeneration) {
		this.regeneration = regeneration;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	

}

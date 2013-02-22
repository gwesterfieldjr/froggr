package sprites;

import game.FroggrGame;
import game.Input;

public class Win extends Sprite{

	private boolean consumed;
	
	public Win(int xPos, int yPos) {
		super(xPos, yPos);
		//setImage("res/sprites/lane/fly.png");
	}

	@Override
	public void tick(Input input) {
		
	}

	public boolean isConsumed() {
		return consumed;
	}

	public void setConsumed(boolean consumed) {
		this.consumed = consumed;
	}
	
	public boolean hasCollidedWith(Sprite sprite){
		int buffer = 15;
		int position = sprite.getXPos();

		if ( ( Math.abs( getXPos() - position) <= buffer ) && getYPos() == 0 ){
			return true;
		} else {
			return false;
		}
	}
	

}

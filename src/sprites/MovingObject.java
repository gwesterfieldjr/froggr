package sprites;

public abstract class MovingObject extends Sprite {
	
	private int direction;
	private int length;

	public static final boolean SAFE = false;
	public static final int LEFT = 0;
	public static final int RIGHT = 1;

	public MovingObject(int xPos, int yPos, int length, int direction) {
		super(xPos, yPos);
		this.length = length;
		this.direction = direction;
	}

}

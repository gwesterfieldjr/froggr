package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;

import sprites.Lane;
import sprites.MovingObject;
import sprites.Platform;
import sprites.Player;
import sprites.Vehicle;

/**
 * 
 * @author Raj
 * 
 */
public class FroggrGame extends Canvas implements Runnable, KeyListener {

	private Player player;
	private int startingLives = 3;
	private Input input = new Input();

	private ArrayList<Lane> lanes = new ArrayList<Lane>();
	private ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
	private ArrayList<Platform> platforms = new ArrayList<Platform>();

	private final int REGENERATION = 225;
	private int time = 0;

	public static final int GAME_WIDTH = 500;
	public static final int GAME_HEIGHT = 700;
	public static final int LANE_HEIGHT = 50;
	public static final Color FOREGROUND_COLOR = Color.BLACK;
	
	// Constants for each lane index
	public static final int LANE_WIN = 0;
	public static final int LANE_WATER_FIFTH = 1;
	public static final int LANE_WATER_FOURTH = 2;
	public static final int LANE_WATER_THIRD = 3;
	public static final int LANE_WATER_SECOND = 4;
	public static final int LANE_WATER_FIRST = 5;
	public static final int LANE_GRASS_FIRST = 6;
	public static final int LANE_GRASS_SECOND = 7;
	public static final int LANE_ROAD_FOURTH = 8;
	public static final int LANE_ROAD_THIRD = 9;
	public static final int LANE_ROAD_SECOND = 10;
	public static final int LANE_ROAD_FIRST = 11;
	public static final int LANE_START = 12;

	public FroggrGame() {
		addKeyListener(this);
		setForeground(FOREGROUND_COLOR);
		setSize(GAME_WIDTH, GAME_HEIGHT);
	}

	private void createLanes() {
		// Create our 13 game lanes
		for (int i=0;i<13;i++) {
			lanes.add(new Lane(0,i*50));
		}
		
		// Add the image for each lane
		lanes.get(LANE_WIN).setImageURL("res/sprites/lane/win.png");

		// Use alternating images randomly for water lanes
		for (int i = LANE_WATER_FIFTH;i <= LANE_WATER_FIRST;i++) {
			Random r = new Random();
			if (r.nextInt()%2==0) {
				lanes.get(i).setImageURL("res/sprites/lane/water-0.png");
			} else {
				lanes.get(i).setImageURL("res/sprites/lane/water-1.png");
			}
		}
		
		// Safe area before water
		lanes.get(LANE_GRASS_FIRST).setImageURL("res/sprites/lane/grass.png");
		lanes.get(LANE_GRASS_SECOND).setImageURL("res/sprites/lane/grass.png");
		
		// Road lanes
		lanes.get(LANE_ROAD_FOURTH).setImageURL("res/sprites/lane/road-top.png");
		lanes.get(LANE_ROAD_THIRD).setImageURL("res/sprites/lane/road-middle.png");
		lanes.get(LANE_ROAD_SECOND).setImageURL("res/sprites/lane/road-middle.png");
		lanes.get(LANE_ROAD_FIRST).setImageURL("res/sprites/lane/road-bottom.png");
		
		// Start lane
		lanes.get(LANE_START).setImageURL("res/sprites/lane/grass.png");
		
		// Create Image object for all lanes
		for(Lane l : lanes) {
			l.createImage(this);
		}
	}

	private void processLanes(Graphics g) {
		for (Lane l : lanes) {
			g.drawImage(l.getImage(), l.getXPos(), l.getYPos(), this);
		}
	}

	private void spawnPlayer(int lives) {
		this.player = new Player(250, GAME_HEIGHT - (2*LANE_HEIGHT), lives);
		player.createImage(this);

	}

	private void generateVehicle(Lane lane, int length, int direction) {
		if (time++ > REGENERATION) {
			time = 0;
			int startPosition = (direction==MovingObject.DIRECTION_LEFT) ? GAME_WIDTH : 0 - (length * 50);
			Vehicle v = new Vehicle(startPosition, lane.getYPos(), length, direction);
			v.setVehicleType(Vehicle.CAR);
			v.createImage(this);
			vehicles.add(v);
		}
	}

	private void generatePlatform(Lane lane, int length, int direction) {

	}

	private void processPlayer(Graphics g) {
		player.tick(input);
		g.drawImage(player.getImage(), player.getXPos(), player.getYPos(), this);
	}

	// process vehicle
	private void processVehicles(Graphics g) {
		for (int i = 0; i < vehicles.size(); i++) {
			Vehicle v = vehicles.get(i);
			if (!v.isRemoved()) {
				v.tick(input);
				g.drawImage(v.getImage(), v.getXPos(), v.getYPos(), this);
			}

		}
	}

	private void processPlatform(Graphics g) {
		// TODO Auto-generated method stub

	}

	// the main game loop
	private void render() {

		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			requestFocus();
			return;
		}

		Graphics g = bs.getDrawGraphics();
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.RED);
		
		processLanes(g);
		processVehicles(g);
		processPlayer(g);
		processPlatform(g);
		
		g.dispose();
		bs.show();

		// game is too fast without this delay
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void addVehiclesToLanes() {
		generateVehicle(lanes.get(LANE_ROAD_FIRST), 1, MovingObject.DIRECTION_LEFT);
		generateVehicle(lanes.get(LANE_ROAD_SECOND), 2, MovingObject.DIRECTION_RIGHT);
		generateVehicle(lanes.get(LANE_ROAD_THIRD), 3, MovingObject.DIRECTION_LEFT);
		generateVehicle(lanes.get(LANE_ROAD_FOURTH), 1, MovingObject.DIRECTION_RIGHT);
	}

	@Override
	public void run() {
		while (true) {
			render();
		}
	}

	/**
	 * This method starts the game loop and thread
	 */
	public void start() {
		createLanes();
		addVehiclesToLanes();
		spawnPlayer(startingLives);
		new Thread(this).start();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		input.set(e.getKeyCode(), false);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		input.set(e.getKeyCode(), true);
	}

}

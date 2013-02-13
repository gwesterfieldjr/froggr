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

	/**
	 * Width of the game canvas in pixels.
	 */
	public static final int GAME_WIDTH = 500;

	/**
	 * Height of the game canvas in pixels.
	 */
	public static final int GAME_HEIGHT = 700;

	/**
	 * Height of the lane in pixels.
	 */
	public static final int LANE_HEIGHT = 50;

	/**
	 * Number of lanes in the game.
	 */
	public static final int NUMBER_OF_LANES = 13;

	/**
	 * Background color of the game canvas.
	 */
	public static final Color FOREGROUND_COLOR = Color.BLACK;

	/**
	 * Win lane index.
	 */
	public static final int LANE_WIN = 0;

	/**
	 * Fifth water lane index.
	 */
	public static final int LANE_WATER_FIFTH = 1;

	/**
	 * Fourth water lane index.
	 */
	public static final int LANE_WATER_FOURTH = 2;

	/**
	 * Third water lane index.
	 */
	public static final int LANE_WATER_THIRD = 3;

	/**
	 * Second water lane index.
	 */
	public static final int LANE_WATER_SECOND = 4;

	/**
	 * First water lane index.
	 */
	public static final int LANE_WATER_FIRST = 5;

	/**
	 * First safe grass lane index.
	 */
	public static final int LANE_GRASS_FIRST = 6;

	/**
	 * Second safe grass lane index.
	 */
	public static final int LANE_GRASS_SECOND = 7;

	/**
	 * Fourth road lane index.
	 */
	public static final int LANE_ROAD_FOURTH = 8;

	/**
	 * Third road lane index.
	 */
	public static final int LANE_ROAD_THIRD = 9;

	/**
	 * Second road lane index.
	 */
	public static final int LANE_ROAD_SECOND = 10;

	/**
	 * First road lane index.
	 */
	public static final int LANE_ROAD_FIRST = 11;

	/**
	 * Starting lane index.
	 */
	public static final int LANE_START = 12;

	/**
	 * Default constructor for FroggrGame
	 */
	public FroggrGame() {
		addKeyListener(this);
		setForeground(FOREGROUND_COLOR);
		setSize(GAME_WIDTH, GAME_HEIGHT);
	}

	/**
	 * Initializes the ArrayList of lanes
	 */
	private void createLanes() {
		// Create our 13 game lanes
		for (int i = 0; i < NUMBER_OF_LANES; i++) {
			lanes.add(new Lane(0, i * 50));
		}

		// Add the image for each lane
		lanes.get(LANE_WIN).setImageURL("res/sprites/lane/win.png");

		// Use alternating images randomly for water lanes
		for (int i = LANE_WATER_FIFTH; i <= LANE_WATER_FIRST; i++) {
			Random r = new Random();
			if (r.nextInt() % 2 == 0) {
				lanes.get(i).setImageURL("res/sprites/lane/water-0.png");
			} else {
				lanes.get(i).setImageURL("res/sprites/lane/water-1.png");
			}
		}

		// Safe area before water
		lanes.get(LANE_GRASS_FIRST).setImageURL("res/sprites/lane/grass.png");
		lanes.get(LANE_GRASS_SECOND).setImageURL("res/sprites/lane/grass.png");

		// Road lanes
		lanes.get(LANE_ROAD_FOURTH)
				.setImageURL("res/sprites/lane/road-top.png");
		lanes.get(LANE_ROAD_THIRD).setImageURL(
				"res/sprites/lane/road-middle.png");
		lanes.get(LANE_ROAD_SECOND).setImageURL(
				"res/sprites/lane/road-middle.png");
		lanes.get(LANE_ROAD_FIRST).setImageURL(
				"res/sprites/lane/road-bottom.png");

		// Start lane
		lanes.get(LANE_START).setImageURL("res/sprites/lane/grass.png");

		// Create Image object for all lanes
		for (Lane l : lanes) {
			l.createImage(this);
		}
	}

	/**
	 * Draws the images on the canvas for the lanes. Used in the game render
	 * loop.
	 * 
	 * @param g
	 *            The Graphics object used by the Canvas.
	 */
	private void processLanes(Graphics g) {
		for (Lane l : lanes) {
			g.drawImage(l.getImage(), l.getXPos(), l.getYPos(), this);
		}
	}

	/**
	 * Creates a new Player sprite at the starting location with a set number of
	 * lives.
	 * 
	 * @param lives
	 *            The number of lives to set
	 */
	private void spawnPlayer(int lives) {
		this.player = new Player(250, GAME_HEIGHT - (2 * LANE_HEIGHT), lives);
		player.createImage(this);
	}

	/**
	 * Generates vehicles for the road lanes.
	 * 
	 * @param lane
	 *            Lane sprite object to generate the vehicle for.
	 * @param length
	 *            length of the vehicle (1, 2 or 3 for cars, 3 for trucks).
	 * @param direction
	 *            the direction that the vehicle moves.
	 * @param vehicleType
	 *            The type of vehicle to generate.
	 */
	private void generateVehicle(Lane lane, int length, int direction,
			int vehicleType) {
		if (time++ > REGENERATION) {
			time = 0;
			int startPosition = (direction == MovingObject.DIRECTION_LEFT) ? GAME_WIDTH
					: 0 - (length * 50);
			Vehicle v = new Vehicle(startPosition, lane.getYPos(), length,
					direction);
			v.setVehicleType(vehicleType);
			v.createImage(this);
			vehicles.add(v);
		}
	}

	/**
	 * Generates the platforms for the water lanes.
	 * 
	 * @param lane
	 *            The Lane sprite object to generate the platform for.
	 * @param length
	 *            The length of the platform.
	 * @param direction
	 *            The direction the platform moves in.
	 * @param platformType
	 *            The type of platform to generate.
	 */
	private void generatePlatform(Lane lane, int length, int direction,
			int platformType) {
		if (time++ > REGENERATION) {
			time = 0;
			int startPosition = (direction == MovingObject.DIRECTION_LEFT) ? GAME_WIDTH
					: 0 - (length * 50);
			Platform p = new Platform(startPosition, lane.getYPos(), length,
					direction);
			p.setPlatformType(platformType);
			p.createImage(this);
			platforms.add(p);
		}
	}

	/**
	 * Draws the image for the player in the main game render loop.
	 * 
	 * @param g
	 *            The Graphics object used in the Canvas.
	 */
	private void processPlayer(Graphics g) {
		player.tick(input);
		g.drawImage(player.getImage(), player.getXPos(), player.getYPos(), this);
	}

	/**
	 * Draws the vehicles in the main game render loop.
	 * 
	 * @param g
	 *            The Graphics object used by the Canvas.
	 */
	private void processVehicles(Graphics g) {
		for (Vehicle v : vehicles) {
			if (!v.isRemoved()) {
				v.tick(input);
				g.drawImage(v.getImage(), v.getXPos(), v.getYPos(), this);
			}
		}
	}

	/**
	 * Draws the platforms in the main game render loop.
	 * 
	 * @param g
	 *            The Graphics object used by the Canvas.
	 */
	private void processPlatforms(Graphics g) {
		for (Platform p : platforms) {
			if (!p.isRemoved()) {
				p.tick(input);
				g.drawImage(p.getImage(), p.getXPos(), p.getYPos(), this);
			}
		}
	}

	/**
	 * The main game loop.
	 */
	private void render() {

		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(2);
			requestFocus();
			return;
		}

		Graphics g = bs.getDrawGraphics();
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.RED);
		
		
		/*
		 * Show the sprites here. Make sure the method to add vehicles and
		 * platforms are displayed here otherwise it won't display. They must
		 * also be placed in the order that they are displayed in terms of
		 * layers. For example, the player will be before the vehicles, so that
		 * it gives the appearance that vehicles run over Froggr.
		 */
		addVehiclesToLanes();
		addPlatformsToLanes();
		processLanes(g);
		processPlatforms(g);
		processPlayer(g);
		processVehicles(g);

		g.dispose();
		bs.show();

		// game is too fast without this delay
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	private void addPlatformsToLanes() {
		generatePlatform(lanes.get(LANE_WATER_FIRST), 3,
				MovingObject.DIRECTION_LEFT, Platform.LILY);
		generatePlatform(lanes.get(LANE_WATER_SECOND), 3,
				MovingObject.DIRECTION_RIGHT, Platform.TURTLE);
		generatePlatform(lanes.get(LANE_WATER_THIRD), 2,
				MovingObject.DIRECTION_LEFT, Platform.TURTLE);
		generatePlatform(lanes.get(LANE_WATER_FOURTH), 3,
				MovingObject.DIRECTION_RIGHT, Platform.LOG);
		generatePlatform(lanes.get(LANE_WATER_FIFTH), 3,
				MovingObject.DIRECTION_LEFT, Platform.LOG);
	}

	/**
	 * Determines what vehicles, length and direction to add to the lanes.
	 * Trucks are always length 2. Other vehicles can be of length 1, 2 or 3.
	 */
	private void addVehiclesToLanes() {
		generateVehicle(lanes.get(LANE_ROAD_FIRST), 1,
				MovingObject.DIRECTION_RIGHT, Vehicle.CAR);
		generateVehicle(lanes.get(LANE_ROAD_SECOND), 2,
				MovingObject.DIRECTION_LEFT, Vehicle.CAR);
		generateVehicle(lanes.get(LANE_ROAD_THIRD), 3,
				MovingObject.DIRECTION_RIGHT, Vehicle.CAR);
		generateVehicle(lanes.get(LANE_ROAD_FOURTH), 2,
				MovingObject.DIRECTION_LEFT, Vehicle.TRUCK);
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

package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;


import sprites.Lane;
import sprites.MovingObject;
import sprites.Platform;
import sprites.Player;
import sprites.Vehicle;

/**
 * 
 * @author Raj Ramsaroop
 * 		   Greg Westerfield, Jr.
 * 
 */
public class FroggrGame extends Canvas implements Runnable, KeyListener {

	private Player player;
	private int startingLives = 3;
	private Input input = new Input();

	/**
	 * Lists of lanes, vehicles, and platforms.
	 */
	private ArrayList<Lane> lanes = new ArrayList<Lane>();
	private ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
	private ArrayList<Platform> platforms = new ArrayList<Platform>();

	/**
	 * Regeneration rates for each water lane. (These can be modified depending on level)
	 */
	private final int FIRST_WATER_LANE_REGENERATION = 225;
	private final int SECOND_WATER_LANE_REGENERATION = 225;
	private final int THIRD_WATER_LANE_REGENERATION = 225;
	private final int FOURTH_WATER_LANE_REGENERATION = 225;
	private final int FIFTH_WATER_LANE_REGENERATION = 225;
	
	/**
	 * Regeneration rates for each road lane. (These can be modified depending on level)
	 */
	private final int FIRST_ROAD_LANE_REGENERATION = 175;
	private final int SECOND_ROAD_LANE_REGENERATION = 225;
	private final int THIRD_ROAD_LANE_REGENERATION = 350;
	private final int FOURTH_ROAD_LANE_REGENERATION = 250;
	
	/**
	 * Vehicle times
	 */
	private  int vehicleTimeLaneOne = 0;
	private  int vehicleTimeLaneTwo = 0;
	private  int vehicleTimeLaneThree = 0;
	private  int vehicleTimeLaneFour = 0;
	
	/**
	 * Platform times
	 */
	private  int platformTimeLaneOne = 0;
	private  int platformTimeLaneTwo = 0;
	private  int platformTimeLaneThree = 0;
	private  int platformTimeLaneFour = 0;
	private  int platformTimeLaneFive = 0;

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
		try {
			BufferedImage image = ImageIO.read(FroggrGame.class
					.getClassLoader().getResource("res/sprites/lane/win.png"));
			lanes.get(LANE_WIN).setImage(image);
		} catch (IOException e) {
			System.out.println("ERROR: Could not load win lane sprite image.");
		}

		// Use alternating images randomly for water lanes
		for (int i = LANE_WATER_FIFTH; i <= LANE_WATER_FIRST; i++) {
			try {
				BufferedImage image = ImageIO.read(FroggrGame.class
						.getClassLoader().getResource(
								"res/sprites/lane/water.gif"));
				lanes.get(i).setImage(image);
			} catch (IOException e) {
				System.out
						.println("ERROR: Could not load water lane sprite images.");
			}
		}

		// Safe area before water
		try {
			BufferedImage image = ImageIO
					.read(FroggrGame.class.getClassLoader().getResource(
							"res/sprites/lane/grass.png"));
			lanes.get(LANE_GRASS_FIRST).setImage(image);
			lanes.get(LANE_GRASS_SECOND).setImage(image);
		} catch (IOException e) {
			System.out
					.println("ERROR: Could not load grass lane sprite image.");
		}

		// Road lanes
		try {
			BufferedImage image = ImageIO.read(FroggrGame.class
					.getClassLoader().getResource(
							"res/sprites/lane/road-top.png"));
			lanes.get(LANE_ROAD_FOURTH).setImage(image);
		} catch (IOException e) {
			System.out
					.println("ERROR: Could not load fourth road sprite image.");
		}

		try {
			BufferedImage image = ImageIO.read(FroggrGame.class
					.getClassLoader().getResource(
							"res/sprites/lane/road-middle.png"));
			lanes.get(LANE_ROAD_THIRD).setImage(image);
			lanes.get(LANE_ROAD_SECOND).setImage(image);
		} catch (IOException e) {
			System.out
					.println("ERROR: Could not load middle road sprite image.");
		}

		try {
			BufferedImage image = ImageIO.read(FroggrGame.class
					.getClassLoader().getResource(
							"res/sprites/lane/road-bottom.png"));
			lanes.get(LANE_ROAD_FIRST).setImage(image);
		} catch (IOException e) {
			System.out
					.println("ERROR: Could not load first road sprite image.");
		}

		// Start lane
		try {
			BufferedImage image = ImageIO
					.read(FroggrGame.class.getClassLoader().getResource(
							"res/sprites/lane/grass.png"));
			lanes.get(LANE_START).setImage(image);
		} catch (IOException e) {
			System.out
					.println("ERROR: Could not load start lane sprite image.");
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
	 *  
	 * @param regenerationRate
	 *            The rate at which the vehicle will be generated.          
	 */
	private void generateVehicle(Lane lane, int length, int direction, int vehicleType, int regenerationRate) {
		int vehicleTime = getVehicleTime(regenerationRate);
		if (vehicleTime > regenerationRate) {
			resetVehicleTime(regenerationRate);
			int startPosition = (direction == MovingObject.DIRECTION_LEFT) ? GAME_WIDTH
					: 0 - (length * 50);
			Vehicle v = new Vehicle(startPosition, lane.getYPos(), length,
					direction);
			v.setVehicleType(vehicleType);
			vehicles.add(v);
		}
	}
	
	/**
	 * Resets the vehicle times according to their corresponding lane regeneration rate.
	 * @param regenerationRate
	 * 				The rate at which the vehicle will be generated.
	 */
	private void resetVehicleTime(int regenerationRate) {
		if (regenerationRate == FIRST_ROAD_LANE_REGENERATION){
			vehicleTimeLaneOne = 0;
		}
		if (regenerationRate == SECOND_ROAD_LANE_REGENERATION){
			vehicleTimeLaneTwo = 0;
		}
		if (regenerationRate == THIRD_ROAD_LANE_REGENERATION){
			vehicleTimeLaneThree = 0;
		}
		if (regenerationRate == FOURTH_ROAD_LANE_REGENERATION){
			vehicleTimeLaneFour = 0;
		}
	}

	/**
	 * Gets the vehicle time according to its corresponding lane regeneration rate.
	 * @param regenerationRate
	 * 				The rate at which the vehicle will be generated.
	 * @return vehicleTimeLaneOne || vehicleTimeLaneTwo || vehicleTimeLaneThree || vehicleTimeLaneFour 	
	 * 				The vehicle time of the called lane.
	 */
	private int getVehicleTime(int regenerationRate) {
		int vehicleTime = 0;
		if (regenerationRate == FIRST_ROAD_LANE_REGENERATION){
			vehicleTime = vehicleTimeLaneOne++;
		}
		if (regenerationRate == SECOND_ROAD_LANE_REGENERATION){
			vehicleTime = vehicleTimeLaneTwo++;
		}
		if (regenerationRate == THIRD_ROAD_LANE_REGENERATION){
			vehicleTime = vehicleTimeLaneThree++;
		}
		if (regenerationRate == FOURTH_ROAD_LANE_REGENERATION){
			vehicleTime = vehicleTimeLaneFour++;
		}
		return vehicleTime;
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
	 * @param regenerationRate
	 *            The rate at which the vehicle will be generated.  
	 */
	private void generatePlatform(Lane lane, int length, int direction, int platformType, int regenerationRate) {
		int platformTime = getPlatformTime(regenerationRate);
		if (platformTime > regenerationRate) {
			resetPlatformTime(regenerationRate);
			int startPosition = (direction == MovingObject.DIRECTION_LEFT) ? GAME_WIDTH
					: 0 - (length * 50);
			Platform p = new Platform(startPosition, lane.getYPos(), length,
					direction);
			p.setPlatformType(platformType);
			platforms.add(p);
		}
	}

	/**
	 * Resets the platform times according to their corresponding lane regeneration rate.
	 * @param regenerationRate
	 * 				The rate at which the platform will be generated.
	 */
	private void resetPlatformTime(int regenerationRate) {
		if (regenerationRate == FIRST_WATER_LANE_REGENERATION){
			platformTimeLaneOne = 0;
		}
		if (regenerationRate == SECOND_WATER_LANE_REGENERATION){
			platformTimeLaneTwo = 0;
		}
		if (regenerationRate == THIRD_WATER_LANE_REGENERATION){
			platformTimeLaneThree = 0;
		}
		if (regenerationRate == FOURTH_WATER_LANE_REGENERATION){
			platformTimeLaneFour = 0;
		}
		if (regenerationRate == FIFTH_WATER_LANE_REGENERATION){
			platformTimeLaneFive = 0;
		}
	}

	/**
	 * Gets the platform time according to its corresponding lane regeneration rate.
	 * @param regenerationRate
	 * 				The rate at which the platform will be generated.
	 * @return platformTimeLaneOne || platformTimeLaneTwo || platformTimeLaneThree || platformTimeLaneFour 	
	 * 				The platform time of the called lane.
	 */
	private int getPlatformTime(int regenerationRate) {
		int platformTime = 0;
		if (regenerationRate == FIRST_WATER_LANE_REGENERATION){
			platformTime = platformTimeLaneOne++;
		}
		if (regenerationRate == SECOND_WATER_LANE_REGENERATION){
			platformTime = platformTimeLaneTwo++;
		}
		if (regenerationRate == THIRD_WATER_LANE_REGENERATION){
			platformTime = platformTimeLaneThree++;
		}
		if (regenerationRate == FOURTH_WATER_LANE_REGENERATION){
			platformTime = platformTimeLaneFour++;
		}
		if (regenerationRate == FIFTH_WATER_LANE_REGENERATION){
			platformTime = platformTimeLaneFive++;
		}
		return platformTime;
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
	 * Removes all sprite's from their corresponding lists.
	 */
	private void removeSpritesFromLists() {
		Iterator<Vehicle> vehicleIterator = vehicles.iterator();
		while (vehicleIterator.hasNext()) {
			Vehicle v = vehicleIterator.next();
			if (v.isRemoved()) {
				vehicleIterator.remove();
			}
		}

		Iterator<Platform> platformIterator = platforms.iterator();
		while (platformIterator.hasNext()) {
			Platform p = platformIterator.next();
			if (p.isRemoved()) {
				platformIterator.remove();
			}
		}
	}

	/**
	 * Determines what platforms, length, direction, and regeneration rate to add to which lane.
	 */
	private void addPlatformsToLanes() {
		generatePlatform(lanes.get(LANE_WATER_FIFTH), 3, MovingObject.DIRECTION_LEFT, Platform.LOG, FIFTH_WATER_LANE_REGENERATION);
		generatePlatform(lanes.get(LANE_WATER_FOURTH), 2, MovingObject.DIRECTION_RIGHT, Platform.TURTLE, FOURTH_WATER_LANE_REGENERATION);
		generatePlatform(lanes.get(LANE_WATER_THIRD), 3, MovingObject.DIRECTION_LEFT, Platform.LOG, THIRD_WATER_LANE_REGENERATION);
		generatePlatform(lanes.get(LANE_WATER_SECOND), 3, MovingObject.DIRECTION_RIGHT, Platform.TURTLE, SECOND_WATER_LANE_REGENERATION);
		generatePlatform(lanes.get(LANE_WATER_FIRST), 3, MovingObject.DIRECTION_LEFT, Platform.LILY, FIRST_WATER_LANE_REGENERATION);
	}

	/**
	 * Determines what vehicles, length, direction and regeneration rate to add to the lanes.
	 * Trucks are always length 2. Other vehicles can be of length 1, 2 or 3.
	 */
	private void addVehiclesToLanes() {
		generateVehicle(lanes.get(LANE_ROAD_FIRST), 1,MovingObject.DIRECTION_RIGHT, Vehicle.CAR, FIRST_ROAD_LANE_REGENERATION);
		generateVehicle(lanes.get(LANE_ROAD_SECOND), 2,MovingObject.DIRECTION_LEFT, Vehicle.CAR, SECOND_ROAD_LANE_REGENERATION);
		generateVehicle(lanes.get(LANE_ROAD_THIRD), 3,MovingObject.DIRECTION_RIGHT, Vehicle.CAR, THIRD_ROAD_LANE_REGENERATION);
		generateVehicle(lanes.get(LANE_ROAD_FOURTH), 2,MovingObject.DIRECTION_LEFT, Vehicle.TRUCK, FOURTH_ROAD_LANE_REGENERATION);
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
		 * Show the sprite's here. Make sure the method to add vehicles and
		 * platforms are displayed here otherwise it won't display. They must
		 * also be placed in the order that they are displayed in terms of
		 * layers. For example, the player will be before the vehicles, so that
		 * it gives the appearance that vehicles run over Froggr.
		 */
		addPlatformsToLanes();
		addVehiclesToLanes();
		processLanes(g);
		processPlatforms(g);
		processPlayer(g);
		processVehicles(g);

		g.dispose();
		bs.show();

		removeSpritesFromLists();

		// game is too fast without this delay
		try {
			Thread.sleep(15);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean imageUpdate(Image img, int flags, int x, int y, int w, int h) {
		System.out.println("Flags: " + flags + " x: " + x + " y: " + y + " w: "
				+ w + " h: " + h);
		repaint();
		return true;
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

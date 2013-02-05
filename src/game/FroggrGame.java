package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Collection;

import sprites.Lane;
import sprites.MovingObject;
import sprites.Player;
import sprites.Vehicle;

/**
 * 
 * @author Raj
 * 
 */
public class FroggrGame extends Canvas implements Runnable, KeyListener {

	
	private Player player;
	private Input input = new Input();
	private static final int NUMBER_OF_ROAD_LANES = 4;
	private static final int NUMBER_OF_WATER_LANES = 6;
	private ArrayList<Lane> roadLanes = new ArrayList<Lane>();
	private ArrayList<Lane> waterLanes = new ArrayList<Lane>();
	private ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
	private final int REGENERATION = 5;
	private int time = 0;

	public static final int GAME_WIDTH = 500;
	public static final int GAME_HEIGHT = 700;
	public static final int LANE_HEIGHT = 50;
	public static final Color FOREGROUND_COLOR = Color.BLACK;

	public FroggrGame() {
		addKeyListener(this);
		setForeground(FOREGROUND_COLOR);
		setSize(GAME_WIDTH, GAME_HEIGHT);
	}

	private void spawnPlayer() {
		this.player = new Player(250, GAME_HEIGHT - LANE_HEIGHT, 3);
	}

	private void generateVehicle(Lane lane) {
		if (time++ > REGENERATION) {
			time = 0;
			vehicles.add(new Vehicle(0, lane.getYPos(), 1,
					MovingObject.DIRECTION_RIGHT));
		}
	}

	private void generateLog() {

	}

	private void generateLily() {

	}

	private void generateTurtle() {

	}

	private void processPlayer(Graphics g) {
		player.tick(input);
		if (input.buttons[Input.UP] && !input.oldButtons[Input.UP]) {

		} else if (input.buttons[Input.DOWN] && !input.oldButtons[Input.DOWN]) {

		} else if (input.buttons[Input.LEFT] && !input.oldButtons[Input.LEFT]) {

		} else if (input.buttons[Input.RIGHT] && !input.oldButtons[Input.LEFT]) {

		}
		g.drawRect(player.getXPos(), player.getYPos(), 50, 50);
	}

	// process vehicle
	private void processVehicles(Graphics g) {
		for (int i = 0; i < vehicles.size(); i++) {
			Vehicle v = vehicles.get(i);
			if (!v.isRemoved()) {
				v.tick(input);
				g.fillRect(v.getXPos(), v.getYPos(), 50, 50);
			}

		}
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

		for (Lane l : roadLanes) {
			generateVehicle(l);
			processVehicles(g);
		}

		
		processPlayer(g);

		g.dispose();
		bs.show();

		// game is too fast without this delay
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void createLanes() {
		int roadY = 450;
		int waterY = 50;
		// set road lanes
		for (int i = 0; i < NUMBER_OF_ROAD_LANES ; i++) {
			roadLanes.add(new Lane(0, roadY));
			roadY = roadY + LANE_HEIGHT;
			
		}

		// set water lanes
		for (int i = 0; i < NUMBER_OF_WATER_LANES; i++) {
			waterLanes.add(new Lane(0, waterY));
			waterY = waterY + LANE_HEIGHT;
		}
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
		spawnPlayer();
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

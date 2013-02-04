package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import sprites.Player;

/**
 * 
 * @author Raj
 * 
 */
public class FroggrGame extends Canvas implements Runnable, KeyListener {

	private Player player;
	private Input input = new Input();

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
		this.player = new Player(250, GAME_HEIGHT - LANE_HEIGHT); 
	}
	
	private void generateVehicle() {
		
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

		processPlayer(g);

		g.dispose();
		bs.show();

		// game is too fast without this delay
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
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
		spawnPlayer();
		new Thread(this).start();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		input.set(arg0.getKeyCode(), false);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		input.set(e.getKeyCode(), true);
	}

}

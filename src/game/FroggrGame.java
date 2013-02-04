package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import sprites.Lane;
import sprites.Player;

/**
 * 
 * @author Raj
 * 
 */
public class FroggrGame extends Canvas implements Runnable, KeyListener {

	private Player player;
	private Input input = new Input();
	private ArrayList<Lane> roadLanes = new ArrayList<Lane>();
	private ArrayList<Lane> waterLanes = new ArrayList<Lane>();

	public static final int WIDTH = 500;
	public static final int HEIGHT = 700;
	public static final Color FOREGROUND_COLOR = Color.BLACK;

	public FroggrGame() {
		this.player = new Player(0, HEIGHT - 50);
		addKeyListener(this);
		setForeground(FOREGROUND_COLOR);
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

	private void setLanes() {
		// set road lanes
		for (int i = 0; i<3; i++) {
			int y = 650;
			roadLanes.add(new Lane(0, y));
			y = y - 50;
		}

		// set water lanes
		for (int i = 0; i<5; i++) {
			int y = 50;
			roadLanes.add(new Lane(0, y));
			y = y + 50;
		}
	}

	@Override
	public void run() {
		while (true) {
			render();
		}
	}

	public void start() {
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

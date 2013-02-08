package game;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class FroggrGameApplication extends JFrame implements ActionListener {

	// Froggr game driver class
	private FroggrGame game;

	// Application Panels
	public static final JPanel pnlTitleScreen = new JPanel();
	public static final JPanel pnlGameScreen = new JPanel();
	public static final JPanel pnlDirectionScreen = new JPanel();

	// Panel Background Color
	public static final Color BACKGROUND_COLOR = Color.BLACK;

	// Title Screen Buttons
	private JButton btnStartGame, btnDirections;

	// Title Screen Froggr Graphic Label
	private JLabel lblTitleScreenGraphic;

	// Direction Screen Buttons
	private JButton btnBackToTitleScreen;

	// Program Layout
	private CardLayout programLayout;

	/**
	 * The Constructor of the FroggrGameApplication Class
	 */
	public FroggrGameApplication() {
		createTitleScreen();
		createDirectionScreen();
		createGameScreen();
		setTitle("Froggr");
		setSize(FroggrGame.GAME_WIDTH + 9, FroggrGame.GAME_HEIGHT + 30);
		setResizable(false);
		programLayout = new CardLayout();
		setLayout(programLayout);
		add(pnlTitleScreen, "Title Screen");
		add(pnlGameScreen, "Game Screen");
		add(pnlDirectionScreen, "Direction's Screen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	/**
	 * Creates the screen in which the game driver class FroggrGame will run in.
	 */

	private void createGameScreen() {
		// Set Panel information
		pnlGameScreen.setLayout(null);
		pnlGameScreen.setPreferredSize(new Dimension(FroggrGame.GAME_WIDTH + 9, FroggrGame.GAME_HEIGHT + 30));
		pnlGameScreen.setBackground(BACKGROUND_COLOR);
		
		// Initialize the Froggr Game Driver Class
		game = new FroggrGame();
		
		// Add Game component to the panel
		pnlGameScreen.add(game);
	}

	/**
	 * Creates game directions screen.
	 */
	private void createDirectionScreen() {
		// Set panel infromation
		pnlDirectionScreen.setLayout(null);
		pnlDirectionScreen.setPreferredSize(new Dimension(FroggrGame.GAME_WIDTH + 9, FroggrGame.GAME_HEIGHT + 30));
		pnlDirectionScreen.setBackground(BACKGROUND_COLOR);

		// Set the back to the title screen button
		btnBackToTitleScreen = new JButton(new ImageIcon("res/sprites/player.png")); // Temporary images
		btnBackToTitleScreen.setRolloverEnabled(true);
		btnBackToTitleScreen.setRolloverIcon(new ImageIcon("res/sprites/car.png"));
		btnBackToTitleScreen.addActionListener(this);
		btnBackToTitleScreen.setBorder(BorderFactory.createEmptyBorder());
		btnBackToTitleScreen.setContentAreaFilled(false);
		btnBackToTitleScreen.setBounds(250, 400, 50, 50);

		// Adds components to the panel
		pnlDirectionScreen.add(btnBackToTitleScreen);
	}

	/**
	 * Creates the title screen.
	 */
	private void createTitleScreen() {
		// Set panel information
		pnlTitleScreen.setLayout(null);
		pnlTitleScreen.setPreferredSize(new Dimension(FroggrGame.GAME_WIDTH + 9, FroggrGame.GAME_HEIGHT + 30));
		pnlTitleScreen.setBackground(BACKGROUND_COLOR);

		// Set Title Screen Graphic
		lblTitleScreenGraphic = new JLabel(new ImageIcon("res/images/title-screen.png"));
		lblTitleScreenGraphic.setBorder(BorderFactory.createEmptyBorder());
		lblTitleScreenGraphic.setBounds(0, 75, 500, 307);

		// Set Start Game Button
		btnStartGame = new JButton(new ImageIcon("res/sprites/player.png")); // Temporary images
		btnStartGame.setRolloverEnabled(true);
		btnStartGame.setRolloverIcon(new ImageIcon("res/sprites/car.png"));
		btnStartGame.addActionListener(this);
		btnStartGame.setBorder(BorderFactory.createEmptyBorder());
		btnStartGame.setContentAreaFilled(false);
		btnStartGame.setBounds(250, 400, 50, 50);

		// Set Directions Button
		btnDirections = new JButton(new ImageIcon("res/sprites/player.png"));
		btnDirections.setRolloverEnabled(true);
		btnDirections.setRolloverIcon(new ImageIcon("res/sprites/car.png"));
		btnDirections.addActionListener(this);
		btnDirections.setBorder(BorderFactory.createEmptyBorder());
		btnDirections.setContentAreaFilled(false);
		btnDirections.setBounds(350, 400, 50, 50);

		// Add components to the panel
		pnlTitleScreen.add(lblTitleScreenGraphic);
		pnlTitleScreen.add(btnStartGame);
		pnlTitleScreen.add(btnDirections);

	}

	/*
	 * 
	 * This sections contains the methods for the ActionListener.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnStartGame) {
			startGame();
			showGameScreen();
		}
		if (e.getSource() == btnDirections) {
			showDirectionsScreen();
		}
		if (e.getSource() == btnBackToTitleScreen) {
			showTitleScreen();
		}
	}

	private void showTitleScreen() {
		programLayout.show(this.getContentPane(), "Title Screen");
	}

	private void showDirectionsScreen() {
		programLayout.show(this.getContentPane(), "Direction's Screen");
	}

	private void showGameScreen() {
		programLayout.show(this.getContentPane(), "Game Screen");
	}

	private void startGame() {
		game.start();
	}

	// MAIN
	public static void main(String[] args) {
		FroggrGameApplication froggr = new FroggrGameApplication();
	}

}

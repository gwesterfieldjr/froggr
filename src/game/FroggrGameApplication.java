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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class FroggrGameApplication extends JFrame implements ActionListener, MouseListener {

	// Froggr game driver class
	private FroggrGame game;

	// Application Panels
	private JPanel pnlTitleScreen, pnlGameScreen, pnlDirectionScreen;

	// Start Screen Buttons
	private JButton btnStartGame, btnDirections;

	// Start Screen Froggr Graphic Label
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
		pnlGameScreen = new JPanel();
		pnlGameScreen.setLayout(null);
		pnlGameScreen.setPreferredSize(new Dimension(FroggrGame.GAME_WIDTH + 9,
				FroggrGame.GAME_HEIGHT + 30));
		pnlGameScreen.setBackground(Color.BLACK);
		game = new FroggrGame();
		pnlGameScreen.add(game);
	}

	/**
	 * Creates game directions screen.
	 */
	private void createDirectionScreen() {
		pnlDirectionScreen = new JPanel();
		pnlDirectionScreen.setLayout(null);
		pnlDirectionScreen.setPreferredSize(new Dimension(
				FroggrGame.GAME_WIDTH + 9, FroggrGame.GAME_HEIGHT + 30));
		pnlDirectionScreen.setBackground(Color.BLACK);
		
		try {
		
			BufferedImage backToTitleScreen = ImageIO.read(new File(
					"res/sprites/player.png"));
			btnBackToTitleScreen = new JButton(new ImageIcon(backToTitleScreen));

		} catch (IOException e) {
			System.err.println("Errrr!");
		}
		
		btnBackToTitleScreen.addActionListener(this);
		btnBackToTitleScreen.setBorder(BorderFactory.createEmptyBorder());
		btnBackToTitleScreen.setContentAreaFilled(false);
		btnBackToTitleScreen.setBounds(250, 400, 50, 50);
		
		pnlDirectionScreen.add(btnBackToTitleScreen);

	}
	/**
	 * Creates the title screen. 
	 */
	private void createTitleScreen() {
		pnlTitleScreen = new JPanel();
		pnlTitleScreen.setLayout(null);
		pnlTitleScreen.setPreferredSize(new Dimension(
				FroggrGame.GAME_WIDTH + 9, FroggrGame.GAME_HEIGHT + 30));
		pnlTitleScreen.setBackground(Color.BLACK);

		try {
			BufferedImage titleScreenGraphic = ImageIO.read(new File(
					"res/images/title-screen.png"));
			lblTitleScreenGraphic = new JLabel(
					new ImageIcon(titleScreenGraphic));

			BufferedImage startGameButton = ImageIO.read(new File(
					"res/sprites/player.png"));
			btnStartGame = new JButton(new ImageIcon(startGameButton));

			BufferedImage directionsButton = ImageIO.read(new File(
					"res/sprites/player.png"));
			btnDirections = new JButton(new ImageIcon(directionsButton));

		} catch (IOException e) {
			System.err.println("Errrr!");
		}

		lblTitleScreenGraphic.setBorder(BorderFactory.createEmptyBorder());
		lblTitleScreenGraphic.setBounds(0, 75, 500, 307);

		btnStartGame.addActionListener(this);
		btnStartGame.setBorder(BorderFactory.createEmptyBorder());
		btnStartGame.setContentAreaFilled(false);
		btnStartGame.setBounds(250, 400, 50, 50);

		btnDirections.addActionListener(this);
		btnDirections.setBorder(BorderFactory.createEmptyBorder());
		btnDirections.setContentAreaFilled(false);
		btnDirections.setBounds(350, 400, 50, 50);

		pnlTitleScreen.add(lblTitleScreenGraphic);
		pnlTitleScreen.add(btnStartGame);
		pnlTitleScreen.add(btnDirections);

	}

	/*
	 * 
	 * 	This sections contains the methods for the ActionListener.
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnStartGame){
			startGame();
			showGameScreen();
		}
		if (e.getSource() == btnDirections){
			showDirectionsScreen();
		}
		if (e.getSource() == btnBackToTitleScreen){
			showTitleScreen();
		}
	}

	private void showTitleScreen() {
		programLayout.show(this.getContentPane(), "Title Screen" );
	}

	private void showDirectionsScreen() {
		programLayout.show(this.getContentPane(), "Direction's Screen" );
	}

	private void showGameScreen() {
		programLayout.show(this.getContentPane(), "Game Screen" );
	}

	private void startGame() {
		game.start();
	}
	
	/*
	 * 
	 *  This section contains the methods for the MouseListener.
	 *  
	 */
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO change pictures of buttons
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO change picture back to original
	}
	
	// Ignore These implemented methods from the MouseListener
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseClicked(MouseEvent e) {}

	// MAIN 
	public static void main(String[] args) {
		FroggrGameApplication froggr = new FroggrGameApplication();
	}








}

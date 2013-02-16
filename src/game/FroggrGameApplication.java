package game;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class FroggrGameApplication extends JFrame implements ActionListener {

	// Froggr game driver class
	private FroggrGame game;

	// Application Panels
	public static final JPanel pnlTitleScreen = new JPanel();
	public static final JPanel pnlGameScreen = new JPanel();
	public static final JPanel pnlInstructionsScreen = new JPanel();

	// Panel Background Color
	public static final Color BACKGROUND_COLOR = Color.BLACK;

	// Title Screen Buttons
	private JButton btnStartGame, btnInstructions, btnCredits;

	// Label graphics
	private JLabel lblTitleScreenGraphic;
	private JLabel lblArrowKeysGraphic;
	
	// Instructions Screen Header
	private JLabel lblInstructionsHeader;
	
	// Instructions Screen TextArea
	private JTextArea txtAreaInstructions;

	// Instructions Screen Buttons
	private JButton btnBackToTitleScreen;

	// Program Layout
	private CardLayout programLayout;

	/**
	 * The Constructor of the FroggrGameApplication Class
	 */
	public FroggrGameApplication() {
		createTitleScreen();
		createInstructionScreen();
		createGameScreen();
		setTitle("Froggr");
		setSize(FroggrGame.GAME_WIDTH + 6, FroggrGame.GAME_HEIGHT + 30);
		setResizable(false);
		programLayout = new CardLayout();
		setLayout(programLayout);
		add(pnlTitleScreen, "Title Screen");
		add(pnlGameScreen, "Game Screen");
		add(pnlInstructionsScreen, "Instruction's Screen");
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
	private void createInstructionScreen() {
		// Set panel information
		pnlInstructionsScreen.setLayout(null);
		pnlInstructionsScreen.setPreferredSize(new Dimension(FroggrGame.GAME_WIDTH + 9, FroggrGame.GAME_HEIGHT + 30));
		pnlInstructionsScreen.setBackground(BACKGROUND_COLOR);

		// Set Instructions Header label
		lblInstructionsHeader = new JLabel("INSTRUCTIONS", JLabel.CENTER);
		lblInstructionsHeader.setFont(new Font("Arial", Font.BOLD, 50));
		lblInstructionsHeader.setForeground(Color.RED);
		lblInstructionsHeader.setBounds(0, 25, 500, 50);
		
		// Set Instructions label
		txtAreaInstructions = new JTextArea("     The player starts with three frogs (lives). The player’s goal is to guide the frog from\n" +
				" the bottom of the screen into one of the four winning zones where flies wait to be eaten\n" +
				" as lunch.\n\n" + 
				"     The lower half of the screen contains a road with motor vehicles, which include\n" +
				" different sized cars and trucks speeding along it horizontally. The middle of the screen,\n" +
				" after the road, contains a median where the player must prepare to navigate the river.\n" +
				" The upper half of the screen consists of a river with logs, turtles, and lilies all moving\n" +
				" horizontally across the screen. The very top of the screen contains four frog homes\n" +
				" which are the destinations for each frog. To beat the game the player must fill each of\n" +
				" the four frog homes with a frog. The player has three lives to do this.\n\n" +
				"     There are many different ways to lose a life in this game, including:\n\n " +
				"        1) Being hit by a road vehicle.\n" +
				"         2) Jumping into the river's water.\n" +
				"         3) Staying on top of a diving turtle until it has completely submerged.\n" +
				"         4) Jumping into a home already occupied by a frog.\n" +
				"         5) Jumping into the side of a frog home.\n\n" +
				"     The only player controls are the arrow keys on the keyboard. The player must use\n" +
				" them to navigate the frog through oncoming traffic and moving logs, turtles, and lilies.");
		txtAreaInstructions.setFont(new Font("Arial", Font.BOLD, 12));
		txtAreaInstructions.setBackground(Color.BLACK);
		txtAreaInstructions.setForeground(Color.WHITE);
		txtAreaInstructions.setBounds(0, 85, 500, 335);
		
		// Set Arrow Keys Graphic
		lblArrowKeysGraphic = new JLabel(new ImageIcon("res/images/arrowkeys.png"));
		lblArrowKeysGraphic.setBorder(BorderFactory.createEmptyBorder());
		lblArrowKeysGraphic.setBounds(75, 425, 356, 287);
		
		// Set the back to the title screen button
		btnBackToTitleScreen = new JButton(new ImageIcon("res/images/menu/back.png")); // Temporary images
		btnBackToTitleScreen.setRolloverEnabled(true);
		btnBackToTitleScreen.setRolloverIcon(new ImageIcon("res/images/menu/back-hover.png"));
		btnBackToTitleScreen.addActionListener(this);
		btnBackToTitleScreen.setBorder(BorderFactory.createEmptyBorder());
		btnBackToTitleScreen.setContentAreaFilled(false);
		btnBackToTitleScreen.setBounds(300, 450, 200, 50);

		// Adds components to the panel
		pnlInstructionsScreen.add(lblInstructionsHeader);
		pnlInstructionsScreen.add(txtAreaInstructions);
		pnlInstructionsScreen.add(lblArrowKeysGraphic);
		pnlInstructionsScreen.add(btnBackToTitleScreen);
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
		btnStartGame = new JButton(new ImageIcon("res/images/menu/start.png")); // Temporary images
		btnStartGame.setRolloverEnabled(true);
		btnStartGame.setRolloverIcon(new ImageIcon("res/images/menu/start-hover.png"));
		btnStartGame.addActionListener(this);
		btnStartGame.setBorder(BorderFactory.createEmptyBorder());
		btnStartGame.setContentAreaFilled(false);
		btnStartGame.setBounds(150, 400, 200, 50);

		// Set Directions Button
		btnInstructions = new JButton(new ImageIcon("res/images/menu/instructions.png"));
		btnInstructions.setRolloverEnabled(true);
		btnInstructions.setRolloverIcon(new ImageIcon("res/images/menu/instructions-hover.png"));
		btnInstructions.addActionListener(this);
		btnInstructions.setBorder(BorderFactory.createEmptyBorder());
		btnInstructions.setContentAreaFilled(false);
		btnInstructions.setBounds(150, 450, 200, 50);
		
		// Set Credits Button
		btnCredits = new JButton(new ImageIcon("res/images/menu/credits.png"));
		btnCredits.setRolloverEnabled(true);
		btnCredits.setRolloverIcon(new ImageIcon("res/images/menu/credits-hover.png"));
		btnCredits.addActionListener(this);
		btnCredits.setBorder(BorderFactory.createEmptyBorder());
		btnCredits.setContentAreaFilled(false);
		btnCredits.setBounds(150,500, 200, 50);

		// Add components to the panel
		pnlTitleScreen.add(lblTitleScreenGraphic);
		pnlTitleScreen.add(btnStartGame);
		pnlTitleScreen.add(btnInstructions);
		pnlTitleScreen.add(btnCredits);

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
		if (e.getSource() == btnInstructions) {
			showInstructionsScreen();
		}
		if (e.getSource() == btnCredits) {
			launchCredits();
		}
		if (e.getSource() == btnBackToTitleScreen) {
			showTitleScreen();
		}
	}

	/**
	 * Launches a pop up that displays the games version and authors.
	 */
	private void launchCredits() {
		JOptionPane.showMessageDialog(null,"Version:  0.1\n\nAuthors:  Raj Ramsaroop (Programmer)\n" +
				"                  Greg Westerfield, Jr. (Programmer)\n                  James Fox (Graphics)",
				"Credits", 1);
		}

	/**
	 * Displays the title screen
	 */
	private void showTitleScreen() {
		programLayout.show(this.getContentPane(), "Title Screen");
	}

	/**
	 * Displays the Instructions Screen
	 */
	private void showInstructionsScreen() {
		programLayout.show(this.getContentPane(), "Instruction's Screen");
	}

	/**
	 * Displays the Game screen
	 */
	private void showGameScreen() {
		programLayout.show(this.getContentPane(), "Game Screen");
	}

	/**
	 * Starts the game on the Game screen panel.
	 */
	private void startGame() {
		game.start();
	}

	// MAIN
	public static void main(String[] args) {
		new FroggrGameApplication();
	}

}

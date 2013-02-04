package game;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class FroggrGameFrame {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Froggr");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FroggrGame game = new FroggrGame();
		frame.setSize(game.WIDTH + 9, game.HEIGHT + 30);
		frame.setLayout(new BorderLayout());
		frame.add(game, BorderLayout.CENTER);
		frame.setVisible(true);
		frame.setResizable(false);
		game.start();
	}
}

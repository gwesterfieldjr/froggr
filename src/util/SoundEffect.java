package util;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * This class plays the sound effects used in the game.
 * @author Raj Ramsaroop 
 * Greg Westerfield, Jr.
 * 
 */
public class SoundEffect {

	/**
	 * Sound for player movement.
	 */
	public static final URL MOVE = SoundEffect.class.getClassLoader().getResource("res/sounds/player-movement.wav");

	/**
	 * Sound for sprite collision.
	 */
	public static final URL COLLISION = SoundEffect.class.getClassLoader().getResource(
			"res/sounds/sprite-collision.wav");

	/**
	 * Sound for sprite splash.
	 */
	public static final URL SPLASH = SoundEffect.class.getClassLoader().getResource("res/sounds/splash.wav");

	/**
	 * Sound for victory.
	 */
	public static final URL VICTORY = SoundEffect.class.getClassLoader().getResource("res/sounds/victory.wav");

	/**
	 * Plays the sound effect.
	 * 
	 * @param fileName
	 *            The URL of the file to play.
	 */
	public static void play(URL fileName) {
		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(fileName);
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			if (clip.isRunning()) {
				clip.stop();
				clip.loop(0);
			}
			clip.start();
		} catch (Exception exc) {
			exc.printStackTrace(System.out);
		}
	}
}

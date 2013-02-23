package util;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * 
 * @author Raj Ramsaroop 
 *         Greg Westerfield, Jr.
 *
 */
public class SoundEffect {
	
	/**
	 * Sound for player movement
	 */
	public static final File MOVE = new File("res/sounds/player-movement.wav");
	
	/**
	 * Sound for sprite collision
	 */
	public static final File COLLISION = new File("res/sounds/sprite-collision.wav");
	
	/**
	 * Sound for sprite splash
	 */
	public static final File SPLASH = new File("res/sounds/splash.wav");
	
	/**
	 * Sound for victory
	 */
	public static final File VICTORY = new File("res/sounds/victory.wav");
	
	public static void play(File fileName)
	{
	    try
	    {
	        Clip clip = AudioSystem.getClip();
	        clip.open(AudioSystem.getAudioInputStream(fileName));
	        if (clip.isRunning()){
	        	clip.stop();
	        	clip.loop(0);
	        }
	        clip.start();
	    }
	    catch (Exception exc)
	    {
	        exc.printStackTrace(System.out);
	    }
	}
}

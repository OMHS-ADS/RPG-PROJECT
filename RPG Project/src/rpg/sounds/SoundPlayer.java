package rpg.sounds;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.LineEvent.Type;

public class SoundPlayer {
	
	public SoundPlayer(){
		//empty
	}
	
	public void playAttackSound(){
		try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("attackSound.wav").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace(); //remove later
	    }
	}
	
	public void playCriticalSound(){
		try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("criticalSound.wav").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace(); //remove later
	    }
	}
	
	public void playMagicSound(){
		try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("magicSound.wav").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace(); //remove later
	    }
	}
}

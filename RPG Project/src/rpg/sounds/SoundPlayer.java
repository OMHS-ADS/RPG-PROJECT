package rpg.sounds;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.LineEvent.Type;

public class SoundPlayer {
	/*attack magic
	enemy defeat
	damage enemy
	take damage
	*/
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
	
	public void playMissSound(){
		try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("missSound.wav").getAbsoluteFile());
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
	
	public void playBlockSound(){
		try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("blockSound.wav").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace(); //remove later
	    }
	}
	
	public void playPickupSound(){
		try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("pickupSound.wav").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace(); //remove later
	    }
	}
	
	public void playDeathSound(){
		try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("deathSound.wav").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace(); //remove later
	    }
	}
}

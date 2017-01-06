package rpg.sounds;
import java.io.BufferedInputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundPlayer {
	/*attack magic
	enemy defeat
	damage enemy
	take damage
	*/
	public SoundPlayer(){
		//empty
	}
	
	
	//The only thing incorrect is the file path
	
	public void playAttackSound(){
		try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(SoundPlayer.class.getResourceAsStream("/sounds/" + "attackSound.wav")));
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
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(SoundPlayer.class.getResourceAsStream("/sounds/" + "criticalSound.wav")));
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
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(SoundPlayer.class.getResourceAsStream("/sounds/" + "missSound.wav")));
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
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(SoundPlayer.class.getResourceAsStream("/sounds/" + "magicSound.wav")));
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
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(SoundPlayer.class.getResourceAsStream("/sounds/" + "blockSound.wav")));
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
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(SoundPlayer.class.getResourceAsStream("/sounds/" + "pickupSound.wav")));
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
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(SoundPlayer.class.getResourceAsStream("/sounds/" + "deathSound.wav")));
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace(); //remove later
	    }
	}
}

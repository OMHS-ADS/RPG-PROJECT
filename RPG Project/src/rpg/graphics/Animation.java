package rpg.graphics;

import java.awt.image.BufferedImage;

import rpg.util.ImageLoader;

/**
 * Animation class which contains images and can cycle through them.
 * @author Zach
 *
 */
public class Animation {

	//Default size is 16
	//Current frame is the current anitmation frame
	//Max frames is the maximum number of frames for this animation
	//animationImages is the array of images to be played
	private int currentFrame,maxFrames;
	private BufferedImage[] animationImages;
	
	//Created the array and filling it with empty images stops it from throwing a null pointer
	/**
	 * The default constructor for an animation. It is the default size of 16 frames, and contains 16 blank images.
	 */
	public Animation() {
		maxFrames = 16;
		currentFrame = 0;
		animationImages = new BufferedImage[maxFrames];
		for(int i = 0; i < maxFrames; i++) {
			animationImages[i] = new BufferedImage(16, 16, BufferedImage.TYPE_3BYTE_BGR);
		}
	}
	
	/**
	 * The constructor for animation which provides the images used for the animation. It sets the maximum frames to the size of the array.
	 * @param anim The images used in the animation
	 * @see BufferedImage
	 */
	public Animation(BufferedImage[] anim) {
		maxFrames = anim.length;
		currentFrame = 0;
		//Below may not work
		animationImages = anim.clone();
	}
	
	/**
	 * Creates an empty animation with a specified amount of frames.
	 * @param maxFrames The amount of frames for this animation
	 */
	public Animation(int maxFrames) {
		this.maxFrames = maxFrames;
		currentFrame = 0;
		animationImages = new BufferedImage[maxFrames];
		for(int i = 0; i < maxFrames; i++) {
			animationImages[i] = new BufferedImage(16, 16, BufferedImage.TYPE_3BYTE_BGR);
		}
	}
	
	/**
	 * Gets the current frame of the animation
	 * @return The current frame number
	 */
	public int getCurrentFrame() {
		return this.currentFrame;
	}
	
	/**
	 * Get the maximum number of frames for this animation.
	 * @return The max frame count
	 */
	public int getMaxFrames() {
		return this.maxFrames;
	}
	
	/**
	 * Switch to the next frame. If the frame goes over the maximum frame count, it resets to 0.
	 */
	public void nextFrame() {
		currentFrame++;
		if(currentFrame==maxFrames)
			currentFrame=0;
	}
	
	/**
	 * Returns the current image from the current frame for this animation.
	 * @return The current image
	 */
	public BufferedImage getCurrentImage() {
		return animationImages[currentFrame];
	}
	
	/**
	 * Set an image for a specific frame. Overrides any existing image.
	 * @param frame The frame number for this image
	 * @param bi The image used
	 */
	public void setFrameImage(int frame, BufferedImage bi) {
		if(frame>maxFrames || frame < 0)
			return;
		animationImages[frame] = bi;
	}
	
	/**
	 * Get the image at a specified frame. Returns null if out of bounds.
	 * @param frame The frame number
	 * @return The image at the specified frame.
	 */
	public BufferedImage getFrameImage(int frame) {
		if(frame>maxFrames || frame < 0)
			return null;
		return animationImages[frame];
	}
	
	
	/**
	 * Constructs an animation using {@link ImageLoader}'s animation retrieval method.
	 * @param name The name of the stored animation.
	 * @return The animation as an object.
	 */
	public static Animation getAnimation(String name) {
		BufferedImage[] images = ImageLoader.getAnimationFrames(name);
		return new Animation(images);		
	}
	
	
	
	
	/*
	 * I dont know why you would need this
	 * public Animation(BufferedImage[] anim, int maxFrames) {
	 *     this.maxFrames = maxFrames;
	 *     currentFrame = 0;
	 *     this.animationImages = anim.clone;
	 * }
	 */
	
}

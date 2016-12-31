package rpg.graphics;

import java.awt.image.BufferedImage;

import rpg.util.ImageLoader;

/**
 * Animation class which contains images and can cycle through them
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
	
	//Created the array and filling it with empty imgages stops it from throwing a null pointer
	public Animation() {
		maxFrames = 16;
		currentFrame = 0;
		animationImages = new BufferedImage[maxFrames];
		for(int i = 0; i < maxFrames; i++) {
			animationImages[i] = new BufferedImage(16, 16, BufferedImage.TYPE_3BYTE_BGR);
		}
	}
	
	public Animation(BufferedImage[] anim) {
		maxFrames = anim.length;
		currentFrame = 0;
		//Below may not work
		animationImages = anim.clone();
	}
	
	public Animation(int maxFrames) {
		this.maxFrames = maxFrames;
		currentFrame = 0;
		animationImages = new BufferedImage[maxFrames];
		for(int i = 0; i < maxFrames; i++) {
			animationImages[i] = new BufferedImage(16, 16, BufferedImage.TYPE_3BYTE_BGR);
		}
	}
	
	public int getCurrentFrame() {
		return this.currentFrame;
	}
	
	public int getMaxFrames() {
		return this.maxFrames;
	}
	
	public void nextFrame() {
		currentFrame++;
		if(currentFrame==maxFrames)
			currentFrame=0;
	}
	
	public BufferedImage getCurrentImage() {
		return animationImages[currentFrame];
	}
	
	public void setFrameImage(int frame, BufferedImage bi) {
		if(frame>maxFrames || frame < 0)
			return;
		animationImages[frame] = bi;
	}
	
	public BufferedImage getFrameImage(int frame) {
		if(frame>maxFrames || frame < 0)
			return null;
		return animationImages[frame];
	}
	
	
	
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

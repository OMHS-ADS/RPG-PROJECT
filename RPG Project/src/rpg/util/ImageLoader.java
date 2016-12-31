package rpg.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ImageLoader {

	private static HashMap<String, BufferedImage> images = new HashMap<String, BufferedImage>();
	private static HashMap<String, BufferedImage[]> animations = new HashMap<String, BufferedImage[]>();

	public static BufferedImage getImage(String name) {
		if (!images.containsKey(name))
			images.put(name, ImageLoader.loadImage(name));
		return images.get(name);
	}

	public static BufferedImage loadImage(String name) {
		try {
			return ImageIO.read(ImageLoader.class.getResourceAsStream("/image/static_image/" + name + ".png"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	//Why not store animation frames in animations? Because it uses less memory to only have one instance of them. Why would we need more?
	public static BufferedImage[] getAnimationFrames(String name) {
		if (!animations.containsKey(name))
			animations.put(name, ImageLoader.loadAnimationFrames(name));
		return animations.get(name);
	}

	
	// Naming convention <num>.png
	// animation_player_1.png ... animation_player_32.png
	public static BufferedImage[] loadAnimationFrames(String name) {
		// Impossible to know frames
		ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
		//This should break when it runs out of frames to find
		int frameNum = 0;
		do {
			try {
				String n = "/image/animations/" + name + "/" + frameNum + ".png";
				//System.out.println("N: " + n);
				BufferedImage bi = ImageIO.read(ImageLoader.class.getResourceAsStream(n));
				if(bi == null) {
					throw new IllegalArgumentException("Couldn't locatate image (could be end of animation)");
				} else {
					images.add(bi);
					frameNum++;
				}
			} catch (IllegalArgumentException | IOException e) {
				//e.printStackTrace();
				//System.out.println("Broken, image size is " + images.size());
				break;
			}
		} while (true);
		BufferedImage[] imageArray = new BufferedImage[images.size()];
		return images.toArray(imageArray);
	}
	
	

}

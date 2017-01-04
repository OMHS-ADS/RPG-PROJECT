package rpg.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

/**
 * ImageLoader is an important utility class which takes care of any image loading needed. It doesn't load images from outside sources, however.
 * Image loader also loads in images for animations that are stored within the source folder.
 *
 */
public class ImageLoader {
	//A hashmap of all images loaded since initialization
	private static HashMap<String, BufferedImage> images = new HashMap<String, BufferedImage>();
	//A hashmap of all animations (array of images) loaded since initialization
	private static HashMap<String, BufferedImage[]> animations = new HashMap<String, BufferedImage[]>();

	/**
	 * Gets a specified image and returns it. If it is not loaded, it will load it. If it doesn't exist, it will return null.
	 * @param name The name of the image
	 * @return The image
	 * @see #loadImage(String)
	 */
	public static BufferedImage getImage(String name) {
		if (!images.containsKey(name))
			images.put(name, ImageLoader.loadImage(name));
		return images.get(name);
	}

	/**
	 * Loads an image from the source folder. Returns null if there is no image found. Note: it loads from /image/static_image/
	 * @param name The name of the image
	 * @return The image loaded
	 */
	public static BufferedImage loadImage(String name) {
		try {
			return ImageIO.read(ImageLoader.class.getResourceAsStream("/image/static_image/" + name + ".png"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	/**
	 * Creates an array of animation frames. If they're not loaded, it loads them.
	 * @see #loadAnimationFrames(String)
	 * @param name The name of the animation
	 * @return The animation images. If non-existent, null.
	 */
	public static BufferedImage[] getAnimationFrames(String name) {
		if (!animations.containsKey(name))
			animations.put(name, ImageLoader.loadAnimationFrames(name));
		return animations.get(name);
	}

	
	// Naming convention <num>.png
	// animation_player_1.png ... animation_player_32.png
	/**
	 * The loadAnimationFrames method loads animations from /image/animations/(name). The proper naming convention for animations is (frameNum).png.
	 * Starting at 0, an animation with 3 frames would be <code>0.png 1.png 2.png</code>. This method continues to load these images, in order, until it can
	 * no longer find an image.
	 * @param name The name of the animation (folder)
	 * @return The array of images if they exist, else null.
	 */
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

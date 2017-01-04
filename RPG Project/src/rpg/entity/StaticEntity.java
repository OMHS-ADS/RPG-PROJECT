package rpg.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import rpg.Tile;
import rpg.graphics.Animation;
import rpg.util.ImageLoader;

/**
 * Inherits {@link Entity}. Similar to {@link AnimatedEntity}. The difference between the two is that AnimatedEntity has an {@link Animation}, where a
 * StaticEntity has an {@link Image}.
 * @see Entity
 * @see AnimatedEntity
 * @see BufferedImage
 *
 */
public class StaticEntity extends Entity {

	private BufferedImage image;
	
	/**
	 * Creates an Entity with a name, and loads that image from its source.
	 * @param tileName
	 */
	public StaticEntity(String tileName) {
		image = ImageLoader.getImage(tileName);
	}

	@Override
	public void render(Graphics g, int x, int y) {
		if(image!=null)
			g.drawImage(image, x, y, Tile.TILE_SIZE,Tile.TILE_SIZE, null);
	}


}

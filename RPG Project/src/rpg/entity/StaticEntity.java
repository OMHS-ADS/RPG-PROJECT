package rpg.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import rpg.Tile;
import rpg.util.ImageLoader;

public class StaticEntity extends Entity {

	private BufferedImage image;
	
	public StaticEntity(String tileName) {
		image = ImageLoader.getImage(tileName);
	}

	@Override
	public void render(Graphics g, int x, int y) {
		if(image!=null)
			g.drawImage(image, x, y, Tile.TILE_SIZE,Tile.TILE_SIZE, null);
	}


}

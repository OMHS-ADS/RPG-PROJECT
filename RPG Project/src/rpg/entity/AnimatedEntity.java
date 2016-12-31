package rpg.entity;

import java.awt.Graphics;

import rpg.Tile;
import rpg.graphics.Animation;

public abstract class AnimatedEntity extends Entity {

	protected Animation a;
	
	public AnimatedEntity(Animation a) {
		this.a = a;
	}
	
	public void render(Graphics g, int xo, int  yo) {
    	//xo and yo are just the tile displacements
    	g.drawImage(a.getCurrentImage(), xo, yo, Tile.TILE_SIZE, Tile.TILE_SIZE, null);
    	a.nextFrame();
    }
	
	public Animation getEntityAnimation() {
		return a;
	}
	
}

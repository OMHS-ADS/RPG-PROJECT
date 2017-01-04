package rpg.entity;

import java.awt.Graphics;

import rpg.Tile;
import rpg.graphics.Animation;

/**
 * Inherits {@link Entity}. Similar to {@link StaticEntity}. The difference between the two is that AnimatedEntity has an {@link Animation}, where a
 * StaticEntity has an {@link Image}. AnimatedEntities render their Animation in their render method.
 * @see Entity
 * @see StaticEntity
 * @see Animation
 */
public abstract class AnimatedEntity extends Entity {

	//Animation is transient because images cannot be serialized. (They can, but I don't want to).
	protected transient Animation a;
	
	/**
	 * Creates an Entity with a specified animation.
	 * @param a The animation for this entity
	 */
	public AnimatedEntity(Animation a) {
		this.a = a;
	}
	
	public void render(Graphics g, int xo, int  yo) {
    	//xo and yo are just the tile displacements
    	g.drawImage(a.getCurrentImage(), xo, yo, Tile.TILE_SIZE, Tile.TILE_SIZE, null);
    	a.nextFrame();
    }
	
	/**
	 * Get this Entity's animation.
	 * @return Its animation.
	 */
	public Animation getEntityAnimation() {
		return a;
	}
	
}

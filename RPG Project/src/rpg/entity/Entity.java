package rpg.entity;

import java.awt.Graphics;
import java.io.Serializable;

/**
 * Entity is the highest level class of an object represented inside the Game world. All other classes used in the world inherit Entity.
 *
 */
public abstract class Entity implements Serializable {
	private static final long serialVersionUID = 7365630204451292944L;

	/**
	 * The render method allows any entity to be visually displayed if needed. Provided graphics and and x and y offset, an Entity will draw itself.
	 * @param g The graphics being used
	 * @param x The x offset
	 * @param y The y offset
	 */
	public abstract void render(Graphics g, int x, int y);

}

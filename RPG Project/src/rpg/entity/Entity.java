package rpg.entity;

import java.awt.Graphics;
import java.io.Serializable;

public abstract class Entity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7365630204451292944L;

	public abstract void render(Graphics g, int x, int y);
}

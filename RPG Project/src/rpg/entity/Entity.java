package rpg.entity;

import java.awt.Graphics;

import rpg.util.Coordinatable;

public abstract class Entity implements Coordinatable {
	public abstract int getX();
	public abstract int getY();
	public abstract void render(Graphics g, int x, int y);
}

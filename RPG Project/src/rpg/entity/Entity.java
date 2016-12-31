package rpg.entity;

import java.awt.Graphics;

import rpg.util.Coordinatable;

public abstract class Entity {
	public abstract void render(Graphics g, int x, int y);
}

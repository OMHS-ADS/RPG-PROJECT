package rpg.entity;

import rpg.util.Coordinatable;

public abstract class Entity implements Coordinatable {
	protected int x,y;
	public Entity(int x,int y) {
		this.x=x;
		this.y=y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}

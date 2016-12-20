package rpg.container;

import java.util.ArrayList;

import rpg.item.Item;

/**
 * A chest is a container with a location
 * @author Morgan
 *
 */
public class Chest extends Container {
	private int x,y;
	public Chest(ArrayList<Item> items,int x,int y) {
		super(items);
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

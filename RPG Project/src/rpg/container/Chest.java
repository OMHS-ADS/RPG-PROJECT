package rpg.container;

import java.awt.Graphics;
import java.util.ArrayList;

import rpg.item.Item;

/**
 * A chest is a container with a location
 * @author Morgan
 * @see Container
 */
public class Chest extends Container {

	/**
	 * Creates  {@link Chest} with the specified items
	 * @param items The desired items
	 */
	public Chest(ArrayList<Item> items) {
		super(items);

	}

	@Override
	public void render(Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		
	}
}

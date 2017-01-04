package rpg.container;

import java.awt.Graphics;
import java.util.ArrayList;

import rpg.item.Item;
/**
 * Default implementation for a character's bag
 * @author Morgan
 * @see Container
 *
 */
public class Bag extends Container {

	/**
	 * Creates a {@link Bag} with the provided items.
	 * @param items The desired items
	 */
	public Bag(ArrayList<Item> items) {
		super(items);
	}

	@Override
	public void render(Graphics g, int x, int y) {
		
	}
}

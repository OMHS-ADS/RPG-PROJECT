package rpg.container;

import java.util.ArrayList;

import rpg.item.Item;
/**
 * Default implementation for a character's bag
 * @author Morgan
 *
 */
public class Bag extends Container {

	public Bag(ArrayList<Item> items) {
		super(items);
	}
}

package rpg.container;

import java.util.ArrayList;

import rpg.entity.Entity;
import rpg.item.Item;
/**
 * Default class for a container. Extends {@link Entity}.
 * @see Bag
 * @see Chest
 * @see Entity
 * @author Morgan
 *
 */
public abstract class Container extends Entity  {
	//The items in the container
	protected ArrayList<Item> items;
	
	/**
	 * Default constructor for containers. Takes in an array for desired items.
	 * @param items Desired items
	 */
	public Container(ArrayList<Item> items) {
		this.items=items;
	}
	
	/**
	 * Returns the contents of this {@link Container}
	 * @return Items
	 */
	public ArrayList<Item> getItems() {
		return items;
	}
	
	/**
	 * Adds an item to the {@link Container}
	 * @param i The {@link Item} added.
	 */
	public void addItem(Item i) {
		items.add(i);
	}
	/**
	 * Adds an item to a specific location inside the {@link Container}.
	 * @param n The index
	 * @param i The {@link Item}
	 */
	public void addItem(int n, Item i) {
		items.add(n, i);
	}
	/**
	 * Removes an {@link Item} from the specified index, and returns it.
	 * @param i The index of the item
	 * @return The item removed
	 */
	public Item removeItem(int i) {
		return items.remove(i);
	}
	
	/**
	 * Removes an item based on the {@link Item}. Returns itself.
	 * @param i The Item being removed
	 * @return The item being removed
	 */
	public Item removeItem(Item i) {
		return items.remove(items.indexOf(i));
	}
	
	/**
	 * Moves an item from one {@link Container} to another.
	 * @param i The index of the item being moved
	 * @param c The desination container
	 * @return The item being moved
	 */
	public Item moveItem(int i, Container c){
		c.addItem(items.get(i));
		return items.remove(i);
	}


	public String toString() {
		return items.toString();
	}
}

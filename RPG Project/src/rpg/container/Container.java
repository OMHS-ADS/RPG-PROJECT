package rpg.container;

import java.util.ArrayList;

import rpg.item.Item;
/**
 * Default class for a container.
 * @see Bag
 * @see Chest
 * @author Morgan
 *
 */
public abstract class Container {
	protected ArrayList<Item> items;
	public Container(ArrayList<Item> items) {
		this.items=items;
	}
	
	public ArrayList<Item> getItems() {
		return items;
	}
	
	public void addItem(Item i) {
		items.add(i);
	}
	public void addItem(int n, Item i) {
		items.add(n, i);
	}
	public Item removeItem(int i) {
		return items.remove(i);
	}
	public Item removeItem(Item i) {
		return items.remove(items.indexOf(i));
	}
	public Item moveItem(int i, Container c){
		c.addItem(items.get(i));
		return items.remove(i);
	}
	public String toString() {
		return items.toString();
	}
}

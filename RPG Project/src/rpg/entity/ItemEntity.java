package rpg.entity;

import java.awt.Graphics;

import rpg.item.Item;
import rpg.util.Coordinatable;
/**
 * An entity that represents an item on the ground
 * @author Morgan
 *
 */
public class ItemEntity extends Entity {
	protected Item rep; //The item represented by the ItemEntity

	/**
	 * Default constructor which takes in the item being represented
	 * @param rep The item
	 * @see Item
	 */
	public ItemEntity(Item rep) {
		this.rep = rep;
	}

	/**
	 * Returns the item being displayed.
	 * @return
	 */
	public Item getItem() {
		return rep;
	}
	@Override
	public void render(Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		
	}
}

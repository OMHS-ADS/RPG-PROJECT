package rpg.entity;

import rpg.item.Item;
import rpg.util.Coordinatable;
/**
 * An entity that represents an item on the ground
 * @author Morgan
 *
 */
public class ItemEntity extends Entity {
	protected Item rep; //The item represented by the ItemEntity
	protected int x,y; //The position of the ItemEntity
	public ItemEntity(Item rep, int x,int y) {
		super(x,y);
		this.rep=rep;
	}
	public Item getItem() {
		return rep;
	}
}

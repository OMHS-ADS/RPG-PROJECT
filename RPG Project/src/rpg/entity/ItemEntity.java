package rpg.entity;

import java.awt.Graphics;

import rpg.Game;
import rpg.item.Item;
import rpg.util.Interactable;
/**
 * An entity that represents an item on the ground. 
 * @see Interactable
 * @author Morgan
 *
 */
public class ItemEntity extends StaticEntity implements Interactable {
	protected Item rep; //The item represented by the ItemEntity

	/**
	 * Default constructor which takes in the item being represented
	 * @param rep The item
	 * @see Item
	 */
	public ItemEntity(Item rep) {
		super(rep.getName());
		this.rep = rep;
	}

	/**
	 * Returns the item being displayed.
	 * @return
	 */
	public Item getItem() {
		return rep;
	}

	public void interact(PlayerCharacter p) {
		//Possibly pick up the item?
	}

	@Override
	public void interact(PlayerCharacter p, Game g) {
		// TODO Auto-generated method stub
		
	}
}

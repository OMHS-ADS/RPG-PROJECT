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
	protected int x,y; //The position of the ItemEntity
	public ItemEntity(Item rep, int x,int y) {
		this.rep=rep;
		this.x=x;
		this.y=y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public Item getItem() {
		return rep;
	}
	@Override
	public void render(Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		
	}
}

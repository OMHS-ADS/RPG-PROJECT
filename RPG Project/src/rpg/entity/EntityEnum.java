package rpg.entity;

import java.util.ArrayList;

import rpg.graphics.Data;
import rpg.item.Item;

/**
 * EntityEnum is used for the level designer. It is unsure how this enum functions.
 * @author Your's truly, xDest.
 *
 */
public enum EntityEnum {
	MORTAL_OBS(new Data[]{new Data("HP",new Integer(1))}),IMMORTAL_OBS(new Data[0]),DIRT(new Data[0]),GRASS(new Data[0]),MARBLE(new Data[0]),PATH(new Data[]{new Data("DIRECTION", new Integer(0))}),STONE(new Data[0]),TREE(new Data[0]),BAG(new Data[]{ new Data("ITEMS",new ArrayList<Item>())}),CHEST(new Data[]{ new Data("ITEMS",new ArrayList<Item>())});
	
	private Data[] data;
	
	//The entity data
	EntityEnum(Data[] d) {
		data = d;
	}
	
	//Set the entity data
	public void setData(Data[] d) {
		data = d;
	}
}

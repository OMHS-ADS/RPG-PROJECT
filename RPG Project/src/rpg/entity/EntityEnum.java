package rpg.entity;

import java.util.ArrayList;

import rpg.graphics.Data;
import rpg.item.Item;

public enum EntityEnum {
	MORTAL_OBS(new Data[]{new Data("HP",new Integer(1))}),IMMORTAL_OBS(new Data[0]),DIRT(new Data[0]),GRASS(new Data[0]),MARBLE(new Data[0]),PATH(new Data[]{new Data("DIRECTION", new Integer(0))}),STONE(new Data[0]),TREE(new Data[0]),BAG(new Data[]{ new Data("ITEMS",new ArrayList<Item>())}),CHEST(new Data[]{ new Data("ITEMS",new ArrayList<Item>())});
	
	private Data[] data;
	
	EntityEnum(Data[] d) {
		data = d;
	}
	
	public void setData(Data[] d) {
		data = d;
	}
}

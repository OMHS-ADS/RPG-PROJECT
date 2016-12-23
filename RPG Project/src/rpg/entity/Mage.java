package rpg.entity;

import rpg.item.Shield;
import rpg.item.Weapon;

public class Mage extends PlayerCharacter {
	public Mage(int x, int y, String name) {
		super(x,y,name);
		this.MAP=3;
		this.hitChance=(float) .8;
	}
}

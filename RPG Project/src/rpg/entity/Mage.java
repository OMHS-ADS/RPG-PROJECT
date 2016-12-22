package rpg.entity;

import rpg.item.Shield;
import rpg.item.Weapon;

public class Mage extends PlayerCharacter {
	public Mage(String name) {
		super(name);
		this.MAP=3;
		this.hitChance=(float) .8;
	}
}

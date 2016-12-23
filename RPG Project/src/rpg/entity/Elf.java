//Gintare
package rpg.entity;

import rpg.item.Shield;
import rpg.item.Weapon;

public class Elf extends PlayerCharacter {
	public Elf(int x, int y, String name) {
		super(x,y,name);
		this.MAP=1;
		this.hitChance=(float) .85;
	}

}

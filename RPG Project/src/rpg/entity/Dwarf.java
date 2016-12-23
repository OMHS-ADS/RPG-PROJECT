//Cameron Nikiel
//Hi
package rpg.entity;

import rpg.item.Shield;
import rpg.item.Weapon;

public class Dwarf extends PlayerCharacter {
	public Dwarf(int x, int y, String name) {
		super(x,y,name);
		this.MAP=4;
		this.hitChance=(float) .65;
	}

}

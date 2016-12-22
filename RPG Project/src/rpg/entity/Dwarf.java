//Cameron Nikiel
//Hi
package rpg.entity;

import rpg.item.Shield;
import rpg.item.Weapon;

public class Dwarf extends PlayerCharacter {
	public Dwarf(String name) {
		super(name);
		this.MAP=4;
		this.hitChance=(float) .65;
	}

}

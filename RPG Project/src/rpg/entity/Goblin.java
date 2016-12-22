//Cameron Nikiel
package rpg.entity;

import rpg.item.Shield;
import rpg.item.Weapon;

public class Goblin extends PlayerCharacter {
	public Goblin(String name) {
		super(name);
		this.MAP=1;
		this.hitChance=(float) .85;
	}
}

//Cameron Nikiel
package rpg.entity;

import rpg.item.Shield;
import rpg.item.Weapon;

public class Ogre extends PlayerCharacter {
	public Ogre(String name) {
		super(name);
		this.MAP=5;
		this.hitChance=(float) .6;
	}
}

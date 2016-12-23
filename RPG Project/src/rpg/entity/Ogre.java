//Cameron Nikiel
package rpg.entity;

import rpg.item.Shield;
import rpg.item.Weapon;

public class Ogre extends PlayerCharacter {
	public Ogre(int x, int y, String name) {
		super(x,y,name);
		this.MAP=5;
		this.hitChance=(float) .6;
	}
}

//Cameron Nikiel
package rpg.entity;

import rpg.item.Shield;
import rpg.item.Weapon;

public class Goblin extends PlayerCharacter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5766885321118782895L;

	public Goblin(String playerName) {
		super("Goblin", playerName);
		this.MAP=1;
		this.hitChance=(float) .85;
	}
	

}

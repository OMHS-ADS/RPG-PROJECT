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
		super("goblin", playerName);
		this.MAP=69;
		this.hitChance=(float) .04;
	}
	

}

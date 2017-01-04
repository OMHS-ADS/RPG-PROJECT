//Cameron Nikiel
package rpg.entity;

import rpg.item.Shield;
import rpg.item.Weapon;

/**
 * The ogre player class
 * @see PlayerCharacter
 *
 */
public class Ogre extends PlayerCharacter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2042968389912079193L;

	public Ogre(String playerName) {
		super("ogre", playerName);
		this.MAP = 5;
		this.hitChance = 0.6f;
	}
	
}

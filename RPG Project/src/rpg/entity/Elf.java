//Gintare
package rpg.entity;

import rpg.item.Shield;
import rpg.item.Weapon;

/**
 * The Elf player class
 * @see PlayerCharacter
 *
 */
public class Elf extends PlayerCharacter {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3363161397212705536L;
	public Elf(String playerName){
		super("elf",playerName);
		//define HITCHANCE
		this.MAP=10;
		this.hitChance=(float) 0.7;
	}
	

}

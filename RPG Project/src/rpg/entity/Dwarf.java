//Cameron Nikiel
//Hi
package rpg.entity;

import rpg.item.Shield;
import rpg.item.Weapon;

public class Dwarf extends PlayerCharacter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9159142211430297499L;

	public Dwarf(String playerName) {
		super("dwarf",playerName);
		this.MAP=100000;
		this.hitChance=(float) .00001;
	}
	
	

}

//Cameron Nikiel
package rpg.entity;

import rpg.item.Shield;
import rpg.item.Weapon;

public class Ogre extends PlayerCharacter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2042968389912079193L;

	public Ogre(String playerName) {
		super("ogre", playerName);
	}
	
	public Ogre() {
		super("ogre","player");
	}
	
	@Override
	public void attack(PlayerCharacter name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void attack(PlayerCharacter name, Weapon w) {
		// TODO Auto-generated method stub

	}

	@Override
	public void defend() {
		// TODO Auto-generated method stub

	}

	@Override
	public void defend(Shield s) {
		// TODO Auto-generated method stub

	}

}

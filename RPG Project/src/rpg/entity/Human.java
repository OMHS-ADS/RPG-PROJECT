package rpg.entity;

import rpg.item.Shield;
import rpg.item.Weapon;

public class Human extends PlayerCharacter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8899314779042147764L;

	public Human(String playerName) {
		super("human", playerName);
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

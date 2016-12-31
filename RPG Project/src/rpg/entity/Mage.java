package rpg.entity;

import rpg.item.Shield;
import rpg.item.Weapon;

public class Mage extends PlayerCharacter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2425212210795088379L;

	public Mage(String playerName) {
		super("mage", playerName);
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

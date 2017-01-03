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
		this.MAP=60;
		this.hitChance=(float) 2.8;
	}
	


}

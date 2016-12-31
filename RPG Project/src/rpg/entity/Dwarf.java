//Cameron Nikiel
//Hi
package rpg.entity;

import rpg.item.Shield;
import rpg.item.Weapon;

public class Dwarf extends PlayerCharacter {

	public Dwarf() {
		super("dwarf");
	}
	
	@Override
	public void attack(PlayerCharacter name) {
		// TODO Auto-generated method stub
		//do 1 dmg default
		//default attack value, dmg = attack * modifier
		// if hits(true){
		// name.defend(dmg * defend multiplier (smaller than with shield))
		//}
		//name.defend(name.getShield()){
		//}
	}

	@Override
	public void attack(PlayerCharacter name, Weapon w) {
		// TODO Auto-generated method stub
		//do 1 dmg default
		//default attack value, dmg = attack * modifier
		//do more dmgs based on modifier
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

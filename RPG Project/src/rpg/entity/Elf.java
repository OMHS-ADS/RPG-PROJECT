//Gintare
package rpg.entity;

import rpg.item.Shield;
import rpg.item.Weapon;

public class Elf extends PlayerCharacter {
	public Elf(){
		super("elf");
		//define HITCHANCE
	}
	@Override
	public void attack(PlayerCharacter name) {
		//if (Math.random() < HITCHANCE){
		//	name.defend(MAP);
		//}
		// if hits(true){
		// name.defend(dmg * defend multiplier (smaller than with shield))
		//}
		//name.defend(name.getShield()){
	}

	@Override
	public void attack(PlayerCharacter name, Weapon w) {
		//if (Math.random()<HITCHANCE){
		//	name.defend(MAP*modifier);
		//}
		//name.defend(name.getShield()){
		//}
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

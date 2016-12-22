//Gintare
package rpg.entity;

import rpg.item.Shield;
import rpg.item.Weapon;

public class Elf extends PlayerCharacter {
	public Elf(){
		//define hitChance
	}
	@Override
	public void attack(PlayerCharacter name) {
		if (Math.random() < hitChance){  //if the attack works, then the enemy has a chance to defend itself
			if(name.getShield()==null){
				name.defend(MAP);
			} else{
				name.defend(MAP,name.getShield());
			}
		}
	}

	@Override
	public void attack(PlayerCharacter name, Weapon w) {
		if (Math.random()<hitChance){
			if(name.getShield()==null){
				name.defend(MAP*(w.getMAP()));
			} else{
				name.defend(MAP*(w.getMAP()),name.getShield());
			}
		}
	}

	@Override
	public void defend(int dmg) {

	}

	@Override
	public void defend(int dmg,Shield s) {

	}

}

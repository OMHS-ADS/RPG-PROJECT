package rpg.entity;

import java.awt.Graphics;

import rpg.graphics.Animation;
import rpg.item.Arm;
import rpg.item.Shield;
import rpg.item.Weapon;

/**
 * Rabit is an Enemy.
 * @see Enemy
 * @author MadelynCarr
 *
 */
public class Rabbit extends Enemy{
	public Rabbit(){
		super(Animation.getAnimation("rabbit"));
		this.HP = 10;
		this.MAP = 5;
		this.MDP = 1;
		this.hitChance = (float) 0.3;
	}

	@Override
	public void move(int direction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attack(Entity target) {
		PlayerCharacter player = ((PlayerCharacter)target);
		if(player.getShield() == null){
			player.defend(MAP);
		}
		else{
			player.defend(MAP, player.getShield());
		}
		
	}

	public void changeHealth(double dmg) { 		//Get damaged or healed by a spell or an attack
    	HP += dmg;
    	//System.out.println(HP);
    }
	
	public void defend(int dmg) {				//(defend an attack)
    	this.changeHealth((double)(dmg * (1 - baseDefend)) * (-1));
    }

	@Override
	public String getLocation() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public double getHP() {
		// TODO Auto-generated method stub
		return HP;
	}
}


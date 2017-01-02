package rpg.entity;

import rpg.item.Shield;
import rpg.item.Weapon;

public class Rabbit extends Enemy{
	public Rabbit(){
		this.HP = 10;
		this.MAP = 1;
		this.MDP = 1;
		this.hitChance = (float) 0.3;
	}

	@Override
	public void move(int direction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attack(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void defend() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getLocation() {
		// TODO Auto-generated method stub
		return null;
	}
}


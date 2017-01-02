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
}


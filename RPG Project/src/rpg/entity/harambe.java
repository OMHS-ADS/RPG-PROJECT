package rpg.entity;

import java.awt.Graphics;

import rpg.graphics.Animation;
import rpg.item.Shield;
import rpg.item.Weapon;

public class harambe extends Enemy{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public harambe(){
		super(Animation.getAnimation("harambe"));
		this.maxHp = 400;
		this.HP = maxHp;
		this.MAP = 57;
		this.MDP = 5;
		this.hitChance = (float) 0.6;
	}
	
	@Override
	public void defend(int d) {
		super.defend(d);
	}


}


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
		super(Animation.getAnimation("rabbit"));
		this.HP = 400;
		this.MAP = 57;
		this.MDP = 100;
		this.hitChance = (float) 0.6;
	}


}


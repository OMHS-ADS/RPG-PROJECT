package rpg.entity;

import java.awt.Graphics;

import rpg.graphics.Animation;
import rpg.item.Shield;
import rpg.item.Weapon;

public class hanzo extends Enemy{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public hanzo(){
		super(Animation.getAnimation("rabbit"));
		this.HP = 200;
		this.MAP = 125;
		this.MDP = 0;
		this.hitChance = (float) .7999;
	}

}


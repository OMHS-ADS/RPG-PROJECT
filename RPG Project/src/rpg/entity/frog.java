package rpg.entity;

import java.awt.Graphics;

import rpg.graphics.Animation;
import rpg.item.Shield;
import rpg.item.Weapon;

public class frog extends Enemy{
	public frog(){
		super(Animation.getAnimation("rabbit"));
		this.HP = 15;
		this.MAP = 3;
		this.MDP = 2;
		this.hitChance = (float) 0.5;
	}


}


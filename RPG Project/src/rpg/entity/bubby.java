package rpg.entity;

import java.awt.Graphics;

import rpg.graphics.Animation;
import rpg.item.Shield;
import rpg.item.Weapon;

public class bubby extends Enemy{
	public bubby(){
		super(Animation.getAnimation("rabbit"));
		this.HP = 300;
		this.MAP = 5;
		this.MDP = 30;
		this.hitChance = (float) 0.6;
	}


}


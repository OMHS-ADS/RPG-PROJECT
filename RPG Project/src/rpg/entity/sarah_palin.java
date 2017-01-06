package rpg.entity;

import java.awt.Graphics;

import rpg.graphics.Animation;
import rpg.item.Shield;
import rpg.item.Weapon;

public class sarah_palin extends Enemy{
	public sarah_palin(){
		super(Animation.getAnimation("rabbit"));
		this.HP = 100;
		this.MAP = 18;
		this.MDP = 50;
		this.hitChance = (float) 0.56;
	}

}


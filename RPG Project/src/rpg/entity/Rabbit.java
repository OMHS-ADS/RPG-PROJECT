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

}


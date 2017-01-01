package rpg.entity;

import java.awt.Graphics;

import rpg.util.Damageable;

//An example of this is like a breakable wall 
public class MortalObstruction extends StaticEntity implements Damageable {

	private double hp;
	
	public MortalObstruction(int hp) {
		super("mortal_obstruction");
		this.hp = hp;
	}

	@Override
	public void changeHealth(double dmg) {
		hp+=dmg;
	}

	@Override
	public double getHP() {
		return hp;
	}
	

}

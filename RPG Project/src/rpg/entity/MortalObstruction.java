package rpg.entity;

import java.awt.Graphics;

import rpg.util.Damageable;

/**
 * MortalObstruction is a {@link Damageable} foreground tile. Its purpose is to block entities from passing through the tile, but it can be 
 * destroyed by natural means.
 * @see ImmortalObsrtuction
 * 
 *
 */
public class MortalObstruction extends StaticEntity implements Damageable {

	private double hp;
	
	/**
	 * Creates a {@link MoralObstruction} with a specified health.
	 * @param hp The health for this object.
	 */
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

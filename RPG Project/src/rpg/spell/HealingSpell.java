package rpg.spell;

import rpg.entity.PlayerCharacter;

public class HealingSpell extends Spell{
	protected double heal;
	public HealingSpell(int x, int y, double vx, double vy, double heal) {
		super(x,y,vx,vy);
		this.heal=heal;
		
	}
	public double getHealing() {
		return heal;
	}
	
	public void doEffect(PlayerCharacter c) {
		c.changeHealth(heal);
	}

}

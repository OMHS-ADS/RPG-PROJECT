package spell;

import rpg.entity.PlayerCharacter;

public class HealingSpell extends Spell{
	protected double heal;
	public HealingSpell(double vx, double vy, double heal) {
		super(vx, vy);
		this.heal=heal;
		// TODO Auto-generated constructor stub
	}
	public double getDmg() {
		return heal;
	}
	
	public void doEffect(PlayerCharacter c) {
		c.changeHealth(heal);
	}

}

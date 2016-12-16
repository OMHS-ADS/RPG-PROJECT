package spell;

import rpg.entity.PlayerCharacter;
public class DamageSpell extends Spell {
	protected double dmg;
	public DamageSpell(double vx, double vy, double dmg) {
		super(vx, vy);
		this.dmg=dmg;
		// TODO Auto-generated constructor stub
	}
	public double getDmg() {
		return dmg;
	}
	
	public void doEffect(PlayerCharacter c) {
		c.changeHealth(-dmg);
	}

}

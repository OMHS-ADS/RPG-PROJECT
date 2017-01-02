package rpg.spell;

import rpg.entity.PlayerCharacter;
public class DamageSpell extends Spell {
	protected double dmg;
	public DamageSpell(int x, int y, double vx, double vy, double dmg) {
		super(x,y,vx,vy);
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

package rpg.spell;

import rpg.entity.PlayerCharacter;

public class DamageOutputSpell extends Spell {
	protected int ap;
	public DamageOutputSpell(double vx, double vy, int dmg) {
		super(vx, vy);
		this.ap=ap;
	}
	public void doEffect(PlayerCharacter c) {
		c.changeMAP(ap);

	}

}

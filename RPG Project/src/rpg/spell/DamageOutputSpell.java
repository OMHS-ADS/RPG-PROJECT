package rpg.spell;

import rpg.entity.PlayerCharacter;

public class DamageOutputSpell extends Spell {
	protected int ap;
	public DamageOutputSpell(int x, int y, double vx, double vy, int ap) {
		super(x,y,vx,vy);
		this.ap=ap;
	}
	public void doEffect(PlayerCharacter c) {
		c.changeMAP(ap);

	}

}

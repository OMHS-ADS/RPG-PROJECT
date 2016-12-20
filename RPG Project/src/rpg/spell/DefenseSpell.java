package rpg.spell;

import rpg.entity.PlayerCharacter;

public class DefenseSpell extends Spell {
	protected int dp;
	public DefenseSpell(double vx, double vy, int dp) {
		super(vx, vy);
		this.dp=dp;
	}
	public void doEffect(PlayerCharacter c) {
		c.changeMDP(dp);

	}

}

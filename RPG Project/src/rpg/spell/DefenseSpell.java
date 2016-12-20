package rpg.spell;

import rpg.entity.PlayerCharacter;

public class DefenseSpell extends Spell {
	protected int dp;
	public DefenseSpell(int x, int y, double vx, double vy, int dp) {
		super(x,y,vx,vy);
		this.dp=dp;
	}
	public void doEffect(PlayerCharacter c) {
		c.changeMDP(dp);

	}

}

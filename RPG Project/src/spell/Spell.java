package spell;

import rpg.entity.PlayerCharacter;
public abstract class Spell {
	protected double vx,vy;
	public Spell(double vx, double vy) {
		this.vx=vx;
		this.vy=vy;
	}
	public double getVx() {
		return vx;
	}
	public double getVy() {
		return vy;
	}
	public abstract void doEffect(PlayerCharacter c);
	
}

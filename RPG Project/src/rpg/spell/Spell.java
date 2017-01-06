package rpg.spell;

import rpg.entity.PlayerCharacter;
import rpg.util.Coordinatable;
/**
 * Default class for spells. Spells have a speed, a position (in order to be rendered), and an effect
 * @author Morgan
 *
 */
public abstract class Spell implements Coordinatable {
	protected double vx,vy; //velocities of fired spell
	protected int x,y;
	public Spell(int x, int y, double vx, double vy) {
		this.vx=vx;
		this.vy=vy;
		this.x=x;
		this.y=y;
	}
	public double getVx() {
		return vx;
	}
	public double getVy() {
		return vy;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public abstract void doEffect(PlayerCharacter c);
	
}

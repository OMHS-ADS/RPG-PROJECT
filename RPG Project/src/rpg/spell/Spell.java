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
	
	//This is not how spell is supposed to be but i needed a way for player to heal. can be removed
	public static Spell getSpell(int spell) {
		Spell[] spells = new Spell[] {new HealingSpell(0,0,0,0,5), new DefenseSpell(0,0,0,0,5), new DamageSpell(0,0,0,0,5), new DamageOutputSpell(0,0,0,0,5)};
		return spells[spell];
	}
}

package rpg.util;

/**
 * An interface specifying whether this object can take damage.
 *
 */
public interface Damageable {

	/**
	 * Change the health of this object
	 * @param dmg The health given or taken to this object
	 */
	public void changeHealth(double dmg);
	/**
	 * Get the current health of this object
	 * @return the current health
	 */
	public double getHP();
}

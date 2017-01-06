package rpg.entity;

import java.awt.Graphics;
/**
 * ImmortalObstruction is a foreground tile which blocks the players (and other entity) movement. It cannot be destroyed by the player or
 * other entities.
 * @see MortalObstruction
 * @author Zach
 *
 */
public class ImmortalObstruction extends StaticEntity {

	
	/**
	 * Default constructor
	 */
	public ImmortalObstruction() {
		super("immortal_obstruction");
	}

	

}

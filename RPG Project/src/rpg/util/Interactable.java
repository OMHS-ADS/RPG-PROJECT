package rpg.util;

import rpg.entity.PlayerCharacter;

/**
 * An interface which specifies if the object is able to be interacted with. Contains an interact method for interactions
 *
 */
public interface Interactable {

	/**
	 * Indicates the player has interacted with this object, and it should do something.
	 * @param p The player interacting.
	 */
	public void interact(PlayerCharacter p);
	
	
}

package rpg.entity;

import rpg.Game;
import rpg.World;
import rpg.graphics.Animation;
import rpg.util.ArrayValue2D;
import rpg.util.Interactable;

/**
 * ExitEntity is an {@link AnimatedEntity} which is {@link Interactable}. The action this Entity does is transports the player to a different world.
 * @see World
 * @see Enity
 * @see PlayerCharacter
 *
 */
public class ExitEntity extends AnimatedEntity implements Interactable {

	private static final long serialVersionUID = 8232615203379526530L;
	private int toWorldId;
	//The World[] worlds array index in World
	
	/**
	 * The constructor which takes in the World ID for the destination world.
	 * @param worldId The index of the destination world.
	 */
	public ExitEntity(int worldId) {
		super(Animation.getAnimation("exit"));
		//To exit to a different room
		toWorldId = worldId;
	}
	
	/**
	 * Gets the id of the destination world
	 * @return The index
	 */
	public int getDestinationId() {
		return this.toWorldId;
	}

	@Override
	public void interact(PlayerCharacter p, Game g) {
		//Move to other world somehow
		World destination = World.getWorld(this.toWorldId);
		//System.out.println("interecated!");
		g.transferPlayerToWorld(destination);
		
	}
	
	

}

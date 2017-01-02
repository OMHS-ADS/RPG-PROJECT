package rpg.entity;

import rpg.graphics.Animation;
import rpg.util.Interactable;

public class ExitEntity extends AnimatedEntity implements Interactable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8232615203379526530L;
	private int toWorldId;
	
	public ExitEntity(Animation a, int worldId) {
		super(Animation.getAnimation("exit"));
		//To exit to a different room
		toWorldId = worldId;
	}
	
	public int getDestinationId() {
		return this.toWorldId;
	}

	@Override
	public void interact(PlayerCharacter p) {
		//Move to other world somehow
	}
	
	

}

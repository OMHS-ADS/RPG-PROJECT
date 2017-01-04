package rpg.util;

import rpg.util.Direction.DirectionWrapper;

public enum PlayerActions {
	MOVE(new DirectionWrapper()),
	ATTACK(new DirectionWrapper()),
	INVENTORY,DROP,EXIT,PICKUP;
	Direction dir;
	PlayerActions(DirectionWrapper d) {
		this.dir=d.getDirection();
	}
	PlayerActions() {
		
	}
	public static PlayerActions MOVE(DirectionWrapper dw) {
		return MOVE(dw);
	}
	public static PlayerActions ATTACK(DirectionWrapper dw) {
		return ATTACK(dw);
	}
}

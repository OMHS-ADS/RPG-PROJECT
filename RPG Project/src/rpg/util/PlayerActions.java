package rpg.util;

public enum PlayerActions {
	MOVE_UP(-1),MOVE_RIGHT(-1),MOVE_DOWN(-1),MOVE_LEFT(-1),
	ATTACK_UP(-1),ATTACK_RIGHT(-1),ATTACK_DOWN(-1),ATTACK_LEFT(-1),
	INVENTORY(-1),DROP(0),EXIT(-1), PICKUP(-1), EQUIP(0),SPELL(0);
	
	private int value;
	
	PlayerActions(int extraValue) {
		this.value = extraValue;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public PlayerActions setValue(int v) {
		this.value = v;
		return this;
	}
}

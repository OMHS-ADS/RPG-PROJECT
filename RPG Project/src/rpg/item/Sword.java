package rpg.item;
public class Sword extends Weapon{
	
	public Sword(){
		super();
		super.setAttack(10);
	}
	
	public Sword(String displayName){
		super("sword", displayName);
		super.setAttack(10);
	}
	// sword attack is 10

}
package rpg.item;
public class Sword extends Weapon{
	
	public Sword(){
		super();
		super.setAttack(10);
	}
	
	public Sword(String name){
		super(name);
		super.setAttack(10);
	}
	// sword attack is 10

}
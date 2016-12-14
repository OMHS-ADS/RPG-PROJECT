package rpg.item;
public class Sword extends Weapon{
	
	public Sword(){
		super();
		super.setAttack(10);
	}
	
	public Sword(String namein, int x, int y){
		super(namein, x, y);
		super.setAttack(10);
	}
	// sword attack is 10

}
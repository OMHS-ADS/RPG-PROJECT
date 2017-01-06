package rpg.item;
public class Axe extends Weapon{

	public Axe(){
		super();
		super.setAttack(20);
	}
	
	public Axe(String displayName){
		super("axe",displayName);
		super.setAttack(20);
	}
	
	//Axe attack is 20
}
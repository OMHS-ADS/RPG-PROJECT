package rpg.item;
public class Axe extends Weapon{

	public Axe(){
		super();
		super.setAttack(20);
	}
	
	public Axe(String name){
		super(name);
		super.setAttack(20);
	}
	
	//Axe attack is 20
}
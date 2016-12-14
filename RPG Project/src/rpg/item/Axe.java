package rpg.item;
public class Axe extends Weapon{

	public Axe(){
		super();
		super.setAttack(20);
	}
	
	public Axe(String name, int x, int y){
		super(name, x, y);
		super.setAttack(20);
	}
	
	//Axe attack is 20
}
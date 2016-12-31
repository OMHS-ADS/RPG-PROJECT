package rpg.item;
abstract public class Weapon extends Item{
	// all the variables are in the super class... isn't this awesome? :D
	
	public Weapon(){
		super();
	}
	
	public Weapon(String name){
		super(name);
	}
	
	public String toString(){
		String str = name + " : \n"
				   //+ "Equipped = " + equipped + "\n"
				   //+ "In somebody's inventory = " + state + "\n"
				   + "Attack Points : " + MAP;// + "\n"
				   //+ "(X,Y) position = (" + xPos + ", " + yPos + ")";
		return str;
	}		
		
	protected void setAttack(int attack){
		MAP = attack;
	}
	
}
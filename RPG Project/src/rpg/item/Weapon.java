package rpg.item;
abstract public class Weapon extends Item{
	// all the variables are in the super class... isn't this awesome? :D
	
	public Weapon(){
		super();
	}
	
	public Weapon(String className, String displayName){
		super(className, displayName);
	}
	
	public String toString(){
		String str = displayName + "- "
				   //+ "Equipped = " + equipped + "\n"
				   //+ "In somebody's inventory = " + state + "\n"
				   + "AP: " + MAP;// + "\n"
				   //+ "(X,Y) position = (" + xPos + ", " + yPos + ")";
		return str;
	}		
		
	protected void setAttack(int attack){
		MAP = attack;
	}
	
}
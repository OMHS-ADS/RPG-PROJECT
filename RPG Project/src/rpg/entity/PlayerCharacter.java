package rpg.entity;
import rpg.item.Shield;
import rpg.item.Weapon;
import rpg.util.Direction;

public abstract class PlayerCharacter {
    protected int MAP; //maximum attack points of the character excluding other items
	protected int MDP; //maximum defense points of the character excluding other items
	protected int HP; //current health points a character has
	protected String name;  //the name of the character
	protected int xPos, yPos; //location of the character
	
    public void move(Direction direction) {   //  			(move in a direction multiple space(s)
    	switch (direction) {
    	case UP:yPos++;break;
    	case RIGHT:xPos++;break;
    	case DOWN:yPos--;break;
    	case LEFT:xPos--;break;
    	}
    }
    public abstract void pickup(String item);	//		(pickup a visible item)
    public abstract void drop(String item);			//(drop an item at your current location)
    public abstract void attack(String name);		//	(attack another character)
    public abstract void attack(String name, Weapon w);	//(attack a character with an item)
    public  void changeHealth(double dmg) { 		//Get damaged or healed by a spell or an attack
    	HP += dmg;
    }
    public abstract void defend();				//(defend an attack)
    public abstract void defend(Shield s);	//		(defend an attack with an item)
    public abstract String getBagContents();			//(return all the contents in the character’s person)
    public abstract String getLocation();			//	(return’s the current character’s room location as X, Y)
}

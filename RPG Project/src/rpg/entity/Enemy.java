package rpg.entity;

import rpg.util.Damageable;

/**
 * The Enemy class, which is the highest level class for an Enemy in the game.
 * @see Entity
 *
 */
public abstract class Enemy extends Entity implements Damageable {
	 	protected int MAP; //maximum attack points of the character excluding other items
		protected int MDP; //maximum defense points of the character excluding other items
		protected int HP; //current health points a character has
		protected String name;  //the name of the character
		protected int xPos, yPos; //location of the character
		protected boolean boss;
		protected float hitChance = (float) 0.75, baseDefend = 0.1f;

		
	    public abstract void move(int direction);   //  			(move in a direction multiple space(s)
	    //public abstract void pickup(String item);	//		(pickup a visible item)
	    //public abstract void drop(String item);			//(drop an item at your current location)
	    public abstract void attack(Entity target);		//	(attack another character)
	    //public abstract void attack(String name, Weapon w);	//(attack a character with an item)
	    public abstract void defend(int dmg);				//(defend an attack)
	    //public abstract void defend(Shield s);	//		(defend an attack with an item)
	    public abstract String getLocation();			//	(return�s the current character�s room location as X, Y)
}

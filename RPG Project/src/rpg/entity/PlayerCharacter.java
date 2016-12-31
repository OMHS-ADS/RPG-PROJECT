package rpg.entity;
import java.awt.Graphics;
import java.io.Serializable;

import rpg.container.Bag;
import rpg.graphics.Animation;
import rpg.item.Arm;
import rpg.item.Fist;
import rpg.item.Item;
import rpg.item.Shield;
import rpg.item.Weapon;
import rpg.util.Damageable;
import rpg.util.Direction;

public abstract class PlayerCharacter extends AnimatedEntity implements Damageable, Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7971927940316969269L;
	protected int MAP; //maximum attack points of the character excluding other items
	protected int MDP; //maximum defense points of the character excluding other items
	protected int HP; //current health points a character has
	protected String name, className;  //the name of the character
	protected Bag inventory;
	protected Weapon weapon;
	protected Shield shield;
	protected float hitChance = (float) 0.75;
	
	public PlayerCharacter(String className, String playerName) {
		super(Animation.getAnimation(className));
		this.name = playerName;
		this.className = className;
		this.MAP=1;
		this.hitChance=(float) 0.75;
		shield = new Arm();
		weapon = new Fist();
		
	}
	
	
	public PlayerCharacter(String className, String playerName, int dmg, float hitChance) {
		super(Animation.getAnimation(className));
		this.name = playerName;
		this.className = className;
		this.MAP=dmg;
		this.hitChance=hitChance;
		shield = new Arm();
		weapon = new Fist();
	}
	
    public void move(Direction direction) {   //  			(move in a direction multiple space(s)
    	//need to update
    	/*switch (direction) {
    	case UP:yPos++;break;
    	case RIGHT:xPos++;break;
    	case DOWN:yPos--;break;
    	case LEFT:xPos--;break;
    	}*/
    }
    public void pickup(Item item) {			//		(pickup a visible item)
    	inventory.addItem(item);
    }
    public void drop(Item item) {			//(drop an item at your current location)
    	new ItemEntity(inventory.removeItem(item)); //Needs a world to put it in
    }
    public abstract void attack(PlayerCharacter name);		//	(attack another character)
    public abstract void attack(PlayerCharacter name, Weapon w);	//(attack a character with an item)
    public void changeHealth(double dmg) { 		//Get damaged or healed by a spell or an attack
    	HP += dmg;
    }
    public double getHP() {
    	return HP;
    }
    public abstract void defend();				//(defend an attack)
    public abstract void defend(Shield s);	//		(defend an attack with an item)
    public Bag getBagContents() {			//(return all the contents in the character�s person)
    	return inventory;
    }
    public String getLocation() {			//	(return�s the current character�s room location as X, Y)
    	return "PlayerCharacter getLocation()";
    }
    public void changeMAP(int c) {
    	MAP += c;
    }
    public void changeMDP(int c){
    	MDP += c;
    }

    public Shield getShield() {
    	return shield;
    }
    public Weapon getWeapon() {
    	return weapon;
    }
    
    public String getClassName() {
    	return this.className;
    }
    
    public String getPlayerName() {
    	return this.name;
    }
    
    public String toString() {
    	return name + "| CLASS: " + className + " | HP: " + HP + " | MAP: " + MAP + " | MDP: " + MDP + " | WEAPON: " + weapon.toString() + " | SHIELD: " + shield.toString();
    }
    
    public void render(Graphics g, int xo, int yo) {
    	if(this.a == null)
    		a = Animation.getAnimation(className);
    	//This is because the animation is lost in serialization
    	super.render(g, xo, yo);
    }
    
    
}

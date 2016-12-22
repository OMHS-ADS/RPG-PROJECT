package rpg.entity;
import java.awt.Graphics;

import rpg.container.Bag;
import rpg.item.Item;
import rpg.item.Shield;
import rpg.item.Weapon;
import rpg.util.Direction;

public abstract class PlayerCharacter extends Entity {
    protected int MAP; //maximum attack points of the character excluding other items
	protected int MDP; //maximum defense points of the character excluding other items
	protected int HP; //current health points a character has
	protected String name;  //the name of the character
	protected int xPos, yPos; //location of the character
	protected Bag inventory;
	protected Weapon weapon;
	protected Shield shield;
	protected float hitChance = (float) 0.75;
	
	public PlayerCharacter() {
		this.MAP=1;
		this.hitChance=(float) 0.75;
	}
	public PlayerCharacter(int dmg, float hitChance) {
		this.MAP=dmg;
		this.hitChance=hitChance;
	}
	
    public void move(Direction direction) {   //  			(move in a direction multiple space(s)
    	switch (direction) {
    	case UP:yPos++;break;
    	case RIGHT:xPos++;break;
    	case DOWN:yPos--;break;
    	case LEFT:xPos--;break;
    	}
    }
    public void pickup(Item item) {			//		(pickup a visible item)
    	inventory.addItem(item);
    }
    public void drop(Item item) {			//(drop an item at your current location)
    	new ItemEntity(inventory.removeItem(item),xPos,yPos); //Needs a world to put it in
    }
    public abstract void attack(PlayerCharacter name);		//	(attack another character)
    public abstract void attack(PlayerCharacter name, Weapon w);	//(attack a character with an item)
    public  void changeHealth(double dmg) { 		//Get damaged or healed by a spell or an attack
    	HP += dmg;
    }
    public abstract void defend();				//(defend an attack)
    public abstract void defend(Shield s);	//		(defend an attack with an item)
    public Bag getBagContents() {			//(return all the contents in the character’s person)
    	return inventory;
    }
    public String getLocation() {			//	(return’s the current character’s room location as X, Y)
    	return xPos + "," + yPos;
    }
    public void changeMAP(int c) {
    	MAP += c;
    }
    public void changeMDP(int c){
    	MDP += c;
    }
    public int getX() {
    	return xPos;
    }
    public int getY() {
    	return yPos;
    }
    public Shield getShield() {
    	return shield;
    }
    public Weapon getWeapon() {
    	return weapon;
    }
    
    public void render(Graphics g, int xo, int  yo) {
    	
    }
}

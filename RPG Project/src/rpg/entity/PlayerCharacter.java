package rpg.entity;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import rpg.Tile;
import rpg.World;
import rpg.container.Bag;
import rpg.graphics.Animation;
import rpg.item.Arm;
import rpg.item.Fist;
import rpg.item.Item;
import rpg.item.Shield;
import rpg.item.Weapon;
import rpg.sounds.SoundPlayer;
import rpg.util.Constants;
import rpg.util.Damageable;
import rpg.util.Direction;

public abstract class PlayerCharacter extends AnimatedEntity implements Damageable, Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7971927940316969269L;
	protected int MAP; //maximum attack points of the character excluding other items
	protected int MDP; //maximum defense points of the character excluding other items
	protected int MAXHP; //Total health points
	protected int HP; //current health points a character has
	protected String name, className;  //the name of the character
	protected Bag inventory;
	protected Weapon weapon;
	protected Shield shield;
	protected float hitChance = (float) 0.75, baseDefend = 0.1f;
	protected int xPos, yPos;
	protected transient SoundPlayer sp;

	
	public PlayerCharacter(String className, String playerName) {
		super(Animation.getAnimation(className));
		this.name = playerName;
		this.className = className;
		this.MAP=1;
		this.hitChance=(float) 0.75;
		shield = new Arm("Arm");
		weapon = new Fist("Fist");
		xPos = 0;
		yPos = 0;
		MAXHP = 20;
		HP = 20;
		this.inventory = new Bag(new ArrayList<Item>());
		sp = new SoundPlayer();
		
	}
	
	
	public PlayerCharacter(String className, String playerName, int dmg, float hitChance) {
		super(Animation.getAnimation(className));
		this.name = playerName;
		this.className = className;
		this.MAP=dmg;
		this.hitChance=hitChance;
		shield = new Arm("Arm");
		weapon = new Fist("Fist");
		this.inventory = new Bag(new ArrayList<Item>());
		sp = new SoundPlayer();
	}
	
	public void checkSPExists() {
		if(sp == null)
			sp = new SoundPlayer();
	}
	
    public void move(Direction direction, World w) {   //  			(move in a direction multiple space(s)
    	int oldX = xPos;
    	int oldY = yPos;
    	//error checking
    	if (direction==Direction.UP && yPos==0 ||
    		direction==Direction.DOWN && yPos==Constants.WORLDMAX_Y-1 ||
    		direction==Direction.LEFT && xPos==0 ||
    		direction==Direction.RIGHT && xPos==Constants.WORLDMAX_X-1) {
    		return; //end move if it would end outside of screen
    	}
    	switch (direction) {
    	case UP:yPos--;break;
    	case RIGHT:xPos++;break;
    	case DOWN:yPos++;break;
    	case LEFT:xPos--;break;
    	}
    	Tile newTile = w.getFGTile(xPos, yPos);
    	if(newTile.getTileEntity() instanceof NullEntity){
    		w.removeEntity(this);
    		w.setTile(xPos, yPos, false, this);
    		w.setTile(oldX, oldY, false, new NullEntity());
    	}
    	else if(newTile.getTileEntity() instanceof ItemEntity){
    		w.removeEntity(this);
    		ItemEntity itemEnt = (ItemEntity)(newTile.getTileEntity());
    		pickup(itemEnt);
    		w.removeEntity(itemEnt);
    		w.setTile(xPos, yPos, false, this);
    		w.setTile(oldX, oldY, false, new NullEntity());
    	}
    	else{
    		xPos = oldX;
    		yPos = oldY;
    	}
    			
    	

    }
    public void pickup(ItemEntity item) {			//		(pickup a visible item)
    	inventory.addItem(item.getItem());
    	checkSPExists();
    	sp.playPickupSound();
    }
    public void drop(String name, World w) {			//(drop an item at your current location)
    	for(Item i: inventory.getItems()){
    		if(i.getName().toUpperCase().equals(name.toUpperCase())){
    			inventory.removeItem(i);
    			return;
    		}
    	}
    }
    
    public void drop(int invVal, World w) {
    	inventory.removeItem(invVal);
    }
    
    public void attack(World w, Direction d) {
    	//Xmod and YMod are values that represent the enemies distance from the player
    	//Tile eT = tile 1 away from player, based on direction
    	//Get the enemy(if there is one) from eT, and use it for damage calcs
    	int xMod = 0;
    	int yMod = 0;
    	//d = Direction.UP;
    	if(d == Direction.UP){
    		yMod = -1;
    	}
    	else if(d == Direction.DOWN){
    		yMod = 1;
    	}
    	else if(d == Direction.LEFT){
    		xMod = -1;
    	}
    	else{
    		xMod = 1;
    	}
    	Tile et = w.getTile(xPos + xMod, yPos + yMod, false);
    	//System.out.println(xPos + xMod + "  " + (yPos + yMod));
    	//System.out.println(et.getTileEntity().toString());
    	//Add a way to check if entity is an enemy or not
    	if(et.getTileEntity() instanceof Enemy){
    		double chance = Math.random();
    		//System.out.println(chance + "  " + hitChance);
    		if (chance < hitChance){  //if the attack works, then the enemy has a chance to defend itself
    			if(!(weapon instanceof Fist)){
    				Enemy e = (Enemy) et.getTileEntity();
    				e.defend(MAP*(weapon.getMAP()));
    				checkSPExists();
    				sp.playCriticalSound();
    				
    			}
    			else{
    				Enemy e = (Enemy) et.getTileEntity();
            		e.defend(MAP);
            		checkSPExists();
            		sp.playAttackSound();
    			}
    			
    		} else {
    			checkSPExists();
    			sp.playMissSound();
    		}
    		
    	}
    	
    	
		
    }

    public void changeHealth(double dmg) { 		//Get damaged or healed by a spell or an attack
    	HP += dmg;
    }
    public double getHP() {
    	return HP;
    }
    public void defend(int dmg) {				//(defend an attack)
    	this.changeHealth(-1 * dmg * (1 - baseDefend));
    	checkSPExists();
    	sp.playBlockSound();
    }			//(defend an attack)
   	public void defend(int dmg, Shield s) {  	//		(defend an attack with an item)
   		this.changeHealth(-1 * dmg * (1 - ((.01)*(double)shield.getDef())));
    	checkSPExists();
    	sp.playBlockSound();
    }	//		(defend an attack with an item)
    public Bag getBagContents() {			//(return all the contents in the character�s person)
    	return inventory;
    }
    
    public void equip(int invValue) {
    	System.out.println(inventory.getItems().size() + " and " + invValue);
    	if(invValue >= inventory.getItems().size())
    		return;
    	Item item = inventory.getItems().get(invValue);
    	if(item instanceof Weapon) {
    		if(!(weapon instanceof Fist)){
				inventory.addItem(weapon);
			}
			weapon = (Weapon)item;
			inventory.removeItem(item);
    	} else if (item instanceof Shield) {
    		if(!(shield instanceof Arm)){
				inventory.addItem(shield);
			}
			shield = (Shield)item;
			inventory.removeItem(item);
    	}
    }
    
    public void equip(){
    	//Placeholder until zach does some unnecessary bs with the inventory
    	//Gets an item as a string, cycles thru inventory to find matching item
    	//if one is found, replace the weapon/shield as necessary, returning the old one to the inventory
    	String itemName = JOptionPane.showInputDialog("Enter an item to equip").toUpperCase();
    	for(Item i: inventory.getItems()){
    		if(i.getDisplayName().toUpperCase().equals(itemName)){
    			if(i instanceof Weapon){
    				if(!(weapon instanceof Fist)){
        				inventory.addItem(weapon);
    				}
    				weapon = (Weapon)i;
    				inventory.removeItem(i);
    				return;
    			}
    			else if(i instanceof Shield){
    				if(!(shield instanceof Arm)){
    					inventory.addItem(shield);
    				}
    				shield = (Shield)i;
    				inventory.removeItem(i);
    				return;
    			}
    			else{
    				//You shouldnt be able to get here
    				return;
    			}
    		}
    	}
    }
    public String getLocation() {			//	(return�s the current character�s room location as X, Y)
    	return "(" + xPos + ", " + yPos + ")";
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
    
    public int getMaxHP(){
    	return MAXHP;
    }
    
    public void restoreHP(){
    	HP = MAXHP;
    }
    
    public int getX() {
    	return this.xPos;
    }
    
    public int getY() {
    	return this.yPos;
    }
    
    public void setWeapon(Weapon w) {
    	this.weapon = w;
    }
    
    public void setShield(Shield s) {
    	this.shield = s;
    }
    
    public String toString() {
    	return name + "| CLASS: " + className + " | HP: " + HP + " | MAP: " + MAP + " | MDP: " + MDP + " | WP: " + weapon.toString() + " | SH: " + shield.toString();
    }
    
    public void render(Graphics g, int xo, int yo) {
    	if(this.a == null)
    		a = Animation.getAnimation(className);
    	//This is because the animation is lost in serialization
    	super.render(g, xo, yo);
    }
    
    
    
}

package rpg.entity;
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
	protected Bag inventory;
	protected Weapon weapon;
	protected Shield shield;
	protected float hitChance = (float) 0.75;
	protected float baseDefend = (float) 0.1;
	
	public PlayerCharacter(int x, int y, String name) {
		super(x,y);
		this.MAP=2;
		this.hitChance=(float) 0.75;
		this.name = name;
	}
	public PlayerCharacter(int x, int y, String name, int dmg, float hitChance) {
		super(x,y);
		this.name=name;
		this.MAP=dmg;
		this.hitChance=hitChance;
	}
	
    public void move(Direction direction) {   //  			(move in a direction multiple space(s)
    	switch (direction) {
    	case UP:y++;break;
    	case RIGHT:x++;break;
    	case DOWN:y--;break;
    	case LEFT:x--;break;
    	}
    }
    public void pickup(Item item) {			//		(pickup a visible item)
    	inventory.addItem(item);
    }
    public void drop(Item item) {			//(drop an item at your current location)
    	new ItemEntity(inventory.removeItem(item),x,y); //Needs a world to put it in
    }
    public void attack(PlayerCharacter name) {

		//do 1 dmg default
		//default attack value, dmg = attack * modifier
		// if hits(true){
		// name.defend(dmg * defend multiplier (smaller than with shield))
		//}
		//name.defend(name.getShield()){
		//}
    	
		if (Math.random() < hitChance){  //if the attack works, then the enemy has a chance to defend itself
			if(name.getShield()==null){
				name.defend(MAP);
			} else{
				name.defend(MAP,name.getShield());
			}
		}
    }		//	(attack another character)
    public void attack(PlayerCharacter name, Weapon w) {

		//do 1 dmg default
		//default attack value, dmg = attack * modifier
		//do more dmgs based on modifier
    	
		if (Math.random()<hitChance){
			if(name.getShield()==null){
				name.defend(MAP*(w.getMAP()));
			} else{
				name.defend(MAP*(w.getMAP()),name.getShield());
			}
		}
    }	//(attack a character with an item)
    public  void changeHealth(double dmg) { 		//Get damaged or healed by a spell or an attack
    	HP += dmg;
    }
    public void defend(int dmg) {				//(defend an attack)
    	this.changeHealth((double)(dmg * (1 - baseDefend)));
    }
    public void defend(int dmg, Shield s) {  	//		(defend an attack with an item)
    	this.changeHealth((double)(dmg * (1 - s.getMAP())));
    }
    public Bag getBagContents() {			//(return all the contents in the character’s person)
    	return inventory;
    }
    public String getLocation() {			//	(return’s the current character’s room location as X, Y)
    	return x + "," + y;
    }
    public void changeMAP(int c) {
    	MAP += c;
    }
    public void changeMDP(int c){
    	MDP += c;
    }
    public Shield getShield() {
    	return shield; //Will return null if no weapon
    }
    public Weapon getWeapon() {
    	return weapon; //Will return null if no weapon
    }
}

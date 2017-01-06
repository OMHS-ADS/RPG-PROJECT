package rpg.item;
import java.io.Serializable;
import java.util.Random;




//Someone do the comments for this one too





abstract public class Item implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4228305767651958209L;
	protected int MAP; //maximum attack points the item can increase a character's attack by
    protected int DHP; //maximum defense points the item can increase a character's defense
    protected String name, displayName;  //the name of the item
    //protected boolean state; // 0 if on the ground, 1 if in character's bag
    //protected int xPos, yPos;
    //protected boolean equipped; //0 if not in use, 1 if in use
    
    
    public Item() {
        //state = false;
        //equipped = false;
    	name = "EMPTY";
    }
    
    
    public Item(String className, String displayName){
        //xPos = x;
        name = className;
        this.displayName = displayName;
        //yPos = y;
      //  MAP = MAPin;
       // DHP = DHPin;
        //equipped = false;
        //state = false;
    }
    
    //add other constructor(s) here if necessary
    
    public String toString(){ 
        String str = name + "  (" + displayName + ")";
        		   // + " : \n"
                   //+ "Equipped = " + equipped + "\n"
                   //+ "In somebody's inventory = " + state + "\n"
                   //+ "(X,Y) position = (" + xPos + ", " + yPos + ")";
        return str;
    }
    
    public String getName(){
    	return name;
    }
    
    /**
     * Return the display name for this object
     * @return
     */
    public String getDisplayName() {
    	return this.displayName;
    }

    @Deprecated                  
    public void setX(int x){}

    @Deprecated
    public void setY(int y){}

    @Deprecated
    public void setEq(boolean eq){}

    @Deprecated
    public void setState(boolean inin){}
    
    @Deprecated
    public boolean getState(){
        return false;
    }

    @Deprecated
    public boolean getEq(){
        return false;
    }
    public int getMAP(){
    	return MAP;
    }
    public static Item getRandomItem(){
    	Item[] itemTypes = new Item[3];
    	itemTypes[0] = new Sword("Sword");
    	itemTypes[1] = new Axe("Axe");
    	itemTypes[2] = new LargeShield("Large Shield");
    	itemTypes[3] = new SmallShield("Small Shield");
    	Random r = new Random();
    	int index = r.nextInt(6);
    	Item wep = itemTypes[index];
    	return wep;
    }
}
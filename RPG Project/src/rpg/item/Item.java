package rpg.item; 
//equipped & state are covered by PlayerCharacter
//xPos/yPos are covered by ItemEntity
import java.util.Random;
abstract public class Item {
    protected int MAP; //maximum attack points the item can increase a character's attack by
    protected int DHP; //maximum defense points the item can increase a character's defense
    protected String name;  //the name of the item
    //protected boolean state; // 0 if on the ground, 1 if in character's bag
    //protected int xPos, yPos;
    //protected boolean equipped; //0 if not in use, 1 if in use
    
    
    public Item() {
        //state = false;
        //equipped = false;
    }
    
    
    public Item(String nameIn){
        //xPos = x;
        name = nameIn;
        //yPos = y;
      //  MAP = MAPin;
       // DHP = DHPin;
        //equipped = false;
        //state = false;
    }
    
    //add other constructor(s) here if necessary
    
    public String toString(){ 
        String str = name;
        		   // + " : \n"
                   //+ "Equipped = " + equipped + "\n"
                   //+ "In somebody's inventory = " + state + "\n"
                   //+ "(X,Y) position = (" + xPos + ", " + yPos + ")";
        return str;
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
    	itemTypes[0] = new Sword();
    	itemTypes[1] = new Axe();
    	itemTypes[2] = new LargeShield();
    	itemTypes[3] = new SmallShield();
    	Random r = new Random();
    	int index = r.nextInt(6);
    	Item wep = itemTypes[index];
    	return wep;
    }
}
package rpg.item;
abstract public class Shield extends Item {
	public Shield(){
		super();
	}
	
	public Shield(String className, String displayName){
		super(className, displayName);
	}
	
	public String toString(){
		String str = displayName + "- "
				   //+ "Equipped = " + equipped + "\n"
				   //+ "In somebody's inventory = " + state + "\n"
				   + "DP: " + DHP;// + "\n"
				   //+ "(X,Y) position = (" + xPos + ", " + yPos + ")";
		return str;
	}
	
	protected void setDefense(int defense){
		DHP = defense;
	}
	public int getDef(){
		return DHP;
	}
}
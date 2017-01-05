package rpg.item;
abstract public class Shield extends Item {
	public Shield(){
		super();
	}
	
	public Shield(String name){
		super(name);
	}
	
	public String toString(){
		String str = name + "- "
				   //+ "Equipped = " + equipped + "\n"
				   //+ "In somebody's inventory = " + state + "\n"
				   + "DP: " + DHP;// + "\n"
				   //+ "(X,Y) position = (" + xPos + ", " + yPos + ")";
		return str;
	}
	
	protected void setDefense(int defense){
		DHP = defense;
	}
}
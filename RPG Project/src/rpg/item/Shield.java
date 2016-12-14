package rpg.item;
abstract public class Shield extends Item {
	public Shield(){
		super();
	}
	
	public Shield(String name, int x, int y){
		super(name, x, y);
	}
	
	public String toString(){
		String str = name + " : \n"
				   + "Equipped = " + equipped + "\n"
				   + "In somebody's inventory = " + state + "\n"
				   + "Defense Points : " + DHP + "\n"
				   + "(X,Y) position = (" + xPos + ", " + yPos + ")";
		return str;
	}
	
	protected void setDefense(int defense){
		DHP = defense;
	}
}
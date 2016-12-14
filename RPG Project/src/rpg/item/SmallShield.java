package rpg.item;
public class SmallShield extends Shield {

	public SmallShield(){
		super();
		super.setDefense(10);
	}
	
	public SmallShield(String name, int x, int y){
		super(name, x, y);
		super.setDefense(10);
	}
}
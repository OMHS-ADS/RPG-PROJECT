package rpg.item;
public class SmallShield extends Shield {

	public SmallShield(){
		super("small_shield_","Small Shield");
		super.setDefense(10);
	}
	
	public SmallShield(String name){
		super("small_shield",name);
		super.setDefense(10);
	}
}
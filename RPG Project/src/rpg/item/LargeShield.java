package rpg.item;


//Someone do comments for all of item
//I literally touched none of these

public class LargeShield extends Shield {

	public LargeShield() {
		super("large_shield", "Large Shield");
		super.setDefense(50);
	}	
	
	public LargeShield(String displayName) {
		super("large_shield",displayName);
		super.setDefense(50);
	}
}
package rpg.entity;

public class NullEntity extends Entity {

	protected int xPos,yPos;
	
	public NullEntity(int x, int y) {
		xPos = x;
		yPos = y;
	}
	
	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return xPos;
	}

	@Override
	public int getY() {
		return yPos;
	}

}

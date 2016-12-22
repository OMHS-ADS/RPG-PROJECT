package rpg.entity;

import java.awt.Graphics;

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

	@Override
	public void render(Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		
	}

}

package rpg.entity;

import java.awt.Graphics;

import rpg.item.Shield;
import rpg.item.Weapon;

public class hanzo extends Enemy{
	public hanzo(){
		this.HP = 200;
		this.MAP = 125;
		this.MDP = 0;
		this.hitChance = (float) .7999;
	}

	@Override
	public void move(int direction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attack(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void defend() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void render(Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		
	}
}


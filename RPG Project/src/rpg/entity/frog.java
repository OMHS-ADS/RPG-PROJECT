package rpg.entity;

import java.awt.Graphics;

import rpg.item.Shield;
import rpg.item.Weapon;

public class frog extends Enemy{
	public frog(){
		this.HP = 15;
		this.MAP = 3;
		this.MDP = 2;
		this.hitChance = (float) 0.5;
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


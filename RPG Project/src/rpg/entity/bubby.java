package rpg.entity;

import java.awt.Graphics;

import rpg.item.Shield;
import rpg.item.Weapon;

public class bubby extends Enemy{
	public bubby(){
		this.HP = 300;
		this.MAP = 5;
		this.MDP = 30;
		this.hitChance = (float) 0.6;
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

	@Override
	public void changeHealth(double dmg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attack(Entity target) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void defend(int dmg) {
		// TODO Auto-generated method stub
		
	}
}


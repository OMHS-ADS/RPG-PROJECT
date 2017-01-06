package rpg.entity;

import java.awt.Color;
import java.awt.Graphics;

import rpg.Tile;
import rpg.graphics.Animation;
import rpg.util.Damageable;

/**
 * The Enemy class, which is the highest level class for an Enemy in the game.
 * @see Entity
 *
 */
public abstract class Enemy extends AnimatedEntity implements Damageable {
	 	
		public Enemy(Animation a) {
	 		super(a);
	 		maxHp = 10;
	 		HP = maxHp;
	 	}
		
	 	protected int MAP; //maximum attack points of the character excluding other items
		protected int MDP; //maximum defense points of the character excluding other items
		protected int HP; //current health points a character has
		protected String name;  //the name of the character
		protected int xPos, yPos; //location of the character
		protected int maxHp;
		protected boolean boss;
		protected float hitChance = (float) 0.75, baseDefend = 0.1f;

		
	    public abstract void move(int direction);   //  			(move in a direction multiple space(s)
	    //public abstract void pickup(String item);	//		(pickup a visible item)
	    //public abstract void drop(String item);			//(drop an item at your current location)
	    public abstract void attack(Entity target);		//	(attack another character)
	    //public abstract void attack(String name, Weapon w);	//(attack a character with an item)
	    public abstract void defend(int dmg);				//(defend an attack)
	    //public abstract void defend(Shield s);	//		(defend an attack with an item)
	    public abstract String getLocation();			//	(return�s the current character�s room location as X, Y)
	    
	    public int getMaxHP() {
	    	return this.maxHp;
	    }
	    
	    @Override
	    public double getHP() {
	    	return this.HP;
	    }
	    
	    public void render(Graphics g, int xo, int yo) {
	    	
	    		//a = Animation.getAnimation(className);
	    	//This is because the animation is lost in serialization
	    	super.render(g, xo, yo);
	    	g.setColor(Color.WHITE);
			int maxBarW = 40;
			int maxHP = this.getMaxHP();
			int cHP = (int)this.getHP();
			int barW = (int)(((double)cHP/(double)maxHP) * maxBarW);
			//System.out.println(barW);
			if(barW > maxBarW)
				return;
			g.fillRect(xo+(Tile.TILE_SIZE/2) - (maxBarW/2)-1, yo+Tile.TILE_SIZE - (13), maxBarW+2, 12);

			g.setColor(Color.BLUE);
			g.fillRect(xo+(Tile.TILE_SIZE/2) - (maxBarW/2), yo+Tile.TILE_SIZE - (12), barW, 10);
	    }
	    

}

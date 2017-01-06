package rpg.entity;

import java.awt.Color;
import java.awt.Graphics;

import rpg.Tile;
import rpg.World;
import rpg.graphics.Animation;
import rpg.util.ArrayValue2D;
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

		
	   
	    

		public void move(PlayerCharacter pc, World currentWorld) {
			ArrayValue2D ePos = currentWorld.getEntities().get(this);
			ArrayValue2D pPos = currentWorld.getEntities().get(pc);
			int xDiff = ePos.getX() - pPos.getX();
			int yDiff = ePos.getY() - pPos.getY();
			int absXDiff = Math.abs(xDiff);
			int absYDiff = Math.abs(yDiff);
			int targetX = 0;
			int targetY = 0;
			Tile newTile;
			if(absXDiff > absYDiff){
				if(xDiff > 0){
					newTile = currentWorld.getFGTile(ePos.getX()-1, ePos.getY());
					targetX = ePos.getX()-1;
					targetY = ePos.getY();
				}
				else{
					newTile = currentWorld.getFGTile(ePos.getX()+1, ePos.getY());
					targetX = ePos.getX()+1;
					targetY = ePos.getY();
				}
			}
			else{
				if(yDiff > 0){
					newTile = currentWorld.getFGTile(ePos.getX(), ePos.getY()-1);
					targetY = ePos.getY()-1;
					targetX = ePos.getX();
				}
				else{
					newTile = currentWorld.getFGTile(ePos.getX(), ePos.getY()+1);
					targetY = ePos.getY()+1;
					targetX = ePos.getX();
				}
			}
			if(newTile.getTileEntity() instanceof NullEntity){
	    		currentWorld.removeEntity(this);
	    		currentWorld.setTile(ePos.getX(), ePos.getY(), false, new NullEntity());
	    		currentWorld.setTile(targetX, targetY, false, this);
	    		
	    	}
			
			
			
			
		}


		public void attack(Entity target) {
			PlayerCharacter player = ((PlayerCharacter)target);
			if(player.getShield() == null){
				player.defend(MAP);
			}
			else{
				player.defend(MAP, player.getShield());
			}
			
		}

		public void changeHealth(double dmg) { 		//Get damaged or healed by a spell or an attack
	    	HP += dmg;
	    	//System.out.println(HP);
	    }
		
		public void defend(int dmg) {				//(defend an attack)
	    	this.changeHealth((double)(dmg * (1 - baseDefend)) * (-1));
	    }

		public String getLocation() {
			// TODO Auto-generated method stub
			return null;
		}





	    public int getMaxHP() {
	    	return this.maxHp;
	    }
	    

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

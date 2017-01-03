package rpg;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import rpg.entity.Entity;
import rpg.entity.NullEntity;
import rpg.entity.decorative.Grass;
import rpg.entity.decorative.Tree;
import rpg.util.ArrayValue2D;
import rpg.util.Constants;

public class World implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4729725033189824197L;
	private Tile[][] foreground;
	private Tile[][] background;
	//This is so we can getLocation without having to search every value of the foreground. Anything to do with adding or removing an
	//entity from the world should be done through world, and no where else.
	private HashMap<Entity, ArrayValue2D> knownEntities;

	public World() {
		knownEntities = new HashMap<Entity,ArrayValue2D>();
		background = new Tile[Constants.WORLDMAX_X][Constants.WORLDMAX_Y];
		foreground = new Tile[Constants.WORLDMAX_X][Constants.WORLDMAX_Y];
		for (int r = 0; r < background.length; r++) {
			for(int c = 0; c < background[c].length; c++) {
				background[r][c] = new Tile(new Grass());
			}
		}
		for (int r = 0; r < foreground.length; r++) {
			for(int c = 0; c < foreground[c].length; c++) {
				foreground[r][c] = new Tile(new NullEntity());
			}
		}
		
		
	}
	
	
	public Tile getFGTile(int x, int y) {
		return foreground[x][y];
	}
	
	public Tile getFGTile(ArrayValue2D v) {
		return getFGTile(v.getX(),v.getY());
	}
	
	public Tile getBGTile(int x, int y) {
		return foreground[x][y];
	}
	
	public Tile getBGTile(ArrayValue2D v) {
		return getBGTile(v.getX(),v.getY());
	}
	
	public void setBGTile(Tile t, ArrayValue2D v) {
		setBGTile(t,v.getX(),v.getY());
	}
	
	public void setFGTile(Tile t, ArrayValue2D v) {
		setFGTile(t,v.getX(),v.getY());
	}
	

	public void setBGTile(Tile t, int r, int c) {
		background[r][c] = t;
	}

	public void setFGTile(Tile t, int r, int c) {
		foreground[r][c] = t;
		knownEntities.put(t.getTileEntity(), new ArrayValue2D(r,c));
	}
	
	public void setTile(int r, int c, boolean isBG, Entity e){
		if(isBG){
			background[r][c].setEntity(e);
			knownEntities.put(e, new ArrayValue2D(r,c));
		}
		else{
			foreground[r][c].setEntity(e);
			knownEntities.put(e, new ArrayValue2D(r,c));
		}
	}
	
	public void swapTiles(int r1, int r2, int c1, int c2, boolean isBG1, boolean isBG2){
		//Glitchy, dont use unless i fix it
		Tile t1, t2;
		if(isBG1){
			t1 = background[r1][c1];
		}
		else{
			t1 = foreground[r1][c1];
		}
		
		if(isBG2){
			t2 = background[r2][c2];
		}
		else{
			t2 = foreground[r2][c2];
		}
		
		Entity e1 = t1.getTileEntity();
		Entity e2 = t2.getTileEntity();
		

		t1.setEntity(e2);
		t2.setEntity(e1);
		
		
		knownEntities.remove(e1);
		knownEntities.remove(e2);
		
		if(!(e1 instanceof NullEntity)){
			knownEntities.put(e1, new ArrayValue2D(r2,c2));
		}
		if(!(e2 instanceof NullEntity)){
			knownEntities.put(e2, new ArrayValue2D(r1,c1));
		}
		
		
		
		
		
	}
	
	public Tile getTile(int r, int c, boolean isBG){
		if(isBG){
			return background[r][c];
		}
		else{
			return foreground[r][c];
		}
	}
	
	public void removeEntity(Entity e) {
		knownEntities.remove(e);
	}

	public Tile[][] getFG() {
		return foreground;
	}

	public Tile[][] getBG() {
		return background;
	}

	
	private static World[] worlds;
	private static boolean init = false;
	
	public static void initWorlds(){
		//This is all temporary stuff bc createWorlds doesnt work
		//Fix it zach
		worlds = new World[1];
		worlds[0] = new World();
		worlds[0].setTile(5, 5, false, new Tree());
	}
	
	public static void createWorlds() {
		ArrayList<World> worldList = new ArrayList<World>();
		//This should break when it runs out of frames to find
		int worldNum = 0;
		do {
			String n = "/worlds/" + worldNum + ".rwrld";
			try {
				//System.out.println("N: " + n);
				ObjectInputStream ois = new ObjectInputStream(World.class.getResourceAsStream(n));
				World w = (World)ois.readObject();
				if(w == null) {
					throw new IllegalArgumentException("Couldn't locatate world (no more worlds)");
				} else {
					worldList.add(w);
					worldNum++;
				}
			} catch (IllegalArgumentException | IOException e) {
				//e.printStackTrace();
				//System.out.println("Broken, image size is " + images.size());
				break;
			} catch (ClassCastException | ClassNotFoundException e) {
				System.out.println("Invalid world file " + n);
			}
		} while (true);
		World[] worldArray = new World[worldList.size()];
		worldList.toArray(worldArray);
		worlds = worldArray; 
		init = true;
	}
	
	
	
	public static World getWorld(int world) {
		return worlds[world];
	}
	
}

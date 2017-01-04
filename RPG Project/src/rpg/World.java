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

/**
 * The World class provides a structure for the Game to run on. It holds tiles and entities. A world has two layers,
 * a foreground and a background. These layers are independent of each other. World contains a HashMap of every known Entity
 * in the World, as well as it's current tile value.
 * 
 * In addition to this, World also has static variables. These include <code>init</code> and <code>World[] worlds</code>. If <code>init == false,</code> then the worlds still
 * need to be loaded into memory. The variable <code>worlds</code> holds them.
 * 
 * @see Tile
 * @see Entity
 *
 */
public class World implements Serializable {

	private static final long serialVersionUID = 4729725033189824197L;
	//The foreground is a 2D array of tiles which map out the world. The background is the same.
	private Tile[][] foreground;
	private Tile[][] background;
	//This is so we can getLocation without having to search every value of the foreground. Anything to do with adding or removing an
	//entity from the world should be done through world, and no where else.
	private HashMap<Entity, ArrayValue2D> knownEntities;

	/**
	 * The default and only Constructor for World. It initializes the background with a new {@link Grass} {@link Tile}, and initializes the foreground with {@link NullEntity} objects.
	 */
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
	
	/**
	 * This method gets an element in the foreground array using an x and y value, representing row and column respectively.
	 * @param x The x value or row in the foreground array
	 * @param y The y value of column in the foreground array
	 * @return The tile at this location. If the x or y value is out of bounds, it will throw an {@link IndexOutOfBoundsException}.
	 */
	public Tile getFGTile(int x, int y) {
		return foreground[x][y];
	}
	
	/**
	 * This method gets an element in the foreground array using an {@link ArrayValue2D} object.
	 * @param v The ArrayValue2D object.
	 * @return The tile at this location. If the value is out of bounds, it will throw an {@link IndexOutOfBoundsException}.
	 * @see World#getFGTile(int, int)
	 */
	public Tile getFGTile(ArrayValue2D v) {
		return getFGTile(v.getX(),v.getY());
	}
	
	/**
	 * This method gets an element in the background array using an x and y value, representing row and column respectively.
	 * @param x The x value or row in the background array
	 * @param y The y value of column in the background array
	 * @return The tile at this location. If the x or y value is out of bounds, it will throw an {@link IndexOutOfBoundsException}.
	 */
	public Tile getBGTile(int x, int y) {
		return foreground[x][y];
	}
	
	/**
	 * This method gets an element in the background array using an {@link ArrayValue2D} object.
	 * @param v The ArrayValue2D object.
	 * @return The tile at this location. If the value is out of bounds, it will throw an {@link IndexOutOfBoundsException}.
	 * @see World#getBGTile(int, int)
	 */
	public Tile getBGTile(ArrayValue2D v) {
		return getBGTile(v.getX(),v.getY());
	}
	
	/**
	 * This method sets the given tile at the {@link ArrayValue2D} location using the background array.
	 * @param t The tile given
	 * @param v The position for placement
	 */
	public void setBGTile(Tile t, ArrayValue2D v) {
		setBGTile(t,v.getX(),v.getY());
	}
	
	/**
	 * This method sets the given tile at the {@link ArrayValue2D} location using the foreground array.
	 * @param t The tile given
	 * @param v The position for placement
	 */
	public void setFGTile(Tile t, ArrayValue2D v) {
		setFGTile(t,v.getX(),v.getY());
	}
	

	/**
	 * This method sets the given tile at the {@link ArrayValue2D} location in the background array using row and column values.
	 * @param r The row / x value.
	 * @param c The column / y value.
	 * @param t The tile placed.
	 */
	public void setBGTile(Tile t, int r, int c) {
		background[r][c] = t;
	}

	/**
	 * This method sets the given tile at the {@link ArrayValue2D} location in the foreground array using row and column values.
	 * @param r The row / x value.
	 * @param c The column / y value.
	 * @param t The tile placed.
	 */
	public void setFGTile(Tile t, int r, int c) {
		foreground[r][c] = t;
		knownEntities.put(t.getTileEntity(), new ArrayValue2D(r,c));
	}
	
	/**
	 * Not to be confused with {@link #setFGTile(Tile, int, int)} or {@link #setBGTile(Tile, int, int)}, this method sets the <strong>{@link Entity}</strong> for the specific Tile.
	 * @param r The row or x value of the desired tile
	 * @param c The column or y value of the desired tile
	 * @param isBG a boolean dictating if this is a background tile. If true, then it is a background tile.
	 * @param e The entity being placed in the specified tile.
	 * @see Tile
	 */
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
	
	/**
	 * This method swaps two Tiles with each other. The tiles can be in different layers, and this will still work.
	 * @param r1 The row / x value for the first tile
	 * @param r2 The row / x value for the second tile
	 * @param c1 The column / y value for the first tile
	 * @param c2 The column / y value for the second tile
	 * @param isBG1 The boolean value for whether the first tile is a background tile or not
	 * @param isBG2 The boolean value for whether the second tile is a background tile or not
	 * @see Tile
	 */
	@Deprecated
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
	
	/**
	 * Get the tile at a specified location.
	 * @param r The row of this tile
	 * @param c The column of this tile
	 * @param isBG Is the tile in the background
	 * @return
	 */
	public Tile getTile(int r, int c, boolean isBG){
		if(isBG){
			return background[r][c];
		}
		else{
			return foreground[r][c];
		}
	}
	
	/**
	 * This removes an Entity from the World. This includes removing it from the Entity HashMap.
	 * @param e The Entity being removed
	 */
	public void removeEntity(Entity e) {
		ArrayValue2D v = knownEntities.remove(e);
		//Because no entities in this hash map should be in the background, I can safely assume that this entity is somewhere in the foreground.
		foreground[v.getX()][v.getY()] = new Tile(new NullEntity());
	}

	/**
	 * Get the foreground array
	 * @return The foreground array
	 */
	public Tile[][] getFG() {
		return foreground;
	}

	/**
	 * Get the background array
	 * @return The background array
	 */
	public Tile[][] getBG() {
		return background;
	}

	//This array holds all of the loaded worlds
	private static World[] worlds;
	//This variable is true when the createWorlds() method is called.
	private static boolean init = false;
	
	/**
	 * initWorlds() is a temporary solution to displaying worlds due to the {@link #createWorlds()} method needing world files to load.
 	 */
	public static void initWorlds(){
		//This is all temporary stuff bc createWorlds doesnt work
		//Fix it zach
		worlds = new World[1];
		worlds[0] = new World();
		worlds[0].setTile(5, 5, false, new Tree());
	}
	
	/**
	 * The <code>static</code> createWorlds() method loads worlds from within the /source/ folder of the jar. It continues loading worlds until it can't find another. The method places
	 * these worlds into the worlds array.
	 */
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
	
	
	/**
	 * Returns a world from the worlds array at the specified index.
	 * @param world The world index
	 * @return The world
	 */
	public static World getWorld(int world) {
		return worlds[world];
	}
	
}

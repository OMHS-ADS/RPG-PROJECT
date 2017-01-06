package rpg;

import rpg.entity.Entity;
import rpg.entity.NullEntity;

/**
 * The Tile class is used as a building block for the {@link World} class. A Tile contains one
 * {@link Entity}, which can be changed. The static {@link #TILE_SIZE} variable dictates the size of the tile for rendering purposes.
 *
 */
public class Tile {

	//The Entity in the tile.
	private Entity tilesEntity;
	//The default tile size.
	public static final int TILE_SIZE = 60;
	//Tile width: 60
	//Tile height: 60
	
	/**
	 * The Constructor for Tile which takes in one {@link Entity}, which while be kept in the tile until it is changed by {@link #setEntity(Entity)}.
	 * @param e The entity being placed in the new Tile.
	 */
	public Tile(Entity e) {
		tilesEntity = e;
	}
	
	/**
	 * The default Constructor for Tile. This constructor takes in no values and sets its entity to a {@link NullEntity}.
	 */
	public Tile(){
		tilesEntity = new NullEntity();
	}
	
	/**
	 * Returns the Entity in this Tile. A possible return result is a {@link NullEntity}, which is a placeholder for no Entity. Although it can
	 * return <code>null</code>, it never should.
	 * @return The Entity in this Tile.
	 */
	public Entity getTileEntity() {
		return tilesEntity;
	}
	
	/**
	 * Sets the Entity for this Tile.
	 * @param e The {@link Entity}.
	 */
	public void setEntity(Entity e){
		tilesEntity = e;
	}
	
}

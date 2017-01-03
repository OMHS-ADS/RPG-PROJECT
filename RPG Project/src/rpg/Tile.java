package rpg;

import rpg.entity.Entity;
import rpg.entity.NullEntity;

public class Tile {

	private Entity tilesEntity;
	public static final int TILE_SIZE = 60;
	//Tile width: 60
	//Tile height: 60
	
	public Tile(Entity e) {
		tilesEntity = e;
	}
	
	public Tile(){
		tilesEntity = new NullEntity();
	}
	
	//Can return a NullEntity which is an empty entity
	public Entity getTileEntity() {
		return tilesEntity;
	}
	
	public void setEntity(Entity e){
		tilesEntity = e;
	}
	
}

package rpg;

import rpg.entity.Entity;

public class Tile {

	private Entity tilesEntity;
	
	//Tile width: 60
	//Tile height: 60
	
	public Tile(Entity e) {
		tilesEntity = e;
	}
	
	//Can return a NullEntity which is an empty entity
	public Entity getTileEntity() {
		return tilesEntity;
	}
	
}

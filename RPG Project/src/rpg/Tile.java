package rpg;

import rpg.entity.Entity;

public class Tile {

	private Entity tilesEntity;
	
	public Tile(Entity e) {
		tilesEntity = e;
	}
	
	//Can return a NullEntity which is an empty entity
	public Entity getTileEntity() {
		return tilesEntity;
	}
	
}

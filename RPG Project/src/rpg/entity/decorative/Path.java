package rpg.entity.decorative;

import rpg.entity.StaticEntity;

/**
 * A path tile used for decorative purposes. It's different in the way that it can have an edge on a specified direction. (Top, right, bottom, left).
 *
 */
public class Path extends StaticEntity {
	
	//0 TOP
	//1 RIGHT
	//2 BOT
	//3 LEFT
	//4 = NONE
	/**
	 * Creates a Path tile with the specified edge. 0 Is top, 1 is right, 2 is bottom, and 3 is left. Selects a random variation of 4. If wanted, 4 is also a valid option which represents no edge. However 
	 * {@link Dirt} should be used instead.
	 * @see Dirt
	 * @param side The side which should have the edge.
	 */
	public Path(int side) {
		super("path_"+ side + "_"+((int)(Math.random() * 4)));
	}

}

package rpg.entity.decorative;

import rpg.entity.StaticEntity;

public class Path extends StaticEntity {
	
	//0 TOP
	//1 RIGHT
	//2 BOT
	//3 LEFT
	//4 = NONE
	public Path(int side) {
		super("path_"+ side + "_"+((int)(Math.random() * 4)));
	}

}

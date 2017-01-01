package rpg.entity.decorative;

import rpg.entity.StaticEntity;

public class Dirt extends StaticEntity {

	public Dirt() {
		super("dirt"+((int)(Math.random() * 4)));
	}
}

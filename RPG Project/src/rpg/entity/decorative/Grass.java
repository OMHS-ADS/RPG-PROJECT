package rpg.entity.decorative;

import rpg.entity.StaticEntity;

/**
 * A grass tile used for decorative purposes. Selects one of 8 variations upon creation.
 *
 */
public class Grass extends StaticEntity {

	public Grass() {
		super("grass"+((int)(Math.random() * 8)));
		// TODO Auto-generated constructor stub
	}

}

package rpg.entity.decorative;

import rpg.entity.StaticEntity;

/**
 * A dirt tile used for decorative purposes. Selects one of 4 random possible dirt textures upon creation.
 *
 *
 */
public class Dirt extends StaticEntity {

	public Dirt() {
		super("dirt"+((int)(Math.random() * 4)));
	}
}

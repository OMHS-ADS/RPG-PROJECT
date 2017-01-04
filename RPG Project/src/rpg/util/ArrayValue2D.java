package rpg.util;

/**
 * The purpose of this class is to make it easier to pass locations of 2 dimensional arrays.
 *
 */
public class ArrayValue2D {
	private final int x, y;

	/**
	 * Constructor which takes in an x / row and y / column value. They cannot be changed
	 * @param x the row
	 * @param y the column
	 */
	public ArrayValue2D(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Get the x value of this object
	 * @return the row
	 */
	public int getX() {
		return x;
	}

	/**
	 * Get the y value of this object
	 * @return the column
	 */
	public int getY() {
		return y;
	}

}

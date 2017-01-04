package rpg.util;

public enum Direction {
	UP,RIGHT,DOWN,LEFT;
	Direction() {}
	/**
	 * A direction, but a class
	 * @author Morgan
	 */
	public static class DirectionWrapper {
		private Direction d;
		public DirectionWrapper(Direction d) {
			this.d=d;
		}
		public DirectionWrapper() {}
		public void changeDirection(Direction d) {
			this.d=d;
		}
		public Direction getDirection() {
			return d;
		}
	}
}

package rpg;

public class World {
	
	private Tile[][] foreground;
	private Tile[][] background;
	
	public World(){
		System.out.println("Hello world!");
	}
	public void setBGTile(Tile t, int r, int c){
		background[r][c] = t;
	}
	public void setFGTile(Tile t, int r, int c){
		foreground[r][c] = t;
	}
}

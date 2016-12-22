package rpg;

public class World {
	
	private Tile[][] foreground;
	private Tile[][] background;
	
	public World(){
	}
	public void setBGTile(Tile t, int r, int c){
		background[r][c] = t;
	}
	public void setFGTile(Tile t, int r, int c){
		foreground[r][c] = t;
	}
	public Tile[][] getFG(){
		return foreground;
	}
	public Tile[][] getBG(){
		return background;
	}
}

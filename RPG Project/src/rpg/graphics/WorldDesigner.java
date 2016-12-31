package rpg.graphics;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import rpg.Tile;

public class WorldDesigner extends JFrame {

	private Location currentTile;
	private Tile[][] bg;
	private Tile[][] fg;
	
	public WorldDesigner() {
		currentTile = new Location(0,0);
		bg = new Tile[16][9];
		fg = new Tile[16][9];
		
	}
	
	
	public void setCurrentLocation(Location l) {
		currentTile = l;
	}
	
	
	
	class Location {
		public final int x,y;
		
		public Location(int x, int y) {
			this.x = x;
			this.y = y;
		
		}
	}
	
	class MouseWatcher extends MouseAdapter {
		
		private WorldDesigner gui;
		
		private final int yoff = 22;
		
		public MouseWatcher(WorldDesigner w) {
			gui = w;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			int xPos = e.getPoint().x/Tile.TILE_SIZE;
			int yPos = (e.getPoint().y+yoff)/Tile.TILE_SIZE;
			gui.setCurrentLocation(new Location(xPos,yPos));
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			
		}
		
		
	}
	
	
}

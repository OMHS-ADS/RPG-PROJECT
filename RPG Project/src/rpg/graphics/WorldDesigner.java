package rpg.graphics;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import rpg.Tile;
import rpg.entity.EntityEnum;

/**
 * This world designer class is unfinished and will most likely remain that way unless this project is continued.
 *
 */
public class WorldDesigner extends JFrame {

	private Location currentTile;
	private JComboBox<EntityEnum> possibleTypes;
	private Tile[][] bg;
	private Tile[][] fg;
	
	public WorldDesigner() {
		currentTile = new Location(0,0);
		bg = new Tile[16][9];
		fg = new Tile[16][9];
		possibleTypes = new JComboBox<EntityEnum>(EntityEnum.values());
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
			if((xPos < 16 && xPos >= 0) && (yPos < 9 && yPos >= 0))
				gui.setCurrentLocation(new Location(xPos,yPos));
			
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			//Switch tiles?
		}
		
		
	}
	
	
}

package rpg.graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JFrame;

import rpg.Game;
import rpg.Tile;
import rpg.World;

/**
 * GameFrame extends {@link JFrame}. It is used to display the {@link World} and everything in it.
 *
 */
public class GameFrame extends JFrame {
	
	/**
	 * Default constructor. Visible by default.
	 */
	public GameFrame() {
		super("World of Datacraft");
		setSize(16*60 + 2, 9*60 + 42);
		this.setUndecorated(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((int)(screenSize.getWidth()/2) - (int)(this.getWidth()/2), (int)(screenSize.getHeight()/2) - (int)(this.getHeight()/2));
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Renders the world of the specified game's current world.
	 * @param g The game rendered.
	 */
	public void renderWorld(Game g, Graphics graphics) {
		World w = g.getCurrentWorld();
		Tile[][] bg = w.getBG();
		Tile[][] fg = w.getFG();
		int yoffset = 20;
		//Draw bg
		for (int r = 0; r < bg.length; r++) {
			for(int c = 0; c < bg[c].length; c++) {
				bg[r][c].getTileEntity().render(graphics, r*Tile.TILE_SIZE, c*Tile.TILE_SIZE + yoffset);
			}
		}
		//Draw grid
		for (int y = 0; y <= bg[0].length; y++) {
			 graphics.drawLine(0, (y*Tile.TILE_SIZE)+yoffset,  (int)this.getSize().getWidth(), (y * Tile.TILE_SIZE) + yoffset);
		}
		for (int x = 0; x < bg.length; x++) {
			 graphics.drawLine((x * Tile.TILE_SIZE), 0+yoffset,  (x * Tile.TILE_SIZE), (int)this.getSize().getHeight());
		}
		//Draw fg
		for (int r = 0; r < fg.length; r++) {
			for(int c = 0; c < fg[c].length; c++) {
				fg[r][c].getTileEntity().render(graphics, r*Tile.TILE_SIZE, c*Tile.TILE_SIZE + yoffset);
			}
		}
		
	}

	

}

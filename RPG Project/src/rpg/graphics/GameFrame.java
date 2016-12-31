package rpg.graphics;

import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import rpg.Game;
import rpg.Tile;
import rpg.World;

public class GameFrame extends JFrame {
	
	
	public GameFrame() {
		super("World of Datacraft");
		setSize(16*60 + 1, 9*60 + 23);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	public void renderWorld(Game g) {
		BufferedImage bi = new BufferedImage((int)this.getSize().getWidth(),(int)this.getSize().getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
		World w = g.getCurrentWorld();
		Tile[][] bg = w.getBG();
		Tile[][] fg = w.getFG();
		int yoffset = 22;
		//Draw bg
		for (int r = 0; r < bg.length; r++) {
			for(int c = 0; c < bg[c].length; c++) {
				bg[r][c].getTileEntity().render(bi.getGraphics(), r*Tile.TILE_SIZE, c*Tile.TILE_SIZE + yoffset);
			}
		}
		//Draw grid
		for (int y = 0; y <= bg[0].length; y++) {
			 bi.getGraphics().drawLine(0, (y*Tile.TILE_SIZE)+yoffset,  (int)this.getSize().getWidth(), (y * Tile.TILE_SIZE) + yoffset);
		}
		for (int x = 0; x < bg.length; x++) {
			 bi.getGraphics().drawLine((x * Tile.TILE_SIZE), 0+yoffset,  (x * Tile.TILE_SIZE), (int)this.getSize().getHeight());
		}
		//Draw fg
		for (int r = 0; r < fg.length; r++) {
			for(int c = 0; c < fg[c].length; c++) {
				fg[r][c].getTileEntity().render(bi.getGraphics(), r*Tile.TILE_SIZE, c*Tile.TILE_SIZE + yoffset);
			}
		}
		this.getGraphics().drawImage(bi, 0, 0, null);
		
	}

	

}

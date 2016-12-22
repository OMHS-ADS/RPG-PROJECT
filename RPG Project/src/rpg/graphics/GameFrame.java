package rpg.graphics;

import javax.swing.JFrame;

import rpg.World;

public class GameFrame extends JFrame {
	
	public GameFrame() {
		super("World of Datacraft");
		setSize(16*60, 9*60 + 20);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	
	
	public void updateFrame(World w) {
		//Do update frame
		
		
	}

}

package rpg;

import java.util.ArrayList;

import rpg.graphics.GameFrame;

public class Game {

	private World currentWorld;
	
	//Nice steady 16 fps
	public static void main(String[] args) {
		Game g = new Game();
		g.start();
	}
	
	
	
	
	
	public Game() {
		currentWorld = World.getWorld(0);
	}
	
	public void start() {
		GameFrame title = new GameFrame();
		title.setVisible(true);
		int TICK = 16;
		final int nsPerTick = (int) 1e9 / TICK;
		double timePassed = 0;
		double currentTime = System.nanoTime();
		double lastTime = System.nanoTime();

		double nsPassed = 0;
		while (title.isVisible()) {
			currentTime = System.nanoTime();
			nsPassed += currentTime - lastTime;
			timePassed += (currentTime - lastTime) / nsPerTick;

			if (timePassed >= 1) {
				// Tick!!!
				title.renderWorld(this);
				timePassed -= 1;
			}

			lastTime = currentTime;
		}
	}
	// ArrayList<World> worlds = new ArrayList<World>();
	// worlds.add(new World());
	
	public World getCurrentWorld() {
		return currentWorld;
	}

}

package rpg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import rpg.entity.Dwarf;
import rpg.entity.Elf;
import rpg.entity.Goblin;
import rpg.entity.Human;
import rpg.entity.Ogre;
import rpg.entity.PlayerCharacter;
import rpg.graphics.GameFrame;
import rpg.util.Direction;

public class Game {

	private World currentWorld;
	private static final String[] classList = new String[] {"HUMAN","ELF","DWARF","GOBLIN","OGRE"};
	public static final String playerDir = System.getProperty("user.home") + "/ADS/RPG/PlayerFiles/";
	private PlayerCharacter localPlayer;
	//Nice steady 16 fps
	public static void main(String[] args) {
		Game g = new Game();
		g.createPlayer();
		g.start();
	}
	
	
	
	
	
	public Game() {
		ArrayList<World> worlds = new ArrayList<World>();
		worlds.add(new World());
	}
	
	public PlayerCharacter loadPlayer(File f) throws Exception {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
		PlayerCharacter ret = (PlayerCharacter)ois.readObject();
		ois.close();
		return ret;
	}
	
	public void createPlayer() {
		localPlayer = null;
		String playerName = JOptionPane.showInputDialog("Enter your player name. If it exists, your character will be loaded. If not, a new character will be created");
		File f = new File(Game.playerDir + playerName + ".rplr");
		boolean loadedSave = false;
		if(f.exists()) {
			try {
				localPlayer = loadPlayer(f);
				loadedSave = true;
			} catch (Exception e) {
				e.printStackTrace();
				localPlayer = null;
				loadedSave = false;
			}
		} else {
			File path = new File(f.getParentFile().getPath());
			path.mkdirs();
			//System.out.println("aa");
		}
		if(!loadedSave) {
			String playerClass = "";
			do { 
				playerClass = JOptionPane.showInputDialog("Choose your class | " + getClassString());
			} while (!isValidClass(playerClass));
			playerClass = playerClass.toLowerCase();
			switch(playerClass) {
				case "ogre":
					localPlayer = new Ogre(playerName);
					break;
				case "human":
					localPlayer = new Human(playerName);
					break;
				case "elf":
					localPlayer = new Elf(playerName);
					break;
				case "goblin":
					localPlayer = new Goblin(playerName);
					break;
				case "dwarf":
					localPlayer = new Dwarf(playerName);
					break;
				default:
					localPlayer = new Human(playerName);
			}
			savePlayer(localPlayer);
		}
		JOptionPane.showMessageDialog(null, localPlayer.toString());
	}
	
	/**
	 * 
	 * @param p
	 * @return Success
	 */
	public boolean savePlayer(PlayerCharacter p) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(Game.playerDir + p.getPlayerName() + ".rplr")));
			oos.writeObject(p);
			oos.flush();
			oos.close();
			System.out.println("Player " + p.getPlayerName() + " saved.");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public String getClassString() {
		String r = "";
		for (String s : classList) {
			r+= "  " + s + "  ";
		}
		return r;
	}
	
	public boolean isValidClass(String s) {
		for(String str : classList) {
			if(str.equalsIgnoreCase(s))
				return true;
		}
		return false;
	}
	
	private void startRenderThread(GameFrame title, Game g) {
		Thread t = new Thread() { 
			@Override
			public void run() {
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
						title.renderWorld(g);
						timePassed -= 1;
					}

					lastTime = currentTime;
				}
			}
		};
		t.start();
	}
	
	public void start() {
		GameFrame title = new GameFrame();
		title.setVisible(true);
		startRenderThread(title, this);
		boolean alive = true;
		boolean notwon = true;
		currentWorld = World.getWorld(0);
		while(alive && notwon){
			if(localPlayer.getCurrentHP() <= 0){
				alive = false;
			}
			else{
				doPlayerTurn();
			}
			
		}
		if(alive == false){
			//Losing stuff here, close game maybe
		}
		else{
			//Winning stuff here
		}
	}

	
	public void doPlayerTurn(){
		String action = getAction();
		Direction direction;
		if(action.toUpperCase().equals("ATTACK")){
			direction = getDir();
		}
		else if(action.toUpperCase().equals("ITEM")){
			
		}
		else if(action.toUpperCase().equals("MOVE")){
			direction = getDir();
			localPlayer.move(direction, currentWorld);
			
		}
	}
	public void doEnemyTurn(){
		
	}
	
	public Direction getDir(){
		String dir = "";
		while(!dir.equals("UP") && !dir.equals("DOWN") && !dir.equals("LEFT") && !dir.equals("RIGHT")){
			dir = JOptionPane.showInputDialog("Enter a direction(UP, DOWN, LEFT, RIGHT):").toUpperCase();
		}
		if (dir.equals("UP")){
			return Direction.UP;
		}
		else if(dir.equals("RIGHT")){
			return Direction.RIGHT;
		}
		else if(dir.equals("LEFT")){
			return Direction.LEFT;
		}
		else{
			return Direction.DOWN;
		}
	}
	public String getAction(){
		String action = ""; 
		while (!action.equals("ATTACK") && !action.equals("ITEM") && !action.equals("MOVE")){
			action = JOptionPane.showInputDialog("Enter an action(MOVE, ATTACK, ITEM):").toUpperCase();
		}
		return action;
	}
	
	public World getCurrentWorld() {
		return currentWorld;
	}

}

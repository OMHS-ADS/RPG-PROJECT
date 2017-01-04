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

import rpg.container.Bag;
import rpg.entity.Dwarf;
import rpg.entity.Elf;
import rpg.entity.Enemy;
import rpg.entity.Entity;
import rpg.entity.Goblin;
import rpg.entity.Human;
import rpg.entity.Ogre;
import rpg.entity.PlayerCharacter;
import rpg.entity.decorative.*;
import rpg.graphics.GameFrame;
import rpg.item.Fist;
import rpg.item.Item;
import rpg.util.ArrayValue2D;
import rpg.util.Direction;

/**
 * The Game class contains the main method, which is the where the game is run. It contains the current world, and the current player.
 *
 */
public class Game {

	//The current world
	private World currentWorld;
	//The list of different possible classes
	private static final String[] classList = new String[] {"HUMAN","ELF","DWARF","GOBLIN","OGRE"};
	//The player save file directory
	public static final String playerDir = System.getProperty("user.home") + "/ADS/RPG/PlayerFiles/";
	//The current player
	private PlayerCharacter localPlayer;
	

	public static void main(String[] args) {
		Game g = new Game();
		g.createPlayer();
		g.start();
	}
	
	
	
	
	/**
	 * The game
	 */
	public Game() {
		World.initWorlds();
	}
	




	/**
	 * This method loads the player from a {@link File} present. If the file does not exist or it is a corrupted file, this method will throw an Exception.
	 * @param f The file
	 * @return The player file
	 * @throws Exception IOException, ClassCastException, FileNotFoundException, ClassNotFoundException...
	 */
	public PlayerCharacter loadPlayer(File f) throws Exception {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
		PlayerCharacter ret = (PlayerCharacter)ois.readObject();
		ois.close();
		return ret;
	}
	
	/**
	 * The create player method creates a player for the game. It will ask the user to input their username. If a file with that name exists,
	 * it will load it as a player. If that fails or it does not exist, it will assist the player in creating a new {@link PlayerCharacter} for them.
	 */
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
	 * This method saves a player to a file. The file location will be the default directory listed in Game, and it's name will be the {@link PlayerCharacter#getPlayerName()} + ".rplr"
	 * @param p The {@link PlayerCharacter}
	 * @return Success Will return true if the player is successfully saved, false otherwise.
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
	
	/**
	 * Create a String from the default class list in Game.
	 * @return A {@link String} of all the classes.
	 */
	public String getClassString() {
		String r = "";
		for (String s : classList) {
			r+= "  " + s + "  ";
		}
		return r;
	}
	
	/**
	 * This method validates whether a string matches one of the available class names. If not, it will return false. This ignores case.
	 * @param s The input String
	 * @return Returns true if the String matches the valid class.
	 */
	public boolean isValidClass(String s) {
		for(String str : classList) {
			if(str.equalsIgnoreCase(s))
				return true;
		}
		return false;
	}
	
	/**
	 * The startRenderThread method creates a new {@link Thread} specifically dedicated to rendering the game. It renders one frame every 1/16th of a second.
	 * @param title The {@link GameFrame} used in the game.
	 * @param g The {@link Game} used for gathering render data.
	 */
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
	
	/**
	 * This method initializes and starts the game. Once called, it is assumed it will not be called again.
	 */
	public void start() {
		GameFrame title = new GameFrame();
		title.setVisible(true);
		startRenderThread(title, this);
		boolean alive = true;
		boolean notwon = true;
		boolean worldWon = false;
		int worldNum = 0;
		currentWorld = World.getWorld(0);
		currentWorld.setTile(0, 0, false, localPlayer);
		while(alive && notwon){
			currentWorld = World.getWorld(worldNum);
			if(worldWon){
				worldNum++;
				localPlayer.restoreHP();
				currentWorld = World.getWorld(worldNum);
				currentWorld.setTile(0, 0, false, localPlayer);
			}
			if(localPlayer.getCurrentHP() <= 0){
				alive = false;
			}
			else{
				doPlayerTurn();
				for (Entity e : currentWorld.getEntities().keySet()) {
					if(e instanceof Enemy){
						doEnemyTurn((Enemy)e);
					}
				}
			}
			
		}
		if(alive == false){
			//Losing stuff here, close game maybe
		}
		else{
			//Winning stuff here
		}
	}

	
	/**
	 * The doPlayerTurn method accepts user input to conduct the players turn.
	 */
	public void doPlayerTurn(){
		String action = getAction();
		Direction direction;
		String allItems = "";
		
		if(action.equals("INVENTORY")){
			Bag b = localPlayer.getBagContents();
			for(Item i: b.getItems()){
				allItems = allItems + i.toString() + "\n";
					JOptionPane.showMessageDialog(null, allItems, "Inventory", JOptionPane.INFORMATION_MESSAGE);
			}
			action = getAction2();
		}

		if(action.equals("ATTACK")){
			direction = getDir();
			if(localPlayer.getWeapon() instanceof Fist){
				localPlayer.attack(currentWorld, direction);
			}
			else{
				localPlayer.weaponAttack(currentWorld, localPlayer.getWeapon(), direction);
			}
			
		}
		else if(action.equals("MOVE")){
			direction = getDir();
			localPlayer.move(direction, currentWorld);
			String loc = localPlayer.getLocation();
			JOptionPane.showMessageDialog(null, loc, "New Location", JOptionPane.INFORMATION_MESSAGE);
			
		}
		else if(action.equals("DROP")){
			String itemName = JOptionPane.showInputDialog("Enter an item to drop:").toUpperCase();
			localPlayer.drop(itemName, currentWorld);
		}
	}
	/**
	 * The doEnemyTurn method conducts AI movement and actions per turn.
	 */
	public void doEnemyTurn(Enemy e){
		if(isPlayerAdjacent(e)){
			e.attack(localPlayer);
		}
	}
	
	/**
	 * The getDir method takes in input from the user and will loop until a valid input is reached. This includes "up" "down" "left" and "right"
	 * @return The Direction from the player.
	 */
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
	
	/**
	 * The getAction method takes in input from the user to decide what action should be taken. It will continue to prompt the user until a valid response is given. Valid
	 * responses include "Attack" "inventory" "move" and "drop"
	 * @return The action, as a {@link String}
	 */
	public String getAction(){
		String action = ""; 
		while (!action.equals("ATTACK") && !action.equals("INVENTORY") && !action.equals("MOVE") && !action.equals("DROP")){
			action = JOptionPane.showInputDialog("Enter an action(MOVE, ATTACK, INVENTORY, DROP):").toUpperCase();
		}
		return action;
	}
	
	/**
	 * The getAction2 does the same thing as {@link #getAction()}?
	 * @return
	 */
	public String getAction2(){
		String action = ""; 
		while (!action.equals("ATTACK") && !action.equals("MOVE") && !action.equals("DROP")){
			action = JOptionPane.showInputDialog("Enter an action(MOVE, ATTACK, DROP):").toUpperCase();
		}
		return action;
	}
	
	/**
	 * The getCurrentWorld method returns the currentWorld that the game is operating on.
	 * @return The current {@link World}.
	 */
	public World getCurrentWorld() {
		return currentWorld;
	}
	
	public boolean isPlayerAdjacent(Entity e){
		ArrayValue2D ePos = currentWorld.getEntities().get(e);
		ArrayValue2D pPos = currentWorld.getEntities().get(localPlayer);
		if(ePos.getX() == pPos.getX()){
			if(ePos.getY() == pPos.getY() + 1 || ePos.getY() == pPos.getY() - 1){
				return true;
			}
		}
		else if(ePos.getY() == pPos.getY()){
			if(ePos.getX() == pPos.getX() + 1 || ePos.getX() == pPos.getX() - 1){
				return true;
			}
		}
		return false;
	}

}

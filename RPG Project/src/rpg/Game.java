package rpg;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import rpg.entity.Goblin;
import rpg.entity.Human;
import rpg.entity.Ogre;
import rpg.entity.PlayerCharacter;
import rpg.entity.decorative.*;
import rpg.graphics.GameFrame;
import rpg.item.Item;
import rpg.util.Direction;
import rpg.util.Direction.DirectionWrapper;
import rpg.util.PlayerActions;

public class Game {

	private World currentWorld;
	private static final String[] classList = new String[] {"HUMAN","ELF","DWARF","GOBLIN","OGRE"};
	public static final String playerDir = System.getProperty("user.home") + "/ADS/RPG/PlayerFiles/";
	private PlayerCharacter localPlayer;
	private static Keyboard k;
	//Nice steady 16 fps
	public static void main(String[] args) {
		Game g = new Game();
		g.createPlayer();
		g.start();
	}
	
	
	
	
	
	public Game() {
		World.initWorlds();
		
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

	private GameFrame title;
	public void start() {
		k = this.new Keyboard();
		title = new GameFrame();
		System.out.println("TEST");
		title.setVisible(true);
		startRenderThread(title, this);
		boolean alive = true;
		boolean notwon = true;
		boolean worldWon = false;
		int worldNum = 0;
		currentWorld = World.getWorld(0);
		currentWorld.setTile(0, 0, false, localPlayer);
		title.addKeyListener(k);
		while(alive && notwon && !quit){
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
			/*else{
				doPlayerTurn();
			}*/
			
		}
		if(!alive){
			//Losing stuff here, close game maybe
			exitGame();
		}
		else{
			//Winning stuff here
		}
	}

	
	public void doPlayerTurn(PlayerActions action){
		Direction direction;
		String allItems = "";
		switch (action) {
		case ATTACK(Direction.UP):{
			localPlayer.attack(localPlayer, currentWorld, Direction.UP);
			break;
		}
		case ATTACK_RIGHT:{
			localPlayer.attack(localPlayer, currentWorld, Direction.RIGHT);
			break;
		}
		case ATTACK_DOWN:{
			localPlayer.attack(localPlayer, currentWorld, Direction.RIGHT);
			break;
		}
		case ATTACK_LEFT:{
			localPlayer.attack(localPlayer, currentWorld, Direction.DOWN);
			break;
		}
		case MOVE_UP:{
			localPlayer.move(Direction.UP, currentWorld);
			break;
		}
		case MOVE_RIGHT:{
			localPlayer.move(Direction.RIGHT, currentWorld);
			break;
		}
		case MOVE_DOWN:{
			localPlayer.move(Direction.DOWN, currentWorld);
			break;
		}
		case MOVE_LEFT:{
			localPlayer.move(Direction.LEFT, currentWorld);
			break;
		}
		case DROP: {
			String itemName = JOptionPane.showInputDialog("Enter an item to drop:").toUpperCase();
			break;
			//Needs to convert player input to item entity
			//localPlayer.drop()
		}
		case INVENTORY: {
			Bag b = localPlayer.getBagContents();
			for(Item i: b.getItems()){
				allItems = allItems + i.toString() + "\n";
					JOptionPane.showMessageDialog(null, allItems, "Inventory", JOptionPane.INFORMATION_MESSAGE);
			}
			break;
		}
		case EXIT: { //exit game with confirmation
			exitGame();
		}
		}
		doEnemyTurn();
	}
	/*
	 * Exits game with confirmation
	 */
	private boolean quit=false;
	private void exitGame() {
		int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?");
		if (confirm == JOptionPane.YES_OPTION) {
			title.dispose();
			quit=true;
		}
	}
	public void doEnemyTurn(){
		
	}
	@Deprecated
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
	@Deprecated
	public String getAction(){
		String action = ""; 
		while (!action.equals("ATTACK") && !action.equals("INVENTORY") && !action.equals("MOVE") && !action.equals("DROP") && !action.equals("EXIT")){
			
			try {
				action = JOptionPane.showInputDialog("Enter an action(MOVE, ATTACK, INVENTORY, DROP, EXIT):").toUpperCase();
				
			} catch (NullPointerException e) {}
		}
		return action;
	}
	public String getAction2(){
		String action = ""; 
		while (!action.equals("ATTACK") && !action.equals("MOVE") && !action.equals("DROP")){
			action = JOptionPane.showInputDialog("Enter an action(MOVE, ATTACK, DROP):").toUpperCase();
		}
		return action;
	}
	
	public World getCurrentWorld() {
		return currentWorld;
	}

	public class Keyboard implements KeyListener {
		
		public Keyboard() {
		}
		
		@Override
		public void keyPressed(KeyEvent e)	{
			switch (e.getKeyCode()) {
			case KeyEvent.VK_W:doPlayerTurn(PlayerActions.MOVE(new DirectionWrapper(Direction.UP)));break;
			case KeyEvent.VK_D:doPlayerTurn(PlayerActions.MOVE(new DirectionWrapper(Direction.RIGHT)));break;
			case KeyEvent.VK_S:doPlayerTurn(PlayerActions.MOVE(new DirectionWrapper(Direction.DOWN)));break;
			case KeyEvent.VK_A:doPlayerTurn(PlayerActions.MOVE(new DirectionWrapper(Direction.LEFT)));break;
			case KeyEvent.VK_I:doPlayerTurn(PlayerActions.INVENTORY);break;
			case KeyEvent.VK_ESCAPE:doPlayerTurn(PlayerActions.EXIT);break;
			case KeyEvent.VK_R:doPlayerTurn(PlayerActions.DROP);break;
			case KeyEvent.VK_T:doPlayerTurn(PlayerActions.ATTACK(new DirectionWrapper(Direction.UP)));break;
			case KeyEvent.VK_H:doPlayerTurn(PlayerActions.ATTACK(new DirectionWrapper(Direction.RIGHT)));break;
			case KeyEvent.VK_G:doPlayerTurn(PlayerActions.ATTACK(new DirectionWrapper(Direction.DOWN)));break;
			case KeyEvent.VK_F:doPlayerTurn(PlayerActions.ATTACK(new DirectionWrapper(Direction.LEFT)));break;
			case KeyEvent.VK_Q:doPlayerTurn(PlayerActions.PICKUP);break;
			}
		}
		public void keyReleased(KeyEvent e) {}
		public void keyTyped(KeyEvent e) {}
	}
	public class MouseListen implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
}

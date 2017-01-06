package rpg;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import javax.swing.JOptionPane;

import rpg.entity.Dwarf;
import rpg.entity.Elf;
import rpg.entity.Enemy;
import rpg.entity.Entity;
import rpg.entity.Goblin;
import rpg.entity.Human;
import rpg.entity.Mage;
import rpg.entity.NullEntity;
import rpg.entity.Ogre;
import rpg.entity.PlayerCharacter;
import rpg.graphics.GameFrame;
import rpg.item.Arm;
import rpg.item.Fist;
import rpg.item.Item;
import rpg.sounds.SoundPlayer;
import rpg.spell.Spell;
import rpg.util.ArrayValue2D;
import rpg.util.Direction;
import rpg.util.ImageLoader;
import rpg.util.PlayerActions;

/**
 * The Game class contains the main method, which is the where the game is run. It contains the current world, and the current player.
 *
 */
public class Game {

	private GameFrame displayWindow;
	//The current world
	private World currentWorld;
	//The list of different possible classes
	private static final String[] classList = new String[] {"HUMAN","ELF","DWARF","GOBLIN","OGRE","MAGE"};
	//The player save file directory
	public static final String playerDir = System.getProperty("user.home") + "/ADS/RPG/PlayerFiles/";
	//The current player
	private PlayerCharacter localPlayer;
	private static Keyboard k;
	private SoundPlayer sp;
	private boolean invRender;
	
	//Nice steady 16 fps
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
		sp = new SoundPlayer();
		invRender = false;
	}
	
	/**
	 * Get the game's sound player
	 * @return The sound player
	 */
	public SoundPlayer getSoundPlayer() {
		return this.sp;
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
		boolean loadedSave = false;
		boolean deadPlayer = false;
		String playerName;
		File f;
		do {
			do {
				playerName = JOptionPane.showInputDialog("Enter your player name. If it exists, your character will be loaded. If not, a new character will be created");
				System.out.println(playerName);
			} while (!containsAny(playerName,"/:*?<>|"));
		f = new File(Game.playerDir + playerName + ".rplr");
		deadPlayer = false;
		loadedSave = false;
		if(f.exists()) {
			try {
				localPlayer = loadPlayer(f);
				loadedSave = true;
				deadPlayer = false;
				if(localPlayer.getHP() <= 0) {
					JOptionPane.showConfirmDialog(null, "The character " + localPlayer.getPlayerName() + " is dead. Please make another character.");
					deadPlayer = true;
					throw new Exception("Player is dead");
				}
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
		} while(deadPlayer);
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
				case "mage":
					localPlayer = new Mage(playerName);
					break;
				default:
					localPlayer = new Human(playerName);
			}
			savePlayer(localPlayer);
		}
		JOptionPane.showMessageDialog(null, localPlayer.toString());
	}
	/**
	 * A method that checks if a & b share characters
	 * @author Morgan
	 */
	private static boolean containsAny(String a, String b) {
		for (char c1 : a.toCharArray()) {
			for (char c2 : b.toCharArray()) {
				if (c1 == c2)
					return true;
			}
		}
		return false;
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
				while (title.isVisible() && !quit) {
					currentTime = System.nanoTime();
					nsPassed += currentTime - lastTime;
					timePassed += (currentTime - lastTime) / nsPerTick;

					if (timePassed >= 1) {
						// Tick!!!
						int width = (int)(g.getDisplayWindow().getWidth());
						int height = (int)(g.getDisplayWindow().getHeight());
						BufferedImage bi = new BufferedImage(width,height, BufferedImage.TYPE_4BYTE_ABGR);
						Graphics graphics = bi.getGraphics();
						if(invRender) {
							//Draw inventory
							try {
								drawInventory(graphics);
							} catch (ConcurrentModificationException e) {
								//shhhhh
							}
							
							drawHud(graphics);
							drawBorder(graphics);
						} else {
							try {
								title.renderWorld(g, graphics);
							} catch (ConcurrentModificationException e) {
								//Do nothing
							}
								drawHud(graphics);
								drawBorder(graphics);
							}
						g.getDisplayWindow().getGraphics().drawImage(bi, 0, 0, g.getDisplayWindow().getWidth(), g.getDisplayWindow().getHeight(), null);
						graphics.dispose();
						timePassed -= 1;
					}

					lastTime = currentTime;
				}
			}
		};
		t.start();
	}
	/**
	 * Draws the border to the game window
	 * @param g
	 */
	public void drawBorder(Graphics g) {
		Color borderColor = new Color(38, 71, 124);
		g.setColor(borderColor);
		g.fillRect(0, 0, this.getDisplayWindow().getWidth(), 20);
		g.drawLine(0, 0, 0, this.getDisplayWindow().getHeight());
		g.drawLine(1, 0, 1, this.getDisplayWindow().getHeight());
		g.drawLine(this.getDisplayWindow().getWidth()-1, 0, this.getDisplayWindow().getWidth()-1, this.getDisplayWindow().getHeight());
		g.drawLine(this.getDisplayWindow().getWidth()-2, 0, this.getDisplayWindow().getWidth()-2, this.getDisplayWindow().getHeight());
		g.drawLine(0, this.getDisplayWindow().getHeight()-1, this.getDisplayWindow().getWidth(),  this.getDisplayWindow().getHeight()-1);
		g.drawLine(0, this.getDisplayWindow().getHeight()-2, this.getDisplayWindow().getWidth(),  this.getDisplayWindow().getHeight()-2);
		g.setColor(Color.red);
		g.fillRect(2, 2, 16, 16);
		g.setColor(Color.WHITE);
		g.drawLine(1, 2, 1+16, 1+16);
		g.drawLine(1, 1+16, 1+15, 2);
	}
	
	/**
	 * Draw the HUD to the window
	 * @param g The window graphics
	 */
	public void drawHud(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawImage(ImageLoader.getImage("parchment"), 1,540+21,960,19, null);
		//g.fillRect(1, 540+21, 960, 19);
		g.setColor(Color.RED);
		int maxBarW = 100;
		int maxHP = localPlayer.getMaxHP();
		int cHP = (int)localPlayer.getHP();
		int barW = (int)(((double)cHP/(double)maxHP) * maxBarW);
		if(barW > maxBarW)
			barW = maxBarW;
		g.drawRect(3, 541+20, maxBarW+1, 18);

		g.setColor(Color.GREEN);
		g.fillRect(4, 541+21, barW, 17);
		
		//This could be done better but im lazy
		g.setFont(new Font("Arial",Font.BOLD,16));
		g.setColor(Color.black);
		g.drawString(cHP + "/" + maxHP, maxBarW/4, 576);
		g.drawString(localPlayer.toString(), maxBarW + 10, 576);
	}
	
	/**
	 * Draw the inventory of the player to the given graphics
	 * @param g The graphics used
	 */
	public void drawInventory(Graphics g) {
		int yoffset = 20;
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, yoffset, (int)this.displayWindow.getWidth(), (int)this.displayWindow.getHeight());
		g.setColor(Color.white);
		for (int y = 0; y < 9; y++) {
			 g.drawLine(0, (y*Tile.TILE_SIZE)+yoffset,  (int)this.displayWindow.getSize().getWidth(), (y * Tile.TILE_SIZE) + yoffset);
		}
		for (int x = 0; x < 16; x++) {
			 g.drawLine((x * Tile.TILE_SIZE), 0+yoffset,  (x * Tile.TILE_SIZE), (int)this.displayWindow.getSize().getHeight());
		}
		ArrayList<Item> items = localPlayer.getBagContents().getItems();
		int maxX = 15;
		int maxY = 8;
		int currentX = 0;
		int currentY = 0;
		//This draws 16*9 items...
		for(Item item : items) {
			g.drawImage(ImageLoader.getImage(item.getName()), (currentX * Tile.TILE_SIZE), (currentY * Tile.TILE_SIZE) + yoffset, Tile.TILE_SIZE, Tile.TILE_SIZE, null);
			currentX++;
			if(currentX>maxX) {
				currentX=0;
				currentY++;
				if(currentY>maxY) {
					//Too many items tbh
					//Learn to manage items
					break;
				}
			}
		}
	}
	
	/**
	 * Get the window
	 * @return The window
	 */
	public GameFrame getDisplayWindow() {
		return this.displayWindow;
	}
	
	
	public void setCurrentWorld(World w) {
		this.currentWorld = w;
	}
	
	/**
	 * This method initializes and starts the game. Once called, it is assumed it will not be called again.
	 */
	public void start() {
		k = this.new Keyboard();
		MouseListen m = new MouseListen(this);
		displayWindow = new GameFrame();
		//System.out.println("TEST");
		startRenderThread(displayWindow, this);
		boolean alive = true;
		boolean notwon = true;
		boolean worldWon = false;
		int worldNum = 0;
		currentWorld = World.getWorld(0);
		currentWorld.setTile(localPlayer.getX(), localPlayer.getY(), false, localPlayer);
		displayWindow.addKeyListener(k);
		displayWindow.addMouseListener(m);
		displayWindow.addMouseMotionListener(m);
		while(alive && notwon && !quit){
			//currentWorld = World.getWorld(worldNum);
			if(worldWon){
				worldNum++;
				localPlayer.restoreHP();
				currentWorld = World.getWorld(worldNum);
				currentWorld.setTile(localPlayer.getX(), localPlayer.getY(), false, localPlayer);
			}
			//Doesn't work for me without a print statement here...?
			System.out.print("");
			if(localPlayer.getHP() <= 0){
				
				alive = false;
			}
		}
		if(!alive) {
			//Losing stuff here, close game maybe
			
			exitGame(true);
		}
		else{
			//Winning stuff here
		}
	}

	
	/**
	 * Switch the world to the specified world
	 * @param w The new World
	 */
	public void transferPlayerToWorld(World w) {
		currentWorld.removeEntity(localPlayer);
		w.getEntities().put(localPlayer, new ArrayValue2D(0,0));
		w.setTile(0, 0, false, localPlayer);
		localPlayer.setX(0);
		localPlayer.setY(0);
		currentWorld = w;
	}
	
	/**
	 * The doPlayerTurn method accepts user input to conduct the players turn.
	 */
	public void doPlayerTurn(PlayerActions action) {
		Direction direction;
		String allItems = "";
		switch (action) {
		case ATTACK_UP:{
			localPlayer.attack(currentWorld, Direction.UP);
			break;
		}
		case ATTACK_RIGHT:{
			localPlayer.attack(currentWorld, Direction.RIGHT);
			break;
		}
		case ATTACK_DOWN:{
			localPlayer.attack(currentWorld, Direction.DOWN);
			break;
		}
		case ATTACK_LEFT:{
			localPlayer.attack(currentWorld, Direction.LEFT);
			break;
		}
		case MOVE_UP:{
			localPlayer.move(Direction.UP, currentWorld, this);
			break;
		}
		case MOVE_RIGHT:{
			localPlayer.move(Direction.RIGHT, currentWorld, this);
			break;
		}
		case MOVE_DOWN:{
			localPlayer.move(Direction.DOWN, currentWorld, this);
			break;
		}
		case MOVE_LEFT:{
			localPlayer.move(Direction.LEFT, currentWorld, this);
			break;
		}
		case DROP: {
			int dropped = action.getValue();
			//String itemName = localPlayer.getBagContents().getItems().get(dropped).getName();
			//System.out.println("trying to drop " + itemName + "  " + dropped);
			localPlayer.drop(dropped, currentWorld);
			break;
			
		}
		case INVENTORY: {
			this.invRender = !this.invRender;
			break;
			/*
			Bag b = localPlayer.getBagContents();
			for(Item i: b.getItems()){
				allItems = allItems + i.toString() + "\n";
			}
			JOptionPane.showMessageDialog(null, allItems, "Inventory", JOptionPane.INFORMATION_MESSAGE);*/
			//break;
		}
		case EQUIP:{
			//localPlayer.equip();
			//System.out.println("trying to equip aaa" + action.getValue());
			localPlayer.equip(action.getValue());
			break;
		}
		case EXIT: { //exit game with confirmation
			exitGame(false);
		} case SPELL: {
			//Rip i killed morgans spell idea :(
			int spellNum = action.getValue();
			Spell cast = Spell.getSpell(spellNum);
			cast.doEffect(localPlayer);
		}
		}
		
		try {
			for (Entity e : currentWorld.getEntities().keySet()) {
				if(e instanceof Enemy){
					doEnemyTurn((Enemy)e);
				}
			}
		} catch (ConcurrentModificationException e) {
			//aaaaaa
		}
		
	}
	/*
	 * Exits game with confirmation
	 */
	private boolean quit=false;
	private void exitGame(boolean death) {
		if(death)
			sp.playDeathSound();
		int confirm = -1;
		if(death) {
			JOptionPane.showMessageDialog(null,"You have died and your character will be unaccessable due to it's death.");
			confirm = JOptionPane.YES_OPTION;
		} else
			confirm = JOptionPane.showConfirmDialog(null,"Are you sure you want to quit?", "Quitting", JOptionPane.YES_NO_OPTION);
		
			if (confirm == JOptionPane.YES_OPTION || death || confirm == JOptionPane.OK_OPTION) {
			displayWindow.dispose();
			quit=true;
			if(localPlayer.getHP() > 0) {
				savePlayer(localPlayer);
			} else {
				//rip player
				//this doesn't work
				localPlayer.getPlayerName().replaceAll(localPlayer.getPlayerName(), localPlayer.getPlayerName() + " is D E A D");
				localPlayer.getBagContents().getItems().clear();
				localPlayer.setShield(new Arm("Arm"));
				localPlayer.setWeapon(new Fist("Fist"));
				savePlayer(localPlayer);
				displayWindow.dispose();
			}
		}
	}
	/**
	 * The doEnemyTurn method conducts AI movement and actions per turn.
	 */
	public void doEnemyTurn(Enemy e){
		//Should remove enemy if dead, someone else make sure it removes the right enemy
		if(e.getHP() <= 0){
			ArrayValue2D ePos = currentWorld.getEntities().get(e);
			currentWorld.setTile(ePos.getX(), ePos.getY(), false, new NullEntity());
			currentWorld.removeEntity(e);
			return;
		}
		if(isPlayerAdjacent(e)){
			e.attack(localPlayer);
		}
	}

	@Deprecated
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
	 * Called to handle mouse movement. This is for moving the window around.
	 * @param e The Event
	 */
	public void windowMoved(MouseEvent e, int xoffset, int yoffset) {
		Point newPoint = e.getLocationOnScreen();
		this.displayWindow.setLocation((int)newPoint.getX()-xoffset,(int)newPoint.getY()-yoffset);
		//System.out.println("AAAAAA");
	}
	
	/**
	 * Get the exit button on the top bar
	 * @return The bounds
	 */
	public Rectangle getExitClickedBounds() {
		return (new Rectangle(2,2,16,16));
	}
	
	/**
	 * Called when the mouse is clicked. This is used for clicking different buttons.
	 * @param e
	 */
	public void mouseClicked(MouseEvent e) {
		if(getExitClickedBounds().contains(e.getPoint()))
			this.exitGame(false);
		if(this.invRender) {
			checkInvClick(e.getPoint(), (e.getButton() == MouseEvent.BUTTON2 || e.isControlDown()));
			//System.out.println("did thing");
		}
	}
	
	/**
	 * Drop or Equip item. Left click is equip, right click is drop.
	 * @param p
	 */
	public void checkInvClick(Point p, boolean isRightClick) {
		int xPos = (int)(p.getX()/Tile.TILE_SIZE);
		int yPos = (int)((p.getY()-20)/Tile.TILE_SIZE);
		int invPos = xPos + (16 * yPos);
		//Item item = localPlayer.getBagContents().getItems().get(invPos);
		//System.out.println(xPos + "  " + yPos + "  " + isRightClick);
		if(isRightClick) {
			PlayerActions action = PlayerActions.DROP;
			action.setValue(invPos);
			doPlayerTurn(action);
		//	System.out.println("trying to drop");
		} else {
			PlayerActions action = PlayerActions.EQUIP;
			action.setValue(invPos);
			doPlayerTurn(action);
			//System.out.println("trying to equip " + action.getValue());
		}
	}
	
	public Rectangle getTopBarBounds() {
		return new Rectangle(0,0,this.getDisplayWindow().getWidth(),20);
	}

	@Deprecated
	/**
	 * The getAction method takes in input from the user to decide what action should be taken. It will continue to prompt the user until a valid response is given. Valid
	 * responses include "Attack" "inventory" "move" and "drop"
	 * @return The action, as a {@link String}
	 */
	public String getAction(){
		String action = ""; 
		while (!action.equals("ATTACK") && !action.equals("INVENTORY") && !action.equals("MOVE") && !action.equals("DROP") && !action.equals("EXIT")){
			
			try {
				action = JOptionPane.showInputDialog("Enter an action(MOVE, ATTACK, INVENTORY, DROP, EXIT):").toUpperCase();
				
			} catch (NullPointerException e) {}
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

	public class Keyboard implements KeyListener {
		
		public Keyboard() {
		}
		
		@Override
		public void keyPressed(KeyEvent e)	{
			switch (e.getKeyCode()) {
			case KeyEvent.VK_W:doPlayerTurn(PlayerActions.MOVE_UP);break;
			case KeyEvent.VK_D:doPlayerTurn(PlayerActions.MOVE_RIGHT);break;
			case KeyEvent.VK_S:doPlayerTurn(PlayerActions.MOVE_DOWN);break;
			case KeyEvent.VK_A:doPlayerTurn(PlayerActions.MOVE_LEFT);break;
			case KeyEvent.VK_I:doPlayerTurn(PlayerActions.INVENTORY);break;
			
			//case KeyEvent.VK_E:doPlayerTurn(PlayerActions.EQUIP);break;
			case KeyEvent.VK_Q:doPlayerTurn(PlayerActions.DROP);break;
			case KeyEvent.VK_ESCAPE:doPlayerTurn(PlayerActions.EXIT);break;
			case KeyEvent.VK_1:doPlayerTurn(PlayerActions.SPELL.setValue(0));break;
			case KeyEvent.VK_2:doPlayerTurn(PlayerActions.SPELL.setValue(1));break;
			case KeyEvent.VK_3:doPlayerTurn(PlayerActions.SPELL.setValue(2));break;
			case KeyEvent.VK_4:doPlayerTurn(PlayerActions.SPELL.setValue(3));break;
			

			case KeyEvent.VK_UP:doPlayerTurn(PlayerActions.ATTACK_UP);break;
			case KeyEvent.VK_DOWN:doPlayerTurn(PlayerActions.ATTACK_DOWN);break;
			case KeyEvent.VK_LEFT:doPlayerTurn(PlayerActions.ATTACK_LEFT);break;
			case KeyEvent.VK_RIGHT:doPlayerTurn(PlayerActions.ATTACK_RIGHT);break;
			}
		}
		public void keyReleased(KeyEvent e) {}
		public void keyTyped(KeyEvent e) {}
	}
	public class MouseListen extends MouseAdapter {

		private Game g;
		private boolean topBarHeld;
		private int xoff,yoff;
		
		public MouseListen(Game g) {
			this.g= g;
			topBarHeld = false;
			
		}
		
		@Override
		public void mouseMoved(MouseEvent e) {
			
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			g.mouseClicked(e);
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			xoff = e.getX();
			yoff = e.getY();
			if(!g.getExitClickedBounds().contains(e.getPoint()) && g.getTopBarBounds().contains(e.getPoint()))
				topBarHeld = true;
		}

		
		@Override
		public void mouseDragged(MouseEvent e) {
			if(topBarHeld)
				g.windowMoved(e, xoff, yoff);
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
		public void mouseReleased(MouseEvent arg0) {
			topBarHeld = false;
		}
		
	}
}

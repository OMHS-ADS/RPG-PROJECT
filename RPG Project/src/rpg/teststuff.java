package rpg;
import java.io.*;

import rpg.item.Axe;
import rpg.item.SmallShield;
import rpg.item.Sword;

public class teststuff{
	public static void main(String [] args){
		Sword sword = new Sword("Grant-Killing Sword", 3, 4);
		System.out.println(sword);
		
		SmallShield squareHeadedGrant = new SmallShield("A really cute shield", 3, 23);
		System.out.println(squareHeadedGrant);
		
		Axe axe = new Axe();
		Axe josh = new Axe("josh", 3, 2);
		System.out.println(axe);
		axe.setState(true);
		System.out.println("State");
		System.out.println(axe.getState());
	}
}
package rpg.entity;
//kyle sluss
public abstract class Enemy {
private double health;
private int x;
private int y;
public void moveX(int X){
	x = X;
}
public void moveY(int Y){
	y = Y;
}
}

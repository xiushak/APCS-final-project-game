import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public abstract class subBoss extends Enemies{
	PApplet applet;
	PImage img;
	public subBoss(PApplet applet, PImage img, float x, float y, float speed) {
		super(applet, img, x, y, speed);
		this.applet = applet;
	}

	@Override
	public boolean hit(String bType) {
		if(hitPoint == 0)
			return false;
		if(bType.equals("END"))
			hitPoint = 0;
		else if(bType.equals("s"))
			hitPoint -= 5;
		else if(bType.equals("m"))
			hitPoint -= 10;
		else if(bType.equals("l"))
			hitPoint -= 20;
		else
			hitPoint -= 30;
		return true;
	}

	@Override
	public void move() {		
		y += speed;
	}

	@Override
	public boolean checkBounds() {
		if(0 < x || x > applet.width)
			return false;
		if(0 < y || y > applet.height)
			return false;
		return true;
	}

	@Override
	public abstract void shoot(int counter, PlayerShip ship, ArrayList<Bullet> bull);
	
	//spreaded beam
	protected void beam(String bType, PlayerShip ship, ArrayList<Bullet> bull, int[] color, float speed, float accel) {
		int degrees = (int)(Math.toDegrees(Math.atan2((double)(ship.getPosY()-y), (double)(ship.getPosX())-x)));
		bull.add(new Bullet(applet, bType, x, y, (float)(Math.random()*2) + speed, accel, degrees + (int)(Math.random()*10) - 5, color));	
	}
	//circular spread
	protected void circle(String bType, int count, ArrayList<Bullet> bull, int[] color, float speed, float accel) {
		//int rand = 0;//(int)(Math.random()*15) + 4;
		int interval = 360/count;
		for(int i = 0; i <= count; i++) {
			bull.add(new Bullet(applet, bType, x, y, speed, accel, interval*i, color));
		}
	}
	protected void spiral(String bType, int count, ArrayList<Bullet> bull, int[] color, float speed, float accel, int rate, int index) {
		int interval = 360/count;
		for(int i = 0; i <= count; i++) {
			bull.add(new Bullet(applet, bType, x, y, speed, accel, interval*i + rate*index, color));
		}
	}
	protected void sideSpray(String bType, int count, ArrayList<Bullet> bull, int[] color, float speed, float accel) {
		for(int i = 0; i < count; i++) {
			bull.add(new Bullet(applet, bType, 0, (float)(Math.random()*applet.height), 
					speed, accel, (float)(Math.random()*180), color));
			bull.add(new Bullet(applet, bType, applet.width, (float)(Math.random()*applet.height), 
					speed, accel, -(float)(Math.random()*180), color));
		}
	}
}
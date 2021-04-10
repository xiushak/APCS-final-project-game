
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public abstract class Bosses extends Enemies{
	private PApplet applet;
	private PImage img;
	private PImage circ;
	private float rot;
	protected int counter = 90;
	protected int phaseNumber = 5;
	protected boolean begin = true;
	protected boolean shoot = false;
	protected boolean immune = true;
	protected boolean move = true;
	
	public Bosses(PApplet applet, PImage img, PImage circ, float x, float y, float speed) {
		super(applet, img, x, y, speed);
		this.applet = applet;
		this.img = img;
		this.circ = circ;
		circ.resize(300, 300);
		hitPoint = 1500;
	}
	
	public int getPhase() {
		return phaseNumber;
	}
	
	public boolean hit(String bType) {
		if(hitPoint <= 0) {
			counter = 90;
			phaseNumber -= 1;
			if(phaseNumber == 0)
				return false;
			shoot = false;
			immune = true;
			move = true;
			hitPoint = 1500;
		}
		if(!immune) {
			if(bType.equals("s"))
				hitPoint -= 5;
			else if(bType.equals("m"))
				hitPoint -= 10;
			else if(bType.equals("l"))
				hitPoint -= 20;
			else
				hitPoint -= 30;
		}
		return true;
	}
	
	public void show() {
		
		applet.translate((x), (y));
		applet.rotate(rot);
		applet.translate(-circ.width/2, -circ.height/2);
		applet.tint(100);
		applet.image(circ, 0, 0);
		applet.resetMatrix();
		
		applet.noTint();
		applet.image(img, x-img.width/2, y-img.height/2);
		
		applet.noFill();
		applet.strokeWeight(5);
		if(phaseNumber > 1) {
			applet.stroke(255);
			applet.ellipse(x, y, 300, 300);
		}
		applet.stroke(0, 255, 0);
		if(hitPoint <= 500) {
			applet.stroke(255,155,0);
		}
		if(hitPoint <= 250) {
			applet.stroke(255, 0, 0);
		}
		applet.arc(x, y, 300, 300, 0, (float)((Math.PI*2)*hitPoint/1500));
		applet.noStroke();
		
		rot += 0.03;
		if(rot == 180)
			rot = 0;
	}
	
	public abstract void move();	
	public abstract void shoot(int counter, PlayerShip ship, ArrayList<Bullet> bull);
	
	
	//Basic patterns
	
	//targeted speadshot
	protected void spreadShot5(String bType, PlayerShip ship, ArrayList<Bullet> bull, int color[], float speed, float accel) {
		int degrees = (int)(Math.toDegrees(Math.atan2((double)(ship.getPosY()-y), (double)(ship.getPosX())-x)));
		bull.add(new Bullet(applet, bType, x, y, speed, accel, degrees + 30, color));
		bull.add(new Bullet(applet, bType, x, y, speed, accel, degrees + 15, color));
		bull.add(new Bullet(applet, bType, x, y, speed, accel, degrees, color));
		bull.add(new Bullet(applet, bType, x, y, speed, accel, degrees + -15, color));
		bull.add(new Bullet(applet, bType, x, y, speed, accel, degrees + -30, color));
	}
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
	//creates a spiral(incrementing index in class calling function)
	protected void spiral(String bType, int count, ArrayList<Bullet> bull, int[] color, float speed, float accel, int rate, int index) {
		int interval = 360/count;
		for(int i = 0; i <= count; i++) {
			bull.add(new Bullet(applet, bType, x, y, speed, accel, interval*i + rate*index, color));
		}
	}
	//spawns bullets along the left and right side at random
	protected void sideSpray(String bType, int count, ArrayList<Bullet> bull, int[] color, float speed, float accel) {
		for(int i = 0; i < count; i++) {
			bull.add(new Bullet(applet, bType, 0, (float)(Math.random()*applet.height), 
					speed, accel, (float)(Math.random()*180), color));
			bull.add(new Bullet(applet, bType, applet.width, (float)(Math.random()*applet.height), 
					speed, accel, -(float)(Math.random()*180), color));
		}
	}
	
	public boolean checkBounds() {
		return true;
	}
}
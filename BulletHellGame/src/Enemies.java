import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public abstract class Enemies extends Character{
	private PApplet applet;
	private PImage img;
	protected int hitBoxSize;
	protected int hitPoint;
	protected int shotCount;
	protected int shotRate;
	public Enemies(PApplet applet, PImage img, float x, float y, float speed) {
		super(x, y, speed);
		this.applet = applet;
		this.img = img;
	}
	
	public abstract boolean hit(String bType);
	public abstract void move();
	public abstract boolean checkBounds();
	public abstract void shoot(int counter, PlayerShip ship, ArrayList<Bullet> bull);
	
	public boolean moveTo(float dx, float dy, float _speed) {
		float degrees = (float)((Math.toDegrees(Math.atan2((double)(dy-y), (double)(dx-x)))) * Math.PI/180);
		//System.out.println(degrees);
		x += (Math.cos(degrees) * _speed);
		y += (Math.sin(degrees) * _speed);
		if(((degrees <= Math.PI/2 && degrees >= 0) && (x >= dx && y >= dy)))
			return true;
		if(((degrees <= Math.PI && degrees >= Math.PI/2) && (x <= dx && y >= dy)))
			return true;
		if(((degrees <= Math.PI*3/2 && degrees >= Math.PI) && (x <= dx && y <= dy)))
			return true;
		if(((degrees <= Math.PI*2 && degrees >= Math.PI*3/2) && (x >= dx && y >= dy)))
			return true;
		return false;
	}
	
	
	public int shotRate() {
		return shotRate;
	}
	public int getShotCount() {
		return shotCount;
	}
	public void show() {
		//loadImage
		applet.image(img, (int)x-img.width/2, (int)y-img.height/2);
	}
	public int hitBoxSize() {
		return hitBoxSize();
	}
	public int returnHP() {
		return hitPoint;
	}
}

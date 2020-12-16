import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;


public abstract class PlayerShip extends Character{
	private PApplet applet;
	private PImage img;
	private int lives = 5;
	protected int change;
	protected int lowChange;
	protected int normChange;
	protected int lowSpeed;
	protected int normSpeed;
	protected int offSet;
	protected int midFireRate;
	protected int outFireRate;
	protected String sideType;
	protected String midType;
	
	public PlayerShip(PApplet applet, PImage img, float x, float y, int change) {
		super(x, y, 5);
		this.applet = applet;
		this.img = img;
		this.change = change;
	}
	
	public abstract void shoot(int counter, Bosses b, ArrayList<Bullet> bull);
	public abstract void drawOrbs();
	public abstract int[] getColor();
	
	public boolean die() {
		lives--;
		if(lives == 0) {
			return false;
		}
		return true;
	}
	@Override
	public void show() {
		//loadImage
		applet.image(img, (int)x-img.width/2, (int)y-img.height/2-offSet);
		//hit-box
		applet.fill(0,255,105);
		applet.ellipse(x, y, 10, 10);
	}
	public void addLife() {
		lives++;
	}
	public int lives() {
		return lives;
	}
	public int getMidFireRate() {
		return midFireRate;
	}
	public int getOutFireRate() {
		return outFireRate;
	}	
	public int getChange() {
		return change;
	}
	public void lowChange() {
		change = lowChange;
	}
	public void normChange() {
		change = normChange;
	}
	public void changeLowSpeed() {
		speed = lowSpeed;
	}
	public void changeNormSpeed() {
		speed = normSpeed;
	}
	public String sideType() {
		return sideType;
	}
	public String midType() {
		return midType;
	}
}

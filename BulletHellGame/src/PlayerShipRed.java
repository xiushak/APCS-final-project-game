import java.util.ArrayList;

import processing.core.PApplet;

public class PlayerShipRed extends PlayerShip{
	private PApplet applet;
	private int[] color = {255, 0, 0};
	
	public PlayerShipRed(PApplet applet, float x, float y) {
		super(applet, applet.loadImage("lib\\ships\\redShip2.png"), x, y, 15);
		this.applet = applet;
		lowChange = 5;
		normChange = 15;
		lowSpeed = 2;
		normSpeed = 5;
		offSet = 10;
		midFireRate = 30;
		outFireRate = 6;
		sideType = "s";
		midType = "l";
	}
	
	//draw orbs next to figure
    public void drawOrbs() {
    	applet.fill(125,0,0);
		applet.ellipse(x+30, y+10, 20, 20);
		applet.ellipse(x-30, y+10, 20, 20);
    }

	@Override
	public int[] getColor() {
		return color;
	}

	@Override
	public void shoot(int counter, Bosses b, ArrayList<Bullet> bullets) {
	    	int up = -90; 
	    	//create side, middle, side in small, medium, small
	    	if(counter%outFireRate == 0) {
	    		Bullet b1 = new Bullet(applet, sideType, x-change, y, 10, 0.99f, up-change, color);
	    		Bullet b3 = new Bullet(applet, sideType, x+change, y, 10, 0.99f, up+change, color);
	    		bullets.add(b1);
	    		bullets.add(b3);
	    	}
	    	if(counter%midFireRate == 0) {
	    		Bullet b2 = new Bullet(applet, midType, x, y, 5, 1, up, color);
	    		bullets.add(b2);
	    }
	}
}

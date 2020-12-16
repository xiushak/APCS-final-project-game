import java.util.ArrayList;

import processing.core.PApplet;

public class PlayerShipGreen extends PlayerShip{
	private PApplet applet;
	private int[] color = {0, 255, 0};
	
	public PlayerShipGreen(PApplet applet, float x, float y) {
		super(applet, applet.loadImage("lib\\ships\\greenShip.png"), x, y, 15);
		this.applet = applet;
		lowChange = 0;
		normChange = 15;
		lowSpeed = 2;
		normSpeed = 5;
		offSet = -3;
		midFireRate = 13;
		outFireRate = 13;
		sideType = "s";
		midType = "l";
	}
	
	//draw orbs next to figure
    public void drawOrbs() {
    	applet.fill(0,200,0);
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
	    	bullets.add(new Bullet(applet, sideType, x-6, y, 8, 1, up-change*2, color));
	    	bullets.add(new Bullet(applet, sideType, x+6, y, 8, 1, up+change*2, color));
	    }
	    if(counter%midFireRate == 0) {
	    	bullets.add(new Bullet(applet, sideType, x-3, y, 8, 1, up-change, color));
	    	bullets.add(new Bullet(applet, sideType, x, y, 8, 1, up, color));
	    	bullets.add(new Bullet(applet, sideType, x+3, y, 8, 1, up+change, color)); 
	    }
	}
}

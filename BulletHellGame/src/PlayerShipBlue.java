import java.util.ArrayList;

import processing.core.PApplet;

public class PlayerShipBlue extends PlayerShip{
	private PApplet applet;
	private int[] color = {150, 150, 200};
	
	public PlayerShipBlue(PApplet applet, float x, float y) {
		super(applet, applet.loadImage("lib\\ships\\blueShip2.png"), x, y, 15);
		this.applet = applet;
		lowChange = 3;
		normChange = 10;
		lowSpeed = 2;
		normSpeed = 5;
		offSet = 10;
		midFireRate = 180;
		outFireRate = 20;
		sideType = "s";
		midType = "l";
	}
	
	//draw orbs next to figure
    public void drawOrbs() {
    	applet.fill(0,0,100);
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
	    	bullets.add(new Bullet(applet, sideType, x-6, y, 8, 0.999f, up-change*2, color));
	    	bullets.add(new Bullet(applet, sideType, x-3, y, 8, 0.999f, up-change, color));
	    	bullets.add(new Bullet(applet, sideType, x+3, y, 8, 0.999f, up+change, color)); 
	    	bullets.add(new Bullet(applet, sideType, x+6, y, 8, 0.999f, up+change*2, color));
	    }
	    if(counter%midFireRate == 0) {
	    	bullets.add(new trackingBullet(applet, midType, x, y, 10, 1.001f, up, color, b));
	    }
	}
}

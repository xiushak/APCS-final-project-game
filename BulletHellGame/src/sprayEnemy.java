import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class sprayEnemy extends subBoss{

	public sprayEnemy(PApplet applet, PImage img, float x, float y, float speed, int hitPoint, int hitBoxSize) {
		super(applet, img, x, y, speed);
		this.hitPoint = hitPoint;
		this.hitBoxSize = hitBoxSize;
	}


	@Override
	public void shoot(int counter, PlayerShip ship, ArrayList<Bullet> bull) {
		int[] color = {255, 255, 255};
		if(counter%40 == 0) {
			beam2(ship, bull, color, 1, 0.01f);
		}
	}
	private void beam2(PlayerShip ship, ArrayList<Bullet> bull, int[] color, float speed, float accel) {
		int degrees = (int)(Math.toDegrees(Math.atan2((double)(ship.getPosY()-y),
				(double)(ship.getPosX())-x)));
		bull.add(new Bullet(applet, "m", x+(float)(Math.random()*15)-7, y+(float)(Math.random()*15)-7,
				(float)(Math.random()*2) + speed, accel, degrees + (int)(Math.random()*10) - 5, color));	
	}
}

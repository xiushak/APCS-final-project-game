import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class CircleEnemyBoss1 extends subBoss{
	
	private int rate;
	private int movePhase = 1;

	public CircleEnemyBoss1(PApplet applet, PImage img, float x, float y, float speed, int rate, int hitPoint, int hitBoxSize) {
		super(applet, img, x, y, speed);
		this.rate = rate;
		this.hitPoint = hitPoint;
		this.hitBoxSize = hitBoxSize;
	}
	
	public void move() {
		if(movePhase == 1) {
			if(moveTo(applet.width - 100, 200, 2)) {
				movePhase = 2;
			}
		}
		else{
			if(moveTo(100, 200, 2)) {
				movePhase = 1;
			}
		}
	}
	
	public void shoot(int counter, PlayerShip ship, ArrayList<Bullet> bull) {
		int[] color = {255, 255, 255};
		if(counter%rate*2 == 0) {
			circle(50, bull, color, 1, 0.02f);
		}
	}
	protected void circle(int count, ArrayList<Bullet> bull, int[] color, float speed, float accel) {
		int interval = 360/count;
		for(int i = 0; i <= count; i++) {
			bull.add(new Bullet(applet, "m", x, y, speed, accel, interval*i, color));
		}
	}
}

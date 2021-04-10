
import java.util.ArrayList;

import processing.core.PApplet;

public class Boss4 extends Bosses{
	private PApplet applet;

	public Boss4(PApplet applet, float x, float y, int speed) {
		super(applet, applet.loadImage("lib//bosses//purpleBoss.png"), applet.loadImage("lib//circles//purpleCircle.png"), x, y, speed);
		this.applet = applet;
		hitBoxSize = 110;
	}
	
	public void move() {
		if(begin) {
			if(moveTo((float)(applet.width/2), 250, 2)){
				begin = false;
				immune = false;
				shoot = true;
			}
			return;
		}
		else if(immune) {
			counter--;
			if(counter == 0) {
				immune = false;
			}
		}
		if(phaseNumber == 4) {
			if(move) {
				if(moveTo((float)(applet.width*3/4), 400f, 4)) {
					shoot = true;
					move = false;
				}
			}
		}
		else if(phaseNumber == 3) {
			if(move) {
				if(moveTo((float)(applet.width*1/4), 400f, 4)) {
					shoot = true;
					move = false;
				}
			}
		}
		else if(phaseNumber == 2) {
			if(move) {
				if(moveTo((float)(applet.width/2), 250f, 4)) {
					shoot = true;
					move = false;
				}
			}
		}
		else if(phaseNumber == 1) {
			shoot = true;
			move = false;
		}
	}

	private int interval = 3;
	public void shoot(int counter, PlayerShip ship, ArrayList<Bullet> bull) {
		if(!shoot)
			return;
		int[] color = {255, 255, 255};
		int[] color2 = {255, 0, 255};
		int[] color3 = {139, 0, 139};
		if(phaseNumber == 5) {
			if(counter%20 == 0) {
				int degrees = (int)(Math.toDegrees(Math.atan2((double)(ship.getPosY()-y), (double)(ship.getPosX())-x)));
				for(int i = 0; i < 30; i++) {
					bull.add(new BulletBounce(applet, "m", applet.width/2*(float)Math.pow(Math.cos(-(360/20.0*i) * Math.PI/180), 3) + ship.getPosX(), 
						100*(float)Math.pow(Math.sin(-360/20.0*i * Math.PI/180), 3) +  y, 2, 1, -90, color2, 1));
					bull.add(new BulletBounce(applet, "m", applet.width/2*(float)Math.pow(Math.cos((360/20.0*i) * Math.PI/180), 3) + ship.getPosX(), 
							100*(float)Math.pow(Math.sin(360/20.0*i * Math.PI/180), 2) + y, 2, 1, 90, color2, 0));
				}
				//super.spiralShootBounce("m", 10, bull, color2, 1.2f, 1, 5, spiralIndex, 1);
			}
		}
		if(phaseNumber == 4) {
			if(counter % 15 == 0) {
				//super.circle("m", 30, bull, color3, 0.2f, 1.005f);		
			}
		}
		if(phaseNumber == 3) {
			if(counter % 15 == 0) {
				//super.circle("m", 30, bull, color3, 0.2f, 1.005f);
			}
		}
		if(phaseNumber <= 2) {
			if(counter % 10 == 0) {
				//super.spiral("m", 25, bull, color2, 0.2f, 1.01f, interval, spiralIndex);
				//spiralIndex++;
			}
			if(counter%60 == 0)
				interval = -interval;
		}
		if(phaseNumber == 1) {
			if(counter%30 == 0) {
				//super.spiralShootBounce("m", 20, bull, color, 1.5f, 1, 5, spiralIndex, 0);
			}
		}
	}
}

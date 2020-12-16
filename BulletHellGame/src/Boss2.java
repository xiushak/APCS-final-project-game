
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class Boss2 extends Bosses{
	private PApplet applet;
	private sprayEnemy orb1;
	private sprayEnemy orb2;
	private int movePhase = 1;

	public Boss2(PApplet applet, float x, float y, int speed) {
		super(applet, applet.loadImage("lib//bosses//greenBoss.png"), applet.loadImage("lib//circles//greenCircle2.png"), x, y, speed);
		this.applet = applet;
		hitBoxSize = 110;
		PImage img = applet.loadImage("lib//enemies//blueOrb.png");
		img.resize(200, 200);
		orb1 = new sprayEnemy(applet, img, 100, 200, 0, 99999999, 200);
		orb2 = new sprayEnemy(applet, img, applet.width-100, 200, 0, 99999999, 200);
	}
		
	public void move() {
		if(begin) {
			if(moveTo((int)(applet.width/2), 300, 2)){
				begin = false;
				immune = false;
				shoot = true;
			}
		}		
		else if(immune) {
			counter--;
			if(counter == 0) {
				immune = false;
				shoot = true;
			}
		}
		if(!begin) {
			if(movePhase == 1) {
				if(moveTo(applet.width - 150, 300, 3)) {
					movePhase = 2;
				}
			}
			else{
				if(moveTo(150, 300, 3)) {
					movePhase = 1;
				}
			}
		}
		if(phaseNumber == 4) {
			shoot = true;
			immune = false;
		}
	}

	public void begin() {
		if(y <= applet.height/4) {
			begin = true;
			return;
		}
		shoot = true;
		begin = false;
		immune = false;
	}

	@Override
	public void shoot(int counter, PlayerShip ship, ArrayList<Bullet> bull) {
		if(!shoot)
			return;
		int[] color = {50, 200, 50};
		int[] color2 = {50, 50, 200};
		int[] color3 = {250, 260, 250};
		int[] color4 = {50, 200, 250};
		if(counter%90 == 0) {
			super.circle("m", 30, bull, color, 1.05f, 0.01f);
		}
		if(phaseNumber <= 4) {
			if((counter+45)%90 == 0)
				super.circle("m", 30, bull, color2, 1.05f, 0.01f);
		}
		if(phaseNumber <= 3 && phaseNumber != 1) {
			if(counter%300 == 0) {
				super.spreadShot5("l", ship, bull, color3, 2.5f, -0.005f);
			}
		}
		if(phaseNumber <= 2) {
			if(counter%20 == 0) {
				super.beam("m", ship, bull, color4, 0.75f, 0.001f);
			}
		}
		if(phaseNumber == 1) {
			orb1.shoot(counter, ship, bull);
			orb2.shoot(counter, ship, bull);
		}
		
	}
}

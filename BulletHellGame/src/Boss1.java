
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class Boss1 extends Bosses{
	private PApplet applet;
	private PImage img;
	private CircleEnemyBoss1 orb1;
	private CircleEnemyBoss1 orb2;

	public Boss1(PApplet applet, float x, float y, int speed) {
		super(applet, applet.loadImage("lib//bosses//grayBoss.png"), applet.loadImage("lib//circles//redCircle.png"), x, y, speed);
		img = applet.loadImage("lib//enemies//blueOrb.png");
		img.resize(200, 200);
		this.applet = applet;
		hitBoxSize = 150;
		orb1 = new CircleEnemyBoss1(applet, img, 100, 200, 0, 100, 99999999, 200);
		orb2 = new CircleEnemyBoss1(applet, img, applet.width-100, 200, 0, 100, 99999999, 200);
	}
	
	public void move() {
		if(begin) {
			if(moveTo((int)(applet.width/2), 200, 2)){
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
		if(phaseNumber <= 3)
			orb1.move();
		if(phaseNumber <= 2)
			orb2.move();
	}
	/*
	public void show() {
		super.show();
		if(phaseNumber <= 3)
			orb1.show();
		if(phaseNumber <= 2)
			orb2.show();
	}
	*/

	private int spiralIndex = 0;
	private int spiralRate = 5;
	private int rateA = 30;
	public void shoot(int counter, PlayerShip ship, ArrayList<Bullet> bull) {
		if(!shoot)
			return;
		int[] color = {255, 255, 255};
		int[] color2 = {255, 0, 0};
		int[] color3 = {150, 0, 0};
		
		if(phaseNumber == 5) {
			if(counter%rateA == 0) {
				super.spiral("m", 30, bull, color2, 0.2f, 0.01f, spiralRate, spiralIndex);
				super.spiral("m", 30, bull, color2, 0.2f, 0.01f, -spiralRate, spiralIndex);
				spiralIndex++;
			}
			if(rateA > 0 && counter%30 == 0)
				rateA--;
		}
		if(phaseNumber <= 4) {
			if(counter%100 == 0) {
				super.circle("m", 30, bull, color3, 4, -0.005f);
			}
		}
		if(phaseNumber == 4) {
			if(counter%100 == 0) {
				super.spreadShot5("l", ship, bull, color, 3, -0.001f);
			}
			if(counter%40 == 0) {
				super.sideSpray("m", 10, bull, color2, 1, 0);
			}
		}
		if(phaseNumber == 3) {
			if(counter%40 == 0) {
				super.sideSpray("m", 8, bull, color2, 1, 0);
			}
		}
		if(phaseNumber <= 3) {
			orb1.shoot(counter+60, ship, bull);
		}
		if(phaseNumber <= 2) {
			orb2.shoot(counter, ship, bull);
		}
		if(phaseNumber == 1) {
			if(counter%12 == 0) {
				super.beam("m", ship, bull, color, 1, -0.001f);
			}
			if((counter+6)%12 == 0) {
				super.beam("m", ship, bull, color3, 1, -0.001f);
			}
		}
	}
}
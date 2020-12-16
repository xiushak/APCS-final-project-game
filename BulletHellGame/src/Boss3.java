
import java.util.ArrayList;

import processing.core.PApplet;

public class Boss3 extends Bosses{
	private PApplet applet;

	public Boss3(PApplet applet, float x, float y, int speed) {
		super(applet, applet.loadImage("lib//bosses//whiteBoss.png"), applet.loadImage("lib//circles//blueCircle.png"), x, y, speed);
		this.applet = applet;
		hitBoxSize = 110;
	}

	public void move() {
		if(begin) {
			if(moveTo((int)(applet.width/2), (int)(applet.height/3), 2)){
				immune = false;
				shoot = true;
				begin = false;
			}
		}
		else if(immune) {
			counter--;
			if(counter == 0) {
				immune = false;
				shoot = true;
			}
		}
	}

	private int spiralIndex = 0;
	private int index;
	public void shoot(int counter, PlayerShip ship, ArrayList<Bullet> bull) {
		if(!shoot)
			return;
		int[] color = {255, 255, 255};
		int[] color2 = {0, 0, 200};
		int[] color3 = {180, 180, 255};
		if(phaseNumber <= 5) {
			if(counter%30 == 0) {
				super.spiral("m", 11, bull, color, 3.5f, -0.005f, 10, index);
				super.spiral("m", 11, bull, color, 3.5f, -0.005f, -10, index);
				index++;
			}
		}
		if(phaseNumber <= 4) {
			if(counter%30 == 0) {
				super.spiral("m", 10, bull, color2, 4f, -0.005f, 9, index+75);
				super.spiral("m", 10, bull, color2, 4f, -0.005f, -9, index+75);
			}
		}
		if(phaseNumber <= 3) {
			if(counter%30 == 0) {
				super.spiral("m", 9, bull, color3, 4.5f, -0.005f, 8, index+100);
				super.spiral("m", 9, bull, color3, 4.5f, -0.005f, -8, index+100);
			}
		}
		if(phaseNumber <= 2) {
			if(counter%50 == 0) {
				super.spiral("m", 20, bull, color, 0.5f, 0.005f, 6, spiralIndex);
				super.spiral("m", 20, bull, color, 0.5f, 0.005f, -6, spiralIndex);
				spiralIndex++;
			}
		}
		if(phaseNumber == 1) {
			if(counter%20 == 0)
				super.sideSpray("m", 5, bull, color, 0.01f, 0);
		}		
	}
}

package tests;

import processing.core.PApplet;

public class shapes extends PApplet{
	private int x, y;
	public static void main() {
		PApplet.main(tests.shapes.class.getName());
	}
	public void settings() {
		size(1080, 720);
	}
	public void setup() {
		x = width/2;
		y = height/2;
	}
	public void draw() {
		background(0);
		noStroke();
		fill(255);
		ellipse(x, y, 1, 1);
		for(int i = 0; i < 360; i += 20) {
			ellipse(x + cos(i * PI/180), y + sin(i*PI/180), 10, 10);
		}
	}
}

import processing.core.PApplet;

public class Bullet {
	private PApplet applet;
	//location
	protected float x, y;
	//speed
	protected double speedx, speedy;
	private double accel;
	protected float speed;
	protected float angleDirection;
	//size in radius
	private int size;
	//direction point
	//private int angleDirection;
	//color of bullet (a, b, c)
	private int[] color;
	private String type;
	
	//normal constructor 
	public Bullet(PApplet applet, String type, float x, float y, float speed, float accel, float angleDirection, int[] color) {
		this.applet = applet;
		//set x and y
		this.x = x;
		this.y = y;
		//set color
		this.color = color.clone();
		//set direction
		this.angleDirection = angleDirection;
		this.speed = speed;
		//set speed
		double radians = angleDirection * (Math.PI / 180);
		speedx = (Math.cos(radians) * speed);
		speedy = (Math.sin(radians) * speed);
		this.accel = accel;
		//construct small
		this.type = type;
		if(type.equals("s"))
			size = 5;
		//construct medium
		else if(type.equals("m"))
			size = 10;
		//construct large
		else if(type.equals("l"))
			size = 20;
		else if(type.equals("xl"))
			size = 30;
	}
	public void show() {
		applet.fill(color[0], color[1], color[2]);
		applet.noStroke();
		applet.ellipse(x, y, size, size);
	}
	public void move() {
		x += speedx;
		y += speedy;
		double radians = angleDirection * (Math.PI / 180);
		speed += accel;
		speedx = (Math.cos(radians) * speed);
		speedy = (Math.sin(radians) * speed);
	}
	
	public boolean checkBound() {
		if(Math.abs(speed) < 0)
			return false;
		if(x < 0 || x > applet.width)
			return false;
		if(y < 0 || y > applet.height)
			return false;
		return true;			
	}
	public int getSize() {
		return size;
	}
	public float getPosX() {
		return x;
	}
	public float getPosY() {
		return y;
	}
	public String getType() {
		return type;
	}
}

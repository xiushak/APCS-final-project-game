import processing.core.PApplet;

public class BulletBounce extends Bullet{
	private PApplet applet;
	private int bounceCount;

	public BulletBounce(PApplet applet, String type, float x, float y, float speed, float accel, float angleDirection, int[] color, int bounceCount) {
		super(applet, type, x, y, speed, accel, angleDirection, color);
		// TODO Auto-generated constructor stub
		this.applet = applet;
		this.bounceCount = bounceCount;
	}
	@Override
	public boolean checkBound() {
		if(Math.abs(speed) < 0)
			return false;
		if(x < 0 || x > applet.width) {
			if(bounceCount == 0)
				return false;
			bounceCount--;
			speedx = -speedx;
		}
		if(y < 0 || y > applet.height) {
			if(bounceCount == 0)
				return false;
			bounceCount--;
			speedy = -speedy;
		}
		return true;	
	}

}

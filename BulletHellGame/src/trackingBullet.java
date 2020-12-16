import processing.core.PApplet;
//yeah... this shit doesnt work
public class trackingBullet extends Bullet{
	private PApplet applet;
	private Enemies target;

	public trackingBullet(PApplet applet, String type, float x, float y, float speed, float accel, float angleDirection, int[] color, Enemies target) {
		super(applet, type, x, y, speed, accel, angleDirection, color);
		// TODO Auto-generated constructor stub
		this.applet = applet;
		this.target = target;
	}
	@Override
	public boolean checkBound() {
		if(Math.abs(speed) < 0)
			return false;
		if(x < 0-50 || x > applet.width+50) {
			return false;
		}
		if(y < 0-50 || y > applet.height+50) {
			return false;
		}
		return true;	
	}
	@Override
	public void move() {
		super.move();
		int degrees = (int)(Math.toDegrees(Math.atan2((double)(target.getPosY()-y), (double)(target.getPosX())-x)));
		if(angleDirection < degrees)
			angleDirection += (degrees - angleDirection)/2;
		else if(angleDirection > degrees)
			angleDirection -= (angleDirection - degrees)/2;
	}

}

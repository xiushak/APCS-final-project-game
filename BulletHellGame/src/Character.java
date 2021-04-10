public abstract class Character {
	//(x, y) pos
	protected float x, y;
	protected float speed;
	
	public Character(float x, float y, float speed) {
		this.x = x;
		this.y = y;
		this.speed = speed;
    }
	
	public abstract void show();
	
	public float getPosX() {
		return x;
	}
	public float getPosY() {
		return y;
	}
	public void setPosX(float x) {
		this.x = x;
	}
	public void setPosY(float y) {
		this.y = y;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public float getSpeed() {
		return speed;
	}
}
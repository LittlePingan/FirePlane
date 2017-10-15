package cn.tedu.shoot;

/**
 * Ó¢ÐÛ¼¶×Óµ¯
 **/
public class Bullet extends FlyingObject{
	int ySpeed = 5;
	
	public Bullet(int x,int y)
	{
		image=ShootGame.bullet;
		width=image.getWidth();
		height=image.getHeight();
		this.x=x;
		this.y=y;
	}
	
	@Override
	public void step() {
		y-=ySpeed;
	}

	@Override
	public boolean outOfBounds() {
		return this.y<=-this.height;
	}
}
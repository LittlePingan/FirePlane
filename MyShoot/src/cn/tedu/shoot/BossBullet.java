package cn.tedu.shoot;

import java.awt.image.BufferedImage;

public class BossBullet extends FlyingObject {

	int ySpeed;
	public BufferedImage[] images;
	int index;

	public BossBullet(int x,int y) {
		// TODO Auto-generated constructor stub
		ySpeed = 1;
		images = new BufferedImage[] { ShootGame.bossbullet0,
				ShootGame.bossbullet0 };
		index = 0;
		this.x=x;
		this.y=y;
	}

	@Override
	public void step() {
		// TODO Auto-generated method stub
		image = images[index++ / 10 % images.length];
		y += ySpeed;

	}

	@Override
	public boolean outOfBounds() {
		// TODO Auto-generated method stub
		return this.y>=ShootGame.HEIGHT;
	}

}

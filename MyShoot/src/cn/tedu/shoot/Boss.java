package cn.tedu.shoot;

import java.awt.image.BufferedImage;

public class Boss extends FlyingObject {

	BufferedImage[] images1;
	BufferedImage[] images2;

	int life;

	int index;

	int type;// 判断是往上还是往下

	int xSpeed;
	int ySpeed;
	int xishu;
	public boolean ok_status;// 是否可以发射子弹状态
	public boolean die = false;// boss机是否死亡

	public Boss() {
		// TODO Auto-generated constructor stub
		life = 200;
		type = 1;// 表示向上
		image = ShootGame.boss0;
		width = image.getWidth();
		height = image.getHeight();
		ySpeed = 2;
		xSpeed = 1;

		xishu = 1;
		ok_status = false;
		x = ShootGame.WIDTH / 2 - this.width / 2;
		y = ShootGame.HEIGHT;
		images1 = new BufferedImage[] { ShootGame.bosss0, ShootGame.bosss1 };
		images2 = new BufferedImage[] { ShootGame.boss0, ShootGame.boss1 };

		index = 0;

	}

	@Override
	public void step() {
		// TODO Auto-generated method stub
		if (outOfBounds()) {
			type = 2;
		}
		if (type == 1) {
			image = images1[index++ / 10 % images1.length];
			y -= ySpeed;
		}
		if (type == 2) {
			image = images2[index++ / 10 % images2.length];
			if (y >= ShootGame.HEIGHT / 20){
				ok_status=true;
				y = ShootGame.HEIGHT / 20;
			}
			else
				y += ySpeed;
			if (x <= 0 || x >= ShootGame.WIDTH - this.width) {
				xSpeed = xSpeed * (-1);
			}
			x += xSpeed;
		}

	}

	@Override
	public boolean outOfBounds() {
		// TODO Auto-generated method stub
		return y < -this.height;
	}

	// 英雄机子弹碰撞boss机
	public boolean hit(FlyingObject obj) {
		int x1 = this.x - obj.width;
		int x2 = this.x + this.width;
		int y1 = this.y - obj.height;
		int y2 = this.y + this.height;
		return obj.x > x1 && obj.x < x2 && obj.y > y1 && obj.y < y2;
	}

	// boss机发射子弹
	public BossBullet[] shoot() {
		int xStep = this.width / 8;
		BossBullet[] bossbullets = new BossBullet[4];
		bossbullets[0] = new BossBullet(this.x + xStep, this.y + this.height);
		bossbullets[1] = new BossBullet(this.x + 3 * xStep, this.y
				+ this.height);
		bossbullets[2] = new BossBullet(this.x + 5 * xStep, this.y
				+ this.height);
		bossbullets[3] = new BossBullet(this.x + 7 * xStep, this.y
				+ this.height);
		return bossbullets;

	}
	
	public int getType(){
		return type;
	}
	
	public boolean getdie_status(){
		return die;
	}
	
	public void die_status(){
		die=true;
	}

	public int getLife() {
		return life;
	}

}

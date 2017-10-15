package cn.tedu.shoot;

import java.awt.image.BufferedImage;

/**
 * 英雄机类 玩家类 图片数组 移动 是否越界 生命值 火力值
 * */
public class Hero extends FlyingObject {
	public BufferedImage[] images;// 图片数组
	int index;// 切换图片的变量
	int life;//
	int doubleFire;

	// 给变量赋值
	public Hero() {
		image = ShootGame.hero0;
		width = image.getWidth();
		height = image.getHeight();
		x = 200;
		y = 500;
		index = 0;
		life = 3;
		doubleFire = 0;
		images = new BufferedImage[] { ShootGame.hero0, ShootGame.hero1,
				ShootGame.hero2, ShootGame.hero3, ShootGame.hero4,
				ShootGame.hero5, ShootGame.hero6, ShootGame.hero7,
				ShootGame.hero8, ShootGame.hero9 };
	}

	// 英雄机跟随鼠标移动的方法
	public void Moveto(int x, int y)// 参数x,y是鼠标的坐标
	{
		this.x = x - this.width / 2;
		this.y = y - this.height / 2;
	}

	// 英雄级发射子弹的方法
	public Bullet[] shoot() {
		int xStep = this.width / 6;
		int yStep = 10;
		if (doubleFire > 0) {// 双倍火力 发射两个子弹
			Bullet[] bullets = new Bullet[2];
			bullets[0] = new Bullet(this.x + 2 * xStep, this.y - yStep);
			bullets[1] = new Bullet(this.x + 4 * xStep, this.y - yStep);
			doubleFire -= 2;// 每发射一次双倍火力 火力值减2
			return bullets;
		} else {// 单倍火力 发射一个子弹
			Bullet[] bullets = new Bullet[1];
			bullets[0] = new Bullet(this.x + 3 * xStep, this.y - yStep);
			return bullets;
		}
	}

	@Override
	public void step() {
		// 图片的切换
		image = images[index++ / 10 % images.length];

	}

	@Override
	public boolean outOfBounds() {
		// TODO Auto-generated method stub
		// 英雄机永不越界
		return false;
	}

	// 英雄机撞敌机
	public boolean hit(FlyingObject obj) {
		int x1 = obj.x - this.width / 2;
		int x2 = obj.x + this.width / 2 + obj.width;
		int y1 = obj.y - this.height / 2;
		int y2 = obj.y + this.height / 2 + obj.height;
		int x = this.x + this.width / 2;
		int y = this.y + this.height / 2;
		return x > x1 && x < x2 && y > y1 && y < y2;
	}
	
	//火力增加
	public void addDoubleFire(int m){
		doubleFire+=m;
	}
	
	//生命增加
	public void addLife(){
		life++;
	}
	
	public void sublife(){
		life--;
	}
	
	public int getLife(){
		return life;
	}
}
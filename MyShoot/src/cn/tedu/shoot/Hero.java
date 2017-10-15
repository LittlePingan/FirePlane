package cn.tedu.shoot;

import java.awt.image.BufferedImage;

/**
 * Ӣ�ۻ��� ����� ͼƬ���� �ƶ� �Ƿ�Խ�� ����ֵ ����ֵ
 * */
public class Hero extends FlyingObject {
	public BufferedImage[] images;// ͼƬ����
	int index;// �л�ͼƬ�ı���
	int life;//
	int doubleFire;

	// ��������ֵ
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

	// Ӣ�ۻ���������ƶ��ķ���
	public void Moveto(int x, int y)// ����x,y����������
	{
		this.x = x - this.width / 2;
		this.y = y - this.height / 2;
	}

	// Ӣ�ۼ������ӵ��ķ���
	public Bullet[] shoot() {
		int xStep = this.width / 6;
		int yStep = 10;
		if (doubleFire > 0) {// ˫������ ���������ӵ�
			Bullet[] bullets = new Bullet[2];
			bullets[0] = new Bullet(this.x + 2 * xStep, this.y - yStep);
			bullets[1] = new Bullet(this.x + 4 * xStep, this.y - yStep);
			doubleFire -= 2;// ÿ����һ��˫������ ����ֵ��2
			return bullets;
		} else {// �������� ����һ���ӵ�
			Bullet[] bullets = new Bullet[1];
			bullets[0] = new Bullet(this.x + 3 * xStep, this.y - yStep);
			return bullets;
		}
	}

	@Override
	public void step() {
		// ͼƬ���л�
		image = images[index++ / 10 % images.length];

	}

	@Override
	public boolean outOfBounds() {
		// TODO Auto-generated method stub
		// Ӣ�ۻ�����Խ��
		return false;
	}

	// Ӣ�ۻ�ײ�л�
	public boolean hit(FlyingObject obj) {
		int x1 = obj.x - this.width / 2;
		int x2 = obj.x + this.width / 2 + obj.width;
		int y1 = obj.y - this.height / 2;
		int y2 = obj.y + this.height / 2 + obj.height;
		int x = this.x + this.width / 2;
		int y = this.y + this.height / 2;
		return x > x1 && x < x2 && y > y1 && y < y2;
	}
	
	//��������
	public void addDoubleFire(int m){
		doubleFire+=m;
	}
	
	//��������
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
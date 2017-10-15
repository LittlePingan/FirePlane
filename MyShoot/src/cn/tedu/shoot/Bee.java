package cn.tedu.shoot;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Bee extends FlyingObject implements Award{
	//ͼƬ����
	BufferedImage[] images;
	//�л�ͼƬ��������
	int index;
	//x�����ƶ�ֵ  y�����ƶ�ֵ
	int xSpeed=1;
	int ySpeed=2;
	//��������
	int awardType;
	
	//��ֵ
	Random random=new Random();
	public Bee()
	{
		image=ShootGame.bee0;
		width=image.getWidth();
		height=image.getHeight();
		x=random.nextInt(ShootGame.WIDTH-this.width);
		y=-this.height;
		awardType=random.nextInt(2);//[0,1]
		images=new BufferedImage[]{ShootGame.bee0,ShootGame.bee1,ShootGame.bee2,ShootGame.bee3,ShootGame.bee4,ShootGame.bee5,ShootGame.bee6,ShootGame.bee7,ShootGame.bee8};
		index=0;
	}
	@Override
	public void step() {
		//ͼƬ�л�
		image=images[index++/60%images.length];
		//y�����ƶ�
		y+=ySpeed;
		x+=xSpeed;
		if(x<=0||x>=ShootGame.WIDTH-this.width)
		{
			xSpeed=-xSpeed;
		}
	}

	@Override
	public boolean outOfBounds() {
		return this.y>=ShootGame.HEIGHT;
	}
	public int getAwardType() {
		return awardType;
	}
}
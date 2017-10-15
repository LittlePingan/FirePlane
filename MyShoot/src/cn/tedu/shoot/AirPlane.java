package cn.tedu.shoot;

import java.awt.image.BufferedImage;
import java.util.Random;

/*�л���*/
public class AirPlane extends FlyingObject implements Enemy{
	//�л����Ʋ�������
	int ySpeed = 2;
	//ͼƬ����
	BufferedImage[] images;
	//�л�ͼƬ�ı�������
	int index;
	
	//��ֵ
	Random random=new Random();
	
	public AirPlane ()
	{
		image=ShootGame.flys0;
		width=image.getWidth();
		height=image.getHeight();
		
		x=random.nextInt(ShootGame.WIDTH-this.width);
		y=-this.height;
		
		images=new BufferedImage[]{ShootGame.flys0,ShootGame.flys1,ShootGame.flys2,ShootGame.flys3,ShootGame.flys4,ShootGame.flys5};
		index=0;
	}
	
	//�л��ƶ�����
	@Override
	public void step() {
		//ͼƬ�л�
		image=images[index++/60%images.length];
		//y�����ƶ�
		y+=ySpeed;
	}

	//�жϵл��Ƿ�Խ��
	@Override
	public boolean outOfBounds() {
		return this.y>=ShootGame.HEIGHT;
	}
}
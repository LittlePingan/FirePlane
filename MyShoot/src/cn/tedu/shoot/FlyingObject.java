package cn.tedu.shoot;
import java.awt.image.BufferedImage;
/*��������*/
public abstract class FlyingObject {
	//ͼƬ
	public BufferedImage image;
	//���
	int width,height;
	//����
	int x,y;
	//�������ƶ�����
	public abstract void step();
	//�жϷ������Ƿ�Խ��
	public abstract boolean outOfBounds();
	
	public boolean shootBy(Bullet b)
	{
		int x1=this.x;
		int x2=this.x+this.width;
		int y1=this.y;
		int y2=this.y+this.height;
		
		int x=b.x;
		int y=b.y;
		
		return x1<x&&x<x2&&y1<y&&y<y2;
	}
}
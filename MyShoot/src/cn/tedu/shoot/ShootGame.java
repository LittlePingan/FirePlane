package cn.tedu.shoot;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * ��������
 */
public class ShootGame extends JPanel {// 1.1 ShootGame����һ�����

	Random random = new Random();
	public static final int WIDTH = 512;
	public static final int HEIGHT = 768;
	// ����ļ���״̬
	public static final int START = 0;// ����
	public static final int RUNNING = 1;// ����
	public static final int PAUSE = 2;// ��ͣ
	public static final int GAME_OVER = 3;// ����
	// ��ǰ״̬Ĭ��������״̬
	public static int state = START;

	private static final long serialVersionUID = 1L;
	// 1.2��������ͼƬ��λ��
	public static BufferedImage background;// ������:����������ͬʱ���Ѿ������˴洢�ռ�
											// �洢�ռ�Ĵ�С�����͸��ݱ���������ȷ��
	public static BufferedImage start; // ����ͼ
	public static BufferedImage pause; // ��ͣͼ
	public static BufferedImage gameover; // ��Ϸ����ͼ
	public static BufferedImage hero0; // Ӣ�ۻ�0
	public static BufferedImage hero1; // Ӣ�ۻ�1
	public static BufferedImage hero2;
	public static BufferedImage hero3;
	public static BufferedImage hero4;
	public static BufferedImage hero5;
	public static BufferedImage hero6;
	public static BufferedImage hero7;
	public static BufferedImage hero8;
	public static BufferedImage hero9;

	public static BufferedImage flys0; // �л�
	public static BufferedImage flys1;
	public static BufferedImage flys2;
	public static BufferedImage flys3;
	public static BufferedImage flys4;
	public static BufferedImage flys5;

	public static BufferedImage bee0; // С�۷�
	public static BufferedImage bee1;
	public static BufferedImage bee2;
	public static BufferedImage bee3;
	public static BufferedImage bee4;
	public static BufferedImage bee5;
	public static BufferedImage bee6;
	public static BufferedImage bee7;
	public static BufferedImage bee8;

	public static BufferedImage bullet; // �ӵ�

	public static BufferedImage boss0;
	public static BufferedImage boss1;
	public static BufferedImage bosss0;
	public static BufferedImage bosss1;

	public static BufferedImage bossbullet0;// boss�ӵ�
	public static BufferedImage bossbullet1;

	// public static BufferedImage bossbu;
	public static BufferedImage gg;
	public static BufferedImage jj;
	// 2.1����Ӣ�ۻ�����
	Hero hero = new Hero();

	// 3.1�����л���������
	FlyingObject[] flyings = {};

	// 4.1����Ӣ�ۻ��ӵ�����
	Bullet[] bullets = {};

	// ����boss��
	Boss[] boss = {};

	// boss���ӵ�
	BossBullet[] bossbullet = {};

	// 1.3����ͼƬ��Դ
	static {
		try {
			background = ImageIO.read(ShootGame.class
					.getResource("background.jpg"));
			start = ImageIO.read(ShootGame.class.getResource("start.jpg"));
			pause = ImageIO.read(ShootGame.class.getResource("zan.jpg"));
			gameover = ImageIO.read(ShootGame.class.getResource("over.jpg"));
			hero0 = ImageIO.read(ShootGame.class.getResource("ws00.png"));
			hero1 = ImageIO.read(ShootGame.class.getResource("ws01.png"));
			hero2 = ImageIO.read(ShootGame.class.getResource("ws02.png"));
			hero3 = ImageIO.read(ShootGame.class.getResource("ws03.png"));
			hero4 = ImageIO.read(ShootGame.class.getResource("ws04.png"));
			hero5 = ImageIO.read(ShootGame.class.getResource("ws05.png"));
			hero6 = ImageIO.read(ShootGame.class.getResource("ws06.png"));
			hero7 = ImageIO.read(ShootGame.class.getResource("ws07.png"));
			hero8 = ImageIO.read(ShootGame.class.getResource("ws08.png"));
			hero9 = ImageIO.read(ShootGame.class.getResource("ws09.png"));
			flys0 = ImageIO.read(ShootGame.class.getResource("flys0.png"));
			flys1 = ImageIO.read(ShootGame.class.getResource("flys1.png"));
			flys2 = ImageIO.read(ShootGame.class.getResource("flys2.png"));
			flys3 = ImageIO.read(ShootGame.class.getResource("flys3.png"));
			flys4 = ImageIO.read(ShootGame.class.getResource("flys4.png"));
			flys5 = ImageIO.read(ShootGame.class.getResource("flys5.png"));
			bee0 = ImageIO.read(ShootGame.class.getResource("qq00.png"));
			bee1 = ImageIO.read(ShootGame.class.getResource("qq01.png"));
			bee2 = ImageIO.read(ShootGame.class.getResource("qq02.png"));
			bee3 = ImageIO.read(ShootGame.class.getResource("qq03.png"));
			bee4 = ImageIO.read(ShootGame.class.getResource("qq04.png"));
			bee5 = ImageIO.read(ShootGame.class.getResource("qq05.png"));
			bee6 = ImageIO.read(ShootGame.class.getResource("qq06.png"));
			bee7 = ImageIO.read(ShootGame.class.getResource("qq07.png"));
			bee8 = ImageIO.read(ShootGame.class.getResource("qq08.png"));
			bullet = ImageIO.read(ShootGame.class.getResource("bullets.png"));

			boss0 = ImageIO.read(ShootGame.class.getResource("boss0.png"));
			boss1 = ImageIO.read(ShootGame.class.getResource("boss1.png"));
			bosss0 = ImageIO.read(ShootGame.class.getResource("bosss0.png"));
			bosss1 = ImageIO.read(ShootGame.class.getResource("bosss1.png"));

			bossbullet0 = ImageIO.read(ShootGame.class
					.getResource("bossbu0.png"));
			bossbullet1 = ImageIO.read(ShootGame.class
					.getResource("bossbu1.png"));

			gg = ImageIO.read(ShootGame.class.getResource("gg.png"));
			jj = ImageIO.read(ShootGame.class.getResource("jj.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// 3.3�������볡����
	int flyingIndex = 0;

	public void EnteredAction() {
		flyingIndex++;
		// �л��볡
		if (flyingIndex % 30 == 0) {
			FlyingObject one = nextOne();// ��ȡ���ɵ�һ���ɻ�
			// ������������flyings����
			flyings = Arrays.copyOf(flyings, flyings.length + 1);
			flyings[flyings.length - 1] = one;
		}
		// Ӣ�ۼ��ӵ��볡
		if (flyingIndex % 10 == 0) {
			Bullet[] bs = hero.shoot();// ��ȡ���ɵ�Ӣ�ۻ��ӵ�
			bullets = Arrays.copyOf(bullets, bullets.length + bs.length);// ���ӵ�����bullets����
			System.arraycopy(bs, 0, bullets, bullets.length - bs.length,
					bs.length);
			if (bs.length == 1) {
				bullets[bullets.length - 1] = bs[0];
			} else if (bs.length == 2) {
				bullets[bullets.length - 2] = bs[0];
				bullets[bullets.length - 1] = bs[1];
			}
		}
	}

	// 3.2����һ���л��ķ���
	public FlyingObject nextOne() {

		int num = random.nextInt(20);// [0,19]
		if (num >= 16)
			return new Bee();
		else
			return new AirPlane();
	}

	// boss���볡
	int flag = 0;

	public void BossEntered() {
		if ((score % 1000) >= 400 && flag == 0) {
			boss = Arrays.copyOf(boss, boss.length + 1);
			boss[boss.length - 1] = new Boss();
			// ��boss���ҵ���ʱ��flag=0
			flag = 1;
		}
	}

	// boss���ӵ��볡
	int flyingindex1 = 0;

	public void BossBulletAction() {
		flyingindex1++;
		for (int i = 0; i < boss.length; i++) {
			if (!boss[i].getdie_status()) {
				if (flyingindex1 % 200 == 0 && boss[i].ok_status) {
					bossbullet = Arrays.copyOf(bossbullet,
							bossbullet.length + 4);
					System.arraycopy(boss[i].shoot(), 0, bossbullet,
							bossbullet.length - 4, 4);
				}
			}
		}

	}

	// 2.3�������ƶ�����
	public void stepAction() {
		// Ӣ�ۻ��ƶ�
		hero.step();
		// 3.6�л��ƶ�
		for (int i = 0; i < flyings.length; i++) {
			flyings[i].step();
		}
		// 4.3Ӣ�ۻ��ӵ��ƶ�
		for (int i = 0; i < bullets.length; i++) {
			bullets[i].step();
		}
		// boss���ƶ�
		for (int i = 0; i < boss.length; i++) {
			// �ӿ��Ʊ���
			if (!boss[i].getdie_status()) {
				boss[i].step();
			}
		}

		// boss���ӵ��ƶ�
		for (int i = 0; i < bossbullet.length; i++) {
			bossbullet[i].step();
		}
	}

	// �ӵ����ел��Ĵ�����
	public void bangAction() {
		for (int i = 0; i < bullets.length; i++) {
			bang(bullets[i], i);
		}
	}

	// ��ײ(�л�ײ��Ӣ�ۻ�����boss���ӵ�����Ӣ�ۻ�)
	public void hitAction() {
		for (int i = 0; i < flyings.length; i++) {
			if (hero.hit(flyings[i])) {

				hero.sublife();
				if (hero.getLife() <= 0){
					state = GAME_OVER;
					flag=0;
				}
					
				// ɾ����ײ���ĵл�
				FlyingObject t;
				t = flyings[flyings.length - 1];
				flyings[flyings.length - 1] = flyings[i];
				flyings[i] = t;
				flyings = Arrays.copyOf(flyings, flyings.length - 1);
			}
		}
		//boss���ӵ�
		for(int i=0;i<bossbullet.length;i++){
			if(hero.hit(bossbullet[i])){
				hero.sublife();
				if (hero.getLife() <= 0){
					state = GAME_OVER;
					flag=0;
				}
				//ɾ����ײ���ӵ�
				bossbullet[i]=bossbullet[bossbullet.length-1];
				bossbullet=Arrays.copyOf(bossbullet, bossbullet.length-1);
			}
		}
	}

	// �ӵ���ײ��boss��
	public void hitaction2() {
		int index = -1;
		for (int i = 0; i < boss.length; i++) {
			if (!boss[i].getdie_status())
				index = i;
		}
		for (int i = 0; i < bullets.length; i++) {
			if (boss[index].hit(bullets[i]) && boss[index].getType() == 2) {
				// �ӵ���ʧ
				bullets[i] = bullets[bullets.length - 1];
				bullets = Arrays.copyOf(bullets, bullets.length - 1);
				boss[index].life -= 1;
				if (boss[index].getLife() <= 0) {
					boss[index].die_status();
					flag = 0;// ������������boss��
					score = (score / 1000 + 1) * 1000;
				}
			}
		}
	}

	int score = 0;

	/* һ���ӵ����ел��Ĵ����� */
	public void bang(Bullet b, int index2) {
		int index = -1;
		// �жϵл���û�б����ӵ�����
		for (int i = 0; i < flyings.length; i++)// �������ел�
		{
			if (flyings[i].shootBy(b))// �������
			{
				index = i;// ȡ���õл�������
				break;
			}
		}

		// ����л����ӵ����� ���д���
		if (index != -1) {
			FlyingObject one = flyings[index];// ��ȡ�����еĵл�
			// ��Ӣ�ۻ��Ĵ���
			if (one instanceof Enemy)// �л���AirPlane
			{
				score += 5;// �ӷ�
			}
			if (one instanceof Award)// �л���Bee
			{
				Bee bee = (Bee) one;
				int type = bee.getAwardType();
				if (type == Award.DOUBLE_FIRE) {// Ӣ�ۼ����ӻ���ֵ
					//hero.doubleFire += 40;
					hero.addDoubleFire(40);
				}
				if (type == Award.LIFE) {// Ӣ����������ֵ
					//hero.life++;
					hero.addLife();
				}
			}
			/** ���ӵ��ͱ����ел��Ĵ��� **/
			// ɾ�����ел����ӵ�
			bullets[index2] = bullets[bullets.length - 1];
			bullets = Arrays.copyOf(bullets, bullets.length - 1);
			// ɾ�����ӵ����еĵл�
			FlyingObject t;
			t = flyings[flyings.length - 1];
			flyings[flyings.length - 1] = flyings[index];
			flyings[index] = t;
			flyings = Arrays.copyOf(flyings, flyings.length - 1);
		}
	}
	
	//Խ�紦��
	public void allOutOfBounds(){
		//���л�Խ��Ĵ����
		for(int i=0;i<flyings.length;i++){
			if(flyings[i].outOfBounds()){
				flyings[i]=flyings[flyings.length-1];
				flyings=Arrays.copyOf(flyings, flyings.length-1);
			}
		}
		//��Ӣ�ۻ��ӵ���Խ�紦��
		for(int i=0;i<bullets.length;i++){
			if(bullets[i].outOfBounds()){
				bullets[i]=bullets[bullets.length-1];
				bullets=Arrays.copyOf(bullets, bullets.length-1);
			}
		}
		
		//��boss���������boss����die����Ϊ����ɾ��
		for(int i=0;i<boss.length;i++){
			if(boss[i].getdie_status()){
				boss[i]=boss[boss.length-1];
				boss=Arrays.copyOf(boss, boss.length-1);
			}
		}
		//��boss��������ӵ���Խ�紦��
		for(int i=0;i<bossbullet.length;i++){
			if(bossbullet[i].outOfBounds()){
				bossbullet[i]=bossbullet[bossbullet.length-1];
				bossbullet=Arrays.copyOf(bossbullet, bossbullet.length-1);
				
			}
		}
	}

	/* ����ִ�з��� */
	public void action() {
		// ��ʱ��
		Timer timer = new Timer();
		int interval = 10;// ʵ��ΪCPU���� ����Ϊ����
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if (state == RUNNING) {
					
					//Խ�紦��
					allOutOfBounds();
					// 2.4
					stepAction();
					// 3.4
					EnteredAction();
					// 5.4
					bangAction();

					BossEntered();

					if (flag == 1) {
						hitaction2();
						BossBulletAction();
					}
					
					hitAction();//���һ��Ҫ����󣬷���boss���ӵ��޷��ж�
					
					//System.out.println(bullets.length);

				}
				repaint();// ��������״̬ ����һֱ��ͣ�ػ�
			}
		}, interval, interval);
		// ����¼�����
		MouseAdapter m = new MouseAdapter() {
			// ������¼�
			public void mouseClicked(MouseEvent arg0) {
				if (state == START) {
					state = RUNNING;
				}
				if (state == GAME_OVER) {
					state = START;
					// ������� ������д
					score=0;

					hero=new Hero();
					flyings=Arrays.copyOf(flyings, 0);
					bullets=Arrays.copyOf(bullets, 0);
					boss=Arrays.copyOf(boss, 0);
					bossbullet=Arrays.copyOf(bossbullet, 0);
					
				}
			}

			// ����ƶ��¼�
			public void mouseMoved(MouseEvent e) {
				if (state == RUNNING)// ��ǰ״̬������״̬ʱ ��ִ�иò���
				{
					int x = e.getX();
					int y = e.getY();
					hero.Moveto(x, y);
				}
			}

			// ��������¼�
			public void mouseEntered(MouseEvent arg0) {
				if (state == PAUSE) {
					state = RUNNING;
				}
			}

			// ����Ƴ��¼�
			public void mouseExited(MouseEvent arg0) {
				if (state == RUNNING) {
					state = PAUSE;
				}
			}
		};

		// ����������¼�
		this.addMouseListener(m);// ����������¼�
		this.addMouseMotionListener(m);// �����껬���¼�
	}

	//���Ʊ���
	int a = 0;
	public void paintBackground(Graphics g){
		a++;
		g.drawImage(background, 0, a, null);
		g.drawImage(background, 0, -background.getHeight() + a, null);
		if (a == background.getHeight()) {
			a = 0;
		}
	}
	
	//���Ƹ��ַ�����
	public void paintFlyingObjects(Graphics g){
		// 2.2����Ӣ�ۻ�
		g.drawImage(hero.image, hero.x, hero.y, null);
		// 3.5���Ƶл�
		for (int i = 0; i < flyings.length; i++) {
			FlyingObject f = flyings[i];
			g.drawImage(f.image, f.x, f.y, null);
		}
		// 4.4����Ӣ�ۻ��ӵ�
		for (int i = 0; i < bullets.length; i++) {
			FlyingObject f = bullets[i];
			g.drawImage(f.image, f.x, f.y, null);
		}

		// ����boss��
		for (int i = 0; i < boss.length; i++) {
			// �ӿ��Ʊ���
			if (!boss[i].die) {
				g.drawImage(boss[i].image, boss[i].x, boss[i].y, null);
			}
		}
		// ����boss���ӵ�
		for (int i = 0; i < bossbullet.length; i++) {
			FlyingObject f = bossbullet[i];
			g.drawImage(f.image, f.x, f.y, null);
		}
	}
	//��������ϵ�����
	public void paintString(Graphics g){
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 28));
		g.setColor(Color.yellow);
		g.drawString("�÷֣�" + score, 350, 710);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
		g.setColor(Color.red);

		g.drawString("������X" + hero.getLife(), 80, 710);
		g.setColor(Color.yellow);
		g.drawString("��ң�" + score * 3 / 2, 230, 710);
		g.setColor(Color.green);
		g.drawString("�ؿ���" + (score / 1000 + 1), 400, 30);

		for (int i = 0; i < boss.length; i++) {
			if (!boss[i].die) {
				g.drawString("Boss����ֵ��" + boss[i].getLife() / 2 + "%", 20, 30);
			}
			// g.drawString("%", 200, 30);
		}
	}
	
	//���Ƹ���״̬
	public void paintStatus(Graphics g){
		switch(state){
		case START:
			g.drawImage(start, 0, 0, null);
			break;
		case PAUSE:
			g.drawImage(pause, 0, 0, null);
			break;
		case GAME_OVER:
			g.drawImage(gameover, 0, 0, null);
			break;	
		}
		/*
		if (state == START) {
			g.drawImage(start, 0, 0, null);
		}
		if (state == PAUSE) {
			g.drawImage(pause, 0, 0, null);
		}
		if (state == GAME_OVER) {
			g.drawImage(gameover, 0, 0, null);
		}
		*/
	}

	// 1.4���Ʒ���(�Ȼ��Ƶ�����ײ�)
	@Override
	public void paint(Graphics g) {
		// super.paint(g);
		//���Ʊ���
		paintBackground(g);
		//���Ƹ��ַ�����
		paintFlyingObjects(g);
		//��������ϵ���
		paintString(g);
		// ���Ƴ���ļ���״̬
		paintStatus(g);
	}

	public static void main(String[] args) {// ������
		// ���ɴ���
		JFrame frame = new JFrame();
		// 1.5����������
		ShootGame game = new ShootGame();
		frame.add(game);
		// ���ô����С
		frame.setSize(512, 768);
		// ���ô���λ�þ���
		frame.setLocationRelativeTo(null);
		// ���������ñ���
		frame.setTitle("����ս��_��ƽ��");
		// ���ô���رհ�ť����
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ���ô����С���ɸı�
		frame.setResizable(false);
		// ���ô�����Զ�����Ϸ�
		frame.setAlwaysOnTop(true);
		// �ô�����ʾ
		frame.setVisible(true);
		game.action();
	}
}
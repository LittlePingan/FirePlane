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
 * 主程序类
 */
public class ShootGame extends JPanel {// 1.1 ShootGame就是一个面板

	Random random = new Random();
	public static final int WIDTH = 512;
	public static final int HEIGHT = 768;
	// 程序的几种状态
	public static final int START = 0;// 启动
	public static final int RUNNING = 1;// 运行
	public static final int PAUSE = 2;// 暂停
	public static final int GAME_OVER = 3;// 结束
	// 当前状态默认是启动状态
	public static int state = START;

	private static final long serialVersionUID = 1L;
	// 1.2声明背景图片的位置
	public static BufferedImage background;// 面试题:声明变量的同时就已经开辟了存储空间
											// 存储空间的大小和类型根据变量类型来确定
	public static BufferedImage start; // 启动图
	public static BufferedImage pause; // 暂停图
	public static BufferedImage gameover; // 游戏结束图
	public static BufferedImage hero0; // 英雄机0
	public static BufferedImage hero1; // 英雄机1
	public static BufferedImage hero2;
	public static BufferedImage hero3;
	public static BufferedImage hero4;
	public static BufferedImage hero5;
	public static BufferedImage hero6;
	public static BufferedImage hero7;
	public static BufferedImage hero8;
	public static BufferedImage hero9;

	public static BufferedImage flys0; // 敌机
	public static BufferedImage flys1;
	public static BufferedImage flys2;
	public static BufferedImage flys3;
	public static BufferedImage flys4;
	public static BufferedImage flys5;

	public static BufferedImage bee0; // 小蜜蜂
	public static BufferedImage bee1;
	public static BufferedImage bee2;
	public static BufferedImage bee3;
	public static BufferedImage bee4;
	public static BufferedImage bee5;
	public static BufferedImage bee6;
	public static BufferedImage bee7;
	public static BufferedImage bee8;

	public static BufferedImage bullet; // 子弹

	public static BufferedImage boss0;
	public static BufferedImage boss1;
	public static BufferedImage bosss0;
	public static BufferedImage bosss1;

	public static BufferedImage bossbullet0;// boss子弹
	public static BufferedImage bossbullet1;

	// public static BufferedImage bossbu;
	public static BufferedImage gg;
	public static BufferedImage jj;
	// 2.1创建英雄机对象
	Hero hero = new Hero();

	// 3.1创建敌机对象数组
	FlyingObject[] flyings = {};

	// 4.1创建英雄机子弹数组
	Bullet[] bullets = {};

	// 创建boss机
	Boss[] boss = {};

	// boss机子弹
	BossBullet[] bossbullet = {};

	// 1.3加载图片资源
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
	// 3.3飞行物入场方法
	int flyingIndex = 0;

	public void EnteredAction() {
		flyingIndex++;
		// 敌机入场
		if (flyingIndex % 30 == 0) {
			FlyingObject one = nextOne();// 提取生成的一个飞机
			// 给飞行物数组flyings扩容
			flyings = Arrays.copyOf(flyings, flyings.length + 1);
			flyings[flyings.length - 1] = one;
		}
		// 英雄级子弹入场
		if (flyingIndex % 10 == 0) {
			Bullet[] bs = hero.shoot();// 提取生成的英雄机子弹
			bullets = Arrays.copyOf(bullets, bullets.length + bs.length);// 给子弹数组bullets扩容
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

	// 3.2生成一个敌机的方法
	public FlyingObject nextOne() {

		int num = random.nextInt(20);// [0,19]
		if (num >= 16)
			return new Bee();
		else
			return new AirPlane();
	}

	// boss机入场
	int flag = 0;

	public void BossEntered() {
		if ((score % 1000) >= 400 && flag == 0) {
			boss = Arrays.copyOf(boss, boss.length + 1);
			boss[boss.length - 1] = new Boss();
			// 当boss机挂掉的时候flag=0
			flag = 1;
		}
	}

	// boss机子弹入场
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

	// 2.3飞行物移动方法
	public void stepAction() {
		// 英雄机移动
		hero.step();
		// 3.6敌机移动
		for (int i = 0; i < flyings.length; i++) {
			flyings[i].step();
		}
		// 4.3英雄机子弹移动
		for (int i = 0; i < bullets.length; i++) {
			bullets[i].step();
		}
		// boss机移动
		for (int i = 0; i < boss.length; i++) {
			// 加控制变量
			if (!boss[i].getdie_status()) {
				boss[i].step();
			}
		}

		// boss机子弹移动
		for (int i = 0; i < bossbullet.length; i++) {
			bossbullet[i].step();
		}
	}

	// 子弹击中敌机的处理方法
	public void bangAction() {
		for (int i = 0; i < bullets.length; i++) {
			bang(bullets[i], i);
		}
	}

	// 碰撞(敌机撞到英雄机或者boss机子弹碰到英雄机)
	public void hitAction() {
		for (int i = 0; i < flyings.length; i++) {
			if (hero.hit(flyings[i])) {

				hero.sublife();
				if (hero.getLife() <= 0){
					state = GAME_OVER;
					flag=0;
				}
					
				// 删除碰撞到的敌机
				FlyingObject t;
				t = flyings[flyings.length - 1];
				flyings[flyings.length - 1] = flyings[i];
				flyings[i] = t;
				flyings = Arrays.copyOf(flyings, flyings.length - 1);
			}
		}
		//boss机子弹
		for(int i=0;i<bossbullet.length;i++){
			if(hero.hit(bossbullet[i])){
				hero.sublife();
				if (hero.getLife() <= 0){
					state = GAME_OVER;
					flag=0;
				}
				//删除碰撞的子弹
				bossbullet[i]=bossbullet[bossbullet.length-1];
				bossbullet=Arrays.copyOf(bossbullet, bossbullet.length-1);
			}
		}
	}

	// 子弹碰撞到boss机
	public void hitaction2() {
		int index = -1;
		for (int i = 0; i < boss.length; i++) {
			if (!boss[i].getdie_status())
				index = i;
		}
		for (int i = 0; i < bullets.length; i++) {
			if (boss[index].hit(bullets[i]) && boss[index].getType() == 2) {
				// 子弹消失
				bullets[i] = bullets[bullets.length - 1];
				bullets = Arrays.copyOf(bullets, bullets.length - 1);
				boss[index].life -= 1;
				if (boss[index].getLife() <= 0) {
					boss[index].die_status();
					flag = 0;// 可以重新生成boss机
					score = (score / 1000 + 1) * 1000;
				}
			}
		}
	}

	int score = 0;

	/* 一个子弹击中敌机的处理方法 */
	public void bang(Bullet b, int index2) {
		int index = -1;
		// 判断敌机有没有被该子弹击中
		for (int i = 0; i < flyings.length; i++)// 对于所有敌机
		{
			if (flyings[i].shootBy(b))// 如果击中
			{
				index = i;// 取出该敌机的坐标
				break;
			}
		}

		// 如果敌机被子弹击中 进行处理
		if (index != -1) {
			FlyingObject one = flyings[index];// 获取被击中的敌机
			// 对英雄机的处理
			if (one instanceof Enemy)// 敌机是AirPlane
			{
				score += 5;// 加分
			}
			if (one instanceof Award)// 敌机是Bee
			{
				Bee bee = (Bee) one;
				int type = bee.getAwardType();
				if (type == Award.DOUBLE_FIRE) {// 英雄级增加火力值
					//hero.doubleFire += 40;
					hero.addDoubleFire(40);
				}
				if (type == Award.LIFE) {// 英雄增加生命值
					//hero.life++;
					hero.addLife();
				}
			}
			/** 对子弹和被击中敌机的处理 **/
			// 删除击中敌机的子弹
			bullets[index2] = bullets[bullets.length - 1];
			bullets = Arrays.copyOf(bullets, bullets.length - 1);
			// 删除被子弹击中的敌机
			FlyingObject t;
			t = flyings[flyings.length - 1];
			flyings[flyings.length - 1] = flyings[index];
			flyings[index] = t;
			flyings = Arrays.copyOf(flyings, flyings.length - 1);
		}
	}
	
	//越界处理
	public void allOutOfBounds(){
		//将敌机越界的处理掉
		for(int i=0;i<flyings.length;i++){
			if(flyings[i].outOfBounds()){
				flyings[i]=flyings[flyings.length-1];
				flyings=Arrays.copyOf(flyings, flyings.length-1);
			}
		}
		//将英雄机子弹做越界处理
		for(int i=0;i<bullets.length;i++){
			if(bullets[i].outOfBounds()){
				bullets[i]=bullets[bullets.length-1];
				bullets=Arrays.copyOf(bullets, bullets.length-1);
			}
		}
		
		//对boss机处理，如果boss机的die属性为真则删除
		for(int i=0;i<boss.length;i++){
			if(boss[i].getdie_status()){
				boss[i]=boss[boss.length-1];
				boss=Arrays.copyOf(boss, boss.length-1);
			}
		}
		//对boss机发射的子弹做越界处理
		for(int i=0;i<bossbullet.length;i++){
			if(bossbullet[i].outOfBounds()){
				bossbullet[i]=bossbullet[bossbullet.length-1];
				bossbullet=Arrays.copyOf(bossbullet, bossbullet.length-1);
				
			}
		}
	}

	/* 程序执行方法 */
	public void action() {
		// 计时器
		Timer timer = new Timer();
		int interval = 10;// 实际为CPU周期 假设为毫秒
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if (state == RUNNING) {
					
					//越界处理
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
					
					hitAction();//这个一定要在最后，否则boss机子弹无法判断
					
					//System.out.println(bullets.length);

				}
				repaint();// 无论哪种状态 程序一直不停重画
			}
		}, interval, interval);
		// 鼠标事件监听
		MouseAdapter m = new MouseAdapter() {
			// 鼠标点击事件
			public void mouseClicked(MouseEvent arg0) {
				if (state == START) {
					state = RUNNING;
				}
				if (state == GAME_OVER) {
					state = START;
					// 清空数据 后面再写
					score=0;

					hero=new Hero();
					flyings=Arrays.copyOf(flyings, 0);
					bullets=Arrays.copyOf(bullets, 0);
					boss=Arrays.copyOf(boss, 0);
					bossbullet=Arrays.copyOf(bossbullet, 0);
					
				}
			}

			// 鼠标移动事件
			public void mouseMoved(MouseEvent e) {
				if (state == RUNNING)// 当前状态是运行状态时 才执行该操作
				{
					int x = e.getX();
					int y = e.getY();
					hero.Moveto(x, y);
				}
			}

			// 鼠标移入事件
			public void mouseEntered(MouseEvent arg0) {
				if (state == PAUSE) {
					state = RUNNING;
				}
			}

			// 鼠标移出事件
			public void mouseExited(MouseEvent arg0) {
				if (state == RUNNING) {
					state = PAUSE;
				}
			}
		};

		// 添加鼠标监听事件
		this.addMouseListener(m);// 添加鼠标操作事件
		this.addMouseMotionListener(m);// 添加鼠标滑动事件
	}

	//绘制背景
	int a = 0;
	public void paintBackground(Graphics g){
		a++;
		g.drawImage(background, 0, a, null);
		g.drawImage(background, 0, -background.getHeight() + a, null);
		if (a == background.getHeight()) {
			a = 0;
		}
	}
	
	//绘制各种飞行物
	public void paintFlyingObjects(Graphics g){
		// 2.2绘制英雄机
		g.drawImage(hero.image, hero.x, hero.y, null);
		// 3.5绘制敌机
		for (int i = 0; i < flyings.length; i++) {
			FlyingObject f = flyings[i];
			g.drawImage(f.image, f.x, f.y, null);
		}
		// 4.4绘制英雄机子弹
		for (int i = 0; i < bullets.length; i++) {
			FlyingObject f = bullets[i];
			g.drawImage(f.image, f.x, f.y, null);
		}

		// 绘制boss机
		for (int i = 0; i < boss.length; i++) {
			// 加控制变量
			if (!boss[i].die) {
				g.drawImage(boss[i].image, boss[i].x, boss[i].y, null);
			}
		}
		// 绘制boss机子弹
		for (int i = 0; i < bossbullet.length; i++) {
			FlyingObject f = bossbullet[i];
			g.drawImage(f.image, f.x, f.y, null);
		}
	}
	//绘制面板上的字体
	public void paintString(Graphics g){
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 28));
		g.setColor(Color.yellow);
		g.drawString("得分：" + score, 350, 710);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
		g.setColor(Color.red);

		g.drawString("生命：X" + hero.getLife(), 80, 710);
		g.setColor(Color.yellow);
		g.drawString("金币：" + score * 3 / 2, 230, 710);
		g.setColor(Color.green);
		g.drawString("关卡：" + (score / 1000 + 1), 400, 30);

		for (int i = 0; i < boss.length; i++) {
			if (!boss[i].die) {
				g.drawString("Boss生命值：" + boss[i].getLife() / 2 + "%", 20, 30);
			}
			// g.drawString("%", 200, 30);
		}
	}
	
	//绘制各种状态
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

	// 1.4绘制方法(先绘制的在最底层)
	@Override
	public void paint(Graphics g) {
		// super.paint(g);
		//绘制背景
		paintBackground(g);
		//绘制各种飞行物
		paintFlyingObjects(g);
		//绘制面板上的字
		paintString(g);
		// 绘制程序的几种状态
		paintStatus(g);
	}

	public static void main(String[] args) {// 主函数
		// 生成窗体
		JFrame frame = new JFrame();
		// 1.5创建面板对象
		ShootGame game = new ShootGame();
		frame.add(game);
		// 设置窗体大小
		frame.setSize(512, 768);
		// 设置窗体位置居中
		frame.setLocationRelativeTo(null);
		// 给窗体设置标题
		frame.setTitle("雷霆战机_徐平安");
		// 设置窗体关闭按钮功能
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 设置窗体大小不可改变
		frame.setResizable(false);
		// 设置窗体永远在最上方
		frame.setAlwaysOnTop(true);
		// 让窗体显示
		frame.setVisible(true);
		game.action();
	}
}
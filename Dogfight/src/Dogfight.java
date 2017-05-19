import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Dogfight extends JPanel implements KeyListener{

	private Airplane plane1, plane2;
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private ArrayList<Bomb> bombs = new ArrayList<Bomb>();
	public static Dogfight panel;

	public static final int WIDTH = 1000;
	public static final int HEIGHT = 700;
	private Image p1I, p2I;

	private int p1c, p2c;

	public static final Rectangle bounds = new Rectangle(0, 0, WIDTH, HEIGHT);
	public static final Rectangle ground = new Rectangle(-1000, HEIGHT - 75, WIDTH + 1000, 800); 
	private static Image background = new ImageIcon("background.png").getImage();

	private boolean w = false, a = false, s = false, d = false, space = false, c = false;
	private boolean up = false, down = false, left = false, right = false, slash = false, period = false;

	private int p1Lives, p2Lives;


	private Menu menu = new Menu();
	public static boolean started = false, ended;
	private int endCount = 0;


	public Dogfight(){
		p1c = 0;
		p2c = 0;
		p1Lives = 5;
		p2Lives = 5;
	}

	public static void main(String[] args){
		started = false;
		background = background.getScaledInstance(WIDTH, HEIGHT, 100);
		JFrame frame = new JFrame("Dogfight!");
		frame.setBounds(200, 100, 1000, 700);
		panel = new Dogfight();
		frame.setFocusable(true);
		frame.getContentPane().add(panel);
		panel.setBackground(Color.WHITE);
		frame.addKeyListener(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setVisible(true);

		while(true){
			panel.p1Lives = 5;
			panel.p2Lives = 5;
			panel.menu = new Menu();
			panel.menu.start();

			while(started != true){
				System.out.println("in");
			}

			panel.createP1();
			panel.createP2();
			panel.plane1 = new Airplane(panel.p1I, true);
			panel.plane2 = new Airplane(panel.p2I, false);

			panel.menu.countdown();

			while(started == true){
				frame.setFocusable(true);
				frame.requestFocus();
				//Airplane movement
				if(panel.plane1.getFlying() == true){
					if(panel.w == true){
						panel.plane1.rotate();
					}
					if(panel.a == true){
						panel.plane1.changeSpeed(-1);
					}
					if(panel.s == true){
						panel.plane1.rotateDown();
					}
					if(panel.d == true){
						panel.plane1.changeSpeed(1);
					}
					if(panel.space == true){
						panel.plane1.shoot();
					}
					if(panel.c == true){
						panel.plane1.bomb();
					}
				}
				if(panel.plane2.getFlying() == true){
					if(panel.up == true){
						panel.plane2.rotate();
					}
					if(panel.down == true){
						panel.plane2.rotateDown();
					}
					if(panel.left == true){
						panel.plane2.changeSpeed(-1);
					}
					if(panel.right == true){
						panel.plane2.changeSpeed(1);
					}
					if(panel.slash == true){
						panel.plane2.shoot();
					}
					if(panel.period == true){
						panel.plane2.bomb();
					}
				}

				//Move bullets, happens before planes move so they don't run into their own bullets
				for(int i = 0; i < panel.bullets.size(); i++){
					panel.bullets.get(i).move();
					if(panel.bullets.get(i).getHitbox().intersects(panel.plane1.getHitbox().getBounds2D())){
						panel.plane1.takeHit(panel.bullets.get(i).getDmg());
						panel.bullets.get(i).setImage(null);
					}
					if(panel.bullets.get(i).getHitbox().intersects(panel.plane2.getHitbox().getBounds2D())){
						panel.plane2.takeHit(panel.bullets.get(i).getDmg());
						panel.bullets.get(i).setImage(null);
					}
					if(!panel.bullets.get(i).getHitbox().intersects(bounds)){
						panel.removeBullet(panel.bullets.get(i));
					}
				}

				for(int i = 0; i < panel.bombs.size(); i ++){
					panel.bombs.get(i).move();
					if(panel.bombs.get(i).getHitbox().intersects(panel.plane1.getHitbox().getBounds2D())){
						panel.plane1.takeHit(panel.bombs.get(i).getDmg());
						panel.bombs.get(i).explode();
					}
					if(panel.bombs.get(i).getHitbox().intersects(panel.plane2.getHitbox().getBounds2D())){
						panel.plane2.takeHit(panel.bombs.get(i).getDmg());
						panel.bombs.get(i).explode();
					}
					if(panel.bombs.get(i).getHitbox().intersects(ground)){
						panel.bombs.get(i).setMoving(false);
						panel.bombs.get(i).explode();
					}
					if(panel.bombs.get(i).getMoving() == false){
						panel.bombs.get(i).setDraw(false);
						panel.bombs.remove(panel.bombs.get(i));
					}
				}

				if(!panel.plane1.getHitbox().intersects(bounds)){
					panel.plane1.returnToMap(bounds);
				}
				if(!panel.plane2.getHitbox().intersects(bounds)){
					panel.plane2.returnToMap(bounds);
				}

				if(panel.plane1.getHitbox().intersects(ground)){
					panel.plane1.takeHit(100);
					panel.plane1.explode();
				}
				if(panel.plane2.getHitbox().intersects(ground)){
					panel.plane2.takeHit(100);
					panel.plane2.explode();
				}
				if(panel.plane1.getMoving() == true){
					panel.plane1.move();
				}
				else{
					panel.plane1.incRespawnTimer();
				}

				if(panel.plane2.getMoving() == true){
					panel.plane2.move();
				}
				else{
					panel.plane2.incRespawnTimer();
				}

				if(panel.plane1.getRespawnTimer() > 30){
					panel.respawnP1();
				}

				if(panel.plane2.getRespawnTimer() > 30){
					panel.respawnP2();
				}


				//Shooting Timers
				panel.plane1.incCanShoot();
				panel.plane1.incCanBomb();
				panel.plane2.incCanShoot();
				panel.plane2.incCanBomb();

				//Delay between loops
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			while(ended == true){
				panel.endCount ++;
				if(panel.endCount > 100){
					ended = false;
				}
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {		
		int code = e.getKeyCode();

		if(menu.getOnMenu() == false){
			if(code == KeyEvent.VK_W){
				if(s != true){
					w = true;
				}
			}
			if(code == KeyEvent.VK_A){
				if(d != true){
					a = true;
				}
			}
			if(code == KeyEvent.VK_S){
				if(w != true){
					s = true;
				}
			}
			if(code == KeyEvent.VK_D){
				if(a != true){
					d = true;
				}
			}
			if(code == KeyEvent.VK_SPACE){
				space = true;
			}
			if(code == KeyEvent.VK_UP){
				if(down != true){
					up = true;
				}
			}
			if(code == KeyEvent.VK_DOWN){
				if(up != true){
					down = true;
				}
			}
			if(code == KeyEvent.VK_LEFT){
				if(right != true){
					left = true;
				}
			}
			if(code == KeyEvent.VK_RIGHT){
				if(left != true){
					right = true;
				}
			}
			if(code == KeyEvent.VK_SLASH){
				slash = true;
			}
			if(code == KeyEvent.VK_C){
				c = true;
			}
			if(code == KeyEvent.VK_PERIOD){
				period = true;
			}
		}
		else{
			if(code == KeyEvent.VK_A){
				panel.menu.changeP1(-1);
				repaint();
			}
			if(code == KeyEvent.VK_D){
				panel.menu.changeP1(1);
				repaint();
			}
			if(code == KeyEvent.VK_LEFT){
				panel.menu.changeP2(-1);
				repaint();
			}
			if(code == KeyEvent.VK_RIGHT){
				panel.menu.changeP2(1);
				repaint();
			}
			if(code == KeyEvent.VK_SPACE){
				panel.menu.end();
				panel.menu.setOnMenu(false);
				started = true;
				repaint();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();

		if(menu.getOnMenu() == false){
			if(code == KeyEvent.VK_W){
				w = false;
			}
			if(code == KeyEvent.VK_A){
				a = false;
			}
			if(code == KeyEvent.VK_S){
				s = false;
			}
			if(code == KeyEvent.VK_D){
				d = false;
			}
			if(code == KeyEvent.VK_SPACE){
				space = false;
			}
			if(code == KeyEvent.VK_UP){
				up = false;
			}
			if(code == KeyEvent.VK_DOWN){
				down = false;
			}
			if(code == KeyEvent.VK_LEFT){
				left = false;
			}
			if(code == KeyEvent.VK_RIGHT){
				right = false;
			}
			if(code == KeyEvent.VK_SLASH){
				slash = false;
			}
			if(code == KeyEvent.VK_C){
				c = false;
			}
			if(code == KeyEvent.VK_PERIOD){
				period = false;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//nada
	}

	private void createP1(){
		if(menu.getP1() == 1){
			p1I = new ImageIcon("bf109.png").getImage();
		}if(menu.getP1() == 2){
			p1I = new ImageIcon("mig.png").getImage();
		}
		if(menu.getP1() == 3){
			p1I = new ImageIcon("spitfire.png").getImage();
		}
		if(menu.getP1() == 4){
			p1I = new ImageIcon("zero.png").getImage();
		}
		if(menu.getP1() == 5){
			p1I = new ImageIcon("thunderbolt.png").getImage();
		}
	}

	private void createP2(){
		if(menu.getP2() == 1){
			p2I = new ImageIcon("bf109.png").getImage();
		}if(menu.getP2() == 2){
			p2I = new ImageIcon("mig.png").getImage();
		}
		if(menu.getP2() == 3){
			p2I = new ImageIcon("spitfire.png").getImage();
		}
		if(menu.getP2() == 4){
			p2I = new ImageIcon("zero.png").getImage();
		}
		if(menu.getP2() == 5){
			p2I = new ImageIcon("thunderbolt.png").getImage();
		}
	}

	private void respawnP1(){
		plane1 = new Airplane(p1I, true);
		p1Lives --;
		if(p1Lives < 0){
			started = false;
			ended = true;
		}
	}

	private void respawnP2(){
		plane2 = new Airplane(p2I, false);
		p2Lives --;
		if(p2Lives < 0){
			started = false;
			ended = true;
		}
	}


	public Menu getMenu(){
		return menu;
	}

	public int getP1Lives(){
		return p1Lives;
	}

	public int getP2Lives(){
		return p2Lives;
	}

	public void addBomb(Bomb bomb){
		panel.bombs.add(bomb);
	}

	public void removeBomb(Bomb bomb){
		panel.bombs.remove(bomb);
	}

	public void addBullet(Bullet bullet){
		panel.bullets.add(bullet);
	}

	public void removeBullet(Bullet bullet){
		panel.bullets.remove(bullet);
		bullet = new Bullet();
	}

	public static void setBackground(Image image){
		background = image;
	}

	public void paintComponent(Graphics g2){
		Graphics2D g = (Graphics2D)g2;
		double ratioX = (double)getWidth()/1000;
		double ratioY = (double)getHeight()/700;
		g.scale(ratioX, ratioY);

		g.drawImage(background, 0, 0, this);


		if(started == true){ //If the game has been started
			//plane1
			if(plane1.getMoving() == false){
				if(p1c < 5){
					plane1.draw(g, this);
					p1c = 0;
				}
				else{
					p1c ++;
				}
			}
			else{
				plane1.draw(g, this);
			}

			//plane2
			if(plane2.getMoving() == false){
				if(p2c < 3){
					plane2.draw(g, this);
					p2c = 0;
				}
				else{
					p2c ++;
				}
			}
			else{
				plane2.draw(g, this);
			}


			//Health
			int p1H = plane1.getHealth();
			int p2H = plane2.getHealth();
			if(p1H < 0){
				p1H = 0;
			}
			if(p2H < 0){
				p2H = 0;
			}
			g.setColor(Color.BLACK);
			g.drawRect(10, 5, 100, 10);
			g.drawRect(WIDTH - 110, 5, 100, 10);
			g.setColor(Color.BLUE);
			g.fillRect(10, 5, p1H, 10);
			g.drawString("P1: " + p1Lives, 115, 15);
			g.setColor(Color.RED);
			g.fillRect(WIDTH - 110, 5, p2H, 10);
			g.drawString("P2: " + p2Lives, WIDTH - 145, 15);


			if(started == true){ //If the game has been started
				for(int i = 0; i < bullets.size(); i++){
					bullets.get(i).paint(g, this);
				}
			}
			for(int i = 0; i < bombs.size(); i++){
				if(bombs.get(i).getDraw() == true){
					bombs.get(i).paint(g, this);
				}
			}
			if(Menu.counting == true){
				g.setColor(Color.RED);
				g.setFont(new Font("Serif", Font.BOLD, 50));
				g.drawString(Menu.countdown + "", WIDTH/2 - Menu.xMinus, HEIGHT/2 - 20);
			}
		}
		else{

			if(menu.getOnMenu() == true){
				menu.paintMenu(g, this);
			}
		}
		
		if(ended == true){
			g.setFont(new Font("Serif", Font.BOLD, 50));
			g.setColor(Color.RED);
			if(Dogfight.panel.getP1Lives() < 0){
				g.drawString("P2 WINS!", 400, 300);
			}
			else{
				g.drawString("P1 WINS!", 400, 300);
			}

		}
	}
}

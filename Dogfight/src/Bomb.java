import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;


public class Bomb{


	private int damage = 100;
	private int x, y;
	private int width, height;
	private Rectangle hitbox;
	private Image bomb;
	private boolean moving = true;
	private boolean drawing = true;
	private Sounds sounds = new Sounds();


	public Bomb(Rectangle startPos){
		moving = true;
		drawing = true;
		x = (int) (startPos.getX() + startPos.getWidth()/2);
		y = (int)(startPos.getY() + startPos.getHeight() + 10);
		bomb = new ImageIcon("bomb.png").getImage();
		bomb = bomb.getScaledInstance(10, 30, 100);
		width = 10;
		height = 30;
		hitbox = new Rectangle(x, y, width, height);
	}


	//Moves the bomb down 15
	public void move(){
		if(moving == true){
			y += 15;
			hitbox = new Rectangle(x, y, width, height);
		}
	}

	public boolean getMoving(){
		return moving;
	}

	public void setMoving(boolean change){
		moving = change;
	}

	public boolean getDraw(){
		return drawing;
	}

	public void setDraw(boolean change){
		drawing = change;
	}
	
	public void explode(){
		sounds.playExplosion();
		bomb = new ImageIcon("explosion.gif").getImage();
		bomb = bomb.getScaledInstance(50, 50, 100);
	}

	public Rectangle getHitbox(){
		return hitbox;
	}

	public int getDmg(){
		return damage;
	}

	//Paints the bomb
	public void paint(Graphics2D g2d, ImageObserver i){
		g2d.drawImage(bomb, x, y, i);
		g2d.setColor(Color.RED);
	}

}
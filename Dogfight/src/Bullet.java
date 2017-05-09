import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Bullet extends JPanel{

	private int x, y;
	private int angle;
	public static final int SIZE = 5;
	private int speed = 13;
	
	public Bullet(int x, int y, int angle){
		this.x = x;
		this.y = y;
		this.angle = angle;
		Dogfight.bullets.add(this);
	}
	
	public void move(){
		while(angle > 360){
			angle -= 360;
		}
		while(angle < -360){
			angle += 360;
		}
		if(angle >= 0 && angle < 90){
			y += Math.sin(Math.toRadians(angle)) * speed;
			x += Math.cos(Math.toRadians(angle))  * speed;
		}
		else if(angle >= 90 && angle < 180){
			y -= Math.sin(Math.toRadians(angle)) * speed;
			x += Math.cos(Math.toRadians(angle))  * speed;
		}
		else if(angle >= 180 && angle < 270){
			y += Math.sin(Math.toRadians(angle)) * speed;
			x += Math.cos(Math.toRadians(angle))  * speed;
		}
		else if(angle >= 270 && angle < 360){
			y += Math.sin(Math.toRadians(angle)) * speed;
			x -= Math.cos(Math.toRadians(angle))  * speed;
		}
	}
	
	public void paint(Graphics g){
		g.setColor(Color.BLACK);
		g.drawOval(x, y, SIZE, SIZE);
	}

	
	
}

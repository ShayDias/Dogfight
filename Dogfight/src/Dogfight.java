import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Dogfight extends JPanel{

	private static Airplane plane1, plane2;
	public static ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	public static Listener listener = new Listener();
	public static Dogfight panel = new Dogfight();
	
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 700;
	
	public static final Rectangle ground = new Rectangle(0, HEIGHT - 100, WIDTH, 100); 
	private Image background = new ImageIcon("background.jpg").getImage();
	
	public static void main(String[] args){
		setup();
	}
	
	
	//Creates the window
	public static void setup(){
		JFrame frame = new JFrame("Dogfight!");
		frame.setBounds(200, 100, 1000, 700);
		frame.getContentPane().add(panel);
		panel.setBackground(Color.WHITE);
		
		
		frame.setBackground(Color.WHITE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	public void paint(Graphics g){
		g.drawImage(background, 0, 0, this);
		plane1.paint(g);
		plane2.paint(g);
		for(int i = 0; i < bullets.size(); i++){
			bullets.get(i).paint(g);
		}
	}
}

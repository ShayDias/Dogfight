import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Menu {

	public static boolean counting = false;
	public static String countdown = "3";
	public static int xMinus = 30;
	private boolean onMenu;
	private Image spitfire = new ImageIcon("spitfire.png").getImage();
	private Image bf109 = new ImageIcon("bf109.png").getImage();
	private Image mig = new ImageIcon("mig.png").getImage();
	private Image thunderbolt = new ImageIcon("thunderbolt.png").getImage();
	private Image zero = new ImageIcon("zero.png").getImage();
	private int width = 200, height = 50;
	private int p1, p2;
	//	private Type p1, p2;

	private JPanel start = new JPanel();


	public Menu(){
		onMenu = true;
		spitfire = spitfire.getScaledInstance(width, height, Image.SCALE_DEFAULT);
		bf109 = bf109.getScaledInstance(width, height, Image.SCALE_DEFAULT);
		mig = mig.getScaledInstance(width, height, Image.SCALE_DEFAULT);
		thunderbolt = thunderbolt.getScaledInstance(width, height, Image.SCALE_DEFAULT);
		zero = zero.getScaledInstance(width, height, Image.SCALE_DEFAULT);
		p1 = 1;
		p2 = 5;
	}


	public boolean getOnMenu(){
		return onMenu;
	}

	public void setOnMenu(boolean x){
		onMenu = x;
	}


	public JPanel getStart(){
		return start;
	}

	public void start(){
		Dogfight.panel.repaint();
		Dogfight.setBackground(new ImageIcon("dogfight.jpg").getImage().getScaledInstance((int)Dogfight.bounds.getWidth(), (int)Dogfight.bounds.getHeight(), 100));

	}

	public void end(){
		Dogfight.setBackground(new ImageIcon("background.png").getImage().getScaledInstance((int)Dogfight.bounds.getWidth(), (int)Dogfight.bounds.getHeight(), 100));
	}

	public void paintMenu(Graphics g, ImageObserver i){

		if(Dogfight.panel.getMenu().getOnMenu() == true){
			g.setColor(new Color(0, 0, 30, 95));
			g.fillRect(325, 0, 355, 250);
			g.setColor(Color.GREEN);
			g.setFont(new Font("Serif", Font.BOLD, 60));
			g.drawString("DOGFIGHT", 350, 50);
			g.setFont(new Font("Serif", Font.BOLD, 30));
			g.drawString("Select Your Plane", 400, 100);
			g.setFont(new Font("Serif", Font.BOLD, 25));
			g.drawString("P1 = Green, P2 = Red", 405, 150);
			g.drawString("Use Arrows to Select", 405, 180);
			g.drawString("Press Space to Start", 405, 210);
			
			//Draws planes
			g.setColor(new Color(0, 0, 0, 95));
			g.fillRect(100, 300, width, height);
			g.fillRect(100, 550, width, height);
			g.fillRect(600, 300, width, height);
			g.fillRect(600, 550, width, height);
			g.fillRect(350, 400, width, height);

			g.drawImage(bf109, 100, 300, i);
			g.drawImage(spitfire, 100, 550, i);
			g.drawImage(mig, 600, 300, i);
			g.drawImage(zero, 600, 550, i);
			g.drawImage(thunderbolt, 350, 400, i);

			g.setColor(Color.GREEN);
			if(p1 == 1){
				g.drawRect(100, 300, width, height);
			}
			else if(p1 == 2){
				g.drawRect(600, 300, width, height);
			}
			else if(p1 == 3){
				g.drawRect(100, 550, width, height);
			}
			else if(p1 == 4){
				g.drawRect(600, 550, width, height);
			}
			else if(p1 == 5){
				g.drawRect(350, 400, width, height);
			}

			g.setColor(Color.RED.brighter());
			if(p2 == 1){
				g.drawRect(100, 300, width, height);
			}
			else if(p2 == 2){
				g.drawRect(600, 300, width, height);
			}
			else if(p2 == 3){
				g.drawRect(100, 550, width, height);
			}
			else if(p2 == 4){
				g.drawRect(600, 550, width, height);
			}
			else if(p2 == 5){
				g.drawRect(350, 400, width, height);
			}

		}


	}

	//Changes P1 selection
	public void changeP1(int change){
		p1 += change;
		if(p1 > 5){
			p1 = 1;
		}
		if(p1 < 1){
			p1 = 5;
		}
		while(p1 == p2){
			p1 += change;
			if(p1 > 5){
				p1 = 1;
			}
			if(p1 < 1){
				p1 = 5;
			}
		}
	}

	public int getP1(){
		return p1;
	}

	//Changes P2 selection
	public void changeP2(int change){
		p2 += change;
		if(p2 > 5){
			p2 = 1;
		}	
		if(p2 < 1){
			p2 = 5;
		}
		while(p1 == p2){
			p2 += change;
			if(p2 > 5){
				p2 = 1;
			}
			if(p2 < 1){
				p2 = 5;
			}
		}
	}

	public int getP2(){
		return p2;
	}
	//Performs the countdown
	public void countdown(){
		counting = true;
		Dogfight.panel.repaint();
		sleep(1200);
		for(int i = 0; i < Integer.parseInt(countdown); countdown = (Integer.parseInt(countdown) - 1) + "" ){
			Dogfight.panel.repaint();
			sleep(1200);
		}
		countdown = "GO";
		xMinus = 50;
		Dogfight.panel.repaint();
		sleep(1200);
		xMinus = 30;
		counting = false;
		countdown = "3";
		Dogfight.panel.repaint();
	}

	//To save on writing try/catch blocks
	private void sleep(int time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

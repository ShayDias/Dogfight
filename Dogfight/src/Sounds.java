import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class Sounds{
	public Sounds() {

	}
	
	/*public void keyPressed(KeyEvent e) {
		int KeyCode = e.getKeyCode();
		
		switch (KeyCode){
		
		case KeyEvent.VK_1:
		
		
			System.out.println("You Pressed Fire");
			KList.playSound();
			
			
		}
	}
	*/

	
//	public static void main(String[] args)
//	  {
//		JFrame window = new JFrame("BulletSounds");
//		KList canvas = new KList();
//		window.add(canvas);
//		window.setBounds(100, 100, 300, 200);
//		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		window.setVisible(true);
//
//		window.addKeyListener(canvas);
//		
//		window.add(canvas);
//		window.setResizable(true);
//
//	  }
	
	public void playSound() {
	    try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("AK47.wav").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	}
	  
	public void playSound2() {
	    try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("AK47.wav").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	}
	  

	public void playPropellers() {
	    try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("ME109.wav").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	}
	
	public void playExplosion() {
	    try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Explosion.wav").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	}
	
	public void playWhistle() {
	    try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("BombWhistle.wav").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	}
	


}

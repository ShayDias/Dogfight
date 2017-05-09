import javax.swing.JPanel;

public class Bullet extends JPanel{

	private int x, y;
	
	public Bullet(int x, int y){
		this.x = x;
		this.y = y;
		Dogfight.bullets.add(this);
	}
	

	
	
}

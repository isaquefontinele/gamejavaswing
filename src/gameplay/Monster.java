package gameplay;

import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Monster extends Creature {
	
	private MonsterType type;
	private int attack;
	private int[] pos = new int[2];
	private int width;
	private int height;
	
	public Monster() {
		this.setImage(new ImageIcon(this.getClass().getResource(
				"/images/hero.png")).getImage());
		this.width = getImage().getWidth(null);
		this.height = getImage().getHeight(null);
		this.type = MonsterType.ZOMBIE;
		this.attack = type.getAttackValue();
	}

	public void move() {

	}
	
	
	//Getters and Setters
	public Rectangle getBounds() {
		return null;
	}
	
	public int[] getPos() {
		return pos;
	}
}

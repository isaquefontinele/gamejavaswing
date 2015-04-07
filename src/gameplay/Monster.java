package gameplay;

import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Monster extends Creature {
	
	private MonsterType type;
	private int attack;
	private int width;
	private int height;
	
	public Monster() {
		this.setImage(new ImageIcon(this.getClass().getResource(
				"/images/monster1.png")).getImage());
		this.setWidth(getImage().getWidth(null));
		this.setHeight(getImage().getHeight(null));
		this.type = MonsterType.ZOMBIE;
		this.setAttack(type.getAttackValue());
	}

	public void move() {

	}
	
	
	//Getters and Setters
	public Rectangle getBounds() {
		return null;
	}
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}
}

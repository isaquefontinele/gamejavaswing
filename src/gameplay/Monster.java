package gameplay;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Monster extends Creature {

	private MonsterType type;
	private int attack;
	private int width;
	private int height;

	public Monster(MonsterType monsterType) {
		setImage(monsterType);
		this.setWidth(getImage().getWidth(null));
		this.setHeight(getImage().getHeight(null));
		this.type = MonsterType.ZOMBIE;
		this.setAttack(type.getAttackValue());
	}

	public void move() {

	}

	// Getters and Setters
	public void setImage(MonsterType monsterType) {
		switch (monsterType) {
		case ESQUELETON:
			super.setImage(new ImageIcon(this.getClass().getResource(
					"/images/esqueleton.png")).getImage());
			break;
		case SPIDER:
			super.setImage(new ImageIcon(this.getClass().getResource(
					"/images/esqueleton.png")).getImage());
			break;
		case ZOMBIE:
			super.setImage(new ImageIcon(this.getClass().getResource(
					"/images/esqueleton.png")).getImage());
			break;
		}

	}
	
	public Rectangle getBounds() {
		return new Rectangle(getX(), getY(), width, height);
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

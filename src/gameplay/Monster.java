package gameplay;

import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Monster extends Creature {

	private MonsterType type;
	private int attack;
	private int width;
	private int height;
	private int SPEED = 1;
	private int maxRange, currentRange;
	private monsterDirection direction;
	private boolean right, left, up, down;

	public Monster(MonsterType monsterType, monsterDirection direction,
			int maxRange) {
		setImage(monsterType);
//		setIncreaseX(0);
//		setIncreaseY(0);
		this.setWidth(getImage().getWidth(null));
		this.setHeight(getImage().getHeight(null));
		this.type = monsterType;
		this.setAttack(type.getAttackValue());
		this.direction = direction;
		this.maxRange = maxRange;
		this.currentRange = 0;

		setMovement();

	}

	private void setMovement() {
		if (direction == monsterDirection.HORIZONTAL) {
			this.right = true;
			this.left = false;
			this.down = false;
			this.up = false;
		} 
		if (direction == monsterDirection.VERTICAL) {
			this.down = true;
			this.up = false;
			this.right = false;
			this.left = false;
		}
	}

	public void ai() {
//		System.out.println(currentRange);
		System.out.println(this.getY());
		if (right) {
			if (currentRange <= maxRange) {
				setIncreaseX(SPEED);
				currentRange += SPEED;
			} else {
				right = false;
				left = true;
			}
		}
		if (left) {
			if (currentRange >= -maxRange) {
				setIncreaseX(-SPEED);
				currentRange -= SPEED;
			} else {
				right = true;
				left = false;
			}
		}

		if (down) {
			if (currentRange < maxRange) {
				setIncreaseY(SPEED);
				currentRange += SPEED;
			} else {
				down = false;
				up = true;
			}
		}
		if (up) {
			if (currentRange >= -maxRange) {
				setIncreaseY(-SPEED);
				currentRange -= SPEED;
			} else {
				down = true;
				up = false;
			}
		}
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

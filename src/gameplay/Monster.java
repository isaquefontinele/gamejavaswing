package gameplay;

import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Monster extends Creature {

	private MonsterType type;
	private int width;
	private int height;
	private int SPEED = 1;
	private int maxRange;
	private int currentRange = 0;
	private monsterDirection direction;
	private boolean right, left, up, down;

	public Monster() {
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
	
	public void atack(int damage) {
		setLife(getLife()-damage);
	}


	public void ai() {
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

	public void setType(MonsterType type) {
		this.type = type;
		setImage(type);
		this.setWidth(getImage().getWidth(null));
		this.setHeight(getImage().getHeight(null));
	}

	public void setDirection(monsterDirection direction) {
		this.direction = direction;
		setMovement();
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
		return type.getAttackValue();
	}

	public MonsterType getType() {
		return type;
	}

	public int getMaxRange() {
		return maxRange;
	}

	public void setMaxRange(int maxRange) {
		this.maxRange = maxRange;
	}

	public monsterDirection getDirection() {
		return direction;
	}

	public int getScore() {
		return type.getScore();
	}
}

package gameplay;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Monster extends Creature {

	private MonsterType type;
	private int width;
	private int height;
	private int SPEED;
	private int maxRange;
	private int currentRange = 0;
	private monsterDirection direction;
	private boolean right, left, up, down;
	private int batIndex = 0;
	private Image esqueleton, bat1, bat2, zombie;

	public Monster() {
	}

	/*
	 * Controls monsters movement
	 */
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

	/*
	 * Updates animation frames
	 */
	public void updateFrame() {
		if (type == MonsterType.BAT) {
			switch (batIndex) {
			case 15:
				setImage(bat1);
				break;
			case 30:
				setImage(bat2);
				break;

			}
			if (batIndex == 30) {
				batIndex = 0;
			} else {
				batIndex += 1;
			}
		}
	}

	/*
	 * Load images
	 */
	private void loadImages() {
		switch (type) {
		case ESQUELETON:
			esqueleton = new ImageIcon(this.getClass().getResource(
					"/images/esqueleton.png")).getImage();
			setImage(esqueleton);
			break;
		case BAT:
			bat1 = new ImageIcon(this.getClass()
					.getResource("/images/bat1.png")).getImage();
			bat2 = new ImageIcon(this.getClass()
					.getResource("/images/bat2.png")).getImage();
			break;
		case ZOMBIE:
			zombie = new ImageIcon(this.getClass().getResource(
					"/images/zombie.png")).getImage();
			break;
		}
	}

	// Getters and Setters
	public void setImage(MonsterType monsterType) {
		switch (monsterType) {
		case ESQUELETON:
			super.setImage(esqueleton);
			break;
		case BAT:
			super.setImage(bat1);
			break;
		case ZOMBIE:
			super.setImage(zombie);
			break;
		}
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

	public void setType(MonsterType type) {
		this.type = type;
		loadImages();
		setImage(type);
		setSpeed(type);
		this.setWidth(getImage().getWidth(null));
		this.setHeight(getImage().getHeight(null));
	}

	private void setSpeed(MonsterType type) {
		switch (type) {
		case ESQUELETON:
			SPEED = 3;
			break;
		case BAT:
			SPEED = 2;
			break;
		case ZOMBIE:
			SPEED = 1;
			break;
		}
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

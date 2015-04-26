package gameplay;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Hero extends Creature {

	private HeroClass heroClass;
	private Direction direction;
	private int SPEED = 4;
	private int width;
	private int height;
	private int shield;
	private boolean canGoRight;
	private boolean canGoLeft;
	private boolean canGoUp;
	private boolean canGoDown;
	private ArrayList<Bullet> bullets;

	public Hero(HeroClass heroClass) {
		setImage(heroClass);
		setIncreaseX(0);
		setIncreaseY(0);
		width = getImage().getWidth(null);
		height = getImage().getHeight(null);
		this.heroClass = heroClass;
		setLife(heroClass.getLife());
		direction = Direction.RIGHT;
		canGoRight = true;
		canGoLeft = true;
		canGoUp = true;
		canGoDown = true;
		setBullets(new ArrayList<Bullet>());
	}

	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
			if (canGoLeft) {
				setIncreaseX(-SPEED);
				setDirection(Direction.LEFT);
			}
		}

		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
			if (canGoRight && getX() <= 75 * 9 + width / 2) {
				setIncreaseX(SPEED);
				setDirection(Direction.RIGHT);
			}
		}

		if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
			if (canGoUp) {
				setIncreaseY(-SPEED);
				setDirection(Direction.UP);
			}
		}

		if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
			if (canGoDown) {
				setIncreaseY(SPEED);
				setDirection(Direction.DOWN);
			}
		}

		if (key == KeyEvent.VK_SPACE) {
			if (heroClass != HeroClass.WARRIOR) { // Warriors can't shoot
				shoot();
			} else {
				//TODO Implement sword attack
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
			setIncreaseX(0);
		}

		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
			setIncreaseX(0);
		}

		if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
			setIncreaseY(0);
		}

		if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
			setIncreaseY(0);
		}
	}

	private void shoot() {
		bullets.add(new Bullet(getX() + width / 2, getY() + height / 2,
				getDirection(), heroClass));
	}

	// Getters and Setters
	public void setImage(HeroClass heroClass) {
		switch (heroClass) {
		case WARRIOR:
			super.setImage(new ImageIcon(this.getClass().getResource(
					"/images/hero.png")).getImage());
			break;
		case MAGE:
			super.setImage(new ImageIcon(this.getClass().getResource(
					"/images/mage.png")).getImage());
			break;
		case HUNTER:
			super.setImage(new ImageIcon(this.getClass().getResource(
					"/images/archer.png")).getImage());
			break;
		}
	}

	public Rectangle getBounds() {
		return new Rectangle(getX(), getY(), width, height);
	}

	public void setShield(int i) {
		this.shield = i;
	}

	public int getShield() {
		return shield;
	}

	public HeroClass getHeroClass() {
		return heroClass;
	}

	public void setHeroClass(HeroClass heroClass) {
		this.heroClass = heroClass;
	}

	public void setCanGoRight(boolean canGoRight) {
		this.canGoRight = canGoRight;
	}

	public void setCanGoLeft(boolean canGoLeft) {
		this.canGoLeft = canGoLeft;
	}

	public void setCanGoUp(boolean canGoUp) {
		this.canGoUp = canGoUp;
	}

	public void setCanGoDown(boolean canGoDown) {
		this.canGoDown = canGoDown;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public ArrayList<Bullet> getBullets() {
		return bullets;
	}

	public void setBullets(ArrayList<Bullet> bullets) {
		this.bullets = bullets;
	}
}

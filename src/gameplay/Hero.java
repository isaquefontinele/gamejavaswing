package gameplay;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Hero extends Creature {

	private HeroClass heroClass;
	private int SPEED = 4;
	private int width;
	private int height;
	private int shield;
	private boolean canGoRight;
	private boolean canGoLeft;
	private boolean canGoUp;
	private boolean canGoDown;
	private int topLeftX;
	private int topLeftY;
	private int topRightX;
	private int topRightY;
	private int downLeftX;
	private int downLeftY;
	private int downRightX;
	private int downRightY;


	public Hero(HeroClass heroClass, int blockSize) {
		super.setBlockSize(blockSize);
		setImage(heroClass);
		this.width = getImage().getWidth(null);
		this.height = getImage().getHeight(null);
		this.setHeroClass(heroClass);
		this.shield = heroClass.getShieldValue();
		this.setHeroClass(heroClass);
		this.canGoRight = true;
		this.canGoLeft = true;
		this.canGoUp = true;
		this.canGoDown = true;
	}

	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
			if (canGoLeft) {
				setIncreaseX(-SPEED);
			}
		}

		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
			if (canGoRight) {
				setIncreaseX(SPEED);
			}
		}

		if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
			if (canGoUp) {
				setIncreaseY(-SPEED);
			}
		}

		if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
			if (canGoDown) {
				setIncreaseY(SPEED);
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


	// Getters and Setters
	public void setImage(HeroClass heroClass) {
		switch (heroClass) {
		case WARRIOR:
			super.setImage(new ImageIcon(this.getClass().getResource(
					"/images/hero.png")).getImage());
			break;
		case MAGE:
			super.setImage(new ImageIcon(this.getClass().getResource(
					"/images/hero.png")).getImage());
			break;
		case HUNTER:
			super.setImage(new ImageIcon(this.getClass().getResource(
					"/images/hero.png")).getImage());
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

	public int getTopLeftX() {
		return topLeftX;
	}

	public void setTopLeftX(int topLeftX) {
		this.topLeftX = topLeftX;
	}

	public int getTopLeftY() {
		return topLeftY;
	}

	public void setTopLeftY(int topLeftY) {
		this.topLeftY = topLeftY;
	}

	public int getTopRightX() {
		return topRightX;
	}

	public void setTopRightX(int topRightX) {
		this.topRightX = topRightX;
	}

	public int getTopRightY() {
		return topRightY;
	}

	public void setTopRightY(int topRightY) {
		this.topRightY = topRightY;
	}

	public int getDownLeftX() {
		return downLeftX;
	}

	public void setDownLeftX(int downLeftX) {
		this.downLeftX = downLeftX;
	}

	public int getDownLeftY() {
		return downLeftY;
	}

	public void setDownLeftY(int downLeftY) {
		this.downLeftY = downLeftY;
	}

	public int getDownRightX() {
		return downRightX;
	}

	public void setDownRightX(int downRightX) {
		this.downRightX = downRightX;
	}

	public int getDownRightY() {
		return downRightY;
	}

	public void setDownRightY(int downRightY) {
		this.downRightY = downRightY;
	}
}

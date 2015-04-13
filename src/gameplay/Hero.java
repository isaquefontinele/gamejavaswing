package gameplay;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Hero extends Creature {

	private HeroClass heroClass;
	private Direction direction;
	private int SPEED = 4;
	private int width;
	private int height;
	private int shield;

	public Hero(HeroClass heroClass, int blockSize) {
		super.setBlockSize(blockSize);
		setImage(heroClass);
		this.width = getImage().getWidth(null);
		this.height = getImage().getHeight(null);
		this.setHeroClass(heroClass);
		this.shield = heroClass.getShieldValue();
		this.setHeroClass(heroClass);
		
	}

	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
			setIncreaseX(-SPEED);
			setDirection(Direction.LEFT);

		}

		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
			setIncreaseX(SPEED);
			setDirection(Direction.RIGHT);

		}

		if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
			setIncreaseY(-SPEED);
			setDirection(Direction.UP);

		}

		if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
			setIncreaseY(SPEED);
			setDirection(Direction.DOWN);

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

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public HeroClass getHeroClass() {
		return heroClass;
	}

	public void setHeroClass(HeroClass heroClass) {
		this.heroClass = heroClass;
	}
}

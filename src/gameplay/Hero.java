package gameplay;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Hero extends Creature {

	private HeroClass heroClass;
	private Direction direction;
	private int width;
	private int height;
	private int shield;
	private boolean stopLeft;
	private boolean stopRight;
	private boolean stopUp;
	private boolean stopDown;

	public Hero(HeroClass heroClass) {
		this.setImage(new ImageIcon(this.getClass().getResource(
				"/images/hero.png")).getImage());
		this.width = getImage().getWidth(null);
		this.height = getImage().getHeight(null);
		this.heroClass = heroClass;
		this.shield = heroClass.getShieldValue();
		this.heroClass = heroClass;
	}

	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
//			if (!stopLeft) {
				setIncreaseX(-4);
				setDirection(Direction.LEFT);
//			}
		}

		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
//			if (!stopRight) {
				setIncreaseX(4);
				setDirection(Direction.RIGHT);
//			}
		}

		if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
//			if (!stopUp) {
				setIncreaseY(-4);
				setDirection(Direction.UP);
//			}
		}

		if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
//			if (!stopDown) {
				setIncreaseY(4);
				setDirection(Direction.DOWN);
//			}
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

	public void Go() {
		setStopLeft(false);
		setStopRight(false);
		setStopUp(false);
		setStopDown(false);
	}

	// Getters and Setters
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

	public void setStopLeft(boolean stopLeft) {
		this.stopLeft = stopLeft;
	}

	public void setStopRight(boolean stopRight) {
		this.stopRight = stopRight;
	}

	public void setStopUp(boolean stopUp) {
		this.stopUp = stopUp;
	}

	public void setStopDown(boolean stopDown) {
		this.stopDown = stopDown;
	}
}

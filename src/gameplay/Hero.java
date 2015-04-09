package gameplay;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Hero extends Creature {

	private HeroClass heroClass;

	private int width;
	private int height;
	private int shield;

	public Hero(HeroClass heroClass) {
		this.setImage(new ImageIcon(this.getClass().getResource(
				"/images/hero.png")).getImage());
		this.width = getImage().getWidth(null);
		this.height = getImage().getHeight(null);
		this.heroClass = HeroClass.WARRIOR;
		this.shield = heroClass.getShieldValue();
		this.heroClass = heroClass;
	}

	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
			setIncreaseX(-4);
		}

		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
			setIncreaseX(4);
		}

		if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
			setIncreaseY(-4);
		}

		if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
			setIncreaseY(4);
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
	public Rectangle getBounds() {
		return new Rectangle(getX(), getY(), width, height);
	}

	public void setShield(int i) {
		this.shield = i;
	}

	public int getShield() {
		return shield;
	}
}

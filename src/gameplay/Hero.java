package gameplay;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Hero extends Creature {

	private HeroClass heroClass;

	private Image image;
	private int width;
	private int height;
	private int shield;

	public Hero() {
		this.image = new ImageIcon(this.getClass().getResource(
				"/images/hero.png")).getImage();
		this.width = image.getWidth(null);
		this.height = image.getHeight(null);
		this.heroClass = HeroClass.WARRIOR;
		this.shield = heroClass.getShieldValue();
	}

	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			increaseX = -1;
		}

		if (key == KeyEvent.VK_RIGHT) {
			increaseX = 1;
		}

		if (key == KeyEvent.VK_UP) {
			increaseY = -1;
		}

		if (key == KeyEvent.VK_DOWN) {
			increaseY = 1;
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			increaseX = 0;
		}

		if (key == KeyEvent.VK_RIGHT) {
			increaseX = 0;
		}

		if (key == KeyEvent.VK_UP) {
			increaseY = 0;
		}

		if (key == KeyEvent.VK_DOWN) {
			increaseY = 0;
		}
	}

	// Getters and Setters
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	public void setShield(int i) {
		this.shield = i;
	}

	public int getShield() {
		return shield;
	}
}

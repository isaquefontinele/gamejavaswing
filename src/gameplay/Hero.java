package gameplay;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Hero extends Creature {

	private HeroClass heroClass;
	private int x;
	private int y;
	private int increaseX;
	private int increaseY;
	private Image image;
	private int width;
	private int height;

	public Hero() {

        image = new ImageIcon(this.getClass().getResource("hero.png")).getImage();
        width = image.getWidth(null);
        height = image.getHeight(null);
	}

	public void move() {

        x += increaseX;
        y += increaseY;

        if (x < 1) {
            x = 1;
        }

        if (y < 1) {
            y = 1;
        }
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

	
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
}

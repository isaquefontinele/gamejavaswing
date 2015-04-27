package gameplay;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Strike {
	
	private final Direction direction;
	private final int SWING_SPEED = 2;
	private final int INITIAL_X, INITIAL_Y;
	private int x, y;
	private int damage = 4;
	private int width, height;
	private Image currentImage;
	boolean visible;

	public Strike(int x, int y, Direction direction) {
		this.direction = direction;
		this.INITIAL_X = x;
		this.INITIAL_Y = y;
		this.x = x;
		this.y = y;
		setImage();
		width = currentImage.getWidth(null);
		height = currentImage.getHeight(null);
	}

	public void move() {
		switch (direction) {
		case RIGHT:
			x += SWING_SPEED;
			break;
		case LEFT:
			x -= SWING_SPEED;
			break;
		case UP:
			y -= SWING_SPEED;
			break;
		case DOWN:
			y += SWING_SPEED;
			break;
		}
	}

	// Getters and Setters
	public void setImage() {
		currentImage = new ImageIcon(this.getClass().getResource( // Just inicializing the image
				"/images/swing_down.png")).getImage();
		
		switch (direction) {
		case RIGHT:
			currentImage = new ImageIcon(this.getClass().getResource( // Just inicializing the image
					"/images/swing_right.png")).getImage();
			break;
		case LEFT:
			currentImage = new ImageIcon(this.getClass().getResource( // Just inicializing the image
					"/images/swing_left.png")).getImage();
			break;
		case UP:
			currentImage = new ImageIcon(this.getClass().getResource( // Just inicializing the image
					"/images/swing_up.png")).getImage();
			break;
		case DOWN:
			currentImage = new ImageIcon(this.getClass().getResource( // Just inicializing the image
					"/images/swing_down.png")).getImage();
			break;
		}
	}
	
	public int getRange() {
		switch (direction) {
		case RIGHT:
			return Math.abs(x-INITIAL_X);
		case LEFT:
			return Math.abs(x-INITIAL_X);
		case UP:
			return Math.abs(y-INITIAL_Y);
		default:
			return Math.abs(y-INITIAL_Y);
		}
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	
	public Image getImage() {
		return currentImage;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getSwingSpeed() {
		return SWING_SPEED;
	}
}

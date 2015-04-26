package gameplay;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Bullet {

	private final Direction direction;
	private HeroClass heroClass;
	private final int MISSILE_SPEED = 2;
	private int x, y;
	private int damage = 3;
	private int width, height;
	private Image currentImage;
	boolean visible;

	public Bullet(int x, int y, Direction direction, HeroClass heroClass) {

		this.direction = direction;
		this.heroClass = heroClass;
		setImage();
		visible = true;
		width = currentImage.getWidth(null);
		height = currentImage.getHeight(null);
		this.x = x;
		this.y = y;
	}

	public void move() {
		switch (direction) {
		case RIGHT:
			x += MISSILE_SPEED;
			break;
		case LEFT:
			x -= MISSILE_SPEED;
			break;
		case UP:
			y -= MISSILE_SPEED;
			break;
		case DOWN:
			y += MISSILE_SPEED;
			break;
		}
	}

	// Getters and Setters
	public void setImage() {
		currentImage = new ImageIcon(this.getClass().getResource( // Just inicializing the image
				"/images/arrowRight.png")).getImage();
		
		// Bullet for Mage
		if (heroClass == HeroClass.MAGE) {
			currentImage = new ImageIcon(this.getClass().getResource(
					"/images/fireball.png")).getImage();
		
		// Bullet for Hunter
		} else {
			switch (direction) {
			case RIGHT:
				currentImage = new ImageIcon(this.getClass().getResource(
						"/images/arrowRight.png")).getImage();
				break;
			case LEFT:
				currentImage = new ImageIcon(this.getClass().getResource(
						"/images/arrowLeft.png")).getImage();
				break;
			case UP:
				currentImage = new ImageIcon(this.getClass().getResource(
						"/images/arrowUp.png")).getImage();
				break;
			default: // Down
				currentImage = new ImageIcon(this.getClass().getResource(
						"/images/arrowDown.png")).getImage();
				break;
			}
		}
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public boolean isVisible() {
		return visible;
	}

	public Image getImage() {
		return currentImage;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
}
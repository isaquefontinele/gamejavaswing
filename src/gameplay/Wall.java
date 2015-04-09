package gameplay;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Wall {

	private Image image;
	private int width;
	private int height;
	private int x;
	private int y;

	public Wall() {
		this.setImage(new ImageIcon(this.getClass().getResource(
				"/images/wall.png")).getImage());
		this.setWidth(getImage().getWidth(null));
		this.setHeight(getImage().getHeight(null));
	}

	// Getters and Setters
	public Rectangle getBounds() {
		return new Rectangle(getX(), getY(), width, height);
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

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
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
}

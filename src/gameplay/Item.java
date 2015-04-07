package gameplay;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Item {

	private Image image;
	private int[] pos = new int[2]; //Change to X,Y
	private int width;
	private int height;

	public Item() {
		this.setImage(new ImageIcon(this.getClass().getResource(
				"/images/treasure.png")).getImage());
		this.setWidth(getImage().getWidth(null));
		this.setHeight(getImage().getHeight(null));
	}

	// Getters and Setters
	public int[] getPos() {
		return pos;
	}

	public void setPos(int[] pos) {
		this.pos = pos;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public int getX() {
		return pos[0];
	}

	public int getY() {
		return pos[1];
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
}

package gameplay;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Wall {
	
	private int x;
	private int y;
	private Image image;
	
	public Wall() {
		this.setImage(new ImageIcon(this.getClass().getResource(
				"/images/wall.png")).getImage());
		
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

}

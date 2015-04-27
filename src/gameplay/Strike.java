package gameplay;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Strike {
	
	private final Direction direction;

	private int x, y;
	private int damage = 4;
	private int width, height;
	private Image currentImage;
	boolean visible;

	public Strike(int x, int y, Direction direction) {
		this.direction = direction;
		this.x = x;
		this.y = y;
		setImage();
	}

	
	
	
	// Getters and Setters
	public Image getImage() {
		return currentImage;
	}

	public void setImage() {
		currentImage = new ImageIcon(this.getClass().getResource( // Just inicializing the image
				"/images/arrowRight.png")).getImage();
		
		switch (direction) {
		case RIGHT:
			
			break;
			
		case LEFT:
			
			break;
			
		case UP:
			
			break;
			
		case DOWN:
			
			break;
		}
	}
}

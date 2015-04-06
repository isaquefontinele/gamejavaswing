package gameplay;

import java.awt.Image;

public class Item {
	
	private Image image;
	private int[] pos = new int[2];
	
	public Item() {
		
	}
	
	
	// Getters and Setters
	public int[] getPos() {
		return pos;
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
}

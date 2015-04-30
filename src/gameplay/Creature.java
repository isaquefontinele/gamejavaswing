package gameplay;

import java.awt.Image;

public abstract class Creature {

	private String name;
	private Image image;
	private int life;
	private int ORIGINAL_LIFE;
	private boolean state;

	private int x;
	private int y;
	private int increaseX;
	private int increaseY;

	public void move() {
		x += increaseX;
		y += increaseY;
	}
	
	public void atack(int damage) {
		setLife(getLife()-damage);
	}

	// Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
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

	public int getIncreaseX() {
		return increaseX;
	}

	public void setIncreaseX(int increaseX) {
		this.increaseX = increaseX;
	}

	public int getIncreaseY() {
		return increaseY;
	}

	public void setIncreaseY(int increaseY) {
		this.increaseY = increaseY;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public int getORIGINAL_LIFE() {
		return ORIGINAL_LIFE;
	}

	public void setORIGINAL_LIFE(int oRIGINAL_LIFE) {
		ORIGINAL_LIFE = oRIGINAL_LIFE;
	}
}

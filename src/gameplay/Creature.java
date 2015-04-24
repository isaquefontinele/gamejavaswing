package gameplay;

import java.awt.Image;

public abstract class Creature {

	private String name;
	private Image image;
	private int life;
	private boolean state;
	private int matrixWidth;
	private int matrixHeigth;

	private int x;
	private int y;
	private int increaseX;
	private int increaseY;

	public void move() {
		x += increaseX;
		y += increaseY;
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

	public int getMatrixWidth() {
		return matrixWidth;
	}

	public void setMatrixWidth(int matrixWidth) {
		this.matrixWidth = matrixWidth;
	}

	public int getMatrixHeigth() {
		return matrixHeigth;
	}

	public void setMatrixHeigth(int matrixHeigth) {
		this.matrixHeigth = matrixHeigth;
	}
}

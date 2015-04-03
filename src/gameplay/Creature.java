package gameplay;

public abstract class Creature {

	private String name;
	private int life;
	private boolean state;
	// private position pos
	// private icon image
	protected int x;
	protected int y;
	protected int increaseX;
	protected int increaseY;

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

}

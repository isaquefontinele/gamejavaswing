package gameplay;

public enum HeroClass {
	WARRIOR(2000), MAGE(1000), HUNTER(1500);

	private int life;

	private HeroClass(int life) {
		this.life = life;
	}

	public int getLife() {
		return life;
	}
}

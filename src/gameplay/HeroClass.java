package gameplay;

public enum HeroClass {
	WARRIOR(20), MAGE(10), HUNTER(15);
	
	private int life;
	
	private HeroClass(int life) {
		this.life = life;
	}
	
	public int getLife() {
		return life;
	}
}

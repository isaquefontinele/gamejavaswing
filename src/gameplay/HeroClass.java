package gameplay;

public enum HeroClass {
	WARRIOR(10), MAGE(2), HUNTER(5);
	
	private int shieldValue;
	
	private HeroClass(int shield) {
		this.shieldValue = shield;
	}

	public int getShieldValue() {
		return shieldValue;
	}
}

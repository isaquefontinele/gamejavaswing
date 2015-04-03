package gameplay;

public enum MonsterType {
	ESQUELETON(9), ZOMBIE(5), SPIDER(3);
	private int attackValue;
	
	private MonsterType(int attack) {
		this.attackValue = attack;
	}
	
	public int getAttackValue() {
		return attackValue;
	}

}

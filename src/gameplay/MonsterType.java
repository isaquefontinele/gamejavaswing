package gameplay;

public enum MonsterType {
	ESQUELETON(5, 20), ZOMBIE(3, 15), SPIDER(2,10);
	private int attackValue, score;
	
	private MonsterType(int attack, int score) {
		this.attackValue = attack;
		this.score = score;
	}
	
	public int getAttackValue() {
		return attackValue;
	}

	public int getScore() {
		return score;
	}

}

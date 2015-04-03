package gameplay;

import java.awt.Rectangle;

public class Monster extends Creature {
	
	private MonsterType type;
	private int attack;
	
	public Monster() {
		this.type = MonsterType.ZOMBIE;
		this.attack = type.getAttackValue();
	}

	public void move() {

	}
	
	public Rectangle getBounds() {
		return null;
	}
}

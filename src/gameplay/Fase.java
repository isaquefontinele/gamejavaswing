package gameplay;

public class Fase {
	
	private final int MATRIX_LENGTH = 9;
	private final int MATRIX_HEIGHT = 9;
	private String[][] matrix = new String [9][9];
	private Item[] items;
	private Monster[] monsters;
	private int[] exitPos;
	private int[] heroPos;

	private String matrixRaw = 
			  "N N N N N N N N E\n"
			+ "N W N N N N N W N\n"
			+ "N W N W W W N W N\n"
			+ "M W N N N N N W M\n"
			+ "N N W N W N W N N\n"
			+ "N W O N M N O N N\n"
			+ "N N W W W W W N N\n"
			+ "N W N N N N N W N\n"
			+ "I N N N N N N N N\n";
	
	/**
	 *  N = Nothing == ground
	 *  W = Walls
	 *  M = Monster
	 *  H = Hero
	 *  E = Exit
	 *  I = Entrance
	 *  O = Objects
	 */
	
	public Fase() {
		// Creating a temporary item
		this.items = new Item[1];
		Item tempItem = new Item();
		int[] num = {100,100};
		tempItem.setPos(num);
		items[0] = tempItem;
		
		// Creating a temporary monster
		this.monsters = new Monster[1];
		Monster tempMonster = new Monster();
		tempMonster.setX(200);
		tempMonster.setY(200);
		monsters[0] = tempMonster;
	}
	
	private void loadMatrix() {
		String[] tempLine = new String[9];
		String[] tempColumn = new String[9];
		
		tempLine = matrixRaw.split("\n");
		for (int i = 0; i < MATRIX_HEIGHT; i++) {
			tempColumn = tempLine[i].split("\\s");
			for (int j = 0; j < MATRIX_LENGTH; j++) {
				matrix[i][j] = tempColumn[j];
			}
		}
	}
	
	private void setThingsPositions() {
//		addItems();
//		addMonsters();
//		setExitPos();
//		setHeroPos();
	}
	
	
	// Getters and Setters
	public String[][] getMatrix() {
		return matrix;
	}

	public Item[] getItems() {
		return items;
	}

	public void setItems(Item[] items) {
		this.items = items;
	}

	public Monster[] getMonsters() {
		return monsters;
	}

	public void setMonsters(Monster[] monsters) {
		this.monsters = monsters;
	}

	public int[] getExitPos() {
		return exitPos;
	}

	public void setExitPos(int[] exitPos) {
		this.exitPos = exitPos;
	}

	public int[] getHeroPos() {
		return heroPos;
	}

	public void setHeroPos(int[] heroPos) {
		this.heroPos = heroPos;
	}
}

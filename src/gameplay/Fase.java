package gameplay;

import java.util.ArrayList;
import java.util.Collection;


public class Fase {
	
	private int WINDOW_WIDTH;
	private int WINDOW_HEIGHT;
	private final int MATRIX_LENGTH = 9;
	private final int MATRIX_HEIGHT = 9;
	private String[][] matrix = new String [9][9];
	private ArrayList<Item> items;
	private ArrayList<Monster> monsters;
	private int[] exitPos;
	private int[] heroPos;
	private ArrayList<Wall> walls;

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
	 * @param wINDOW_HEIGHT 
	 * @param wINDOW_WIDTH 
	 */
	
	public Fase(int WINDOW_WIDTH, int WINDOW_HEIGHT) {
		this.WINDOW_WIDTH = WINDOW_WIDTH;
		this.WINDOW_HEIGHT = WINDOW_HEIGHT;
		// Creating a temporary item
		this.items = new ArrayList<Item>(1);
		Item tempItem = new Item();
		tempItem.setX(100);
		tempItem.setY(100);
		items.add(tempItem);
		
		// Creating a temporary monster
		this.monsters = new ArrayList<Monster>(1);
		Monster tempMonster = new Monster();
		tempMonster.setX(200);
		tempMonster.setY(200);
		monsters.add(tempMonster);
		
		walls = new ArrayList<Wall>();
		loadMatrix();
		setThingsPositions();
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
		
		int blockWidth = WINDOW_WIDTH / matrix.length;
		int blockHeight = WINDOW_HEIGHT / matrix[0].length;
		
		String[] matrixRow;
		for (int i = 0; i < matrix.length; i++) {
			matrixRow = matrix[i];
			for (int j = 0; j < matrixRow.length; j++) {
				switch (matrixRow[j]) {
				case "W":
					Wall tempWall = new Wall();
					tempWall.setX(blockWidth*j);
					tempWall.setY(blockWidth*i);
					walls.add(tempWall);
					break;
				case "O":
//					Item tempItem = new Item();
//					tempItem.setPos(pos)
					break;
				case "E":
					
					break;
				case "H":
					
					break;
				case "M":
					
					break;
				default: // Nothing
					
					break;
				}
			}
		}
	}
	
	
	// Getters and Setters
	public String[][] getMatrix() {
		return matrix;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	public ArrayList<Monster> getMonsters() {
		return monsters;
	}

	public void setMonsters(ArrayList<Monster> monsters) {
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

	public ArrayList<Wall> getWalls() {
		return walls;
	}
}

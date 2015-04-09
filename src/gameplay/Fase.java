package gameplay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;


public class Fase {
	
	private int WINDOW_WIDTH;
	private int WINDOW_HEIGHT;
	private final int MATRIX_LENGTH = 11;
	private String[][] matrix = new String [MATRIX_LENGTH][MATRIX_LENGTH];
	private ArrayList<Item> items;
	private ArrayList<Monster> monsters;
	private ArrayList<Wall> walls;
	private int heroPosX;
	private int heroPosY;
	private int exitPosX;
	private int exitPosY;
	
	

	private String matrixRaw = 

			  "W W W W W W W W W E W\n"
			+ "W N N N N N N N N N W\n"
			+ "W N W N N N N N W N W\n"
			+ "W N W N W W W N W N W\n"
			+ "W M W N N N N N W M W\n"
			+ "W N N W N W N W N N W\n"
			+ "W N W O N M N O W N W\n"
			+ "W N N W W W W W N N W\n"
			+ "W N W N N N N N W N W\n"
			+ "W N N N N H N N N N W\n"
			+ "W W W W W W W W W W W";
	
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
		
		this.items = new ArrayList<Item>();
		this.monsters = new ArrayList<Monster>();
		this.walls = new ArrayList<Wall>();
		
		loadMatrix();
		setThingsPositions();
	}
	
	private void loadMatrix() {
		String[] tempLine = new String[11];
		String[] tempColumn = new String[11];
		
		tempLine = matrixRaw.split("\n");
		for (int i = 0; i < MATRIX_LENGTH; i++) {
			tempColumn = tempLine[i].split("\\s");
			for (int j = 0; j < MATRIX_LENGTH; j++) {
				matrix[i][j] = tempColumn[j];
			}
		}
	}
	
	private void setThingsPositions() {
		
		int blockWidth = 76;
		int blockHeight = 76;
		String[] matrixRow;
		for (int i = 0; i < matrix.length; i++) {
			matrixRow = matrix[i];
			for (int j = 0; j < matrixRow.length; j++) {
				switch (matrixRow[j]) {
				case "W":
					Wall tempWall = new Wall();
					tempWall.setX(blockWidth*j);
					tempWall.setY(blockHeight*i);
					walls.add(tempWall);
					break;
				case "O":
					Item tempItem = new Item();
					tempItem.setX(blockWidth*j);
					tempItem.setY(blockHeight*i);
					items.add(tempItem);
					break;
				case "E":
					setExitPosX(blockWidth*j);
					setExitPosY(blockHeight*i);
					break;
				case "H":
					setHeroPosX(blockWidth*j);
					setHeroPosY(blockHeight*i);
					break;
				case "M":
					Monster tempMonster = new Monster(MonsterType.ESQUELETON);
					tempMonster.setX(blockWidth*j);
					tempMonster.setY(blockHeight*i);
					monsters.add(tempMonster);
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

	public ArrayList<Wall> getWalls() {
		return walls;
	}

	public int getHeroPosX() {
		return heroPosX;
	}

	public void setHeroPosX(int heroPosX) {
		this.heroPosX = heroPosX;
	}

	public int getHeroPosY() {
		return heroPosY;
	}

	public void setHeroPosY(int heroPosY) {
		this.heroPosY = heroPosY;
	}

	public int getExitPosX() {
		return exitPosX;
	}

	public void setExitPosX(int exitPosX) {
		this.exitPosX = exitPosX;
	}

	public int getExitPosY() {
		return exitPosY;
	}

	public void setExitPosY(int exitPosY) {
		this.exitPosY = exitPosY;
	}
}

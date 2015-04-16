package gameplay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;


public class Fase {
	
	private int WINDOW_WIDTH;
	private int WINDOW_HEIGHT;
	private final int MATRIX_LENGTH = 11;
	private int faseNum = 1;
	private String[][] matrix = new String [MATRIX_LENGTH][MATRIX_LENGTH];
	private ArrayList<Item> items;
	private ArrayList<Monster> monsters;
	private ArrayList<Wall> wallsToPrint;
	private ArrayList<Wall> wallsToCollision;
	private ArrayList<Integer> groundTilesX;
	private ArrayList<Integer> groundTilesY;
	private int blockSize;
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
	
	public Fase(int WINDOW_WIDTH, int WINDOW_HEIGHT, int blockSize) {
		this.WINDOW_WIDTH = WINDOW_WIDTH;
		this.WINDOW_HEIGHT = WINDOW_HEIGHT;
		setBlockSize(blockSize);
		
		this.items = new ArrayList<Item>();
		this.monsters = new ArrayList<Monster>();
		this.wallsToPrint = new ArrayList<Wall>();
		this.wallsToCollision = new ArrayList<Wall>();
		this.groundTilesX = new ArrayList<Integer>();
		this.groundTilesY = new ArrayList<Integer>();
		
		
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
		
		int blockWidth = 75;
		int blockHeight = 75;
		String[] matrixRow;
		for (int i = 0; i < matrix.length; i++) {
			matrixRow = matrix[i];
			for (int j = 0; j < matrixRow.length; j++) {
				switch (matrixRow[j]) {
				case "W": 
					Wall tempWall = new Wall();
					tempWall.setX(blockWidth*j);
					tempWall.setY(blockHeight*i);
					//Otimization: exclude external walls from collison
					if (i != 0 && i != matrix.length-1) {
						if (j != 0 && j != matrixRow.length-1) {
							wallsToCollision.add(tempWall);
						}
					}
					wallsToPrint.add(tempWall);
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
					groundTilesX.add(blockWidth*j);
					groundTilesY.add(blockHeight*i);
					break;
				case "H":
					setHeroPosX(blockWidth*j);
					setHeroPosY(blockHeight*i);
					groundTilesX.add(blockWidth*j);
					groundTilesY.add(blockHeight*i);
					break;
				case "M":
					Monster tempMonster = new Monster(MonsterType.ESQUELETON, monsterDirection.HORIZONTAL, 100);
					tempMonster.setX(blockWidth*j);
					tempMonster.setY(blockHeight*i);
					monsters.add(tempMonster);
					groundTilesX.add(blockWidth*j);
					groundTilesY.add(blockHeight*i);
					break;
				default: // Nothing
					groundTilesX.add(blockWidth*j);
					groundTilesY.add(blockHeight*i);
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

	public ArrayList<Wall> getWallsToPrint() {
		return wallsToPrint;
	}
	
	public ArrayList<Wall> getWallsToCollision() {
		return wallsToCollision;
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

	public int getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}

	public int getFaseNum() {
		return faseNum;
	}

	public void setFaseNum(int faseNum) {
		this.faseNum = faseNum;
	}
	
	public int getMatrixWidth() {
		return matrix.length;
	}

	public int getMatrixHeight() {
		return matrix[0].length;
	}
	
	public ArrayList<Integer> getGroundTilesX() {
		return groundTilesX;
	}

	public ArrayList<Integer> getGroundTilesY() {
		return groundTilesY;
	}
}

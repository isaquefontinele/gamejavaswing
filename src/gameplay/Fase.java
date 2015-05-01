package gameplay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;


public class Fase {
	
	private int MATRIX_ROWS = 12;
	private int MATRIX_COLUMNS = 11;
	private int BLOCK_SIZE = 75;

	private int monstersTotal = 3;
	private int torchesTotal = 4;
	private int itemsTotal = 2;
	private int heroPosX;
	private int heroPosY;
	private int exitPosX;
	private int exitPosY;
	private String[][] matrix;
	private String[][] matrixMonstersConfigs;
	private String[] matrixTorchesConfigs;
	private ArrayList<Item> items;
	private ArrayList<Monster> monsters;
	private ArrayList<Wall> walls;
	private ArrayList<Torch> torches;
	private ArrayList<Integer> groundTilesX;
	private ArrayList<Integer> groundTilesY;
	private Wall doorWall;
	
	private String loadString;
	private String matrixRaw;
	private String monsterConfig;
	private String torchesConfig;

	/**
	 *  N = Nothing == ground
	 *  W = Walls
	 *  M = Monster
	 *  H = Hero
	 *  E = Exit
	 *  I = Entrance
	 *  O = Objects
	 *  T = Torch
	 * @param wINDOW_HEIGHT 
	 * @param wINDOW_WIDTH 
	 */
	
	public Fase(String newFase) {
		setBlockSize(BLOCK_SIZE);
		
		this.loadString = newFase;
		this.items = new ArrayList<Item>();
		this.monsters = new ArrayList<Monster>();
		this.walls = new ArrayList<Wall>();
		this.groundTilesX = new ArrayList<Integer>();
		this.groundTilesY = new ArrayList<Integer>();
		this.torches = new ArrayList<Torch>();

		rawBreak();
		loadMatrix();
		loadMonstersConfig();
		loadTorchesConfig();
		setThingsPositions();
	}
	
	private void rawBreak() {
		
		
		String[] temp = loadString.split("\n");
		String[] dimension = temp[0].split("x");
		
		
		this.MATRIX_ROWS = Integer.parseInt(dimension[0].trim());
		this.MATRIX_COLUMNS = Integer.parseInt(dimension[1].trim());
		String tempo = "";
		for (int i = 1; i < MATRIX_ROWS+1; i++) {
			tempo+=temp[i];
			if (i != MATRIX_ROWS) {
				tempo+="\n";
			}
		}
		matrixRaw = tempo;
		monstersTotal = Integer.parseInt(temp[MATRIX_ROWS+1].trim());
		tempo = "";
		for (int i = MATRIX_ROWS+2; i < (MATRIX_ROWS+2)+monstersTotal; i++) {
			tempo+=temp[i];
			if (i != (MATRIX_ROWS+1)+monstersTotal) {
				tempo+="\n";
			}
		}
		monsterConfig = tempo;
		itemsTotal = Integer.parseInt(temp[(MATRIX_ROWS+monstersTotal)+2].trim());
		tempo = "";
		for (int i = (MATRIX_ROWS+monstersTotal)+3; i < ((MATRIX_ROWS+monstersTotal)+3+itemsTotal); i++) {
			tempo+=temp[i];
		}
		
		
		
		torchesTotal = Integer.parseInt(temp[(MATRIX_ROWS+monstersTotal)+3+itemsTotal].trim());
		tempo = "";
		for (int i = (MATRIX_ROWS+monstersTotal)+4+itemsTotal; i < ((MATRIX_ROWS+monstersTotal)+4+itemsTotal+torchesTotal); i++) {
			tempo+=temp[i];
			if (i !=  ((MATRIX_ROWS+monstersTotal)+4+itemsTotal+torchesTotal)) {
				tempo+="\n";
			}
		}
		torchesConfig = tempo;
	}

	private void loadMatrix() {
		String[] tempLine = new String[3];
		String[] tempColumn = new String[MATRIX_COLUMNS];
		
		matrix = new String [MATRIX_ROWS][MATRIX_COLUMNS];
		
		tempLine = matrixRaw.split("\n");
		for (int i = 0; i < MATRIX_ROWS; i++) {
			tempColumn = tempLine[i].split("\\s");
			for (int j = 0; j < MATRIX_COLUMNS; j++) {
				matrix[i][j] = tempColumn[j];
			}
		}
		
	}
	
	private void loadMonstersConfig() {
		matrixMonstersConfigs = new String[monstersTotal][3];
		String[] tempLine = new String[3];
		String[] tempColumn = new String[3];
				
		tempLine = monsterConfig.split("\n");
		for (int i = 0; i < monstersTotal; i++) {
			tempColumn = tempLine[i].split("\\s");
			for (int j = 0; j < 3; j++) {
				matrixMonstersConfigs[i][j] = tempColumn[j];
			}
		}
	}
	
	private void loadTorchesConfig() {
		matrixTorchesConfigs = new String[torchesTotal];
		String[] tempLine = new String[3];
				
		matrixTorchesConfigs = torchesConfig.split("\n");
	}
	
	private void setThingsPositions() {
		int monsterIndex = 0;
		int torchesIndex = 0;
		String[] matrixRow;
		for (int i = 0; i < matrix.length; i++) {
			matrixRow = matrix[i];
			for (int j = 0; j < matrixRow.length; j++) {
				
				switch (matrixRow[j]) {
				
				case "W": 
					Wall tempWall = new Wall();
					tempWall.setX(BLOCK_SIZE*j);
					tempWall.setY(BLOCK_SIZE*i);
					walls.add(tempWall);
					break;
				case "O":
					Item tempItem = new Item();
					tempItem.setX(BLOCK_SIZE*j);
					tempItem.setY(BLOCK_SIZE*i);
					items.add(tempItem);
					// Ground tile
					groundTilesX.add(BLOCK_SIZE*j);
					groundTilesY.add(BLOCK_SIZE*i);
					break;
				case "E":
					setExitPosX(BLOCK_SIZE*j);
					setExitPosY(BLOCK_SIZE*i);
					// Ground tile
					groundTilesX.add(BLOCK_SIZE*j);
					groundTilesY.add(BLOCK_SIZE*i);
					
					Wall doorWall = new Wall();
					doorWall.setX(BLOCK_SIZE*j);
					doorWall.setY(BLOCK_SIZE*i);
					setDoorWall(doorWall);
					walls.add(doorWall);
					break;
				case "H":
					setHeroPosX(BLOCK_SIZE*j);
					setHeroPosY(BLOCK_SIZE*i);
					// Ground tile
					groundTilesX.add(BLOCK_SIZE*j);
					groundTilesY.add(BLOCK_SIZE*i);
					break;
				case "M":
					// Create generic monster
					Monster tempMonster = new Monster();
					// Set Type
					if (matrixMonstersConfigs[monsterIndex][0].equals("S")) {
						tempMonster.setType(MonsterType.ESQUELETON);
						tempMonster.setLife(10);
						tempMonster.setORIGINAL_LIFE(10);
					} else if (matrixMonstersConfigs[monsterIndex][0].equals("B")) {
						tempMonster.setType(MonsterType.BAT);
						tempMonster.setLife(6);
						tempMonster.setORIGINAL_LIFE(6);
					} else if (matrixMonstersConfigs[monsterIndex][0].equals("Z")) {
						tempMonster.setType(MonsterType.ZOMBIE);
						tempMonster.setLife(10);
						tempMonster.setORIGINAL_LIFE(10);
					}
					// Set Direction of Movement
					if (matrixMonstersConfigs[monsterIndex][1].equals("H")) {
						tempMonster.setDirection(monsterDirection.HORIZONTAL);
					} else {
						tempMonster.setDirection(monsterDirection.VERTICAL);
					}
					// Set max Range
					tempMonster.setMaxRange(Integer.parseInt(matrixMonstersConfigs[monsterIndex][2]));
					// Set location
					tempMonster.setX(BLOCK_SIZE*j);
					tempMonster.setY(BLOCK_SIZE*i);
					monsters.add(tempMonster);
					monsterIndex++;
					// Ground tile
					groundTilesX.add(BLOCK_SIZE*j);
					groundTilesY.add(BLOCK_SIZE*i);
					break;
				case "T":
					// Add torch
					Torch tempTorch = new Torch();
					tempTorch.setX(BLOCK_SIZE*j);
					tempTorch.setY(BLOCK_SIZE*i);
					if (matrixTorchesConfigs[torchesIndex].trim().equals("L")) {
						tempTorch.setDirection(Direction.LEFT);
					} else {
						tempTorch.setDirection(Direction.RIGHT);
					}
					torches.add(tempTorch);
					torchesIndex++;
					
					// Add wall
					Wall tempWall2 = new Wall();
					tempWall2.setX(BLOCK_SIZE*j);
					tempWall2.setY(BLOCK_SIZE*i);
					walls.add(tempWall2);
					break;
				default: // Nothing
					// Ground tile
					groundTilesX.add(BLOCK_SIZE*j);
					groundTilesY.add(BLOCK_SIZE*i);
				}
			}
		}
	}
	
	
	public ArrayList<Torch> getTorches() {
		return torches;
	}

	public void setTorches(ArrayList<Torch> torches) {
		this.torches = torches;
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

	public void setWalls(ArrayList<Wall> walls) {
		this.walls = walls;
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
		return BLOCK_SIZE;
	}

	public void setBlockSize(int blockSize) {
		this.BLOCK_SIZE = blockSize;
	}
	
	public int getMatrixWidth() {
		return MATRIX_ROWS;
	}

	public int getMatrixHeight() {
		return MATRIX_COLUMNS;
	}
	
	public ArrayList<Integer> getGroundTilesX() {
		return groundTilesX;
	}

	public ArrayList<Integer> getGroundTilesY() {
		return groundTilesY;
	}

	public Wall getDoorWall() {
		return doorWall;
	}

	public void setDoorWall(Wall doorWall) {
		this.doorWall = doorWall;
	}
}

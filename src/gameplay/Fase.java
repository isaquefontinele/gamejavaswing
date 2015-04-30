package gameplay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;


public class Fase {
	
	private final int MATRIX_LENGTH = 11;
	private int faseNum = 1;
	private int monstersTotal = 3;
	private int torchesTotal = 4;
	private int blockSize;
	private int heroPosX;
	private int heroPosY;
	private int exitPosX;
	private int exitPosY;
	private String[][] matrix = new String [MATRIX_LENGTH][MATRIX_LENGTH];
	private String[][] matrixMonstersConfigs = new String[monstersTotal][3];
	private String[] matrixTorchesConfigs = new String[torchesTotal];
	private ArrayList<Item> items;
	private ArrayList<Monster> monsters;
	private ArrayList<Wall> walls;
	private ArrayList<Torch> torches;
	private ArrayList<Integer> groundTilesX;
	private ArrayList<Integer> groundTilesY;
	private Wall doorWall;

	private String matrixRaw = 

			  "W W W W W W W W W E W\n"
			+ "W N N N N N N N N N W\n"
			+ "W N W N N N N N W N W\n"
			+ "W N T N W W W N T N W\n"
			+ "W M W N N N N N W M W\n"
			+ "W N N W N W N W N N W\n"
			+ "W N W O N M N O W N W\n"
			+ "T N N W W W W W N N T\n"
			+ "W N W N N N N N W N W\n"
			+ "W N N N N H N N N N W\n"
			+ "W W W W W W W W W W W";

	private String monsterConfig = 
			
			  "E V 100\n"
			+ "E V 100\n"
			+ "B H 100";
	
	private String torchesConfig =
			  "R\n"
			+ "L\n"
			+ "R\n"
			+ "L";
	
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
	
	public Fase(int blockSize) {
		setBlockSize(blockSize);
		
		this.items = new ArrayList<Item>();
		this.monsters = new ArrayList<Monster>();
		this.walls = new ArrayList<Wall>();
		this.groundTilesX = new ArrayList<Integer>();
		this.groundTilesY = new ArrayList<Integer>();
		this.torches = new ArrayList<Torch>();
		
		
		
		loadMatrix();
		loadMonstersConfig();
		loadTorchesConfig();
		setThingsPositions();
	}
	
	private void loadMatrix() {
		String[] tempLine = new String[3];
		String[] tempColumn = new String[11];
		
		tempLine = matrixRaw.split("\n");
		for (int i = 0; i < MATRIX_LENGTH; i++) {
			tempColumn = tempLine[i].split("\\s");
			for (int j = 0; j < MATRIX_LENGTH; j++) {
				matrix[i][j] = tempColumn[j];
			}
		}
	}
	
	private void loadMonstersConfig() {
		String[] tempLine = new String[3];
		String[] tempColumn = new String[3];
		
		tempLine = monsterConfig.split("\n");
		for (int i = 0; i < monstersTotal; i++) {
			tempColumn = tempLine[i].split("\\s");
			for (int j = 0; j < 3; j++) {
				matrixMonstersConfigs[i][j] = tempColumn[j];
			}
		}
//		Arrays.deepToString(matrixMonstersConfigs);
	}
	
	private void loadTorchesConfig() {
		String[] tempLine = new String[3];
				
		matrixTorchesConfigs = torchesConfig.split("\n");
		Arrays.toString(matrixTorchesConfigs);
	}
	
	private void setThingsPositions() {
		int monsterIndex = 0;
		int torchesIndex = 0;
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
					walls.add(tempWall);
					break;
				case "O":
					Item tempItem = new Item();
					tempItem.setX(blockWidth*j);
					tempItem.setY(blockHeight*i);
					items.add(tempItem);
					// Ground tile
					groundTilesX.add(blockWidth*j);
					groundTilesY.add(blockHeight*i);
					break;
				case "E":
					setExitPosX(blockWidth*j);
					setExitPosY(blockHeight*i);
					// Ground tile
					groundTilesX.add(blockWidth*j);
					groundTilesY.add(blockHeight*i);
					
					Wall doorWall = new Wall();
					doorWall.setX(blockWidth*j);
					doorWall.setY(blockHeight*i);
					setDoorWall(doorWall);
					walls.add(doorWall);
					break;
				case "H":
					setHeroPosX(blockWidth*j);
					setHeroPosY(blockHeight*i);
					// Ground tile
					groundTilesX.add(blockWidth*j);
					groundTilesY.add(blockHeight*i);
					break;
				case "M":
					// Create generic monster
					Monster tempMonster = new Monster();
					// Set Type
					if (matrixMonstersConfigs[monsterIndex][0].equals("E")) {
						tempMonster.setType(MonsterType.ESQUELETON);
						tempMonster.setLife(12);
						tempMonster.setORIGINAL_LIFE(12);
					} else if (matrixMonstersConfigs[monsterIndex][0].equals("B")) {
						tempMonster.setType(MonsterType.BAT);
						tempMonster.setLife(6);
						tempMonster.setORIGINAL_LIFE(6);
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
					tempMonster.setX(blockWidth*j);
					tempMonster.setY(blockHeight*i);
					monsters.add(tempMonster);
					monsterIndex++;
					// Ground tile
					groundTilesX.add(blockWidth*j);
					groundTilesY.add(blockHeight*i);
					break;
				case "T":
					// Add torch
					Torch tempTorch = new Torch();
					tempTorch.setX(blockWidth*j);
					tempTorch.setY(blockHeight*i);
					if (matrixTorchesConfigs[torchesIndex].equals("L")) {
						tempTorch.setDirection(Direction.LEFT);
					} else {
						tempTorch.setDirection(Direction.RIGHT);
					}
					torches.add(tempTorch);
					torchesIndex++;
					
					// Add wall
					Wall tempWall2 = new Wall();
					tempWall2.setX(blockWidth*j);
					tempWall2.setY(blockHeight*i);
					walls.add(tempWall2);
					break;
				default: // Nothing
					// Ground tile
					groundTilesX.add(blockWidth*j);
					groundTilesY.add(blockHeight*i);
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

	public Wall getDoorWall() {
		return doorWall;
	}

	public void setDoorWall(Wall doorWall) {
		this.doorWall = doorWall;
	}
}

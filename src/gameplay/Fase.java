package gameplay;

public class Fase {
	
	private final int MATRIX_LENGTH = 9;
	private final int MATRIX_HEIGHT = 9;
	private String[][] matrix = new String [9][9];
	private Item[] items;

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
	
	public Fase() {
		
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
	
	
	// Getters and Setters
	public String[][] getMatrix() {
		return matrix;
	}
	
	public int getNumOfItems() {
		return items.length;
	}
}

package menu;

public class Score implements Comparable<Score> {

	private int points;

	// private Player player;
	private String player;

	public Score(String player, int points) {
		this.points = points;
		this.player = player;
	}

	// Reads in a string with the correct information separating information
	// blocks by " "
	public Score(String fileInput) {
		String[] tokens = fileInput.split(" ");

		player = tokens[0].trim();
		points = Integer.parseInt(tokens[1].trim());
	}

	@Override
	// player.getName();
	public String toString() {
		return "Name: " + player + " - Score: " + points;
	}

	public String fileToString() {
		return (player + " " + points);
	}

	@Override
	public int compareTo(Score other) {
		return (other.getPoints() - this.points);
	}

	// Getters and Setters
	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}
}

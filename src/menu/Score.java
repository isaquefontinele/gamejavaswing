package menu;

public class Score implements Comparable<Score> {

	private int points;
	
	//Time may be more useful as a timestamp, this may complicate the file
	//we could just keep track of seconds or something like that
	private int time;
	//private Player player;
	private String player;

	public Score(String player, int time, int points) {
		this.points = points;
		this.time = time;
		this.player = player;
	}
	
	/*public Score(Player player, int time, int points) {
		this.points = points;
		this.time = time;
		this.player = player;
	}	*/
	
	//Reads in a string with the correct information separating information blocks by " " 
	public Score(String fileInput)
	{
		String[] tokens = fileInput.split(" ");
		
		player = tokens[0].trim();
		points = Integer.parseInt(tokens[1].trim());
		time = Integer.parseInt(tokens[2].trim());
	}

	@Override
	//player.getName();
	public String toString() {
		return "Name: " + player + " - Score: " + points
				+ " - Time: " + time;
	}

	@Override
	public int compareTo(Score other) {
		return this.points - other.getPoints();
	}

	// Getters and Setters
	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

	/*public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}*/
}

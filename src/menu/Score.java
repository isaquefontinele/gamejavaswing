package menu;

import gameplay.Player;

public class Score implements Comparable<Score> {

	private int points;
	private int time;
	private Player player;

	public Score(Player player, int time, int points) {
		this.points = points;
		this.time = time;
		this.player = player;
	}

	@Override
	public String toString() {
		return "Name: " + player.getName() + " - Score: " + points
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

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}

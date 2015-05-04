package gameplay;

public class Player {

	private String name;
	private int id;

	public Player(String name) {
		this.name = name;
		this.id = 0000; // To implement

	}

	@Override
	public String toString() {
		return "Name: " + name;
	}

	// Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}

import javax.swing.JButton;


public class Scoreboard extends Jpanel{

	private Score[] scores;
	
	public Scoreboard(int WINDOW_WIDTH, int WINDOW_HEIGHT)
	{
		setLayout(new GridLayout(11, 1));
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setBackground(Color.GREEN);
		Action backAction = new backAction();
		JButton back = new JButton(backAction);
		back.setText("Back");
		
		//Need "empty" buttons to store score information, create a new scorebutton class to facilitate this
		//For should have default scores initialized before this or load scores from a file
		add(back);
	}
	
	public void setBoard(boolean det)
	{
		Component[] components1 = getComponents();
		for (int i = 0; i < components2.length; i++) {
			components1[i].setEnabled(det);
		}	
	}
	

}

package menu;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Scoreboard extends JPanel{

	private Score[] scores;
	private File f;
	private Score newScore;
	private String filepath;
	private FileReader read;
	
	public Scoreboard(int WINDOW_WIDTH, int WINDOW_HEIGHT)
	{
		scores = new Score[10];
//Default array of scores
		newScore = new Score("DLO", 1000, 600);
		for(int i = 0; i < 10; i++)
		{
			scores[i] = newScore;
		}
		
		
//Unsuccessful at reading from file, it has no errors and fails to bring up the gui
//		filepath = "C:\\Users\\Sirdanile\\git\\gamejavaswing\\src\\menu\\GameScores.txt";
//		f = new File(filepath);
//		try {
//			read = new FileReader(f);
//		} catch (FileNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//
//		
//		
//		for(int i = 0; i < 10; i++)
//		{
//			char check = ' ';
//			String gen = "";
//			while(check != '\n')
//			{
//				try {
//					check = (char) read.read();
//				} catch (IOException e) {
//				}
//				if(check != '\n')
//				{
//					gen = gen + check;
//				}
//			}
//			newScore = new Score(gen);
//			scores[i] = newScore;
//		}
//		try {
//			read.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setLayout(new GridLayout(10, 1));

		setBackground(Color.WHITE);
		for(int i = 0; i < 10; i++)
		{
			add(new JLabel(scores[i].toString()));	
		}
		


		
		//Need "empty" buttons to store score information, create a new scorebutton class to facilitate this
		//For should have default scores initialized before this or load scores from a file
	}

}

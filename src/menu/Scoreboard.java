package menu;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

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
		filepath = "/menu/GameScores.txt";
		// Load the directory as a resource
		URL dir_url = this.getClass().getResource(filepath);
		// Turn the resource into a File object
		try {
			f = new File(dir_url.toURI());
		} catch (URISyntaxException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			read = new FileReader(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i = 0; i < 10; i++)
		{
			System.out.println(i);
			char check = ' ';
			String gen = "";
			while(check != '\n')
			{
				System.out.println(i);
				try {
					check = (char) read.read();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if(check != '\n' && (int)check != -1)
				{
					gen = gen + check;
				}
			}
			newScore = new Score(gen);
			scores[i] = newScore;
		}
		try
		{
			read.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setLayout(new GridLayout(10, 1));

		setBackground(Color.WHITE);
		for(int i = 0; i < 10; i++)
		{
			add(new JLabel(scores[i].toString()));	
		}
	}
}

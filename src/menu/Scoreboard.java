package menu;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;

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
		filepath = "/menu/GameScores.txt";
		scores = new Score[10];
		newScore = new Score("DLO", 1000, 600);
		for(int i = 0; i < 10; i++)
		{
			scores[i] = newScore;
		}		
		readFromFile();

		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setLayout(new GridLayout(10, 1));
		setBackground(Color.WHITE);
		updateLabels();
		printScores();
	}
	
	public void updateLabels()
	{
		this.removeAll();
		for(int i = 0; i < 10; i++)
		{
			add(new JLabel(scores[i].toString()));	
		}
	}
	
	//Writes the current score board to file to be read later
	public void writeToFile()
	{
		PrintWriter out;
		String temp = "";
		for(int i = 0; i < 10; i++)
		{
			temp += scores[i].fileToString();
			temp += "\n";
		}
		
		URL dir_url = this.getClass().getResource(filepath);
		File tempFile;
		try {
			tempFile = new File(dir_url.toURI());
			out = new PrintWriter(tempFile);
			out.println(temp);
			out.close();		
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//Reads default file to score board
	public void readFromFile()
	{
		// Load the directory as a resource
		URL dir_url = this.getClass().getResource(filepath);
		// Turn the resource into a File object
		try {
			f = new File(dir_url.toURI());
		} catch (URISyntaxException e2) {
			e2.printStackTrace();
		}
		try {
			read = new FileReader(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < 10; i++)
		{
			char check = ' ';
			String gen = "";
			while(check != '\n')
			{
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
			e.printStackTrace();
		}	
		updateLabels();
	}
	
	//Adds a new score into the array, sorts the array by score, then truncates to the top 10 scores again 
	public void updateWithNewScore(Score newScore)
	{
		Score[] tempScore = new Score[11];
		for(int i = 0; i < 10; i++)
		{
			tempScore[i] = scores[i];
		}
		tempScore[10] = newScore;
		Arrays.sort(tempScore);
		for(int i = 0; i < 10; i++)
		{
			scores[i] = tempScore[i];
		}
		updateLabels();
	}
	
	public void printScores()
	{
		for(int i = 0; i < 10; i++)
		{
			System.out.println(scores[i].toString());
		}
	}
	
	public void defaultScores()
	{
		String newScores = "DLO 1000 100\nABC 900 150\nDEF 800 200\nGHI 700 250\n"
				+ "JKL 600 300\nMNO 500 350\nPQR 400 400\nSTU 300 450\nVWX 200 500\nYZA 100 550\n";
		PrintWriter out;
		URL dir_url = this.getClass().getResource(filepath);
		File tempFile;
		try {
			tempFile = new File(dir_url.toURI());
			out = new PrintWriter(tempFile);
			out.println(newScores);
			out.close();		
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		readFromFile();
	}
	
}

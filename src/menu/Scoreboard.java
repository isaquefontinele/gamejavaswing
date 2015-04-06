package menu;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;


public class Scoreboard extends JPanel{

	private Score[] scores;
	
	public Scoreboard(int WINDOW_WIDTH, int WINDOW_HEIGHT)
	{
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
//		setLayout(new GridLayout(10, 1));
		
		setBackground(Color.GREEN);
		add(new JButton());
		add(new JTextPane());


		
		//Need "empty" buttons to store score information, create a new scorebutton class to facilitate this
		//For should have default scores initialized before this or load scores from a file
	}
	
}

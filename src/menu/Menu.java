package menu;

//package action;

import gameplay.Gameplay;
import gameplay.HeroClass;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * A frame with a panel that demonstrates color change actions.
 */
public class Menu extends JFrame {

	private JPanel buttonPanel, showPanel;
	private static Dimension screenSize;
	private static int WINDOW_WIDTH;
	private static int WINDOW_HEIGHT;
	private static double windowSize = 0.8; // Size of the window in percentage
	private GridLayout manager;
	private Scoreboard scores;
	private Gameplay gameplay;
	private HeroClass currentHeroClass;
	public boolean inGame = false;

	public Menu() {

		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		WINDOW_WIDTH = (int) Math.round(screenSize.width * windowSize);
		WINDOW_HEIGHT = (int) Math.round(screenSize.height * windowSize);
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(5, 1));
		showPanel = new JPanel();
		showPanel.setLayout(new GridLayout(1,1));
		scores = new Scoreboard(WINDOW_WIDTH/2, WINDOW_HEIGHT);

		// define actions
		Action playAction = new playAction();
		Action creditsAction = new creditsAction();
		Action settingsAction = new settingsAction();
		Action scoresAction = new scoresAction();
		Action exitAction = new exitAction();

		// define buttons
		JButton playButton = new JButton(playAction);
		JButton creditsButton = new JButton(creditsAction);
		JButton settingsButton = new JButton(settingsAction);
		JButton scoresButton = new JButton(scoresAction);
		JButton exitButton = new JButton(exitAction);

		// add buttons
		buttonPanel.add(playButton);
		buttonPanel.add(settingsButton);
		buttonPanel.add(scoresButton);
		buttonPanel.add(creditsButton);
		buttonPanel.add(exitButton);

		// Set names
		playButton.setText("Play");
		creditsButton.setText("Credits");
		settingsButton.setText("Settings");
		scoresButton.setText("Scores");
		exitButton.setText("Exit");

		// add panel to frame
		manager = new GridLayout(1, 2);
		setLayout(manager);

		add(buttonPanel);
		add(showPanel);

	}
	

	// Trigger visibility of the main menu components
//	private void enableButtonPanel(boolean pick) {
//		Component[] components1 = buttonPanel.getComponents();
//		for (int i = 0; i < components1.length; i++) {
//			components1[i].setEnabled(pick);
//		}
//	}
//
//	private void enableShowPanel(boolean pick) {
//		Component[] components1 = showPanel.getComponents();
//		for (int i = 0; i < components1.length; i++) {
//			components1[i].setEnabled(pick);
//		}
//	}

	private class playAction extends AbstractAction {

		public void actionPerformed(ActionEvent event) {

//			enableButtonPanel(false);
//			enableShowPanel(false);
			currentHeroClass = HeroClass.WARRIOR;

			
			setSize(WINDOW_HEIGHT, WINDOW_HEIGHT);
			setLocationRelativeTo(null);
			setLayout(null);
			gameplay = new Gameplay(WINDOW_HEIGHT, WINDOW_HEIGHT, currentHeroClass, buttonPanel, showPanel);
			
			add(gameplay);
			
			gameplay.enableButtonPanel(false);
			gameplay.enableShowPanel(false);
			gameplay.setLocation(0, 0);
		}
	}

	private class creditsAction extends AbstractAction {

		public void actionPerformed(ActionEvent event) {
			showPanel.removeAll();
			JLabel l = new JLabel("Isaque Fontinele & Danny O'Dea");
			showPanel.add(l);
			revalidate();
			repaint();
		}
	}

	private class settingsAction extends AbstractAction {

		public void actionPerformed(ActionEvent event) {
			showPanel.removeAll();
			Action muteAction = new muteAction();
			JButton mute = new JButton(muteAction);
			mute.setText("Mute");
			showPanel.add(mute);
			revalidate();
			repaint();
		}
	}

	private class scoresAction extends AbstractAction {

		public void actionPerformed(ActionEvent event) {
			showPanel.removeAll();
			showPanel.add(scores);
			revalidate();
			repaint();
			
		}
	}

	private class exitAction extends AbstractAction {
		public void actionPerformed(ActionEvent event) {
			System.exit(0);
		}
	}
	
	private class muteAction extends AbstractAction {
		public void actionPerformed(ActionEvent event) {
			//Code dealing with removing sound
		}
	}
	
	// Getters and Setters
	public Gameplay getGameplay() {
		return gameplay;
	}

	public void setGameplay(Gameplay gameplay) {
		this.gameplay = gameplay;
	}
}

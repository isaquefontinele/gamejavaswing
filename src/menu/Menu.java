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
	private static int MENU_WIDTH;
	private static int MENU_HEIGHT;
	private static int GAME_WIDTH;
	private static int GAME_HEIGHT;
	private static double windowSize = 0.8; // Size of the window in percentage
	private GridLayout manager;
	private Scoreboard scores;
	private Gameplay gameplay;
	private HeroClass currentHeroClass;
	public boolean inGame = false;

	public Menu() {
		int blockSize = 75;
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		MENU_WIDTH = (int) Math.round(screenSize.width * windowSize);
		MENU_HEIGHT = (int) Math.round(screenSize.height * windowSize);
		GAME_WIDTH = blockSize * 11;
		GAME_HEIGHT = blockSize * 11;
//		setSize(MENU_WIDTH, MENU_HEIGHT);
		resizeWindow();

		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(5, 1));
		showPanel = new JPanel();
		showPanel.setLayout(new GridLayout(1,1));
		scores = new Scoreboard(MENU_WIDTH/2, MENU_HEIGHT);

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
	

	@Override
	public void paint(Graphics g) {
		super.paint(g);
//		System.out.println(this.getWidth() + " " + this.getHeight());
	}
	
	private class playAction extends AbstractAction {

		public void actionPerformed(ActionEvent event) {

			currentHeroClass = HeroClass.WARRIOR;
			
			setSize(GAME_WIDTH, GAME_HEIGHT);
			setLocationRelativeTo(null);
			setLayout(null);
			
			// New Game
			if (gameplay == null) {
				gameplay = new Gameplay(MENU_WIDTH, MENU_HEIGHT, currentHeroClass, buttonPanel, showPanel);
				add(gameplay);
				
			} else { // Continue existing game
				gameplay.setVisible(true);
				gameplay.setEnabled(true);
			}
			
			gameplay.enableButtonPanel(false);
			gameplay.enableShowPanel(false);
			gameplay.setLocation(0, 0);
		}
	}

	private class creditsAction extends AbstractAction {

		public void actionPerformed(ActionEvent event) {
			showPanel.removeAll();
			manager = new GridLayout(3, 3);
			showPanel.setLayout(manager);
			showPanel.add(new JLabel(""));
			showPanel.add(new JLabel(""));
			showPanel.add(new JLabel(""));
			showPanel.add(new JLabel(""));
			JLabel l = new JLabel("Isaque Fontinele & Danny O'Dea");
			showPanel.add(l);
			showPanel.add(new JLabel(""));
			showPanel.add(new JLabel(""));
			showPanel.add(new JLabel(""));
			showPanel.add(new JLabel(""));
			revalidate();
			repaint();
		}
	}

	private class settingsAction extends AbstractAction {

		public void actionPerformed(ActionEvent event) {
			showPanel.removeAll();
			Action muteAction = new muteAction();
			JButton mute = new JButton(muteAction);
			manager = new GridLayout(3, 3);
			showPanel.setLayout(manager);
			mute.setText("Mute");
			showPanel.add(new JLabel(""));
			showPanel.add(new JLabel(""));
			showPanel.add(new JLabel(""));
			showPanel.add(new JLabel(""));
			showPanel.add(mute);
			showPanel.add(new JLabel(""));
			showPanel.add(new JLabel(""));
			showPanel.add(new JLabel(""));
			showPanel.add(new JLabel(""));
			revalidate();
			repaint();
		}
	}

	private class scoresAction extends AbstractAction {

		public void actionPerformed(ActionEvent event) {
			showPanel.removeAll();
			showPanel.setLayout(null);
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
	
	private void resizeWindow() {
		if (!inGame) {
			setSize(MENU_WIDTH, MENU_HEIGHT);
		} else {
			setSize(GAME_WIDTH, GAME_HEIGHT);
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

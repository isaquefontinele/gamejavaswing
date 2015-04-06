package menu;

//package action;

import gameplay.Gameplay;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.sun.corba.se.impl.orbutil.RepIdDelegator;
import com.sun.javafx.scene.control.skin.ButtonSkin;

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
	JButton playButton;
	private Scoreboard scores;

	public Menu() {

		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		WINDOW_WIDTH = (int) Math.round(screenSize.width * windowSize);
		WINDOW_HEIGHT = (int) Math.round(screenSize.height * windowSize);
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(5, 1));
		showPanel = new JPanel();
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
		buttonPanel.add(creditsButton);
		buttonPanel.add(settingsButton);
		buttonPanel.add(scoresButton);
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
	private void enableButtonPanel(boolean pick) {
		Component[] components1 = buttonPanel.getComponents();
		for (int i = 0; i < components1.length; i++) {
			components1[i].setEnabled(pick);
		}

	}

	private void enableShowPanel(boolean pick) {
		Component[] components1 = showPanel.getComponents();
		for (int i = 0; i < components1.length; i++) {
			components1[i].setEnabled(pick);
		}
	}

	private class playAction extends AbstractAction {

		public void actionPerformed(ActionEvent event) {

			enableButtonPanel(false);
			enableShowPanel(false);

			Gameplay gameplay = new Gameplay(WINDOW_WIDTH, WINDOW_HEIGHT);
			add(gameplay);
		}
	}

	private class creditsAction extends AbstractAction {

		public void actionPerformed(ActionEvent event) {

		}
	}

	private class settingsAction extends AbstractAction {

		public void actionPerformed(ActionEvent event) {

		}
	}

	private class scoresAction extends AbstractAction {

		public void actionPerformed(ActionEvent event) {
			showPanel.removeAll();
			showPanel.add(scores);
			repaint();
			
		}
	}

	private class exitAction extends AbstractAction {
		public void actionPerformed(ActionEvent event) {
			System.exit(0);
		}
	}
}

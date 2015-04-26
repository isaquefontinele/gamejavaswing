package menu;

import gameplay.Gameplay;
import gameplay.HeroClass;
import gameplay.Player;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * The best game ever!
 */
public class Menu extends JFrame {

	private final int MENU_WIDTH = 800;
	private final int MENU_HEIGHT = 600;
	private final int BLOCK_SIZE = 75;
	private int GAME_WIDTH = BLOCK_SIZE * 11 + 15;;
	private int GAME_HEIGHT = BLOCK_SIZE * 12 - 30;;

	public boolean inGame = false;
	
	private HeroClass currentHeroClass;
	private GridLayout manager;
	private Scoreboard scores;
	private Gameplay gameplay;
	private Player currentPlayer;

	private JLabel titleLabel;
	private JPanel headPanel, buttonPanel;
	private JPanel showPanel;
	private JButton playButton;
	private JButton continueButton;
	private JButton scoresButton;
	private JButton settingsButton;
	private JButton creditsButton;
	private JButton exitButton;
	private Menu menu;
	
	
	public Menu() {
		menu = this;
		resizeWindow();
		initObjects();
		applyMenuLayout();
	}
	

	private void initObjects() {
		headPanel = new JPanel();
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(5, 1));
		showPanel = new JPanel();
		titleLabel = new JLabel();
//		showPanel.setLayout(new GridLayout(1,1));
		scores = new Scoreboard(MENU_WIDTH/2, MENU_HEIGHT);

		// define actions
		Action playAction = new playAction();
		Action continueAction = new continueAction();
		Action creditsAction = new creditsAction();
		Action settingsAction = new settingsAction();
		Action scoresAction = new scoresAction();
		Action exitAction = new exitAction();

		// define buttons
		playButton = new JButton(playAction);
		creditsButton = new JButton(creditsAction);
		settingsButton = new JButton(settingsAction);
		scoresButton = new JButton(scoresAction);
		exitButton = new JButton(exitAction);
		continueButton = new JButton(continueAction);
	}


	private void applyMenuLayout() {

		// add buttons
		buttonPanel.add(playButton);
		buttonPanel.add(continueButton);
		buttonPanel.add(scoresButton);
		buttonPanel.add(settingsButton);
		buttonPanel.add(creditsButton);
		buttonPanel.add(exitButton);
		
		// Set names
		playButton.setText("Play");
		creditsButton.setText("Credits");
		settingsButton.setText("Settings");
		scoresButton.setText("Scores");
		exitButton.setText("Exit");
		continueButton.setText("Continue");
        titleLabel.setText("Dangerous Dungeon");

		// Title
        titleLabel.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        // Background colors
        headPanel.setBackground(new java.awt.Color(51, 255, 0));
        showPanel.setBackground(new java.awt.Color(255, 102, 102));
        
        continueButton.setEnabled(false);


     // Inside layout for head
        javax.swing.GroupLayout headPanelLayout = new javax.swing.GroupLayout(headPanel);
        headPanel.setLayout(headPanelLayout);
        headPanelLayout.setHorizontalGroup(
            headPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        headPanelLayout.setVerticalGroup(
            headPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );


        // Inside layout for buttons panel
        javax.swing.GroupLayout buttonPanelLayout = new javax.swing.GroupLayout(buttonPanel);
        buttonPanel.setLayout(buttonPanelLayout);
        buttonPanelLayout.setHorizontalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(creditsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(playButton, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                    .addComponent(settingsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scoresButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(exitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(continueButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        buttonPanelLayout.setVerticalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonPanelLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(playButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(continueButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scoresButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(settingsButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(creditsButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exitButton)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        
        // Inside layout for show panel
        javax.swing.GroupLayout showPanelLayout = new javax.swing.GroupLayout(showPanel);
        showPanel.setLayout(showPanelLayout);
        showPanelLayout.setHorizontalGroup(
            showPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 569, Short.MAX_VALUE)
        );
        showPanelLayout.setVerticalGroup(
            showPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 472, Short.MAX_VALUE)
        );

        // Layout for all the panels
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(headPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(showPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(headPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(showPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
	}


	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}
	
	private class playAction extends AbstractAction {

		public void actionPerformed(ActionEvent event) {

			currentHeroClass = HeroClass.MAGE;
			
			setSize(GAME_WIDTH, GAME_HEIGHT);
			setLocationRelativeTo(null);
			setLayout(null);
			
			// New Game
			gameplay = new Gameplay(MENU_WIDTH, MENU_HEIGHT, currentHeroClass, buttonPanel, showPanel, menu);
			add(gameplay);

			gameplay.enableButtonPanel(false);
			gameplay.enableShowPanel(false);
			gameplay.setLocation(0, 0);
		}
	}
	
	private class continueAction extends AbstractAction {

		public void actionPerformed(ActionEvent event) {
			
			setSize(GAME_WIDTH, GAME_HEIGHT);
			gameplay.setVisible(true);
			gameplay.setEnabled(true);
			
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
			int dialogButton = JOptionPane.YES_NO_OPTION;
			dialogButton = JOptionPane.showConfirmDialog (null, "Are you sure you want to leave this awesome game???","Warning",dialogButton);
            
            if (dialogButton == 0) {
            	System.exit(0);
			}
		}
	}
	
	public void resizeWindow() {
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

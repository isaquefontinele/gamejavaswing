package menu;

import gameplay.Gameplay;
import gameplay.HeroClass;
import gameplay.Player;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FilenameFilter;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.*;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 * The best game ever!
 */
public class Menu extends JFrame {

	private final int MENU_WIDTH = 800;
	private final int MENU_HEIGHT = 600;
	private final int BLOCK_SIZE = 75;
	private int GAME_WIDTH = BLOCK_SIZE * 11 + 15;;
	private int GAME_HEIGHT = BLOCK_SIZE * 12 - 30;;

	private boolean inGame = false;
	
	private HeroClass currentHeroClass;
	private GridLayout manager;
	private Scoreboard scores;

	private Gameplay gameplay;
	private Player currentPlayer;
	
	//Music Variables
	private MediaPlayer player;
	private ArrayList<MediaPlayer> players;
	private MediaPlayer nextPlayer;
	private MediaView mediaView;
	private JButton skip;
	private JButton mute;
	private JButton unmute;

	private JLabel titleLabel;
	private JPanel headPanel, buttonPanel;
	private JPanel showPanel;
	private JButton playButton;
	private JButton continueButton;
	private JButton scoresButton;
	private JButton settingsButton;
	private JButton creditsButton;
	private JButton exitButton;
	
	private JRadioButton mageButton;
	private JRadioButton hunterButton;
	private JRadioButton warriorButton; 
	private ButtonGroup group;
	
	private Menu menu;
	private JLabel jLabel1;
	private JLabel jLabel2;
	
	
	public Menu() {
		resizeWindow();
		initObjects();
		applyMenuLayout();
	}
	

	private void initObjects() {
		
		menu = this;
		
		currentPlayer = new Player("Jeff");
		headPanel = new JPanel();
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(5, 1));
		showPanel = new JPanel();
		titleLabel = new JLabel();
		scores = new Scoreboard(MENU_WIDTH/2, MENU_HEIGHT);
		
		// define actions
		Action playAction = new playAction();
		Action continueAction = new continueAction();
		Action creditsAction = new creditsAction();
		Action settingsAction = new settingsAction();
		Action scoresAction = new scoresAction();
		Action exitAction = new exitAction();
		Action muteAction = new muteAction();
		Action unmuteAction = new unmuteAction();
		Action skipAction = new skipAction();
		
		// define buttons
		playButton = new JButton(playAction);
		creditsButton = new JButton(creditsAction);
		settingsButton = new JButton(settingsAction);
		scoresButton = new JButton(scoresAction);
		exitButton = new JButton(exitAction);
		continueButton = new JButton(continueAction);
		skip = new JButton(skipAction);
		mute = new JButton(muteAction);
		unmute = new JButton(unmuteAction);
		
		//Radio Buttons
		group = new ButtonGroup();
		mageButton = new JRadioButton("Mage");
		hunterButton = new JRadioButton("Hunter");
		warriorButton = new JRadioButton("Warrior");
		mageButton.setSelected(true);
		group.add(mageButton);
		group.add(hunterButton);
		group.add(warriorButton);
		
		jLabel1 = new JLabel("Choose Your hero!");
		jLabel2 = new JLabel("Adjust the music:");
		// Music
		JFXPanel fxPanel = new JFXPanel();
		SwingUtilities.invokeLater(new Runnable() {
	      @Override public void run() {
	        initMusic();
	      }
	    });
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
		skip.setText("Skip Current Track");
		mute.setText("Mute");
		unmute.setText("Unmute");

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
        setShowPanelLayout();
        

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
	
	private void setShowPanelLayout() {
		// Inside layout for show panel
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        mageButton.setBackground(showPanel.getBackground());
        hunterButton.setBackground(showPanel.getBackground());
        warriorButton.setBackground(showPanel.getBackground());

        javax.swing.GroupLayout showPanelLayout = new javax.swing.GroupLayout(showPanel);
        showPanel.setLayout(showPanelLayout);
        showPanelLayout.setHorizontalGroup(
            showPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(showPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(showPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(showPanelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(showPanelLayout.createSequentialGroup()
                        .addGroup(showPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(showPanelLayout.createSequentialGroup()
                                .addComponent(skip, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(mute, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(unmute, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(showPanelLayout.createSequentialGroup()
                                .addComponent(mageButton)
                                .addGap(18, 18, 18)
                                .addComponent(warriorButton)
                                .addGap(18, 18, 18)
                                .addComponent(hunterButton))
                            .addComponent(jLabel1))
                        .addGap(64, 64, Short.MAX_VALUE))))
        );
        showPanelLayout.setVerticalGroup(
            showPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(showPanelLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel1)
                .addGap(44, 44, 44)
                .addGroup(showPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(warriorButton)
                    .addComponent(mageButton)
                    .addComponent(hunterButton))
                .addGap(69, 69, 69)
                .addComponent(jLabel2)
                .addGap(42, 42, 42)
                .addGroup(showPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(skip, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mute, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(unmute, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(111, Short.MAX_VALUE))
        );
		
	}

	// Can you hear it? This code is playing Music!
	private void initMusic() {
		
		// Get File path to folder, generate list of players for every mp3 in it
		URL dir_url = this.getClass().getResource(
				"/MusicReplaceIfWorriedAboutCopyright/");
		try {
			File dir = new File(dir_url.toURI());
			players = new ArrayList<MediaPlayer>();
			for (String file : dir.list(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return name.endsWith(".mp3");
				}
			}))
				players.add(createPlayer("file:///"
						+ (dir + "\\" + file).replace("\\", "/").replaceAll(
								" ", "%20")));

		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		// play each audio file in turn.
		mediaView = new MediaView(players.get(0));
		for (int i = 0; i < players.size(); i++) {
			player = players.get(i);
			nextPlayer = players.get((i + 1) % players.size());
			player.setOnEndOfMedia(new Runnable() {
				@Override
				public void run() {
					skip.doClick();
				}
			});
		}

		// start playing the first track.
		mediaView.setMediaPlayer(players.get(0));
		mediaView.getMediaPlayer().play();
	}

	private MediaPlayer createPlayer(String aMediaSrc) {
		/**
		 * @return a MediaPlayer for the given source which will report any
		 *         errors it encounters
		 */
		final MediaPlayer player = new MediaPlayer(new Media(aMediaSrc));
		return player;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}
	
	// "Light, Camera, ACTIONS!"
	private class playAction extends AbstractAction {

		public void actionPerformed(ActionEvent event) {
			
			if (warriorButton.isSelected()) {
				currentHeroClass = HeroClass.WARRIOR;
			} else if (mageButton.isSelected()) {
				currentHeroClass = HeroClass.MAGE;
			} else if (hunterButton.isSelected()) {
				currentHeroClass = HeroClass.HUNTER;
			}
			
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

			setShowPanelLayout();
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
	
	private class muteAction extends AbstractAction {
		public void actionPerformed(ActionEvent event) {
			mediaView.getMediaPlayer().pause();
			
		}
	}
	
	private class unmuteAction extends AbstractAction {
		public void actionPerformed(ActionEvent event) {
			mediaView.getMediaPlayer().play();
		}
	}
	
	private class skipAction extends AbstractAction {
		public void actionPerformed(ActionEvent event) {
			MediaPlayer curPlayer = mediaView.getMediaPlayer();
	        MediaPlayer nextPlayer = players.get((players.indexOf(curPlayer) + 1) % players.size());
	        mediaView.setMediaPlayer(nextPlayer);
	        curPlayer.stop();
	        nextPlayer.play();
		}
	}
	
	
	public void resizeWindow() {
		if (!inGame) {
			setSize(MENU_WIDTH, MENU_HEIGHT);
		} else {
			setSize(GAME_WIDTH, GAME_HEIGHT);
		}
		
	}
	
	// Getters and Setters
	public Gameplay getGameplay() {
		return gameplay;
	}

	public void setGameplay(Gameplay gameplay) {
		this.gameplay = gameplay;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	
	
	public Scoreboard getScores() {
		return scores;
	}

	public void setScores(Scoreboard scores) {
		this.scores = scores;
	}
}

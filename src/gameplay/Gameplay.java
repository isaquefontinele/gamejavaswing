package gameplay;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import menu.Menu;
import menu.Score;

public class Gameplay extends JPanel implements ActionListener, Runnable {

	private int GAME_WIDTH;
	private int GAME_HEIGHT;
	private final int SPEED = 10;
	private final int BLOCK_SIZE = 75;
	private final long DELAY = 25;
	private int currentFaseNumber = 0;
	
	private int currentScore = 0;
	private float lifeBarWidth;

	private Timer timer;
	private Hero hero;
	private JPanel buttonPanel;
	private JPanel showPanel;
	private String[] fasesString;
	private ArrayList<Fase> fases;
	private Fase currentFase;
	private Thread animator;
	private Menu menu;
	private File file;
	
	private ArrayList<Item> items;
	private ArrayList<Monster> monsters;
	private ArrayList<Wall> walls;
	private ArrayList<Bullet> bullets;
	private ArrayList<Integer> groundTilesX;
	private ArrayList<Integer> groundTilesY;
	private ArrayList<Torch> torches;
	private ArrayList<Strike> strikes;

	private Image groundTile;
	private Image door;
	private Image lifeBar;

	private boolean inGame;
	private boolean firstTouch;
	private FileReader read;
	
	private ArrayList<MediaPlayer> soundEffects;

	public Gameplay(int MENU_WIDTH, int MENU_HEIGHT, HeroClass heroClass,
			JPanel buttonPanel, JPanel showPanel, Menu menu) {

		this.showPanel = showPanel;
		this.buttonPanel = buttonPanel;
		this.menu = menu;

		addKeyListener(new TAdapter()); // Keyboard input
		setFocusable(true);
		setBackground(Color.GRAY);
		setDoubleBuffered(true);

		hero = new Hero(heroClass);
		
		fases = new ArrayList<Fase>();
		loadFases();
		initObjects();
	}

	public void initObjects() {
		inGame = true;
		firstTouch = true;
		
		
		
		//currentFase = new Fase();
		System.out.println(fases.size());
		currentFase = fases.get(currentFaseNumber);
		
		this.GAME_WIDTH = BLOCK_SIZE * currentFase.getMatrixWidth();
		this.GAME_HEIGHT = BLOCK_SIZE * currentFase.getMatrixHeight();
		setSize(GAME_WIDTH, GAME_HEIGHT);

		// Local Images
		groundTile = new ImageIcon(this.getClass().getResource(
				"/images/groundTile2.png")).getImage();
		door = new ImageIcon(this.getClass().getResource("/images/door.png"))
				.getImage();
		lifeBar = new ImageIcon(this.getClass().getResource(
				"/images/lifeBar.png")).getImage();
		
		//Sound Effect Setup
		URL dir_url = this.getClass().getResource("/soundEffects/");
		try {
			File dir = new File(dir_url.toURI());
			soundEffects = new ArrayList<MediaPlayer>();
			for (String file : dir.list(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return name.endsWith(".mp3");
				}
			}))
				soundEffects.add(createPlayer("file:///"
						+ (dir + "\\" + file).replace("\\", "/").replaceAll(
								" ", "%20")));

		} catch (URISyntaxException e) {
			e.printStackTrace();
		}	
		
		items = currentFase.getItems();
		monsters = currentFase.getMonsters();
		walls = currentFase.getWalls();
		hero.setX(currentFase.getHeroPosX());
		hero.setY(currentFase.getHeroPosY());
		groundTilesX = currentFase.getGroundTilesX();
		groundTilesY = currentFase.getGroundTilesY();
		torches = currentFase.getTorches();
		bullets = new ArrayList<Bullet>();
		strikes = new ArrayList<Strike>();

		timer = new Timer(SPEED, this);
		timer.start();
	}

	// Main Loop
	@Override
	public void actionPerformed(ActionEvent arg0) {

		monstersMove();
		hero.move();
		bulletsMove();
		checkCollisions();
		repaint();
	}
	
	private void loadFases() {
		URL dir_url = this.getClass().getResource("/gameplay/fases.txt/");
		try {
			file = new File(dir_url.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		try {
			read = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		char check = ' ';
		String gen = "";

		while (!gen.contains("END")) {

			try {
				check = (char) read.read();
			} catch (IOException e) {
				e.printStackTrace();
			}
			gen = gen + check;
		}
		try {
			read.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		fasesString = gen.split("NEXT");
		
//		System.out.println(Arrays.toString(fasesString));
		for (int i = 0; i < fasesString.length; i++) {
			fases.add(new Fase(fasesString[i]));
		}
	}

	// Updates bullets movement
	private void bulletsMove() {
		for (int i = 0; i < bullets.size(); i++) {
			if (bullets.get(i).isVisible()) {
				bullets.get(i).move();
			} else {
				bullets.remove(bullets.get(i));
			}
		}

		for (int i = 0; i < strikes.size(); i++) {
			strikes.get(i).move();
		}
	}

	// Updates monsters movement
	private void monstersMove() {
		for (int i = 0; i < monsters.size(); i++) {
			monsters.get(i).ai();
			monsters.get(i).move();
		}
	}

	// Change visibility of the main menu components
	public void enableButtonPanel(boolean pick) {
		Component[] components1 = buttonPanel.getComponents();
		for (int i = 0; i < components1.length; i++) {
			components1[i].setEnabled(pick);
		}
	}

	public void enableShowPanel(boolean pick) {
		Component[] components1 = showPanel.getComponents();
		for (int i = 0; i < components1.length; i++) {
			components1[i].setEnabled(pick);
		}
	}

	private void animate() {
		// Update Torch
		for (int i = 0; i < torches.size(); i++) {
			torches.get(i).updateFrame();
		}
		for (int i = 0; i < monsters.size(); i++) {
			monsters.get(i).updateFrame();
		}
	}

	@Override
	public void addNotify() {
		super.addNotify();
		animator = new Thread(this);
		animator.start();
	}

	@Override
	public void run() {
		long pastTime, timeDiff, sleep;
		pastTime = System.currentTimeMillis();

		while (true) {
			animate();
			repaint();

			// Stuff to keep the loops with the same time
			timeDiff = System.currentTimeMillis() - pastTime;
			sleep = DELAY - timeDiff;
			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				System.out.println("Interrupted: " + e.getMessage());
			}
			pastTime = System.currentTimeMillis();
		}
	}

	// WATCH OUT!!! Collisions ahead!
	private void checkCollisions() {
		Rectangle heroRect = hero.getBounds();

		// Collisions of hero with monsters
		Rectangle monsterRect;
		for (int i = 0; i < monsters.size(); i++) {
			monsterRect = monsters.get(i).getBounds();
			if (firstTouch) {
				if (heroRect.intersects(monsterRect)) {
					soundEffects.get(1).stop();
					soundEffects.get(1).play();
					hero.atack(monsters.get(i).getAttack());
				}
			} else {
				firstTouch = true;
			}
		}

		// Collisions of hero with walls
		hero.setCanGoDown(true);
		hero.setCanGoUp(true);
		hero.setCanGoRight(true);
		hero.setCanGoLeft(true);
		for (int i = 0; i < walls.size(); i++) {

			if (walls.get(i).getBounds().intersects(hero.getBounds())) {
				if (hero.getDirection() == Direction.RIGHT) {
					hero.setCanGoRight(false);
					hero.setX(walls.get(i).getX() - hero.getWidth());
					break;
				} else if (hero.getDirection() == Direction.LEFT) {
					hero.setCanGoRight(false);
					hero.setX(walls.get(i).getX() + BLOCK_SIZE);
					break;
				} else if (hero.getDirection() == Direction.UP) {
					hero.setCanGoRight(false);
					hero.setY(walls.get(i).getY() + BLOCK_SIZE);
					break;
				} else if (hero.getDirection() == Direction.DOWN) {
					hero.setCanGoRight(false);
					hero.setY(walls.get(i).getY() - hero.getHeight());
					break;
				}
			}
		}

		// Collisions between bullets and monsters
		for (int i = 0; i < bullets.size(); i++) {
			for (int j = 0; j < monsters.size(); j++) {
				if (bullets.get(i).getBounds()
						.intersects(monsters.get(j).getBounds())) {
					soundEffects.get(4).stop();
					soundEffects.get(4).play();
					monsters.get(j).atack(bullets.get(i).getDamage());
					bullets.remove(bullets.get(i));
					// Kill monster
					if (monsters.get(j).getLife() <= 0) {
						currentScore += monsters.get(j).getScore();
						monsters.remove(monsters.get(j));
					}
					break;
				}
			}
		}

		// Collisions between bullets and walls
		for (int i = 0; i < bullets.size(); i++) {
			for (int j = 0; j < walls.size(); j++) {
				if (bullets.get(i).getBounds()
						.intersects(walls.get(j).getBounds())) {
					bullets.remove(bullets.get(i));
					break;
				}
			}
		}

		// Collisions between strikes and monsters
		for (int i = 0; i < monsters.size(); i++) {
			for (int j = 0; j < strikes.size(); j++) {
				if (monsters.get(i).getBounds()
						.intersects(strikes.get(j).getBounds())) {
					soundEffects.get(3).stop();
					soundEffects.get(3).play();
					monsters.get(i).atack(strikes.get(j).getDamage());
					strikes.remove(strikes.get(j));
					// Kill monster
					if (monsters.get(i).getLife() <= 0) {
						currentScore += monsters.get(i).getScore();
						monsters.remove(monsters.get(i));
					}
				}
				if (strikes.size() > 0) {
					if (strikes.get(j).getRange() >= 10) {
						strikes.remove(strikes.get(j));
					}
				}
			}
		}
		if (strikes.size() > 0) {
			for (int i = 0; i < strikes.size(); i++) {
				if (strikes.get(i).getRange() >= 10) {
					strikes.remove(strikes.get(i));
				}
			}
		}

		// Collect items
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getBounds().intersects(hero.getBounds())) {
				soundEffects.get(0).stop();
				soundEffects.get(0).play();	
				currentScore += 50;
				items.remove(items.get(i));
				break;
			}
			
		}		
	}

	// -----------------------End of Collisions-----------------------

	// Start of Painting area. Take care about fresh ink.
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		String pauseStr = "Game Paused";
		String touchingStr = "Touching monster";
		String lifeStr = "Hero Life:";
		String scoreStr = "Score: " + currentScore;
		String winStr = "Congratulations, you won!";
		Font small = new Font("Helvetica", Font.BOLD, 30);
		FontMetrics metr = this.getFontMetrics(small);
		g2d.setColor(Color.white);
		g2d.setFont(small);

		if (inGame) {

			// Print ground
			for (int i = 0; i < groundTilesX.size(); i++) {
				g2d.drawImage(groundTile, groundTilesX.get(i),
						groundTilesY.get(i), this);
			}

			// Print Walls
			for (int i = 0; i < walls.size(); i++) {
				g2d.drawImage(walls.get(i).getImage(), walls.get(i).getX(),
						walls.get(i).getY(), this);
			}

			// Print bullets
			bullets.addAll(hero.getBullets());
			hero.getBullets().clear();

			for (int i = 0; i < bullets.size(); i++) {
				g2d.drawImage(bullets.get(i).getImage(), bullets.get(i).getX(),
						bullets.get(i).getY(), this);
			}

			// Print strikes
			strikes.addAll(hero.getStrikes());
			hero.getStrikes().clear();

			for (int i = 0; i < strikes.size(); i++) {
				g2d.drawImage(strikes.get(i).getImage(), strikes.get(i).getX(),
						strikes.get(i).getY(), this);
			}

			// Print items
			for (int i = 0; i < items.size(); i++) {
				g2d.drawImage(groundTile, items.get(i).getX(), items.get(i)
						.getY(), this);
				g2d.drawImage(items.get(i).getImage(), items.get(i).getX(),
						items.get(i).getY(), this);
			}

			// Print monsters
			for (int i = 0; i < monsters.size(); i++) {
				g2d.drawImage(monsters.get(i).getImage(), monsters.get(i)
						.getX(), monsters.get(i).getY(), this);
				// Life bar
				lifeBarWidth = 75 *(monsters.get(i).getLife() / (float) monsters.get(i).getORIGINAL_LIFE());
				if (monsters.get(i).getLife() > 0) {
					g2d.drawImage(lifeBar, monsters.get(i).getX(), monsters.get(i).getY() - 5, Math.round(lifeBarWidth), lifeBar
							.getHeight(null), null);
				}
			}

			// Print door
			if (items.size() != 0) {
				g2d.drawImage(door, currentFase.getExitPosX(),
						currentFase.getExitPosY(), this);
			} else {
				walls.remove(currentFase.getDoorWall());
			}

			// Print hero
			g2d.drawImage(hero.getImage(), hero.getX(), hero.getY(), this);

			// Print torches
			for (int i = 0; i < torches.size(); i++) {
				// If torch is on right side of the board
				if (torches.get(i).getDirection() == Direction.LEFT) {
					g2d.drawImage(torches.get(i).getImage(), torches.get(i)
							.getX() - torches.get(i).getWidth() / 2, torches
							.get(i).getY(), torches.get(i).getWidth(), torches
							.get(i).getHeight(), null);
				} else {
					g2d.drawImage(torches.get(i).getImage(),
							torches.get(i).getX() + torches.get(i).getWidth()
									/ 2 + BLOCK_SIZE, torches.get(i).getY(),
							-torches.get(i).getWidth(), torches.get(i)
									.getHeight(), null);
				}

			}

			// Pause message
			if (!timer.isRunning() && !heroIsOutsideLayout()) {
				g2d.drawString(pauseStr,
						(GAME_WIDTH - metr.stringWidth(pauseStr)) / 2,
						GAME_HEIGHT / 2);
			}



			// Victory message
			if (heroIsOutsideLayout()) {
				currentFaseNumber++;
				if (currentFaseNumber > fases.size()) {
					g2d.drawString(winStr,
							(GAME_WIDTH - metr.stringWidth(touchingStr)) / 2,
							GAME_HEIGHT / 2);
					timer.stop();
					updateScore();
				} else {
					initObjects();
				}
				
			}

			// Life string
			g2d.drawString(lifeStr, BLOCK_SIZE / 3, BLOCK_SIZE / 2);
			
			// Hero lifebar
			lifeBarWidth = 100 *(hero.getLife() / (float) hero.getORIGINAL_LIFE());
			g2d.drawImage(lifeBar, BLOCK_SIZE*2 + BLOCK_SIZE/2, BLOCK_SIZE / 3, Math.round(lifeBarWidth), lifeBar
					.getHeight(null)*2, null);

			// Score
			g2d.drawString(scoreStr, BLOCK_SIZE * 4, BLOCK_SIZE / 2);

			// Update screen
			g.dispose();
		}
	}
	
	private boolean heroIsOutsideLayout() {
		if(hero.getX()<=0 || hero.getX() > BLOCK_SIZE*currentFase.getMatrixHeight()+1 || hero.getY() <= 0 || hero.getY() >= BLOCK_SIZE*currentFase.getMatrixWidth()+1){
			return true;
		}
		return false;
	}

	// -----------------------End of Painting Area-----------------------

	private void updateScore() {
		// currentScore;
		// timer. need to get time
		Score newScore = new Score(menu.getCurrentPlayer().getName(), 350, 1050);
		menu.getScores().updateWithNewScore(newScore);
	}

	// Keyboard stuff
	public void checkState(KeyEvent e) {
		int key = e.getKeyCode();

		// Pause
		if (key == KeyEvent.VK_P) {
			if (timer.isRunning()) {
				timer.stop();
				repaint();
			} else {
				timer.start();
			}
		}

		// ESC
		// Get out of the game back to menu
		if (key == KeyEvent.VK_ESCAPE) {
			this.setVisible(false);
			this.setEnabled(false);
			enableButtonPanel(true);
			enableShowPanel(true);
			timer.stop();
			menu.resizeWindow();
		}
	}

	public int getGAME_WIDTH() {
		return GAME_WIDTH;
	}

	public void setGAME_WIDTH(int gAME_WIDTH) {
		GAME_WIDTH = gAME_WIDTH;
	}

	public int getGAME_HEIGHT() {
		return GAME_HEIGHT;
	}

	public void setGAME_HEIGHT(int gAME_HEIGHT) {
		GAME_HEIGHT = gAME_HEIGHT;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	// Other Classes
	private class TAdapter extends KeyAdapter {

		public void keyReleased(KeyEvent e) {
			hero.keyReleased(e);
		}

		public void keyPressed(KeyEvent e) {
			hero.keyPressed(e);
			checkState(e);
		}
	}
	
	private MediaPlayer createPlayer(String aMediaSrc) {
		/**
		 * @return a MediaPlayer for the given source which will report any
		 *         errors it encounters
		 */
		final MediaPlayer player = new MediaPlayer(new Media(aMediaSrc));
		return player;
	}
}

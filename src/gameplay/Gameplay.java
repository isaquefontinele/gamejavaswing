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
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements ActionListener, Runnable {

	private final int GAME_WIDTH, GAME_HEIGHT;
	private int SPEED = 5;
	private int currentScore = 0;
	private final int blockSize = 75;
	private int animateIndex = 0;
	private final long DELAY = 25;

	private Timer timer;
	private Hero hero;
	private JPanel buttonPanel, showPanel;
	private Fase currentFase;
	private Thread animator;

	private ArrayList<Item> items;
	private ArrayList<Monster> monsters;
	private ArrayList<Wall> walls;
	private ArrayList<Bullet> bullets;
	private ArrayList<Integer> groundTilesX;
	private ArrayList<Integer> groundTilesY;
	private ArrayList<Torch> torches;

	private Image groundTile;
	private Image door;

	private boolean inGame;
	private boolean touchingMonster;

	public Gameplay(int MENU_WIDTH, int MENU_HEIGHT, HeroClass heroClass,
			JPanel buttonPanel, JPanel showPanel) {

		this.GAME_WIDTH = blockSize * 11;
		this.GAME_HEIGHT = blockSize * 11;
		setSize(GAME_WIDTH, GAME_HEIGHT);

		this.showPanel = showPanel;
		this.buttonPanel = buttonPanel;

		addKeyListener(new TAdapter()); // Keyboard input
		setFocusable(true);
		setBackground(Color.GRAY);
		setDoubleBuffered(true);

		hero = new Hero(heroClass);

		initObjects();
	}

	public void initObjects() {
		inGame = true;
		currentFase = new Fase(GAME_WIDTH, GAME_HEIGHT, blockSize);

		// Local Images
		groundTile = new ImageIcon(this.getClass().getResource(
				"/images/groundTile2.png")).getImage();
		door = new ImageIcon(this.getClass().getResource("/images/door.png"))
				.getImage();

		hero.setMatrixWidth(currentFase.getMatrixWidth());
		hero.setMatrixHeigth(currentFase.getMatrixHeight());

		items = currentFase.getItems();
		monsters = currentFase.getMonsters();
		walls = currentFase.getWalls();
		hero.setX(currentFase.getHeroPosX());
		hero.setY(currentFase.getHeroPosY());
		groundTilesX = currentFase.getGroundTilesX();
		groundTilesY = currentFase.getGroundTilesY();
		torches = currentFase.getTorches();
		bullets = new ArrayList<Bullet>();

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

	// Updates bullets movement
	private void bulletsMove() {
		for (int i = 0; i < bullets.size(); i++) {
			if (bullets.get(i).isVisible()) {
				bullets.get(i).move();
			} else {
				bullets.remove(bullets.get(i));
			}
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

		// Colisions of hero with monsters
		Rectangle monsterRect;
		for (int i = 0; i < monsters.size(); i++) {
			monsterRect = monsters.get(i).getBounds();

			if (heroRect.intersects(monsterRect)) {
				touchingMonster = true;
				break;
			} else {
				touchingMonster = false;
			}
		}

		// Colisions of hero with walls
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
					hero.setX(walls.get(i).getX() + blockSize);
					break;
				} else if (hero.getDirection() == Direction.UP) {
					hero.setCanGoRight(false);
					hero.setY(walls.get(i).getY() + blockSize);
					break;
				} else if (hero.getDirection() == Direction.DOWN) {
					hero.setCanGoRight(false);
					hero.setY(walls.get(i).getY() - hero.getHeight());
					break;
				}
			}
		}

		// Colisions between bullets and monsters
		for (int i = 0; i < bullets.size(); i++) {
			for (int j = 0; j < monsters.size(); j++) {
				if (bullets.get(i).getBounds()
						.intersects(monsters.get(j).getBounds())) {
					monsters.get(j).shoot(bullets.get(i).getDamage());
					bullets.remove(bullets.get(i));

					if (monsters.get(j).getLife() <= 0) {
						currentScore += monsters.get(j).getScore();
						monsters.remove(monsters.get(j));
					}
					break;
				}
			}
		}

		// Colisions between bullets and walls
		for (int i = 0; i < bullets.size(); i++) {
			for (int j = 0; j < walls.size(); j++) {
				if (bullets.get(i).getBounds()
						.intersects(walls.get(j).getBounds())) {
					bullets.remove(bullets.get(i));
					break;
				}
			}
		}

		// Collect items
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getBounds().intersects(hero.getBounds())) {
				currentScore += 50;
				items.remove(items.get(i));
				break;
			}
		}
	}

	// -----------------------End of Colisions-----------------------

	// Painting area. Take care with fresh ink.
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		String pauseStr = "Game Paused";
		String touchingStr = "Touching monster";
		String lifeStr = "Life: " + hero.getLife();
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
				if (torches.get(i).getX() >= GAME_WIDTH/2) {
					g2d.drawImage(torches.get(i).getImage(), torches.get(i).getX() - torches.get(i).getWidth()/2,
							torches.get(i).getY(), torches.get(i).getWidth(), torches.get(i).getHeight(), null);
				} else {
					g2d.drawImage(torches.get(i).getImage(), torches.get(i).getX() + torches.get(i).getWidth()/2 + blockSize,
							torches.get(i).getY(), -torches.get(i).getWidth(), torches.get(i).getHeight(), null);
				}
				
			}

			// Print Pause
			if (!timer.isRunning() && hero.getY() + hero.getHeight() > 0) {
				g2d.drawString(pauseStr,
						(GAME_WIDTH - metr.stringWidth(pauseStr)) / 2,
						GAME_HEIGHT / 2);
			}

			// Touching monster
			if (touchingMonster) {
				g2d.drawString(touchingStr,
						(GAME_WIDTH - metr.stringWidth(touchingStr)) / 2,
						GAME_HEIGHT / 2);
			}

			// Life
			g2d.drawString(lifeStr, blockSize / 3, blockSize / 2);

			// Score
			g2d.drawString(scoreStr, blockSize * 3, blockSize / 2);

			// Victory message
			if (hero.getY() + hero.getHeight() <= 0) {
				g2d.drawString(winStr,
						(GAME_WIDTH - metr.stringWidth(touchingStr)) / 2,
						GAME_HEIGHT / 2);
				timer.stop();
				updateScore();
			}

			// Update screen
			g.dispose();
		}
	}

	// -----------------------End of Painting Area-----------------------

	private void updateScore() {
		// Do you thing with currentScore
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
			// this.getParent().getParent();
		}
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
}

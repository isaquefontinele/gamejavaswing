package gameplay;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Menu;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements ActionListener {

	private int GAME_WIDTH, GAME_HEIGHT;
	private int SPEED = 5;
	private Fase currentFase;
	
	private ArrayList<Item> items;
	private ArrayList<Monster> monsters;
	private ArrayList<Wall> wallsToPrint;
	private ArrayList<Wall> wallsToCollision;
	private ArrayList<Bullet> bullets;
	private ArrayList<Integer> groundTilesX;
	private ArrayList<Integer> groundTilesY;
	
	private Image groundTile;
	private Image door;
	private Timer timer;
	private Hero hero;
	private JPanel buttonPanel, showPanel;

	private boolean inGame;
	private boolean doorOpened;
	private boolean touchingMonster;
	private final int blockSize = 75;


	
	
	public Gameplay(int MENU_WIDTH, int MENU_HEIGHT, HeroClass heroClass, JPanel buttonPanel, JPanel showPanel) {

		this.GAME_WIDTH = blockSize*11;
		this.GAME_HEIGHT = blockSize*11;
		setSize(GAME_WIDTH, GAME_HEIGHT);

		this.showPanel = showPanel;
		this.buttonPanel = buttonPanel;
		
		addKeyListener(new TAdapter()); // Keyboard input
        setFocusable(true);
        setBackground(Color.GRAY);
        setDoubleBuffered(true);
        inGame = true;
        groundTile = new ImageIcon(this.getClass().getResource(
				"/images/groundTile.png")).getImage();
        door = new ImageIcon(this.getClass().getResource(
				"/images/door.png")).getImage();
        currentFase = new Fase(GAME_WIDTH, GAME_HEIGHT, blockSize);
        
        hero = new Hero(heroClass, blockSize);
        hero.setMatrixWidth(currentFase.getMatrixWidth());
        hero.setMatrixHeigth(currentFase.getMatrixHeight());
        
        initObjects();

        timer = new Timer(SPEED, this);
        timer.start();
        
	}

	public void initObjects() {

		items = currentFase.getItems();
		monsters = currentFase.getMonsters();
		wallsToPrint = currentFase.getWallsToPrint();
		wallsToCollision = currentFase.getWallsToCollision();;
		hero.setX(currentFase.getHeroPosX());
		hero.setY(currentFase.getHeroPosY());
		groundTilesX = currentFase.getGroundTilesX();
		groundTilesY = currentFase.getGroundTilesY();
		bullets = new ArrayList<Bullet>();
    }
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
        if (items.size()==0) {
            doorOpened = true;
        }
        
		
		monstersMove();
		hero.move();
		bulletsMove();
		checkCollisions();
		repaint();
	}
	
    private void bulletsMove() {
		for (int i = 0; i < bullets.size(); i++) {
			if (bullets.get(i).isVisible()){
				bullets.get(i).move();
			} else {
				bullets.remove(bullets.get(i));
			}
		}
	}

	private void monstersMove() {
//    	System.out.println(timer.getDelay());
		for (int i = 0; i < monsters.size(); i++) {
			monsters.get(i).ai();
			monsters.get(i).move();
		}
//		repaint();
		
	}

	private class TAdapter extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            hero.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
            hero.keyPressed(e);
            checkState(e);
        }
    }
	
	
	private void updateScore() {
		
	}

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
//			this.getParent().getParent();
		}
	}
	
	// Trigger visibility of the main menu components
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

	// WATCH OUT!!! Collisions ahead!
	private void checkCollisions() {
		Rectangle heroRect = hero.getBounds();
		updateCorners();
		
		// Colisions with monsters
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
		
		// Colisions with walls
		hero.setCanGoDown(true);
		hero.setCanGoUp(true);
		hero.setCanGoRight(true);
		hero.setCanGoLeft(true);
		for (int i = 0; i < wallsToCollision.size(); i++) {
			
			if (wallsToCollision.get(i).getBounds().intersects(hero.getBounds())) {
				if (hero.getDirection() == Direction.RIGHT) {
					hero.setCanGoRight(false);
					hero.setX(wallsToCollision.get(i).getX()-hero.getWidth());
					break;
				}
				else if (hero.getDirection() == Direction.LEFT) {
					hero.setCanGoRight(false);
					hero.setX(wallsToCollision.get(i).getX()+blockSize);
					break;
				}
				else if (hero.getDirection() == Direction.UP) {
					hero.setCanGoRight(false);
					hero.setY(wallsToCollision.get(i).getY()+blockSize);
					break;
				}
				else if (hero.getDirection() == Direction.DOWN) {
					hero.setCanGoRight(false);
					hero.setY(wallsToCollision.get(i).getY()-hero.getHeight());
					break;
				}
			}
		}
		
		// Colisions between bullets and monsters
		for (int i = 0; i < bullets.size(); i++) {
			for (int j = 0; j < monsters.size(); j++) {
				if (bullets.get(i).getBounds().intersects(monsters.get(j).getBounds())) {
					monsters.get(j).shoot(bullets.get(i).getDamage());
					bullets.remove(bullets.get(i));
					
					if (monsters.get(j).getLife() <= 0) {
						monsters.remove(monsters.get(j));
					}
					break;
				}
			}
		}
		
		// Collect items
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getBounds().intersects(hero.getBounds())) {
				items.remove(items.get(i));
			}
		}
	}
	

	
	private void updateCorners() {
		hero.setTopLeftX(hero.getX()-1);
		hero.setTopLeftY(hero.getY()-1);
		
		hero.setTopRightX(hero.getTopLeftX()+75+1);
		hero.setTopRightY(hero.getY());
		
		hero.setDownLeftX(hero.getTopLeftX());
		hero.setDownLeftY(hero.getTopLeftY()+75+1);
		
		hero.setDownRightX(hero.getDownLeftX()+75+1);
		hero.setDownRightY(hero.getDownLeftY());
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		
		
		if (inGame) {
			
			// Print ground
			for (int i = 0; i < groundTilesX.size(); i++) {
				g2d.drawImage(groundTile, groundTilesX.get(i), groundTilesY.get(i),
	                    this);
			}
			
			// Print Walls
			for (int i = 0; i < wallsToPrint.size(); i++) {
//				Image scaledImage = walls.get(i).getImage().getScaledInstance(100, 100, Image.SCALE_FAST);
//				BufferedImage imageBuff = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
//				Graphics g2 = imageBuff.getGraphics();

				g2d.drawImage(wallsToPrint.get(i).getImage(), wallsToPrint.get(i).getX(), wallsToPrint.get(i).getY(),
	                    this);
//				g2.dispose();
			}
			
			// Print bullets
			bullets.addAll(hero.getBullets());
			hero.getBullets().clear();
			
			for (int i = 0; i < bullets.size(); i++) {
				g2d.drawImage(bullets.get(i).getImage(), bullets.get(i).getX(), bullets.get(i).getY(),
	                    this);
			}
			
			// Print items
			for (int i = 0; i < items.size(); i++) {
				g2d.drawImage(groundTile, items.get(i).getX(), items.get(i).getY(),
	                    this);
				g2d.drawImage(items.get(i).getImage(), items.get(i).getX(), items.get(i).getY(),
	                    this);
			}
			
			// Print monsters
			for (int i = 0; i < monsters.size(); i++) {
				g2d.drawImage(monsters.get(i).getImage(), monsters.get(i).getX(), monsters.get(i).getY(),
	                    this);
			}
			
			// Print door
			if (items.size() != 0) {
				g2d.drawImage(door, currentFase.getExitPosX(), currentFase.getExitPosY(),
	                    this);
			}

			
			// Print hero
			g2d.drawImage(hero.getImage(), hero.getX(), hero.getY(),
                    this);			
			
			// Print Pause
			if (!timer.isRunning()) {
				
				String msg = "Game Paused";
				Font small = new Font("Helvetica", Font.BOLD, 30);
	            FontMetrics metr = this.getFontMetrics(small);
	            g2d.setColor(Color.white);
	            g2d.setFont(small);
				
				g2d.drawString(msg, (GAME_WIDTH - metr.stringWidth(msg)) /2, GAME_HEIGHT/2);
			}
			
			// Touching monster
			if (touchingMonster) {
				String msg = "Touching monster";
				Font small = new Font("Helvetica", Font.BOLD, 30);
	            FontMetrics metr = this.getFontMetrics(small);
	            g2d.setColor(Color.white);
	            g2d.setFont(small);
				
				g2d.drawString(msg, (GAME_WIDTH - metr.stringWidth(msg)) /2, GAME_HEIGHT/2);
			}
			
			
			// Update screen
	        g.dispose();
		}
	}
}

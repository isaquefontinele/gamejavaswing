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

import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements ActionListener {

	private int WINDOW_WIDTH, WINDOW_HEIGHT;
	private int SPEED = 5;
	private int currentFaseNumber = 1;
	private Fase currentFase;
	private ArrayList<Item> items;
	private ArrayList<Monster> monsters;
	private ArrayList<Wall> walls;
	
	private Timer timer;
	private boolean inGame;
	private Hero hero;
	private boolean doorOpened;
	private boolean touchingMonster;

	
	
	public Gameplay(int WINDOW_WIDTH, int WINDOW_HEIGHT, HeroClass heroClass) {

		this.WINDOW_WIDTH = WINDOW_WIDTH;
		this.WINDOW_HEIGHT = WINDOW_HEIGHT;
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

		addKeyListener(new TAdapter()); // Keyboard input
        setFocusable(true);
        setBackground(Color.GREEN);
        setDoubleBuffered(true);
        inGame = true;

        currentFase = new Fase(WINDOW_WIDTH, WINDOW_HEIGHT);
        hero = new Hero(heroClass);
        initObjects();

        timer = new Timer(SPEED, this);
        timer.start();


	}

	public void initObjects() {

		items = currentFase.getItems();
		monsters = currentFase.getMonsters();
		walls = currentFase.getWalls();
		hero.setX(currentFase.getHeroPosX());
		hero.setY(currentFase.getHeroPosY());

    }
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
        if (items.size()==0) {
            doorOpened = true;
        }
        
        
		hero.move();
		checkCollisions();
		repaint();
//		timer.setDelay(5);
		
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
		
		// Somehow get out of the game back to menu
		if (key == KeyEvent.VK_ESCAPE) {
//			this.setVisible(false);
			
		}

	}

	private void checkCollisions() {
		Rectangle heroRect = hero.getBounds();
		
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
		
//		 Colisions with walls
//		Rectangle wallRect;
//		for (int i = 0; i < walls.size(); i++) {
//			wallRect = walls.get(i).getBounds();
//			
//			if (heroRect.intersects(wallRect)) {
//				if (hero.getDirection() == Direction.LEFT) {
//					hero.setStopLeft(true);
//					break;
//				} else if (hero.getDirection() == Direction.RIGHT) {
//					hero.setStopRight(true);
//					break;
//				} else if (hero.getDirection() == Direction.UP) {
//					hero.setStopUp(true);
//					break;
//				} else if (hero.getDirection() == Direction.DOWN) {
//					hero.setStopDown(true);
//					break;
//				} else {
//					hero.Go();
//				}
//			}
//		}
		
	}
	

	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		
		if (inGame) {
			

			
			// Print Walls
			for (int i = 0; i < walls.size(); i++) {
//				Image scaledImage = walls.get(i).getImage().getScaledInstance(100, 100, Image.SCALE_FAST);
//				BufferedImage imageBuff = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
//				Graphics g2 = imageBuff.getGraphics();

				
				g2d.drawImage(walls.get(i).getImage(), walls.get(i).getX(), walls.get(i).getY(),
	                    this);
//				g2.dispose();
			}
			
			
			
			// Print items
			for (int i = 0; i < items.size(); i++) {
				g2d.drawImage(items.get(i).getImage(), items.get(i).getX(), items.get(i).getY(),
	                    this);
			}
			
			// Print monsters
			for (int i = 0; i < monsters.size(); i++) {
				g2d.drawImage(monsters.get(i).getImage(), monsters.get(i).getX(), monsters.get(i).getY(),
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
				
				g2d.drawString(msg, (WINDOW_WIDTH - metr.stringWidth(msg)) /2, WINDOW_HEIGHT/2);
			}
			
			// Touching monster
			if (touchingMonster) {
				String msg = "Touching monster";
				Font small = new Font("Helvetica", Font.BOLD, 30);
	            FontMetrics metr = this.getFontMetrics(small);
	            g2d.setColor(Color.white);
	            g2d.setFont(small);
				
				g2d.drawString(msg, (WINDOW_WIDTH - metr.stringWidth(msg)) /2, WINDOW_HEIGHT/2);
			}
			
			
			// Update screen
//	        Toolkit.getDefaultToolkit().sync();
	        g.dispose();
		}
	}
}

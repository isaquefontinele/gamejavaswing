package gameplay;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements ActionListener {

	private int WINDOW_WIDTH, WINDOW_HEIGHT;
	private int SPEED = 5;
	private int currentFaseNumber = 1;
	private Fase currentFase;
	private String[][] matrix;
	private Item[] items;
	private Monster[] monsters;
	
	private Timer timer;
	private boolean inGame;
	private Hero hero;
	private boolean doorOpened;

	
	
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
        matrix = currentFase.getMatrix();
        hero = new Hero(heroClass);
        initObjects();

        timer = new Timer(SPEED, this);
        timer.start();
	}

	public void initObjects() {

		items = currentFase.getItems();
		monsters = currentFase.getMonsters();
		

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
		
		if (key == KeyEvent.VK_ESCAPE) {
			this.removeAll();
		}

	}

	private void checkCollisions() {
		Rectangle heroRect = hero.getBounds();
		Rectangle[] monstersRect = new Rectangle[monsters.length];
		
		for (int i = 0; i < monsters.length; i++) {
			monstersRect[i] = monsters[i].getBounds();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
        if (items.length==0) {
            doorOpened = true;
        }
        
        
		hero.move();
		checkCollisions();
		repaint();
		
//		this.removeAll();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		if (inGame) {
			
			Graphics2D g2d = (Graphics2D)g;
			
			// Print items
			for (int i = 0; i < items.length; i++) {
				g2d.drawImage(items[i].getImage(), items[i].getX(), items[i].getY(),
	                    this);
			}
			
			// Print monsters
			for (int i = 0; i < monsters.length; i++) {
				g2d.drawImage(monsters[i].getImage(), monsters[i].getX(), monsters[i].getY(),
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
			
			// Update screen
	        Toolkit.getDefaultToolkit().sync();
	        g.dispose();
		}
	}
}

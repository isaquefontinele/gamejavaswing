package gameplay;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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

	private int SPEED = 5;
	private int currentFaseNumber = 1;
	private Fase currentFase;
	private String[][] matrix = currentFase.getMatrix();
	private Item[] items;
	private Monster[] monsters;
	
	private Timer timer;
	private boolean inGame;
	private Hero hero;
	private boolean doorOpened;

	
	
	public Gameplay(int WINDOW_WIDTH, int WINDOW_HEIGHT) {

		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		
		addKeyListener(new TAdapter()); // Keyboard input
        setFocusable(true);
        setBackground(Color.GREEN);
        setDoubleBuffered(true);
        inGame = true;

        currentFase = new Fase(WINDOW_WIDTH, WINDOW_HEIGHT);
        hero = new Hero();
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
        }
    }
	
	
	private void updateScore() {
		
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
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		if (inGame) {
			
			Graphics2D g2d = (Graphics2D)g;
			
			// Print hero
			g2d.drawImage(hero.getImage(), hero.getX(), hero.getY(),
                    this);
			
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
			
			// Print background
//			g2d.setColor(Color.RED);
			
			// Update screen
	        Toolkit.getDefaultToolkit().sync();
	        g.dispose();
		}
	}
}

package gameplay;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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

	private int speed = 5;
	private int currentFaseNumber = 1;
	private Fase currentFase = new Fase();
	private String[][] matrix = currentFase.getMatrix();
	private ArrayList<Item> items;
	
	private Timer timer;
	private boolean inGame;
	private Hero hero;
	private boolean doorOpened;
	private ArrayList<Monster> monsters;
	
	
	public Gameplay(int WINDOW_WIDTH, int WINDOW_HEIGHT) {

		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		
		setBackground(Color.GREEN);
		addKeyListener(new TAdapter());
        setFocusable(true);
        setDoubleBuffered(true);
        inGame = true;

        hero = new Hero();
        initObjects();

        timer = new Timer(speed, this);
        timer.start();
	}

	public void initObjects() {
		items = new ArrayList<Item>();
		monsters = new ArrayList<Monster>();
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

	
	@Override
	public void actionPerformed(ActionEvent arg0) {
        if (items.size()==0) {
            doorOpened = true;
        }
        
        
		hero.move();
		checkCollisions();
		repaint();
		
	}

	private void checkCollisions() {
		Rectangle heroRect = hero.getBounds();
		Rectangle[] monstersRect = new Rectangle[monsters.size()];
		
		for (int i = 0; i < monsters.size(); i++) {
			monstersRect[i] = monsters.get(i).getBounds();
		}
	}
}

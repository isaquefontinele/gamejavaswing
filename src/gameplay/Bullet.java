package gameplay;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Bullet {

	private final Direction direction;
    private int x, y;
    private Image currentImage;
    boolean visible;
    private int width, height;
    private final int BLOCK_SIZE = 75;
    private final int MISSILE_SPEED = 2;

    public Bullet(int x, int y, Direction direction) {

    	this.direction = direction;
    	setImage();
        visible = true;
        width = currentImage.getWidth(null);
        height = currentImage.getHeight(null);
        this.x = x;
        this.y = y;
    }


    

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void move() {       
        switch (direction) {
		case RIGHT:
			x += MISSILE_SPEED;
			break;
		case LEFT:
			x -= MISSILE_SPEED;
			break;
		case UP:
			y -= MISSILE_SPEED;
			break;
		case DOWN:
			y += MISSILE_SPEED;
			break;
		}
        
        
//        if (x <= BLOCK_SIZE*2) {
//        	visible = false;
//		}
//		
//		if (y <= BLOCK_SIZE && x < BLOCK_SIZE*9) {
//			visible = false;
//		}
//		 
////		 if (x >= blockSize*(matrixWidth-2)) {
////			x = blockSize*(matrixWidth-2);
////		 }
////		 
////		 if (y >= blockSize*(matrixHeigth-2)) {
////			 y = blockSize*(matrixHeigth-2);
////		}
//        if (x > BOARD_WIDTH)
//            visible = false;
    }

    public Image getImage() {
        return currentImage;
    }
    
	public void setImage() {
		currentImage = new ImageIcon(this.getClass().getResource("/images/arrowRight.png")).getImage();
        switch (direction) {
		case RIGHT:
			currentImage = new ImageIcon(this.getClass().getResource("/images/arrowRight.png")).getImage();
			break;
		case LEFT:
			currentImage = new ImageIcon(this.getClass().getResource("/images/arrowLeft.png")).getImage();
			break;
		case UP:
			currentImage = new ImageIcon(this.getClass().getResource("/images/arrowUp.png")).getImage();
			break;
		default: // Down
			currentImage = new ImageIcon(this.getClass().getResource("/images/arrowDown.png")).getImage();
			break;
		}
	}

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
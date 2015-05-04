package gameplay;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

import javax.swing.ImageIcon;

public class Torch {

	private Image image;
	private Image f1, f2, f3, f4, f5, f6, f7, f8, f9;
	private int torchIndex = 1;
	private int x;
	private int y;
	private int width;
	private int height;
	private Direction direction;

	public Torch() {

		this.f1 = new ImageIcon(this.getClass().getResource(
				"/images/torch1.png")).getImage();
		this.f2 = new ImageIcon(this.getClass().getResource(
				"/images/torch2.png")).getImage();
		this.f3 = new ImageIcon(this.getClass().getResource(
				"/images/torch3.png")).getImage();
		this.f4 = new ImageIcon(this.getClass().getResource(
				"/images/torch4.png")).getImage();
		this.f5 = new ImageIcon(this.getClass().getResource(
				"/images/torch5.png")).getImage();
		this.f6 = new ImageIcon(this.getClass().getResource(
				"/images/torch6.png")).getImage();
		this.f7 = new ImageIcon(this.getClass().getResource(
				"/images/torch7.png")).getImage();
		this.f8 = new ImageIcon(this.getClass().getResource(
				"/images/torch8.png")).getImage();
		this.f9 = new ImageIcon(this.getClass().getResource(
				"/images/torch9.png")).getImage();
		this.image = f1;
		this.direction = Direction.LEFT;

		setWidth(getImage().getWidth(null));
		setHeight(getImage().getHeight(null));
	}

	/*
	 * Updates the current frame of the torch
	 */
	public void updateFrame() {
		switch (torchIndex) {
		case 1:
			setImage(f1);
			break;
		case 2:
			setImage(f2);
			break;
		case 3:
			setImage(f3);
			break;
		case 4:
			setImage(f4);
			break;
		case 5:
			setImage(f5);
			break;
		case 6:
			setImage(f6);
			break;
		case 7:
			setImage(f7);
			break;
		case 8:
			setImage(f8);
			break;
		case 9:
			setImage(f9);
			break;
		}
		if (torchIndex == 9) {
			torchIndex = 1;
		} else {
			torchIndex += 1;
		}
	}

	// Getters and Setters
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
}

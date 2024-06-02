package model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public abstract class AObject {
	protected IMoveBehavior moveBehavior;
    protected int x;
    protected int y;
    protected int deltaY;
    protected int deltaX;
    protected int speed;
    protected int weight;
    protected int height;
    protected boolean dead = false;
    protected Image image;

    public AObject(IMoveBehavior moveBehavior, int x, int y, int deltaY, int deltaX, int speed, int weight, int height, boolean dead, Image image) {
        this.moveBehavior = moveBehavior;
        this.x = x;
        this.y = y;
        this.deltaY = deltaY;
        this.deltaX = deltaX;
        this.speed = speed;
        this.weight = weight;
        this.height = height;
        this.dead = dead;
        this.image = image;
    }

    public abstract void render(Graphics g);

    public IMoveBehavior getMoveBehavior() {
        return moveBehavior;
    }

    public void setMoveBehavior(IMoveBehavior moveBehavior) {
        this.moveBehavior = moveBehavior;
    }

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

    public int getDeltaX() {
        return deltaX;
    }

    public void setDeltaX(int deltaX) {
        this.deltaX = deltaX;
    }

    public int getDeltaY() {
        return deltaY;
    }
    
    public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public void setDeltaY(int deltaY) {
        this.deltaY = deltaY;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
    public Rectangle getBounds() {
		return new Rectangle(x, y, weight, height);
	}

	
}

package model;

import java.awt.Graphics; 
import java.awt.Image;


public class Rock extends AObject {
	public Rock(IMoveBehavior moveBehavior, int x, int y, int deltaY, int deltaX, int speed, int weight, int height,
			boolean dead, Image image) {
		super(moveBehavior, x, y, deltaY, deltaX, speed, weight, height, dead, image);
	}

	@Override
	public void render(Graphics g) {
		if (image != null) {
			g.drawImage(image, x, y, weight, height, null);
		}
	}
	public void setDead(boolean status) {
		this.dead = status;
	}
	public void performMove() {
		moveBehavior.move(this);
		if(this.y < 0) {
			this.setDead(true);
		}
		if (getY() > GameContext.getInstance().getGamePanel().getHeight()) {
            // Reset the rock position to the top
            setY(0);
            // Randomize the X position for variety
            setX((int) (Math.random() * (GameContext.getInstance().getGamePanel().getWidth() - getWeight())));
        }
	}
}

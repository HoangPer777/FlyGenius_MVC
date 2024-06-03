package model;

import java.awt.Graphics; 
import java.awt.Image;

import view.GameFrame;


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
	public void resetPosition() {
        // Reset the rock to the top of the screen with a random X position
        this.y = 0;
        this.x = (int) (Math.random() * (GameFrame.getGameWight() - this.weight));
        this.dead = false; // Ensure the rock is marked as alive
    }
}

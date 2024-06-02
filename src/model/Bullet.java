package model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Bullet extends AObject {

	public Bullet(IMoveBehavior moveBehavior, int x, int y, int deltaY, int deltaX, int speed, int weight, int height,
			boolean dead, Image image) {
		super(moveBehavior, x, y, deltaY, deltaX, speed, weight, height, dead, image);
	}



	@Override
	public void render(Graphics g) {
		if (image != null) {
			g.drawImage(image, x, y, weight, height, null);
		}
	}

	public boolean isDead() {
		return this.y < 0;
	}

	public void performMove() {
		moveBehavior.move(this);
		if (this.y < 0) {
			this.setDead(true);
		}
	}

	public void setDead(boolean status) {
		this.dead = status;
	}

//	public Rectangle getBounds() {
//		return new Rectangle(x, y, weight, height);
//	}
}

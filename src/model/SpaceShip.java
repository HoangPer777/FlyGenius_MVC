package model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.List;

public class SpaceShip extends AObject {
	private int healthPlayer = 3;
	private List<Bullet> bullets;
//	private BufferedImage currAnimation;
//	private List<Bullet> spaceShipShoot;
	public IMoveBehavior moveBehavior;

	public SpaceShip(IMoveBehavior moveBehavior, int x, int y, int deltaY, int deltaX, int speed, int weight,
			int height, boolean dead, Image image, GameContext gameContext) {
		super(moveBehavior, x, y, deltaY, deltaX, speed, weight, height, dead, image);
		this.moveBehavior = moveBehavior;
	}

	public void setHealthPlayer(int healthPlayer) {
		this.healthPlayer = healthPlayer;
	}

	public IMoveBehavior getMoveBehavior() {
		return moveBehavior;
	}

	public void performMove() {
		moveBehavior.move(this);
	}

	public int getHealthPlayer() {
		return this.healthPlayer;
	}

	@Override
	public void render(Graphics g) {
		if (image != null) {
			g.drawImage(image, x, y, weight, height, null);
		}
	}

	public boolean isDead() {
		return dead;
	}

}

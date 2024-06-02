package model;

import java.util.Random;

import view.GameFrame;

public class MoveFreedom implements IMoveBehavior {
	private final GameContext gameContext = GameContext.getInstance();
	private boolean movingLeft = true;
	AObject object;
	public static final int speed = 2;

	public boolean isMovingLeft() {
		return movingLeft;
	}

	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}


	@Override
	public void move(Object obj) {
		object = (AObject) obj;
//		int speed = object.getSpeed();
		int x = object.getX();
		int y = object.getY();
		boolean movingLeft = isMovingLeft();

		if (movingLeft) {
			object.setX(x - speed);
			if (x <= 0) {
				setMovingLeft(false);
				object.setY(y + 20); // Move down when hitting the edge
			}
		} else {
			object.setX(x + speed);
			if (x >= GameFrame.getGameWight() - object.getWeight()) {
				setMovingLeft(true);
				object.setY(y + 20); // Move down when hitting the edge
			}
		}
		Random random = new Random();
		if (random.nextInt(100) < 0.1) {
			Laser laser = new Laser(new MoveStraight(), object.x + object.weight / 2, object.y + object.height, 
					1, // Move 
					0, 
					2, // speed
					8, 
					8, false, ImageLoader.loadImage("/images/laser.png") // Load laser image
			);
			gameContext.addLaser(laser);
		}

	}

}

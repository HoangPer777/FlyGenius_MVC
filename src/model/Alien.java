package model;

import java.awt.Graphics; 
import java.awt.Image;
import java.util.Random;

public class Alien extends AObject {
    private int health = 2;
//    private boolean movingLeft = true;
    public IMoveBehavior moveBehavior; 
    private Random random;

    public Alien(IMoveBehavior moveBehavior, int x, int y, int deltaY, int deltaX, int speed, int weight, int height, boolean dead, Image image, GameContext gameContext) {
        super(moveBehavior, x, y, deltaY, deltaX, speed, weight, height, dead, image);
        this.moveBehavior = moveBehavior; 
        this.random = new Random();
    }

    @Override
    public void render(Graphics g) {
        if (image != null) {
            g.drawImage(image, x, y, weight, height, null);
        }
    }

    public Random getRandom() {
		return random;
	}

//	public boolean isMovingLeft() {
//        return movingLeft;
//    }
//
//    public void setMovingLeft(boolean movingLeft) {
//        this.movingLeft = movingLeft;
//    }


    public int getSpeed() {
        return this.speed;
    }

    public void performMove() {
        moveBehavior.move(this);
//        if (random.nextInt(100) < 2) { // 2% chance to shoot every move
//            shoot();
//        }
    }

//    public void shoot() {
//        Laser laser = new Laser(
//            new MoveStraight(),
//            this.x + this.weight / 2,
//            this.y + this.height,
//            1, // Move downward
//            0,
//            4, //speed
//            5,
//            10,
//            false,
//            ImageLoader.loadImage("/images/laser.png") // Load laser image
//        );
//        gameContext.addLaser(laser);
//    }

	public boolean isDead() {
		return this.dead == true;
	}
	
	public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
//    public Rectangle getBounds() {
//        return new Rectangle(x, y, weight, height);
//    }

}

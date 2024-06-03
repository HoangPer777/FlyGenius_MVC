package model;

import view.GamePanel;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import controller.MoveControl;

public class GameContext {
	private static GameContext instance;
	private SpaceShip spaceShip;
	private Rock rock;
	private List<Alien> aliens;
	private List<Bullet> bullets;
	private List<Laser> lasers;
	private List<Rock> rocks;
	MoveControl moveControl;
	private boolean gameRunning;
	private GamePanel gamePanel;
	private int score;

	private GameContext() {
		aliens = new ArrayList<>();
		bullets = new ArrayList<>();
		lasers = new ArrayList<>();
		rocks = new ArrayList<>();
		gameRunning = true;
		score = 0;
	}

	public static synchronized GameContext getInstance() {
		if (instance == null) {
			instance = new GameContext();
		}
		return instance;
	}

	public List<Rock> getRocks() {
		return rocks;
	}

	public void addRock(Rock rock) {
		rocks.add(rock);
	}

	public void removeRock(Rock rock) {
		rocks.remove(rock);
	}

	public Rock getRock() {
		return rock;
	}

	public void setRock(Rock rock) {
		this.rock = rock;
	}

	public SpaceShip getSpaceShip() {
		return spaceShip;
	}

	public void setSpaceShip(SpaceShip spaceShip) {
		this.spaceShip = spaceShip;
	}

	public List<Alien> getAlienList() {
		return aliens;
	}

	public void addAlien(Alien alien) {
		aliens.add(alien);
	}

	public List<Bullet> getBullets() {
		return bullets;
	}

	public void addBullet(Bullet bullet) {
		bullets.add(bullet);
	}

	public void removeBullet(Bullet bullet) {
		bullets.remove(bullet);
	}

	public List<Laser> getLasers() {
		return lasers;
	}

	public void addLaser(Laser laser) {
		lasers.add(laser);
	}

	public void removeLaser(Laser laser) {
		lasers.remove(laser);
	}

	public boolean isGameRunning() {
		return gameRunning;
	}

	public void setGameRunning(boolean gameRunning) {
		this.gameRunning = gameRunning;
	}

	public GamePanel getGamePanel() {
		return gamePanel;
	}

	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public int getScore() {
		return score;
	}

//	public void incrementScore() {
//		score++;
//	}

	// Method to check if two objects are colliding
	public boolean isColliding(AObject obj1, AObject obj2) {
		return obj1.getX() < obj2.getX() + obj2.getWeight() && obj1.getX() + obj1.getWeight() > obj2.getX()
				&& obj1.getY() < obj2.getY() + obj2.getHeight() && obj1.getY() + obj1.getHeight() > obj2.getY();
	}

	
	public void handleCollisions() {
		List<Bullet> bulletsToRemove = new ArrayList<>();
		List<Alien> aliensToRemove = new ArrayList<>();

		for (Bullet bullet : bullets) {
			for (Alien alien : aliens) {
				if (isColliding(bullet, alien)) {
					alien.setHealth(alien.getHealth() - 1);
					bullet = null;
					score += 5;
					if (alien.getHealth() <= 0) {
						alien.isDead();
						aliensToRemove.add(alien);
						bullet = null; // Set the bullet reference to null
					}
				}
			}
		}
		 for (Bullet bullet : bullets) {
	            for (Rock rock : rocks) {
	                if (isColliding(rock, bullet)) {
	                    bullet.setDead(true);
	                    rock.resetPosition(); // Reset the rock's position instead of removing it
	                    score += 1; 
	                }
	            }
	        }

		bullets.removeAll(bulletsToRemove);
		aliens.removeAll(aliensToRemove);
	}
	
	public void collectBullet_rock() {
		List<Bullet> bulletRemove = new ArrayList<Bullet>();
		List<Rock> rockRemove = new ArrayList<>();
		for (Bullet bullet : bullets) {
			for (Rock rock : rocks) {
				if (isColliding(rock, bullet)) {
					score+=1;
					bulletRemove.add(bullet);
					rockRemove.add(rock);
				}

			}
		}
		bullets.removeAll(bulletRemove);
		rocks.removeAll(rockRemove);
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void addScore(int points) {
		this.score += points;
	}

	public void collectionSpaceShip_Rock() {
		List<Rock> rocksToRemove = new ArrayList<>();
		for (Rock rock : rocks) {
			if (isColliding(rock, spaceShip)) {
				spaceShip.setHealthPlayer(spaceShip.getHealthPlayer() - 1);
				rocksToRemove.add(rock);

			}
		}
		rocks.removeAll(rocksToRemove); // only 1
	}

	public void collectionSpaceShip_Laser() {
		List<Laser> laserToRemove = new ArrayList<Laser>();
		for(Laser laser: lasers) {
			if(isColliding(laser, spaceShip)) {
				spaceShip.setHealthPlayer(spaceShip.getHealthPlayer() - 1);
				laserToRemove.add(laser);
			}
		}
		lasers.removeAll(laserToRemove);
	}
	
	
	
	 public void update() {
	        SpaceShip spaceShip = getSpaceShip();
	        spaceShip.performMove();

	        for (Rock rock : getRocks()) {
	            rock.performMove();
	        }

	        for (Alien alien : getAlienList()) {
	            alien.performMove();
	        }

	        for (Bullet bullet : getBullets()) {
	            bullet.performMove();
	        }

	        for (Laser laser : getLasers()) {
	            laser.performMove();
	        }

	        handleCollisions();
	        collectBullet_rock();
	        collectionSpaceShip_Laser();
	        collectionSpaceShip_Rock();

	        // Additional game logic
	    }

	
}
package controller;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Alien;
import model.Bullet;
import model.GameContext;
import model.ImageLoader;
import model.Laser;
import model.MoveFreedom;
import model.MoveStraight;
import model.Rock;
import model.SpaceShip;
import view.GamePanel;

public class GameController implements ActionListener {
	private GameContext gameContext;
	private GamePanel gamePanel;
	private Image rockImage = ImageLoader.loadImage("/images/rock.png");
	private Image alienImage = ImageLoader.loadImage("/images/alien22.png");
	private Random random;

	public GameController(GameContext gameContext, GamePanel gamePanel) {
		this.gameContext = gameContext;
		this.gamePanel = gamePanel;
		this.rockImage = ImageLoader.loadImage("/images/rock.png");
		this.alienImage = ImageLoader.loadImage("/images/alien2.png");
		this.random = new Random();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (gameContext.isGameRunning()) {
			updateObjects();
			checkAlienPositionAndSpawn();
			gameContext.update();
			gamePanel.repaint();
		}
	}

	private void updateObjects() {
		// Update game objects
		gameContext.getSpaceShip().performMove();
		for (Rock rock : gameContext.getRocks()) {
			rock.performMove();
		}
		gameContext.getRocks().removeIf(rock -> {
			if (rock.getX() > gamePanel.getWidth()) {
				int randomX = new Random().nextInt(gamePanel.getWidth() + 50);
				Rock newRock = new Rock(new MoveStraight(), randomX, 0, 1, 1, 1, 50, 50, false, rockImage);
				gameContext.addRock(newRock);
				return true;
			}
			return false;
		});

		for (Alien alien : gameContext.getAlienList()) {
			alien.performMove();
		}

		for (Bullet bullet : gameContext.getBullets()) {
			bullet.performMove();
		}

		for (Laser laser : gameContext.getLasers()) {
			laser.performMove();
		}

		gameContext.handleCollisions();
		gameContext.collectBullet_rock();
	}

	

	private void checkAlienPositionAndSpawn() {
		int panelHeight = gamePanel.getHeight();
		int panelWidth = gamePanel.getWidth();

		for (Alien alien : gameContext.getAlienList()) {
			if (alien.getY() >= panelHeight / 2 && gameContext.getAlienList().size() < 2) {
				spawnAlien(panelWidth);
				break; // Only spawn one alien per frame
			}
		}
		
		if (gameContext.getAlienList().size() < 1) {
			spawnAlien(gamePanel.getWidth());
		}
	}

	private void spawnAlien(int panelWidth) {
		int randomX = random.nextInt(panelWidth - 50); // Ensure alien is within bounds
		Alien newAlien = new Alien(new MoveFreedom(), randomX, 0, 1, 1, 1, 50, 50, false, alienImage, gameContext);
		gameContext.addAlien(newAlien);
	}

	
	

	

}

package model;

import java.awt.Image;
import java.util.Random;

import javax.swing.JFrame;

import controller.GameController;
import controller.MoveControl;
import view.GameFrame;
import view.GamePanel;

public class GameMain {
	public static void main(String[] args) {

		GameContext gameContext = GameContext.getInstance();

		// Initialize JFrame and GamePanel
		JFrame frame = new GameFrame();
		GamePanel gamePanel = new GamePanel();
		gameContext.setGamePanel(gamePanel);

//		/////
//		GameController controller = new GameController(gameContext, gamePanel);
//		gamePanel.setController(controller);

		frame.add(gamePanel);
		frame.setVisible(true);

		// Load images
		Image spaceShipImage = ImageLoader.loadImage("/images/spaceship.png");
		Image alienImage = ImageLoader.loadImage("/images/alien22.png");
		Image bulletImage = ImageLoader.loadImage("/images/bullet.png");
		Image rockImage = ImageLoader.loadImage("/images/rock.png");
		Image backgroundImage = ImageLoader.loadImage("/images/background.png");

		// Initialize spaceship
		SpaceShip spaceShip = new SpaceShip(new MoveControl(), 400, 500, 0, 0, 4, 50, 50, false, spaceShipImage,
				gameContext);
		gameContext.setSpaceShip(spaceShip);

		// Initialize aliens
		Alien alien = new Alien(new MoveFreedom(), 100, 100, 1, 1, 1, 50, 50, false, alienImage, gameContext);
		gameContext.addAlien(alien);

//		int randomX = (int) (Math.random() * (GameFrame.getGameWight() - 50));
//		Rock rock = new Rock(new MoveStraight(),randomX, 00, 1, 1, 1, 50, 50, false, rockImage);
//		gameContext.setRock(rock);

		// Initialize multiple rocks
		Random random = new Random();
		for (int i = 0; i < 3; i++) { // Example: add 3 rocks
			int randomX = random.nextInt(GameFrame.getGameWight());
			int randomY = (int) (Math.random() * (GameFrame.getGameHight() / 3 + 400));
			Rock rock = new Rock(new MoveStraight(), randomX, randomY, 1, 1, 1, 40, 40, false, rockImage);
			gameContext.addRock(rock);
		}
		for (int i = 0; i < 2; i++) {
			int randomX = random.nextInt(GameFrame.getGameWight());
			int randomY = (int) (Math.random() * (GameFrame.getGameHight() / 3 - 500));
			Rock rock = new Rock(new MoveStraight(), randomX, randomY, 1, 1, 1, 45, 45, false, rockImage);
			gameContext.addRock(rock);
		}
		for (int i = 0; i < 1; i++) {
			int randomX = random.nextInt(GameFrame.getGameWight());
			int randomY = (int) (Math.random() * (GameFrame.getGameHight() / 3 - 500));
			Rock rock = new Rock(new MoveStraight(), randomX, randomY, 1, 1, 2, 50, 50, false, rockImage);
			gameContext.addRock(rock);
		}
		for (int i = 0; i < 1; i++) {
			int randomX = random.nextInt(GameFrame.getGameWight());
			int randomY = (int) (Math.random() * (GameFrame.getGameHight() / 2 - 650));
			Rock rock = new Rock(new MoveStraight(), randomX, randomY, 1, 1, 2, 50, 50, false, rockImage);
			gameContext.addRock(rock);
		}
		// Initialize the first rock
//        Random random = new Random();
//        int initialRockX = random.nextInt(GameFrame.getGameWight() - 50);
//        Rock initialRock = new Rock(new MoveStraight(), initialRockX, 0, 1, 1, 1, 50, 50, false, rockImage);
//        gameContext.addRock(initialRock);

		// Add key listener for spaceship controls
		frame.addKeyListener((MoveControl) spaceShip.getMoveBehavior());

		GameController gameController = new GameController(gameContext, gamePanel);
		gamePanel.setController(gameController);

		Random random1 = new Random();
		while (gameContext.isGameRunning()) {
			spaceShip.performMove();
//			rock.performMove();

			// Use GameController to handle game logic
            gameController.actionPerformed(null);
            
			for (Rock rock : gameContext.getRocks()) {
				rock.performMove();
			}
			// Check if any rock has left the screen and spawn a new rock
			gameContext.getRocks().removeIf(rock -> {
				if (rock.getX() > GameFrame.getGameWight()) {
					// Spawn new rock
					int randomX = random1.nextInt(GameFrame.getGameWight() + 50);
					Rock newRock = new Rock(new MoveStraight(), randomX, 0, 1, 1, 1, 50, 50, false, rockImage);
					gameContext.addRock(newRock);
					return true; // Remove the old rock
				}
				return false; // Keep the rock
			});

			for (Alien a : gameContext.getAlienList()) {
				a.performMove();
			}

			for (Bullet b : gameContext.getBullets()) {
				b.performMove();
			}

			for (Laser l : gameContext.getLasers()) {
				l.performMove();
			}

			// Handle collisions and update score
//			gameContext.handleCollisions();
//			gameContext.collectBullet_rock();
//			gameContext.collectionSpaceShip_Laser();
//			gameContext.collectionSpaceShip_Rock();
////			gameContext.collectionSpaceShip_Alien();
//			gameContext.getGamePanel().repaint();

			try {
				Thread.sleep(16); // ~60 FPS
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

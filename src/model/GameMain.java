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

		frame.add(gamePanel);
		frame.setVisible(true);

		ImageManager imageManager = ImageManager.getInstance();
		Image spaceShipImage = imageManager.getSpaceShipImage();
		Image alienImage = imageManager.getAlienImage();
		Image rockImage = imageManager.getRockImage();
		
		// Initialize spaceship
		SpaceShip spaceShip = new SpaceShip(new MoveControl(), 400, 500, 0, 0, 4, 50, 50, false, spaceShipImage,
				gameContext);
		gameContext.setSpaceShip(spaceShip);

		// Initialize aliens
		Alien alien = new Alien(new MoveFreedom(), 100, 100, 1, 1, 1, 50, 50, false, alienImage, gameContext);
		gameContext.addAlien(alien);

		GameController gameController = new GameController(gameContext, gamePanel);
		gamePanel.setController(gameController);
		
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


		// Add key listener for spaceship controls
		frame.addKeyListener((MoveControl) spaceShip.getMoveBehavior());


		GameLoop gameLoop = new GameLoop(gameContext, gameController);
		gameLoop.start();
	}
}

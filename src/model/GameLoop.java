package model;

import java.awt.Image;
import java.util.Random;

import controller.GameController;
import view.GameFrame;

public class GameLoop extends Thread {
    private GameContext gameContext;
    private GameController gameController; 	
    private Random random1 = new Random();
    SpaceShip spaceShip;
    ImageManager imageManager = ImageManager.getInstance();
    Image rockImage = imageManager.getRockImage();
    public GameLoop(GameContext gameContext, GameController gameController) {
        this.gameContext = gameContext;
        this.gameController = gameController;
    }

    @Override
    public void run() {
        while (gameContext.isGameRunning()) {
            gameContext.getSpaceShip().performMove();

            gameController.actionPerformed(null);

            for (Rock rock : gameContext.getRocks()) {
                rock.performMove();
            }
            gameContext.getRocks().removeIf(rock -> {
                if (rock.getX() > GameFrame.getGameWight()) {
                    int randomX = random1.nextInt(GameFrame.getGameWight() + 50);
                    Rock newRock = new Rock(new MoveStraight(), randomX, 0, 1, 1, 1, 50, 50, false, rockImage);
                    gameContext.addRock(newRock);
                    return true;
                }
                return false;
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
            
//            if (spaceShip.getHealthPlayer() == 0) {
//                gameContext.setGameRunning(false);
//            }
            

            // Handle collisions and update score
            gameContext.handleCollisions();
            gameContext.collectBullet_rock();
            gameContext.collectionSpaceShip_Laser();
            gameContext.collectionSpaceShip_Rock();
            gameController.checkGameOver();
            gameContext.getGamePanel().repaint();

            try {
                Thread.sleep(16); // ~60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

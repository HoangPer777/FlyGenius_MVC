package view;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import controller.GameController;
import model.Alien;
import model.Bullet;
import model.GameContext;
import model.IMoveBehavior;
import model.ImageManager;
import model.Laser;
import model.Rock;
import model.SpaceShip;

public class GamePanel extends JPanel {
	private GameContext gameContext = GameContext.getInstance();
	private Timer timer;
	private GameController gameController;
	ImageManager imageManager = ImageManager.getInstance();
	Image backgroundImage = imageManager.getBackgroundImage();

	public GamePanel() {

		timer = new Timer(10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (gameController != null) {
					gameController.actionPerformed(e);
				}
			}
		});

	}

	public void setController(GameController controller) {
		this.gameController = controller;
	}

//	public void setBackgroundImage(Image backgroundImage) {
//		this.backgroundImage = backgroundImage;
//	}
//
//	public Image getBackgroundImage() {
//		return this.backgroundImage;
//	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (backgroundImage != null) {
			g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
		}
		SpaceShip spaceShip = gameContext.getSpaceShip();
		if (spaceShip != null) {
			spaceShip.render(g);
		}
//        Rock rock = gameContext.getRock();
//        if(rock != null) {
//        	rock.render(g);
//        }
		for (Rock rock : gameContext.getRocks()) {
			rock.render(g);
		}
		for (Alien alien : gameContext.getAlienList()) {
			alien.render(g);
		}
		for (Bullet bullet : gameContext.getBullets()) {
			bullet.render(g);
		}
		for (Laser laser : gameContext.getLasers()) {
			laser.render(g);
		}

		g.setColor(new Color(52, 152, 219));
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.drawString("HealthPlayer: " + spaceShip.getHealthPlayer(), getWidth() - 155, 20);

		// Draw the score
		g.setColor(new Color(230, 126, 34));
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.drawString("Score: " + gameContext.getScore(), 10, 20);
		
		 if (!gameContext.isGameRunning()) {
	            drawGameOver(g);
	        }
	}

	private void drawGameOver(Graphics g) {
        g.setColor(new Color(231, 76, 60));
        g.setFont(new Font("Arial", Font.BOLD, 60));
        String gameOverText = "GameOver";
        int gameOverTextWidth = g.getFontMetrics().stringWidth(gameOverText);
        g.drawString(gameOverText, (getWidth() - gameOverTextWidth) / 2, getHeight() / 2);
    }
	
}

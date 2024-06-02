package view;

import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.Timer;

import controller.GameController;
import model.Alien;
import model.Bullet;
import model.GameContext;
import model.IMoveBehavior;
import model.ImageLoader;
import model.Laser;
import model.Rock;
import model.SpaceShip;

import java.awt.Image;

public class GamePanel extends JPanel {
	private GameContext gameContext = GameContext.getInstance();
	private Timer timer;
	IMoveBehavior moveBehavior;
	private GameController controller;
	private Image backgroundImage = ImageLoader.loadImage("/images/background.jpg");

//	public GamePanel() {
////		gameContext = GameContext.getInstance();
//		timer = new Timer(10, this);
//		timer.start();
//	}

	public GamePanel() {

		timer = new Timer(10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (controller != null) {
					controller.actionPerformed(e);
				}
			}
		});

	}

	public void setController(GameController controller) {
		this.controller = controller;
	}

	public void setBackgroundImage(Image backgroundImage) {
		this.backgroundImage = backgroundImage;
	}

	public Image getBackgroundImage() {
		return this.backgroundImage;
	}



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
		
		g.setColor(Color.BLUE);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.drawString("HealthPlayer: " + spaceShip.getHealthPlayer(), 150 , 20);

		// Draw the score
		g.setColor(Color.ORANGE);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.drawString("Score: " + gameContext.getScore(), 10, 20);
	}
}

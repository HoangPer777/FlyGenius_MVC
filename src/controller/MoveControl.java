package controller;

import java.awt.event.KeyEvent; 
import java.awt.event.KeyListener;

import model.AObject;
import model.Bullet;
import model.GameContext;
import model.IMoveBehavior;
import model.ImageLoader;
import model.MoveStraight;
import model.SpaceShip;
import view.GameFrame;

public class MoveControl implements IMoveBehavior, KeyListener {
//    private SpaceShip spaceShip;
    private final GameContext gameContext = GameContext.getInstance();
    private Bullet bullet;
    private AObject object;

    public static final int speed = 3;

    @Override
    public void move(Object obj) {
    	object = (AObject)obj;
        object.setX(object.getX() + object.getDeltaX());
        object.setY(object.getY() + object.getDeltaY());

        // Horizontal bounds check
        if (object.getX() < 0) {
            object.setX(0);
        }

        if (object.getX() + object.getWeight() > GameFrame.getGameWight()) {
            object.setX(GameFrame.getGameWight() - object.getWeight());
        }

        // Vertical bounds check
        if (object.getY() < 0) {
            object.setY(0);
        }
        if (object.getY() + (object.getHeight() + 30) > GameFrame.getGameHight()) { // margin 30 because img
            object.setY(GameFrame.getGameHight() - (object.getHeight() + 30));
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        // Do nothing
    }


    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int key = keyEvent.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            object.setDeltaX(-speed);
        }
        if (key == KeyEvent.VK_RIGHT) {
            object.setDeltaX(speed);
        }
        if (key == KeyEvent.VK_UP) {
            object.setDeltaY(-speed); 
        }
        if (key == KeyEvent.VK_DOWN) {
            object.setDeltaY(speed); 
        }
        
        if (key == KeyEvent.VK_SPACE) {
//            if (gameContext.isGameRunning() && !object.isDead() && (bullet == null || bullet.isDead())) {
        	if (gameContext.isGameRunning() && !object.isDead()) {
                bullet = new Bullet(
                    new MoveStraight(),
                    object.getX() + object.getWeight() / 2,
                    object.getY(),
                    -1, //delta y
                    0,	//delta x
                    4, // speed
                    20, // weight
                    35, // height
                    false,
                    ImageLoader.loadImage("/images/bullet22.png") // Load bullet image
                );
                gameContext.addBullet(bullet);
                // Uncomment this to play sound
                // new Thread(() -> {
                //     gameContext.getLaserAudioPlayer().playSound();
                // }).start();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        int key = keyEvent.getKeyCode();
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
            object.setDeltaX(0);
        }
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
            object.setDeltaY(0);
        }
    }

}
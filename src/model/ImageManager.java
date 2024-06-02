package model;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageManager {
    private static ImageManager instance;
    private Image spaceShipImage;
    private Image alienImage;
    private Image bulletImage;
    private Image rockImage;
    private Image backgroundImage;
    private Image laserImage;

    private ImageManager() {
        spaceShipImage = loadImage("/images/spaceship.png");
        alienImage = loadImage("/images/alien22.png");
        bulletImage = loadImage("/images/bullet22.png");
        rockImage = loadImage("/images/rock.png");
        backgroundImage = loadImage("/images/background.png");
        laserImage = loadImage("/images/laser.png");
    }

    public static ImageManager getInstance() {
        if (instance == null) {
            instance = new ImageManager();
        }
        return instance;
    }

    private static Image loadImage(String path) {
        try {
            return ImageIO.read(ImageManager.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Image getSpaceShipImage() {
        return spaceShipImage;
    }

    public Image getAlienImage() {
        return alienImage;
    }

    public Image getBulletImage() {
        return bulletImage;
    }

    public Image getRockImage() {
        return rockImage;
    }

    public Image getBackgroundImage() {
        return backgroundImage;
    }
    public Image getlaserImage() {
        return laserImage;
    }
}

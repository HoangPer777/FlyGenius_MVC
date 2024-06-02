package model;

import java.awt.Image;
import javax.swing.ImageIcon;

public class ImageFactory {
    public static Image createImage(String path) {
        return new ImageIcon(path).getImage();
    }
}

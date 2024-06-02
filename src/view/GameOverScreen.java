package view;

import java.awt.Graphics;
import javax.swing.JPanel;

public class GameOverScreen extends JPanel {
    private GamePanel gamePanel;

    public GameOverScreen(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        initUI();
    }

    private void initUI() {
        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("Game Over", 350, 250);
    }
}

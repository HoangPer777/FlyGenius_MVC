package view;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class StartGameScreen extends JPanel {
    private GamePanel gamePanel;

    public StartGameScreen(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        initUI();
    }

    private void initUI() {
        setLayout(null);

        JButton startButton = new JButton("Start Game");
        startButton.setBounds(350, 300, 100, 50);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                gamePanel.startGame();
            }
        });

        add(startButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw your start screen graphics here
        g.drawString("Welcome to the Game!", 350, 250);
    }
}

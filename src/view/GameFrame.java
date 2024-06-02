package view;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
	public static final int GAME_WIGHT = 800;
	public static final int GAME_HIGHT = 600;
	
    public static int getGameWight() {
		return GAME_WIGHT;
	}

	public static int getGameHight() {
		return GAME_HIGHT;
	}
	
	public GameFrame() {
        setTitle("Game FlyGenius");
        setSize(GAME_WIGHT, GAME_HIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
       
        add(new GamePanel());
    }
}

package Displays;

import java.awt.*;

public class GameOver extends GameObject {
    public GameOver(int x, int y, ID id) {
        super(x, y, id);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.orange);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
        g.drawString("GAME OVER",200,100);
        g.drawString("YOUR SCORE IS " + ScoreBoard.score,200,300);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
        g.drawString("PRESS ENTER TO PLAY AGAIN OR Q TO QUIT", 100,500);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}

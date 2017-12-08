package Displays;

import java.awt.*;

public class ScoreBoard extends GameObject{

    public static int score;
    public static int lives = Game.NUMBER_OF_LIVES;

    public ScoreBoard(int x, int y, ID id) {
        super(x, y, id);
    }


    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.ORANGE);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
        g.drawString("SCORE: " + score, 10, 25);
        g.drawString("LIVES: " + lives, x = 10, y = 50 );

    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}

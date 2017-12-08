package Displays;

import Displays.GameObject;
import Displays.ID;

import java.awt.*;

public class Menu extends GameObject{
    public Menu(int x, int y, ID id) {
        super(x, y, id);
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.orange);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
        g.drawString("WELCOME TO SPACE INVADERS",5,100);
        g.drawString("PRESS ENTER TO BEGIN",100,300);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}

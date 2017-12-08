package Displays;

import com.sun.xml.internal.bind.v2.model.core.ID;

import java.awt.*;
import java.util.Random;

public class Player extends GameObject {

    public static int iWIDTH = 60, iHEIGHT = 10;
    Handler handler;
    Random r = new Random();
    public Player(int x, int y, Displays.ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        this.belongsTo = null;
    }

    @Override
    public void tick() {
        x += vX;
        y += vY;

        if(x < 0){
            x = Game.WIDTH - iWIDTH;
        }
        else if(x > Game.WIDTH - iWIDTH){
            x = 0;
        }

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.orange);
        g.fillRect(x,y,iWIDTH,iHEIGHT);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,iWIDTH,iHEIGHT);
    }
}

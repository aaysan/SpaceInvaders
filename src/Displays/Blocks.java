package Displays;

import java.awt.*;

public class Blocks extends GameObject{
    public static int iWIDTH = 50, iHEIGHT = 50;
    Handler handler;
    public Blocks(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        this.belongsTo = null;
    }

    @Override
    public void tick() {
        collision();
    }

    private void collision() {
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempobj = handler.object.get(i);

            if(tempobj.getId() == Displays.ID.Bullet){
                if(getBounds().intersects(tempobj.getBounds())) {
                    handler.removeObject(tempobj);
                    if (tempobj.getBelongsTo() == ID.Invader){
                        handler.removeObject(this);
                    }
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(x,y,iWIDTH,iHEIGHT);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,iWIDTH,iHEIGHT);
    }
}

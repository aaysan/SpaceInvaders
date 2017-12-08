package Displays;

import org.newdawn.slick.Music;

import java.awt.*;
import java.util.Random;

public class Invader extends GameObject {

    static int iWIDTH = 20, iHEIGHT = 20;
    static int pixelsMoved = 0;
    Handler handler;
    static int counter = 0;
    static boolean period = false;
    static int period_count = Game.INVADER_COL;


    public Invader(int x, int y, ID id, Handler handler, boolean lastRow, int row, int col) {
        super(x, y, id);
        this.handler = handler;
        this.belongsTo = null;
        vX = Game.INVADER_SPEED;
        this.isBottomRow = lastRow;
        this.row = row;
        this.col = col;
        currentShooter = false;
        notShotyet = true;

    }

    @Override
    public void tick() {
        //y += vY;

        if(y > Game.HEIGHT){
            Game.state = Game.gameState.End;
        }

        x += vX;


        if((x < 0 || x > Game.WIDTH - iWIDTH)){
//            x -= vX;
//            for(int i = 0; i < handler.object.size(); i++){
//                GameObject temp = handler.object.get(i);
//
//                if(temp.getId() == ID.Invader && temp.row == this.row){
//                    temp.setvX(-vX);
//                    temp.setX(temp.getX() + temp.getvX());
//                    int alp = 0;
//                    temp.setY(temp.getY() + Game.INVADER_DROPDOWNRATE);
//                }
//
//
//
//            }

            vX = -vX;
            y += Game.INVADER_DROPDOWNRATE;
            period_count = Game.INVADER_COL;

        }

        collision();


        if (currentShooter && Math.random() < 0.0008*(double)Game.INVADER_SHOOT_SPEED) {


            if (isBottomRow) {

                currentShooter = false;
                notShotyet = false;
                shoot();
                period = false;
                period_count--;
                MusicPlayer.getSounds("invaderDead").play();


            }
        }



         /*   if(period_count > 0 && isBottomRow && currentShooter){
            currentShooter = false;
            notShotyet = false;
            shoot();
            period = false;
            period_count--;
            MusicPlayer.getSounds("invaderDead").play();
        }*/




    }

    @Override
    public void render(Graphics g) {


        if(isBottomRow){
            g.setColor(Color.green);
        }
        else {
            g.setColor(Color.red);
        }

        g.fillRect(x,y,iWIDTH,iHEIGHT);
    }

    public void collision(){
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempobj = handler.object.get(i);

            if(tempobj.getId() == ID.Player){
                if(getBounds().intersects(tempobj.getBounds())){
                    Game.state = Game.gameState.End;
                    //TODO:: burada ne oluyor? Oyun mu bitiyor cunku yani geri dogar dogmaz blocklar bitmis olucak
                }
            }
        }
    }

    public void shoot(){
        handler.addObject(new Bullet(getX(), getY() + Invader.iHEIGHT, ID.Bullet, handler, ID.Invader));
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,iWIDTH,iHEIGHT);
    }

}

package Displays;

import org.newdawn.slick.Music;

import java.awt.*;
import java.util.Random;

public class Bullet extends GameObject {
    public static int iWIDTH = 7, iHEIGHT = 15;
    Handler handler;


    public Bullet(int x, int y, ID id, Handler handler, ID belongsTo) {
        super(x, y, id);
        this.handler = handler;
        this.belongsTo = belongsTo;

        if (belongsTo == ID.Player) {
            vY = -5;
        } else if (belongsTo == ID.Invader) {
            vY = 5;
        }

    }

    @Override
    public void tick() {
        y += vY;
        x += vX;

        if (y < 0 || y > Game.HEIGHT) {
            handler.removeObject(this);
            return;
        }

        collision();

    }

    public void collision(){
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempobj = handler.object.get(i);

            if(tempobj.getId() == Displays.ID.Invader || tempobj.getId() == ID.Player){
                if(getBounds().intersects(tempobj.getBounds())){

                    if(tempobj.getId() == ID.Invader && belongsTo == ID.Player){
                        ScoreBoard.score++;
                        Game.blockcount--;
                        int trow = tempobj.row;
                        int tcol = tempobj.col;
                        handler.removeObject(tempobj);

                        //MusicPlayer.getSounds("invaderDead").play();

                        setNewLastRow(trow,tcol);

                        if(Game.blockcount == 0){
                            Game.state = Game.gameState.End;
                        }
                        handler.removeObject(this);

                    }
                    else if(tempobj.getId() == ID.Player){
                        ScoreBoard.lives--;
                        handler.removeObject(tempobj);
                        handler.addObject(new Player(Game.WIDTH/2, Game.HEIGHT - 60, ID.Player, handler));
                        if(ScoreBoard.lives == 0) {
                            Game.state = Game.gameState.End;
                        }
                        handler.removeObject(this);

                        for(int j = 0; j < handler.object.size(); j++){
                            GameObject removingbulls = handler.object.get(j);

                            if(removingbulls.getId() == ID.Bullet){
                                handler.removeObject(removingbulls);
                                j--;
                            }
                        }

                    }

                }
            }
        }
    }

    private void setNewLastRow(int row, int col) {
        boolean hasBottom = false;
        for(int i = handler.object.size() - 1; i >= 0 ; i--){
            GameObject temp = handler.object.get(i);

            if(temp.getId() == ID.Invader && temp.row > row - 1 && temp.row != row  && temp.col == col && !hasBottom){
                hasBottom = true;
            }

            if(temp.getId() == ID.Invader && temp.row < row && temp.col == col && !hasBottom){

                temp.isBottomRow = true;
                break;
            }


        }
        //Game.columncount--;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(x,y,iWIDTH,iHEIGHT);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,iWIDTH,iHEIGHT);
    }

    public ID getBelongsTo() {
        return belongsTo;
    }

    public void setBelongsTo(ID belongsTo) {
        this.belongsTo = belongsTo;
    }

}

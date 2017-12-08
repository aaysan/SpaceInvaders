package Displays;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private Handler handler;
    boolean right = false;
    boolean left = false;
    boolean space = false;
    boolean spacePressed = false;
    public KeyInput(Handler handler){
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

//        System.out.println(key);

        for(int k = 0 ; k < handler.object.size(); k++){
            GameObject temp = handler.object.get(k);

            if(key == KeyEvent.VK_P){
                if(Game.state == Game.gameState.Game){
                    Game.state = Game.gameState.End;
                }
                else{
                    Game.state = Game.gameState.Game;
                }
            }

            if(temp.getId() == ID.Player && key == KeyEvent.VK_RIGHT){
                right = true;
                temp.setvX(5);
            }
            if(temp.getId() == ID.Player && key == KeyEvent.VK_LEFT){
                left = true;
                temp.setvX(-5);
            }
            if(temp.getId() == ID.Player && key == KeyEvent.VK_SPACE && !spacePressed){
                MusicPlayer.getSounds("playerShooting").play();
                space = true;
                spacePressed = true;
                handler.addObject(new Bullet(temp.getX() + Player.iWIDTH/2 - Bullet.iWIDTH/2,temp.getY() - Player.iHEIGHT/2- Bullet.iHEIGHT/2 ,ID.Bullet,handler, ID.Player));
            }
        }

    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        for(int i = 0 ; i < handler.object.size(); i++){
            GameObject temp = handler.object.get(i);

            if(temp.getId() == ID.Player && key == KeyEvent.VK_RIGHT){
                right = false;
            }
            if(temp.getId() == ID.Player && key == KeyEvent.VK_LEFT){
                left = false;
            }
            if(temp.getId() == ID.Player && (!right || !left)){
                temp.setvX(0);
            }
            if(key == KeyEvent.VK_SPACE){
                spacePressed = false;
            }


        }

    }

    public void initGame(){



    }


}

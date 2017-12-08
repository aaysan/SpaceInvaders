package Displays;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;

public class Game extends Canvas implements Runnable {



    static int INVADER_COL = 0;
    static int INVADER_ROW = 0;
    static int INVADER_DROPDOWNRATE = 0;
    static int INVADER_SPEED = 0;
    static int INVADER_SHOOT_SPEED = 5;
    static int NUMBER_OF_LIVES = 0;


    static int INVADERNUM = INVADER_COL * INVADER_ROW;
    static int invaders_start;


    static int blockcount = INVADERNUM;
    static int WIDTH = 800, HEIGHT = 600;
    static int columncount = INVADER_COL;
    static int shootPerPixel = WIDTH/INVADER_SHOOT_SPEED;
    int tickflag = 0;
    int renderflag = 0;

    private Thread thread;
    private boolean running = false;
    private Handler handler;

    public enum gameState{
        End,
        Game;
    }

    public static gameState state = gameState.Game;

    public Game(){


        Scanner inFile = null;

        {
            try {
                inFile = new Scanner(new File("/Users/maysan/Desktop/Education/ECE30862/Space Invaders/src/inputs.txt"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        INVADER_COL = inFile.nextInt();
        INVADER_ROW = inFile.nextInt();
        INVADER_SPEED = inFile.nextInt();
        INVADER_DROPDOWNRATE = inFile.nextInt();
        INVADER_SHOOT_SPEED = inFile.nextInt();
        NUMBER_OF_LIVES = inFile.nextInt();

        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));

        MusicPlayer.init();
        MusicPlayer.getMusic("mainmusic").loop();

        new Window(WIDTH,HEIGHT,"Space Invaders", this);

        handler.addObject(new Blocks(125, 300, ID.Blocks, handler));
        handler.addObject(new Blocks(175, 300, ID.Blocks, handler));
        handler.addObject(new Blocks(125, 350, ID.Blocks, handler));
        handler.addObject(new Blocks(175, 350, ID.Blocks, handler));

        handler.addObject(new Blocks(350, 300, ID.Blocks, handler));
        handler.addObject(new Blocks(400, 300, ID.Blocks, handler));
        handler.addObject(new Blocks(350, 350, ID.Blocks, handler));
        handler.addObject(new Blocks(400, 350, ID.Blocks, handler));

        handler.addObject(new Blocks(575, 300, ID.Blocks, handler));
        handler.addObject(new Blocks(625, 300, ID.Blocks, handler));
        handler.addObject(new Blocks(575, 350, ID.Blocks, handler));
        handler.addObject(new Blocks(625, 350, ID.Blocks, handler));
        handler.addObject(new ScoreBoard(0, 0, ID.ScoreBoard));

        invaders_start = 12;

        //create the invaders
        Game.blockcount = Game.INVADERNUM;
        ScoreBoard.score = 0;
        ScoreBoard.lives = Game.NUMBER_OF_LIVES;

        for (int i = 0; i < INVADER_ROW; i++) {
            for (int j = 0; j < INVADER_COL; j++) {

                if(i == INVADER_ROW - 1){
                    handler.addObject(new Invader(0 + Invader.iWIDTH * j + (10 * j), 0 + Invader.iHEIGHT * i + (10 * i), ID.Invader, handler, true, i ,j));

                }
                else{
                    handler.addObject(new Invader(0 + Invader.iWIDTH * j + (10 * j), 0 + Invader.iHEIGHT * i + (10 * i), ID.Invader, handler, false, i ,j));
                }
            }
        }
        handler.addObject(new Player(Game.WIDTH / 2, Game.HEIGHT - 60, ID.Player, handler));



    }

    public static void main(String args[]){
        new Game();
    }

    public synchronized void start(){
        thread = new Thread(this);
        running = true;
        thread.start();
    }

    public synchronized void stop(){
        try {
            thread.join();
            running = false;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;


        Random r = new Random();
        int i = r.nextInt(200);
        int count = 0;

        while(running){

            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while( delta >= 1){

                //if(count > i) {
                    GameObject temp = pickRandomShooter();
//                    if(temp != null) {
//                        if (temp.notShotyet) {
//                            temp.currentShooter = true;
//                        } else {
//
//                            temp = pickRandomShooter();
//                            temp.currentShooter = true;
//                        }
//
//                    }
//                //}
                //count = (count + 1) % 200;



                Invader.period = false;
                tick();
                //fixLoc();
                delta--;
            }
            if(running){
                render();
            }


            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                frames = 0;
            }
        }
        stop();
    }

    private GameObject pickRandomShooter() {
        Random r = new Random();
        int flag = 0;


        int rand = r.nextInt(Game.INVADER_COL) ;

        for(int i = 0; i < handler.object.size(); i++) {
            GameObject temp = handler.object.get(i);
            flag = 1;
            if(temp.getId() == ID.Invader && temp.col == rand && temp.isBottomRow){
                temp.currentShooter = true;
                return temp;
            }
        }

        return null;
    }

    private void tick(){

        if(state != gameState.End || tickflag == 0) {
            if(tickflag == 0){tickflag++;}
            handler.tick();
        }


    }

    private void render(){

        if(state == gameState.End){
            renderflag++;
            if(renderflag > 1){
                return;
            }
        }

        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.darkGray);
        g.fillRect(0,0,WIDTH,HEIGHT );

        handler.render(g);
        g.dispose();
        bs.show();

    }





}

package Displays;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import java.util.HashMap;
import java.util.Map;


public class MusicPlayer {

    public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
    public static Map<String, Music> musicMap = new HashMap<String, Music>();

    public static void init(){
        try {
            soundMap.put("playerShooting", new Sound("res/shoot.wav"));
            soundMap.put("invaderDead", new Sound("res/invaderkilled.wav"));
            musicMap.put("mainmusic", new Music("res/spaceinvaders1.ogg"));
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public static Music getMusic(String key){
        return musicMap.get(key);
    }

    public static Sound getSounds(String key){
        return soundMap.get(key);
    }
}

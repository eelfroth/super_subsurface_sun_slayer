package com.BauhausGamesSyndicate.SSSS.overworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 *
 * @author Benedikt Vogler
 * @author Paul Flechsig
 * @author Jacob Bauer
 */
public class Eingang{
    private final int x = 2120;
    private final int width = 200;
    private static Sound rising;
    private static Sound descend;

    public Eingang() {
        rising = Gdx.audio.newSound(Gdx.files.internal("com/BauhausGamesSyndicate/SSSS/assets/rising.ogg"));
        descend = Gdx.audio.newSound(Gdx.files.internal("com/BauhausGamesSyndicate/SSSS/assets/descend.ogg"));
    }

    public int getX() {
        return x;
    }

    public int getWidth() {
        return width;
    }
    
    public void rise(){
        rising.play();
    }
    
    public void descend(){
        descend.play();
    }
    
     public void dispose(){
        descend.dispose();
        rising.dispose();
    }
    
}

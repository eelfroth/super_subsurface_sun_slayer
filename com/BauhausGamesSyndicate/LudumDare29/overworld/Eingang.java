/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.BauhausGamesSyndicate.LudumDare29.overworld;

import com.BauhausGamesSyndicate.LudumDare29.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 *
 * @author Benedikt Vogler
 * @author Paul Flechsig
 * @author Jacob Bauer
 */
public class Eingang{
    private final int x = 2000;
    private final int width = 200;
    private static Sound rising;
    private static Sound descend;

    public Eingang() {
        rising = Gdx.audio.newSound(Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/assets/rising.mp3"));
        descend = Gdx.audio.newSound(Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/assets/descend.ogg"));
    }
    
    
    public void render(GameScreen gs){
        ShapeRenderer sh = gs.getShapeRenderer();
        sh.begin(ShapeRenderer.ShapeType.Filled);
        sh.rect(x, 0, width, 400);
        sh.end();
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

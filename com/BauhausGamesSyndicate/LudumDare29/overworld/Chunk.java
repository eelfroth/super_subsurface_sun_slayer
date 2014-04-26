/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.BauhausGamesSyndicate.LudumDare29.overworld;

import com.BauhausGamesSyndicate.LudumDare29.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author Benedikt Vogler
 */
public class Chunk {
    public static final int HEIGHT =1024;
    public static final int WIDTH = 4096;//2^14
    private Texture texture;
    private int id;

    public Chunk(int id) {
        this.id = id;
        this.texture = new Texture(Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/assets/map"+id+".png"));
    }
    
    
    
    public void render(GameScreen gs){
        int y = Gdx.graphics.getHeight()-Chunk.HEIGHT; 
        int x = id*Chunk.WIDTH-0;
        //int m=Overworld.getMapWidth();
        int m=1;
        if (x < -m)
            x += m;
        else
            x = x % m;

        if (x<Gdx.graphics.getWidth() && x+Chunk.WIDTH > 0)
           gs.getBatch().draw(texture, x, y);
    };
}

package com.BauhausGamesSyndicate.SSSS.overworld;

import com.BauhausGamesSyndicate.SSSS.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author Benedikt Vogler
 * @author Paul Flechsig
 * @author Jacob Bauer
 */
public class Chunk {
    public static final int HEIGHT =1024;
    public static final int WIDTH = 4096;//2^14
    private final Texture texture;
    private final int id;

    public Chunk(int id) {
        this.id = id;
        this.texture = new Texture(Gdx.files.internal("com/BauhausGamesSyndicate/SSSS/assets/map"+id+".png"));
    }
    
    
    
    public void render(GameScreen gs){
        int m=Overworld.getMapWidth();
        int y = 0; 
        int x = id*Chunk.WIDTH;
        
        int cc = Overworld.getCameraPos()/4;//current chunk 0-3

        if (id >cc+1)
            x-=m;
                
        if (x < -m)
            x += m;
        else
            x = x % m;

        //check if visible
        if (x+Chunk.WIDTH > Overworld.getCameraPos() && x < 1920+Overworld.getCameraPos())
           gs.getBatch().draw(texture, x, y);
    };
}

package com.BauhausGamesSyndicate.LudumDare29;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import java.util.ArrayList;

/**
 *
 * @author Benedikt Vogler
 */
public class Overworld {
    private final int[] heightmap;
    private final ArrayList<AbstractEntity> entityList = new ArrayList<>();
    private int cameraPos = 0;
    private final int height =1024;
    private final int width = 4096;//2^14
    private Texture[] chunkgraphic; 

    public Overworld() {
        this.heightmap = new int[100];
        for (int x = 0; x < heightmap.length/2; x++) {
            heightmap[x] = (int) (Math.random()*height);
        }
        
        chunkgraphic = new Texture[3];//max 3 backgroudn tiles
        chunkgraphic[0] = new Texture(Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/map1.png"));
        chunkgraphic[1] = new Texture(Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/map2.png"));
        chunkgraphic[2] = new Texture(Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/map1.png"));
    }
    
    public void update(float delta){
        cameraPos+=delta;
        cameraPos = cameraPos % (width*chunkgraphic.length);
    }
    
    public void render(GameScreen gs){
       // sh.begin(ShapeRenderer.ShapeType.Filled);
        gs.getBatch().begin();
        
        //render check left side
        int tile = cameraPos/width;
        if (tile >= chunkgraphic.length) tile=0;
        
        for (int i = 0; i < chunkgraphic.length; i++) {
            Texture tex = chunkgraphic[i];
            int x = -cameraPos+i*width;
            int y = Gdx.graphics.getHeight()-height; 
            
            if (x<Gdx.graphics.getWidth() && x+width > 0)
               gs.getBatch().draw(chunkgraphic[tile], x, y);
        }
        
        
        gs.getBatch().end();
        //sh.end();
    }
    
/**
 * The resolution of the heightmap
     * @return 
 */
    public int resolution(){
        return width/heightmap.length;
    }
    
}

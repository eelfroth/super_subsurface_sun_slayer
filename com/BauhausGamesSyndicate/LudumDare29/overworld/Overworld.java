package com.BauhausGamesSyndicate.LudumDare29.overworld;


import com.BauhausGamesSyndicate.LudumDare29.AbstractEntity;
import com.BauhausGamesSyndicate.LudumDare29.GameScreen;
import com.BauhausGamesSyndicate.LudumDare29.Minion;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.ArrayList;

/**
 *
 * @author Benedikt Vogler
 */
public class Overworld {
    private static int[] heightmap;
    private static final ArrayList<AbstractEntity> entityList = new ArrayList<>();
    private static int cameraPos = 0;
    private static int anzMinions = 10;
    private static Chunk[] chunks; 
    private static Texture background;

    public Overworld() {        
        chunks = new Chunk[3];//max 3 backgroudn tiles
        chunks[0] = new Chunk(0);
        chunks[1] = new Chunk(1);
        chunks[2] = new Chunk(0);
        
        background = new Texture(Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/assets/bg.png"));
        
        //heightmap generieren
        Overworld.heightmap = new int[200];
        for (int x = 0; x < heightmap.length; x++) {
            heightmap[x] = (int) (Math.random()*Chunk.HEIGHT/2);
        }
        
        //minnions in liste füllen
        for (int i = 0; i <= anzMinions; i++){
            entityList.add(new Minion(Gdx.graphics.getWidth()/2f, Gdx.graphics.getHeight()/2f ) );
        }
    }
    
    public void update(float delta){
        cameraPos+=delta/2;
        cameraPos = cameraPos % (Chunk.WIDTH*chunks.length);
        
        for( AbstractEntity m: entityList){
            m.update(delta);
        }
        
    }
    
    public void render(GameScreen gs){
        gs.getBatch().begin();
        
        gs.getCamera().translate(-Overworld.getCameraPos()/2, 0);
        gs.getCamera().update();
        gs.getBatch().setProjectionMatrix(gs.getCamera().combined);
        
        //background
         int y = Gdx.graphics.getHeight()-Chunk.HEIGHT; 
         for (int i = 0; i < getMapWidth()/background.getWidth(); i++) {
            int x = i*background.getWidth();
//            int m=getMapWidth();
//            if (x < -m)
//                x += m;
//            else
//                x = x % m;
            
            if (x+background.getWidth() > Overworld.getCameraPos()/2 && x < Gdx.graphics.getWidth()+Overworld.getCameraPos()/2)
               gs.getBatch().draw(background, x, y);
        }
         
        gs.getCamera().translate(Overworld.getCameraPos()/2, 0);
        gs.getCamera().update();
        gs.getBatch().setProjectionMatrix(gs.getCamera().combined);

        
        //middleground
        for (Chunk chunk : chunks) {
            chunk.render(gs);
        }
         
        for( AbstractEntity m: entityList){
            m.render(gs);
        }
        gs.getBatch().end();
        
        
        ShapeRenderer sh = gs.getShapeRenderer();
        sh.begin(ShapeRenderer.ShapeType.Line);
        for (int i = 0; i < heightmap.length; i++) {
            sh.line(i*resolution(), getHeightmapValue(i), (i+1)*resolution(), getHeightmapValue(i+1));
        }
        sh.end();
        
    }
    
/**
 * The resolution of the heightmap
     * @return 
 */
    public int resolution(){
        return getMapWidth()/heightmap.length;
    }
    
   /**
    * Round
    * @param sample
    * @return 
    */
    public static int getHeightmapValue(int sample){
        int m = heightmap.length;
        int i = (sample < 0) ? (m - (Math.abs(sample) % m) ) %m : (sample % m);
        return heightmap[i];
    }
    
    public int getMapWidth(){
        return Chunk.WIDTH*chunks.length;
    }

    public static int getCameraPos() {
        return cameraPos;
    }
    
    
}

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
    private Texture graphic; 

    public Overworld() {
        this.heightmap = new int[100];
        for (int x = 0; x < heightmap.length/2; x++) {
            heightmap[x] = (int) (Math.random()*height);
        }
        graphic = new Texture(Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/map1.png"));
    }
    
    public void update(float delta){
        cameraPos+=delta/5;
    }
    
    public void render(GameScreen gs){
       // sh.begin(ShapeRenderer.ShapeType.Filled);
        gs.getBatch().begin();
        gs.getBatch().draw(graphic, -cameraPos, Gdx.graphics.getHeight()-height);
        
        //render map
//        for (int x = 0; x < heightmap.length; x++) {
//            for (int y = 0; y < heightmap[x].length; y++) {
//                if (cameraPos<(x+1)*Tile.WIDTH && heightmap[x][y] != null)    //render only if visible
//                    heightmap[x][y].render(gs, x*Tile.WIDTH-cameraPos,y*Tile.HEIGHT);
//            }
//        }
        
        gs.getBatch().end();
        //sh.end();
    }
    
/**
 * The resolution of the heightmap
 */
    public int resolution(){
        return width/heightmap.length;
    }
    
}

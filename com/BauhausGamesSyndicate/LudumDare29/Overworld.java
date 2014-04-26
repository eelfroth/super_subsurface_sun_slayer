package com.BauhausGamesSyndicate.LudumDare29;


import com.badlogic.gdx.graphics.Color;
import java.util.ArrayList;

/**
 *
 * @author Benedikt Vogler
 */
public class Overworld {
    private final int[] heightmap;
    private final ArrayList<AbstractEntity> entityList = new ArrayList<>();
    private int cameraPos = 0;
    private int anzMinions = 10;
    private final int height =1080;
    private final int width = 16384;//2^14

    public Overworld() {
        this.heightmap = new int[100];
        for (int x = 0; x < heightmap.length/2; x++) {
            heightmap[x] = (int) (Math.random()*height);
        }
        
        for (int i = 0; i <= anzMinions; i++){
            entityList.add(new Minion(width/2f, height/2f ) );
        }
    }
    
    public void update(float delta){
        for( AbstractEntity m: entityList){
            m.update();
        }
        
        cameraPos++;
    }
    
    public void render(GameScreen gs){
       // sh.begin(ShapeRenderer.ShapeType.Filled);
        gs.getBatch().begin();
        for( AbstractEntity m: entityList){
            m.render(gs);
        }
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

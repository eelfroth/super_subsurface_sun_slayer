package com.BauhausGamesSyndicate.LudumDare29;


import com.badlogic.gdx.graphics.Color;
import java.util.ArrayList;

/**
 *
 * @author Benedikt Vogler
 */
public class Map {
    private Tile[][] data;
    private final ArrayList<AbstractEntity> entityList = new ArrayList<>();
    private int cameraPos = 0;

    public Map() {
        this.data = new Tile[100][32];
        for (Tile[] x : data) {
            for (int y = 0; y < x.length/2; y++) {
                x[y] = new Tile(0);
            }
        }
    }
    
    public void update(float delta){
        cameraPos++;
    }
    
    public void render(GameScreen gs){
       // sh.begin(ShapeRenderer.ShapeType.Filled);
        gs.getBatch().begin();
        
        //render map
        for (int x = 0; x < data.length; x++) {
            for (int y = 0; y < data[x].length; y++) {
                if (cameraPos<(x+1)*Tile.WIDTH && data[x][y] != null)    //render only if visible
                    data[x][y].render(gs, x*Tile.WIDTH-cameraPos,y*Tile.HEIGHT);
            }
        }
        gs.getBatch().end();
        //sh.end();
    }
    
}

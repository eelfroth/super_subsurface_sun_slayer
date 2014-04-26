package com.BauhausGamesSyndicate.LudumDare29;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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
            for (int y = 0; y < x.length; y++) {
                x[y] = new Tile(
                    new Color((float) Math.random()/2, (float) Math.random()/2, (float) Math.random()/2, 1)
                );
            }
        }
    }
    
    public void update(float delta){
        cameraPos++;
    }
    
    public void render(ShapeRenderer sh){
        sh.begin(ShapeRenderer.ShapeType.Filled);
        
        //render map
        for (int x = 0; x < data.length; x++) {
            for (int y = 0; y < data[x].length; y++) {
                if (cameraPos<(x+1)*Tile.WIDTH && data[x][y] != null)    //render only if visible
                    data[x][y].render(sh, x*Tile.WIDTH-cameraPos,y*Tile.HEIGHT);
            }
        }
        sh.end();
    }
    
}

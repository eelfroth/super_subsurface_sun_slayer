package com.BauhausGamesSyndicate.LudumDare29;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 *
 * @author Benedikt Vogler
 */
public class Tile {
   public static final int WIDTH = 320;
   public static final int HEIGHT = 40;
   private static TextureAtlas spritesheet;
   private boolean colission = true;
   private int id;

    public Tile(int id) {
        this.id = id;
        switch(id){
            case 0://earth
                break;
            case 1: //grass
                colission=false;
                break;
            case 2:
                break;
        }
    }

    public static void loadsheet(){
        spritesheet = new TextureAtlas(Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/spritesheet.txt"));
    }

    void render(GameScreen gs, int xPos, int yPos) {
        Sprite sprite = new Sprite(spritesheet.findRegion(Integer.toString(id)));
        sprite.setX(xPos);
        sprite.setY(yPos);
        sprite.draw(gs.getBatch());
    }
   
   
}

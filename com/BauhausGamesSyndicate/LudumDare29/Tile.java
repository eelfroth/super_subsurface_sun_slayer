package com.BauhausGamesSyndicate.LudumDare29;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 *
 * @author Benedikt Vogler
 */
public class Tile {
   public static final int WIDTH = 320;
   public static final int HEIGHT = 40;
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

    void render(GameScreen gs, int xPos, int yPos) {
        Sprite sprite = new Sprite(GameScreen.getSpritesheet().findRegion(Integer.toString(id)));
        sprite.setX(xPos);
        sprite.setY(yPos);
        sprite.draw(gs.getBatch());
    }

    public boolean colide() {
        return colission;
    }
    
}

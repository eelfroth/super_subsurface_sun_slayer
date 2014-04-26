
package com.BauhausGamesSyndicate.LudumDare29;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 *
 * @author Benedikt Vogler
 */
public abstract class AbstractEntity extends Sprite{
    
    public AbstractEntity(float x, float y, String name) {
        super(GameScreen.getSpritesheet().findRegion(name));
        setX(x);
        setY(y);
    }
    
    public void update(float x, float y){
        
    }
    
    public void render(GameScreen gs){
        
    }
}
